import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/* CompliantNode refers to a node that follows the rules (not malicious)*/
public class CompliantNode implements Node {
	
	private Set<Transaction> trans;

    public CompliantNode(double p_graph, double p_malicious, double p_txDistribution, int numRounds) {
        // IMPLEMENT THIS
    	trans = new HashSet<Transaction>();
    }

    /** {@code followees[i]} is true if and only if this node follows node {@code i} */
    public void setFollowees(boolean[] followees) {
        // IMPLEMENT THIS
    }

    /** initialize proposal list of transactions */
    public void setPendingTransaction(Set<Transaction> pendingTransactions) {
        // IMPLEMENT THIS
    	trans = pendingTransactions;
    }

    /**
     * @return proposals to send to my followers. REMEMBER: After final round, behavior of
     *         {@code getProposals} changes and it should return the transactions upon which
     *         consensus has been reached.
     */
    public Set<Transaction> sendToFollowers() {
        // IMPLEMENT THIS
    	return trans;
    }

    /** receive candidates from other nodes. */
    public void receiveFromFollowees(Set<Candidate> candidates) {
        // IMPLEMENT THIS
    	for (Candidate candidate: candidates) {
    		trans.add(candidate.tx);
    	}
    }
}
