import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

/**
 * Malicious nodes may have arbitrary behavior. For instance, among other things, a malicious node might:
 * Change behavior between rounds to avoid detection.
 */
public class MaliciousNode3 implements Node {
	
	private Set<Transaction> trans = null;

    public MaliciousNode3(double p_graph, double p_malicious, double p_txDistribution, int numRounds) {
    	trans = new HashSet<Transaction>();
    }

    public void setFollowees(boolean[] followees) {
        return;
    }

    public void setPendingTransaction(Set<Transaction> pendingTransactions) {
    	trans = pendingTransactions;
    }

    public Set<Transaction> sendToFollowers() {
        return trans;
    }

    public void receiveFromFollowees(Set<Candidate> candidates) {
        return;
    }
}
