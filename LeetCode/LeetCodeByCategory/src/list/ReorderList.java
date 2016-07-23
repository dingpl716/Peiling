package list;
import definition.ListNode;

//	Given a singly linked list L: L0→L1→…→Ln-1→Ln,
//	reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…
//	
//	You must do this in-place without altering the nodes' values.
//	
//	For example,
//	Given {1,2,3,4}, reorder it to {1,4,2,3}.
public class ReorderList {
//	先反转有半部分的list，
//	然后在用双指针，一个在头，一个在中间，一一相连
    public void reorderList(ListNode head) {
        int length = getListLength(head);
        if(length < 3)
        	return;
        ListNode half = head;
        for(int i=1; i<(length+1)/2; ++i)
        	half = half.next;
        reverseList(half);
        
        ListNode start1 = head;
        ListNode start2 = half.next;
        half.next = null;
        ListNode result = new ListNode(Integer.MIN_VALUE);
        ListNode current = result;
        while(start2 != null) {
        	ListNode tmp1 = start1.next;
        	ListNode tmp2 = start2.next;
        	current.next = start1;
        	current = current.next;
        	current.next = start2;
        	current = current.next;
        	start1 = tmp1;
        	start2 = tmp2;
        }
        current.next = start1;
        current = current.next;
        if(current != null)
        	current.next = null;
    }
    
    private int getListLength(ListNode head) {
    	int result = 0;
    	while(head != null){
    		++result;
    		head = head.next;
    	}
    	return result;
    }
    
//    input: preHead -> 1 -> 2 .... -> n
//    output: preHead -> n -> n-1 .... -> 1
    private void reverseList(ListNode preHead) {
    	ListNode cur = preHead.next;
    	if(cur == null)
    		return;
    	ListNode post = cur.next;
    	while(post != null) {
    		ListNode tmp = post.next;
    		post.next = cur;
    		cur = post;
    		post = tmp;
    	}
    	
    	preHead.next.next = null;
    	preHead.next = cur;
    }
}
