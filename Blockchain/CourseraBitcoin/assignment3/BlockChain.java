import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

// Block Chain should maintain only limited block nodes to satisfy the functions
// You should not have all the blocks added to the block chain in memory 
// as it would cause a memory overflow.

/**
 * @author Dingp
 *
 */
public class BlockChain {
    public static final int CUT_OFF_AGE = 1;
    
    private static class BlockStatus {
    	public Block block;
    	public UTXOPool pool;
    	public int height;
    	public long timeStamp;
    	
    	public BlockStatus(Block block, UTXOPool pool, int height) {
    		this.block = block;
    		this.pool = pool;
    		this.height = height;
    		this.timeStamp = System.currentTimeMillis();
    	}
    }
    
    private ByteArrayWrapper maxHeight;
    private int lowestHeight;
    private HashMap<ByteArrayWrapper, BlockStatus> blocksStatus = null;
    private TransactionPool txPool;
    
    /**
     * create an empty block chain with just a genesis block. Assume {@code genesisBlock} is a valid
     * block
     */
    public BlockChain(Block genesisBlock) {
        // IMPLEMENT THIS
    	txPool = new TransactionPool();
    	blocksStatus = new HashMap<ByteArrayWrapper, BlockStatus>();
    	
    	ByteArrayWrapper genesisBlockHash = new ByteArrayWrapper(genesisBlock.getHash());
    	UTXOPool genesisPool = new UTXOPool();
    	
    	// First we need to add coinbase transaction to the UXTOPool 
    	// before we can use it to validate transactions in block
    	Transaction coinBase = genesisBlock.getCoinbase();
    	for (int i = 0; i < coinBase.numOutputs(); ++i) {
    		UTXO utxo = new UTXO(coinBase.getHash(), i);
    		genesisPool.addUTXO(utxo, coinBase.getOutput(i));
    	}
    	
    	// Process the transactions based on the pool we got.
    	TxHandler handler = new TxHandler(genesisPool);
    	ArrayList<Transaction> genesisTxs = genesisBlock.getTransactions();
    	if (genesisTxs != null && genesisTxs.size() > 0) {
    		Transaction[] possibleTxs = genesisTxs.toArray(new Transaction[0]);
    		Transaction[] acceptedTxs = handler.handleTxs(possibleTxs);
    		if (acceptedTxs == null || acceptedTxs.length != possibleTxs.length) {
    			throw new IllegalArgumentException("The genesis block contains invalid transactions.");
    		}
    	}
    	genesisPool = handler.getUTXOPool();
    	
    	BlockStatus value = new BlockStatus(genesisBlock, genesisPool, 1);
    	
    	blocksStatus.put(genesisBlockHash, value);
    	maxHeight = new ByteArrayWrapper(genesisBlock.getHash());
    	lowestHeight = 1;
    	
    	
    }

    /** Get the maximum height block */
    public Block getMaxHeightBlock() {
        return getBlockStatus(maxHeight).block;
    }

    /** Get the UTXOPool for mining a new block on top of max height block */
    public UTXOPool getMaxHeightUTXOPool() {
        // IMPLEMENT THIS
    	return getBlockStatus(maxHeight).pool;
    }

    /** Get the transaction pool to mine a new block */
    public TransactionPool getTransactionPool() {
        // IMPLEMENT THIS
    	return new TransactionPool(txPool);
    }

    /**
     * Add {@code block} to the block chain if it is valid. For validity, all transactions should be
     * valid and block should be at {@code height > (maxHeight - CUT_OFF_AGE)}.
     * 
     * <p>
     * For example, you can try creating a new block over the genesis block (block height 2) if the
     * block chain height is {@code <=
     * CUT_OFF_AGE + 1}. As soon as {@code height > CUT_OFF_AGE + 1}, you cannot create a new block
     * at height 2.
     * 
     * @return true if block is successfully added
     */
    public boolean addBlock(Block block) {
        // IMPLEMENT THIS
    	if (block == null || block.getPrevBlockHash() == null) {
    		return false;
    	}
    	
    	ByteArrayWrapper parentHash = new ByteArrayWrapper(block.getPrevBlockHash());
    	if (!blocksStatus.containsKey(parentHash)) {
    		return false;
    	}
    	
    	BlockStatus parentBlockStatus = blocksStatus.get(parentHash);
    	BlockStatus maxHeightBlockStatus = getBlockStatus(maxHeight);
    	int newHeight = parentBlockStatus.height + 1;
    	if (newHeight <= maxHeightBlockStatus.height - CUT_OFF_AGE) {
    		return false;
    	}
    	
    	// use this pool to validate the txs in the block.
    	UTXOPool poolToValidate = new UTXOPool(parentBlockStatus.pool);
    	
    	// First we need to add coinbase transaction to the UXTOPool 
    	// before we can use it to validate transactions in block
    	Transaction coinBase = block.getCoinbase();
    	for (int i = 0; i < coinBase.numOutputs(); ++i) {
    		UTXO utxo = new UTXO(coinBase.getHash(), i);
    		poolToValidate.addUTXO(utxo, coinBase.getOutput(i));
    	}
    	
    	// Process the transactions based on the pool we got.
    	TxHandler handler = new TxHandler(poolToValidate);
    	Transaction[] possibleTxs = block.getTransactions().toArray(new Transaction[0]);
    	Transaction[] acceptedTxs = handler.handleTxs(possibleTxs);
    	if (acceptedTxs == null || acceptedTxs.length != possibleTxs.length) {
    		return false;
    	}
    	
    	// After processing the transactions, we shall get a new UTXOPool
    	UTXOPool newPool = handler.getUTXOPool();
    	
    	// Since the transactions included by the new block have been process,
    	// We should remove these transactions from the txs pool.
    	for (Transaction tx : block.getTransactions()) {
    		txPool.removeTransaction(tx.getHash());
    	}
    	
    	// Create a new block status for this block, and add it to the chain.
    	BlockStatus newBlockStatus = new BlockStatus(block, newPool, newHeight);
    	ByteArrayWrapper newBlockHash = new ByteArrayWrapper(block.getHash());
    	blocksStatus.put(newBlockHash, newBlockStatus);
    	
    	// Keep track of the block that has the max height
    	if (newBlockStatus.height > maxHeightBlockStatus.height) {
    		maxHeight = newBlockHash;
    	}
    	
    	updateLowestHeight();
    	
    	return true;
    }

    /** Add a transaction to the transaction pool */
    public void addTransaction(Transaction tx) {
        // IMPLEMENT THIS
    	txPool.addTransaction(tx);
    }
    
    private BlockStatus getBlockStatus(ByteArrayWrapper blockHash) {
    	return blocksStatus.get(blockHash);
    }
    
    private void updateLowestHeight() {
    	if (getBlockStatus(maxHeight).height - lowestHeight > CUT_OFF_AGE) {
    		Iterator<Entry<ByteArrayWrapper, BlockStatus>> itr = blocksStatus.entrySet().iterator();
    		while (itr.hasNext()) {
    			Entry<ByteArrayWrapper, BlockStatus> entry = itr.next();
    			BlockStatus blockStatus = entry.getValue();
    			if (blockStatus.height <= lowestHeight) {
    				itr.remove();
    			}
    		}
    		
    		lowestHeight++;
    	}
    }
}