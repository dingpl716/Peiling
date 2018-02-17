import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TxHandler {

	private UTXOPool pool = null;
	private Map<byte[], Transaction> transPool = null;
	
    /**
     * Creates a public ledger whose current UTXOPool (collection of unspent transaction outputs) is
     * {@code utxoPool}. This should make a copy of utxoPool by using the UTXOPool(UTXOPool uPool)
     * constructor.
     */
    public TxHandler(UTXOPool utxoPool) {
        // IMPLEMENT THIS
    	if (utxoPool != null) {
    		pool = new UTXOPool(utxoPool);
    	}
    	
    	transPool = new HashMap<byte[], Transaction>();    }

    /**
     * @return true if:
     * (1) all outputs claimed by {@code tx} are in the current UTXO pool, 
     * 条件1的表述是有错误的。应该是All inputs claimed by tx are in the current pool
     * (2) the signatures on each input of {@code tx} are valid, 
     * (3) no UTXO is claimed multiple times by {@code tx},
     * (4) all of {@code tx}s output values are non-negative, and
     * (5) the sum of {@code tx}s input values is greater than or equal to the sum of its output
     *     values; and false otherwise.
     */
    public boolean isValidTx(Transaction tx) {
        // IMPLEMENT THIS
    	if (tx == null) {
    		return false;
    	}
    	
    	if (tx.getOutputs() == null || tx.getOutputs().size() == 0){
    		return false;
    	}
    	   	
    	// (4) all of {@code tx}s output values are non-negative,
    	double outputSum = 0;
    	ArrayList<Transaction.Output> outputs = tx.getOutputs();
    	for (int i = 0; i < outputs.size(); ++i) {
    		Transaction.Output output = outputs.get(i);
    		if (output == null || output.value < 0) {
    			return false;
    		}
    		
    		outputSum += output.value;
    	}
    	
    	// (1) all outputs(should be inputs) claimed by {@code tx} are in the current UTXO pool,
    	// (2) the signatures on each input of {@code tx} are valid, 
    	// (3) no UTXO is claimed multiple times by {@code tx},
    	double inputSum = 0;
    	Set<UTXO> metUTXO = new HashSet<UTXO>();
    	ArrayList<Transaction.Input> inputs = tx.getInputs();
    	if (inputs != null) {
    		for (int i = 0; i < inputs.size(); ++i) {
    			Transaction.Input input = inputs.get(i);
    			
    			//条件(3) 确保input对应的uxto不会在pool里面重复
    			UTXO utxo = new UTXO(input.prevTxHash, input.outputIndex);
    			if (metUTXO.contains(utxo)) {
    				return false;
    			} else {
    				metUTXO.add(utxo);
    			}
    			
    			// 条件1
    			if (!pool.contains(utxo)){
        			return false;
        		}
    			
    			// 条件2
    			Transaction.Output preOutput = pool.getTxOutput(utxo);
    			byte[] messageToSign = tx.getRawDataToSign(i);
    			if (!Crypto.verifySignature(preOutput.address, messageToSign, input.signature)) {
    				return false;
    			}
    			
    			inputSum += preOutput.value;
    		}
    	}
    	
    	// (5) the sum of tx's input values is greater than or equal to the sum of its output
        // values; and false otherwise.
    	return inputSum >= outputSum; 
    }

    /**
     * Handles each epoch by receiving an unordered array of proposed transactions, checking each
     * transaction for correctness, returning a mutually valid array of accepted transactions, and
     * updating the current UTXO pool as appropriate.
     */
    public Transaction[] handleTxs(Transaction[] possibleTxs) {
        // IMPLEMENT THIS
    	return null;
    }

}
