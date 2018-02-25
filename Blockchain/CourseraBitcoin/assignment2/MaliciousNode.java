import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

/**
 * Malicious nodes may have arbitrary behavior. For instance, among other things, a malicious node might:
 * 1. be functionally dead and never actually broadcast any transactions.
 * 2. constantly broadcasts its own set of transactions and never accept transactions given to it.
 * 3. change behavior between rounds to avoid detection.
 * 
 * 
 * This class is mimicing the first malicious behavior -- be functionally dead.
 */
public class MaliciousNode implements Node {

    public MaliciousNode(double p_graph, double p_malicious, double p_txDistribution, int numRounds) {
    }

    public void setFollowees(boolean[] followees) {
        return;
    }

    public void setPendingTransaction(Set<Transaction> pendingTransactions) {
        return;
    }

    public Set<Transaction> sendToFollowers() {
        return new HashSet<Transaction>();
    }

    public void receiveFromFollowees(Set<Candidate> candidates) {
        return;
    }
}
