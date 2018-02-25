// Example of a Simulation. This test runs the nodes on a random graph.
// At the end, it will print out the Transaction ids which each node
// believes consensus has been reached upon. You can use this simulation to
// test your nodes. You will want to try creating some deviant nodes and
// mixing them in the network to fully test.

import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import com.sun.org.apache.xml.internal.security.utils.Base64;

import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Simulation {

    public static void main(String[] args) {
 
       // There are four required command line arguments: 
       // p_graph (.1, .2, .3),
       // p_malicious (.15, .30, .45), 
       // p_txDistribution (.01, .05, .10), 
       // numRounds (10, 20). You should try to test your CompliantNode
       // code for all 3x3x3x2 = 54 combinations.

//       double p_graph = Double.parseDouble(args[0]); // parameter for random graph: prob. that an edge will exist
//       double p_malicious = Double.parseDouble(args[1]); // prob. that a node will be set to be malicious
//       double p_txDistribution = Double.parseDouble(args[2]); // probability of assigning an initial transaction to each node 
//       int numRounds = Integer.parseInt(args[3]); // number of simulation rounds your nodes will run for
       
    	double[] p_graphArray = new double[] {.1}; // 经过测算consensus rate的平均值之后发现这个参数对consensus rate几乎没有影响
    	double[] p_maliciousArray = new double[] {.45};
    	double[] p_txDistributionArray = new double[] {.01}; // 这个参数能极大的提高consensus rate
    	int[] numRoundsArray = new int[] {20};
    	
    	for (double p_graph : p_graphArray) {
    		for (double p_malicious : p_maliciousArray) {
    			for (double p_txDistribution: p_txDistributionArray) {
    				for (int numRounds : numRoundsArray) {
    					simulate(p_graph, p_malicious, p_txDistribution, numRounds);
    					
//    					System.out.println("p_graph: " + p_graph + " p_malicious: " + p_malicious + " p_txDistribution: " + p_txDistribution + " numRounds: " + numRounds);
//    					int rounds = 10;
//    					double consensusRate = 0;
//    					for (int i = 0; i < rounds; ++i) {
//    						consensusRate += simulate(p_graph, p_malicious, p_txDistribution, numRounds);
//    					}
//    					System.out.println("Consensus rate: " + consensusRate / rounds + "%" );
    				}
    			}
    		}
    	}
    }
    
    public static double simulate(double p_graph, double p_malicious, double p_txDistribution, int numRounds) {
        int numNodes = 100;
        int numCompliant = 0;
        int numMaliciouus = 0;
        
    	// pick which nodes are malicious and which are compliant
        Node[] nodes = new Node[numNodes];
        for (int i = 0; i < numNodes; i++) {
           if(Math.random() < p_malicious) {
         	  // When you are ready to try testing with malicious nodes, replace the
         	  // instantiation below with an instantiation of a MaliciousNode
         	  nodes[i] = new MaliciousNode(p_graph, p_malicious, p_txDistribution, numRounds);
         	  numMaliciouus++;
           }
           else {
         	  nodes[i] = new CompliantNode(p_graph, p_malicious, p_txDistribution, numRounds);
         	  numCompliant++;
           }
        }
  
  
        // initialize random follow graph
        // j is publisher, i is listener
        boolean[][] followees = new boolean[numNodes][numNodes]; // followees[i][j] is true iff i follows j
        for (int i = 0; i < numNodes; i++) {
           for (int j = 0; j < numNodes; j++) {
              if (i == j) continue;
              if(Math.random() < p_graph) { // p_graph is .1, .2, or .3
                 followees[i][j] = true;
              }
           }
        }
  
        // notify all nodes of their followees
        for (int i = 0; i < numNodes; i++)
           nodes[i].setFollowees(followees[i]);
  
        // initialize a set of 500 valid Transactions with random ids
        int numTx = 500;
        HashSet<Integer> validTxIds = new HashSet<Integer>();
        Random random = new Random();
        for (int i = 0; i < numTx; i++) {
           int r = random.nextInt();
           validTxIds.add(r);
        }
  
        // 真正被加入到graph里面的所有不同的transaction
        // 上面那个validTxIds只是所有可能的valid transaction，但是不一定被放到graph里面了
        HashSet<Integer> totalDifferentTransctions = new HashSet<Integer>();
        
        // distribute the 500 Transactions throughout the nodes, to initialize
        // the starting state of Transactions each node has heard. The distribution
        // is random with probability p_txDistribution for each Transaction-Node pair.
        for (int i = 0; i < numNodes; i++) {
           HashSet<Transaction> pendingTransactions = new HashSet<Transaction>();
           for(Integer txID : validTxIds) {
              if (Math.random() < p_txDistribution) { // p_txDistribution is .01, .05, or .10.
             	 Transaction tran = new Transaction(txID);
             	 totalDifferentTransctions.add(txID);
                  pendingTransactions.add(tran);
              }
           }
           nodes[i].setPendingTransaction(pendingTransactions);
        }
  
        // Simulate for numRounds times
        for (int round = 0; round < numRounds; round++) { // numRounds is either 10 or 20
  
           // gather all the proposals into a map. The key is the index of the node receiving
           // proposals. The value is an ArrayList containing 1x2 Integer arrays. The first
           // element of each array is the id of the transaction being proposed and the second
           // element is the index # of the node proposing the transaction.
      	 // Key是接收到proposal的listener.
      	 // Value是一个array of (TransactionId and Publisher)
        	HashMap<Integer, Set<Candidate>> allProposals = new HashMap<>();
  
           // i是publisher
           for (int i = 0; i < numNodes; i++) {
              Set<Transaction> proposals = nodes[i].sendToFollowers();
              for (Transaction tx : proposals) {
                 if (!validTxIds.contains(tx.id))
                    continue; // ensure that each tx is actually valid
  
                 // j是listener
                 for (int j = 0; j < numNodes; j++) {
                    if(!followees[j][i]) continue; // tx only matters if j follows i
  
                    if (!allProposals.containsKey(j)) {
                  	  Set<Candidate> candidates = new HashSet<>();
                  	  allProposals.put(j, candidates);
                    }
                    
                    Candidate candidate = new Candidate(tx, i);
                    allProposals.get(j).add(candidate);
                 }
              }
           }
  
           // Distribute the Proposals to their intended recipients as Candidates
           for (int i = 0; i < numNodes; i++) {
              if (allProposals.containsKey(i))
                 nodes[i].receiveFromFollowees(allProposals.get(i));
           }
        }
  
        // print results
//        for (int i = 0; i < numNodes; i++) {
//           Set<Transaction> transactions = nodes[i].sendToFollowers();
//           System.out.println("Transaction ids that Node " + i + " believes consensus on:");
//           for (Transaction tx : transactions)
//              System.out.println(tx.id);
//           System.out.println();
//           System.out.println();
//        }
        
        
        Map<String, ArrayList<Node>> groups = groupNodes(nodes);
        System.out.println("p_graph: " + p_graph + " p_malicious: " + p_malicious + " p_txDistribution: " + p_txDistribution + " numRounds: " + numRounds);
        System.out.println("We have " + groups.size() + " groups totaly.");
        System.out.println("Compliant nodes: " + numCompliant + ". Malicious nodes: " + numMaliciouus);
        System.out.println("Transactions number: " + totalDifferentTransctions.size());
        int i = 0;
        int consensusNum = 0; // 达成一致的transaction数量
        for (String key : groups.keySet()) {
     	   ArrayList<Node> nodeList = groups.get(key);
     	   if (nodeList.size() < 10) {
     		   continue;
     	   }
     	   
     	   int numTrans = nodeList.get(0).sendToFollowers().size();
     	   consensusNum = numTrans > consensusNum ? numTrans : consensusNum;
     	   System.out.println(String.format("Group %1$d, Number of nodes %2$d, Number of transactions %3$d", ++i, nodeList.size(), numTrans));
        }
        
        double consensusRate = 100.00 * consensusNum / totalDifferentTransctions.size();
        System.out.println("Consensus rate: " + consensusRate + "%" );
        System.out.println();
        System.out.println();
        
        return consensusRate;
    }
    
    private static Map<String, ArrayList<Node>> groupNodes(Node[] nodes) {
    	Map<String, ArrayList<Node>> groups = new HashMap<String, ArrayList<Node>>();
    	for (int i = 0; i < nodes.length; i++) {
    		Set<Transaction> transactions = nodes[i].sendToFollowers();
    		String key = getHashKey(transactions);
    		
    		if (groups.containsKey(key)) {
    			groups.get(key).add(nodes[i]);
    		} else {
    			ArrayList<Node> value = new ArrayList<Node>();
    			value.add(nodes[i]);
    			groups.put(key, value);
    		}
    	}
    	
    	return groups;
    }
    
    private static String getHashKey(Set<Transaction> transactions) {
    	int i = 0;
		int[] ids = new int[transactions.size()];
		for (Transaction tran : transactions) {
			ids[i++] = tran.hashCode();
		}
		Arrays.sort(ids);
		
		ArrayList<Byte> id_bytes = new ArrayList<Byte>(transactions.size() * 4);
		for (i = 0; i < ids.length; ++i) {
			ByteBuffer buffer = ByteBuffer.allocate(Integer.SIZE / 8);
			buffer.putInt(ids[i]);
			byte[] b = buffer.array();
			for (int j = 0 ; j < b.length; ++j) {
				id_bytes.add(b[j]);
			}
		}
		
		byte[] message = new byte[id_bytes.size()];
		for (int j = 0; j < id_bytes.size(); ++j) {
			message[j] = id_bytes.get(j);
		}
		
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		md.update(message);
        byte[] digest = md.digest();
        return Base64.encode(digest);
    }
}

