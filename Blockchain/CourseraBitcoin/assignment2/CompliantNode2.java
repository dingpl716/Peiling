import java.util.HashSet;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

/* CompliantNode refers to a node that follows the rules (not malicious)*/
/**
 * 这个implementation是从网上找的 
 *
 */
public class CompliantNode2 implements Node {

    private boolean[] followees;

    private Set<Transaction> pendingTransactions;

    private boolean[] blackListed;

    public CompliantNode2(double p_graph, double p_malicious, double p_txDistribution, int numRounds) {
    }

    public void setFollowees(boolean[] followees) {
        this.followees = followees;
        this.blackListed = new boolean[followees.length];
    }

    public void setPendingTransaction(Set<Transaction> pendingTransactions) {
        this.pendingTransactions = pendingTransactions;
    }

    /**
     * 不知道为什么，但是经过测试，每次清除现有的transaction set的确能提高consensus的节点数量
     */
    public Set<Transaction> sendToFollowers() {
        Set<Transaction> toSend = new HashSet<>(pendingTransactions);
        pendingTransactions.clear();
        return toSend;
    }

    public void receiveFromFollowees(Set<Candidate> candidates) {
        Set<Integer> senders = candidates.stream().map(c -> c.sender).collect(toSet());
        for (int i = 0; i < followees.length; i++) {
            if (followees[i] && !senders.contains(i))
                blackListed[i] = true;
        }
        for (Candidate c : candidates) {
            if (!blackListed[c.sender]) {
                pendingTransactions.add(c.tx);
            }
        }
    }
}