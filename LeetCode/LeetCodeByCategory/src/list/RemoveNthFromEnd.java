package list;
import definition.ListNode;

//	Given a linked list, remove the nth node from the end of list and return its head.
//	
//	For example,
//	
//	   Given linked list: 1->2->3->4->5, and n = 2.
//	
//	   After removing the second node from the end, the linked list becomes 1->2->3->5.
//	Note:
//	Given n will always be valid.
//	Try to do this in one pass.

public class RemoveNthFromEnd {
    public ListNode removeNthFromEnd1(ListNode head, int n) {
        if(n == 0)
            return head;
        if(head == null)
            return head;
        if(head.next == null && n > 0)
            return null;
            
        ListNode left = head;
        // remove the last node
        if(n == 1){
            while(left.next.next != null)
                left = left.next;
            left.next = null;
            return head;
        }else {
            ListNode right = left;
            for(int i=1; i<n; ++i)
                right = right.next;
            while(right.next != null) {
                left = left.next;
                right = right.next;
            }
            if( head != left) {
                left.val = left.next.val;
                ListNode tmp = left.next;
                left.next = left.next.next;
                tmp.next = null; 
            } else {  //  !!!!!! 这里一定要考虑到删除第一个节点的情况
                head = head.next;
                left.next = null;
            }
            return head;
        }
    }
    
//    5 -> 4 -> 3 -> 2 -> 1
    public ListNode removeNthFromEnd(ListNode head, int n) {
    	if(head == null)
    		return head;
    	ListNode preHead = new ListNode(Integer.MIN_VALUE);
    	preHead.next = head;
    	
    	ListNode pre = preHead;
    	ListNode target = head;
    	ListNode tail = target;
    	// tail should go n-1 step from target
    	for(int i=1; i<n; ++i) {
    		tail = tail.next;
    	}
    	// locate the target by moving tail to then end, 
    	while(tail.next != null) {
    		pre = pre.next;
    		target = target.next;
    		tail = tail.next;
    	}
    	// remove target
    	pre.next = target.next;
    	target.next = null;
    	
    	return preHead.next;
    }
}
