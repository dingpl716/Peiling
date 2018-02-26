
import java.security.PublicKey;

public class BlockHandler {
    private BlockChain blockChain;

    /** assume blockChain has the genesis block */
    public BlockHandler(BlockChain blockChain) {
        this.blockChain = blockChain;
    }

    /**
     * add {@code block} to the block chain if it is valid.
     * 
     * @return true if the block is valid and has been added, false otherwise
     */
    public boolean processBlock(Block block) {
        if (block == null)
            return false;
        return blockChain.addBlock(block);
    }

    /** create a new {@code block} over the max height {@code block} */
    public Block createBlock(PublicKey myAddress) {
        Block parent = blockChain.getMaxHeightBlock();
        byte[] parentHash = parent.getHash();
        Block current = new Block(parentHash, myAddress);
        UTXOPool uPool = blockChain.getMaxHeightUTXOPool();
        
        // 这个pool里面装的全是待处理的transaction
        TransactionPool txPool = blockChain.getTransactionPool();
        Transaction[] txs = txPool.getTransactions().toArray(new Transaction[0]);
        
        // 用handler处理所有待处理的transaction
        TxHandler handler = new TxHandler(uPool);
        Transaction[] rTxs = handler.handleTxs(txs);
        
        // 把以及处理好的transaction加到block里面去
        for (int i = 0; i < rTxs.length; i++)
            current.addTransaction(rTxs[i]);

        current.finalize();
        if (blockChain.addBlock(current))
            return current;
        else
            return null;
    }

    /** process a {@code Transaction} */
    public void processTx(Transaction tx) {
        blockChain.addTransaction(tx);
    }
}
