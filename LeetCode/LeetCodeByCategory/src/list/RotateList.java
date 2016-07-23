package list;
import definition.ListNode;

//	Given a list, rotate the list to the right by k places, where k is non-negative.
//	
//	For example:
//	Given 1->2->3->4->5->NULL and k = 2,
//	return 4->5->1->2->3->NULL.

public class RotateList {

//	这道题的实际意义是把list向右循环移动n位
//	所以最开始求个mod
    public ListNode rotateRight(ListNode head, int n) {
    	if(head == null || head.next == null)
    		return head;
    	ListNode preHead = new ListNode();
    	preHead.next = head;
    	
    	ListNode tail = preHead;
    	int length = 0;
    	while(tail.next != null) {
    		tail = tail.next;
    		++length;
    	}
    	n = n % length;
    	if(n == 0)
    		return head;
    	
    	ListNode breakNode = preHead;
    	ListNode last = preHead;
    	for(int i=0; i<n; ++i) {
    		if(last != null)
    			last = last.next;
    	}
    	if(last == null || last.next == null)	
    		return head;
    	
    	while(last.next != null) {
    		breakNode = breakNode.next;
    		last = last.next;
    	}
    	
    	ListNode tmp = breakNode.next;
    	breakNode.next = last.next;
    	last.next = preHead.next;
    	preHead.next = tmp;
    	
    	return preHead.next;
    }
}
