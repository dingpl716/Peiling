package list;

import definition.ListNode;

//	Reverse a singly linked list.
//	
//	click to show more hints.
//	
//	Hint:
//	A linked list can be reversed either iteratively or recursively. Could you implement both?

public class ReverseLinkedList {

    public ListNode reverseList(ListNode head) {
    	if (head == null) {
    		return head;
    	}
    	
        ListNode tail = head;
        while (tail.next != null) {
        	tail = tail.next;
        }
        
        dfs(head);
        
        return tail;
    }
    
    private ListNode dfs(ListNode head) {
    	ListNode next = null;
    	if (head.next != null) {
    		next = dfs(head.next); 
    	}
    	
    	if (next != null) {
    		next.next = head;
    	}
    	
    	head.next = null;
    	
    	return head;
    }
}
