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
    public ListNode reverseKGroup(ListNode head, int k) {
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
}
