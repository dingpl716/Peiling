package list;
import definition.ListNode;

//	Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
//	
//	If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.
//	
//	You may not alter the values in the nodes, only nodes itself may be changed.
//	
//	Only constant memory is allowed.
//	
//	For example,
//	Given this linked list: 1->2->3->4->5
//	
//	For k = 2, you should return: 2->1->4->3->5
//	
//	For k = 3, you should return: 3->2->1->4->5

public class ReverseNodesInKGroup {
	
	/*
	 * start 指向一条链开头的前一个
	 * last 这条链的最后一个
	 * cur 从start.next开始
	 * post 从cur.next开始，到last结束，包含last
	 */
    public ListNode reverseKGroup1(ListNode head, int k) {
        if(head == null || head.next == null || k == 1)
        	return head;
        ListNode preHead = new ListNode(Integer.MIN_VALUE);
        preHead.next = head;
        
        ListNode start = preHead;
        ListNode last = start;
        int step = 0;
        while(last != null) {
        	// locate the group
        	if(step < k) {
        		last = last.next;
        		++step;
        		continue;
        	}
        	// reverse
        	ListNode cur = start.next;
        	ListNode post = cur.next;
        	ListNode tmp = post.next;
        	while(step > 1) {
        		tmp = post.next;
        		post.next = cur;
        		cur = post;
        		post = tmp;
        		--step;
        	}
        	// concatenate the chains
        	ListNode startBCK = start.next;
        	start.next.next = tmp;
        	start.next = last;
        	step = 0;
        	start = startBCK;
        	last = start;
        }
        return preHead.next;
    }

    /**
     * A better implementation of the method above.
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup(ListNode head, int k) {
    	if (head == null || k < 2){
    		return head;
    	}
    	
    	// Add a new node in front of head.
    	ListNode beforeHead = new ListNode(-1);
    	beforeHead.next = head;
    	
    	ListNode KthNode = moveKSteps(beforeHead, k);
    	ListNode start = beforeHead;
    	
    	while(KthNode != null){
    		// reverse the node between start and KthNode, include KthNode, but not start
    		start = reverse(start, KthNode);
    		KthNode = moveKSteps(start, k);
    	}
     	
    	return beforeHead.next;
    }
    
    /**
     * Reverses the nodes between start and end, include end, but not start.
     * Input: start -> 1 -> 2 -> ... -> n-1 -> n -> end -> xxxxx
     * Ouput: start -> end -> n -> n-1 -> ... -> 2 -> 1 -> xxxxx and return 1.
     * @param start
     * @param end
     */
    private ListNode reverse(ListNode start, ListNode end){
    	// After reversion, the first node of this section will be the last one.
    	// So keep a record of it, and return it latter.
    	ListNode result = start.next;
    	
    	// reverse the nodes by letting the node after current node point to current node.
    	ListNode current = start.next;
    	ListNode postCurrent = current.next;
    	ListNode tmp = postCurrent.next;
    	
    	while(current != end){
    		postCurrent.next = current;
    		current = postCurrent;
    		postCurrent = tmp;
    		if (postCurrent != null){
    			tmp = postCurrent.next;
    		}
    	}
    	
    	start.next.next = postCurrent;
    	start.next = end;
    	return result;
    }
    
    
    /**
     * Moves k steps from start.
     * Input: start -> 1 -> 2 -> 3 -> 4.... and k =3
     * Output: Node(3)
     * @param start
     * @param k
     * @return
     */
    private ListNode moveKSteps(ListNode start, int k){
    	if (start == null){
    		return null;
    	}
    	
    	ListNode result = start;
    	while(result != null && k-- > 0){
    		result = result.next;
    	}
    	
    	return result;
    }
}
