package list;

import definition.ListNode;

//	Given a singly linked list, group all odd nodes together followed by the even nodes. Please note here we are talking about the node number and not the value in the nodes.
//	
//	You should try to do it in place. The program should run in O(1) space complexity and O(nodes) time complexity.
//	
//	Example:
//	Given 1->2->3->4->5->NULL,
//	return 1->3->5->2->4->NULL.
//	
//	Note:
//	The relative order inside both the even and odd groups should remain as it was in the input. 
//	The first node is considered odd, the second node even and so on ...

/**
 * 
 * 1 -> 3 -> 5 - > 2 -> 4 -> 6 -> 7 -> 8 -> 9
 * 5是lastOdd
 * 2是firstEven = lastOdd.next
 * 6是lastEven
 * 7是oddToSwitch = lastEven.next
 * 我们要做的就是把7插入到135链和246链之间
 * @author Dingp
 *
 */
public class OddEvenLinkedList {
	public ListNode oddEvenList(ListNode head) {
        
		if (head == null || head.next == null) {
			return head;
		}
		
		ListNode lastOdd = head;
		ListNode firstEven = lastOdd.next;
		ListNode lastEven = firstEven;
		ListNode oddToSwitch = lastEven.next;
		
		while(oddToSwitch != null){
			ListNode evenAfterOddToSwitch = oddToSwitch.next;
			
			lastOdd.next = oddToSwitch;
			oddToSwitch.next = firstEven;
			lastEven.next = evenAfterOddToSwitch;
			
			lastOdd = lastOdd.next;
			lastEven = lastEven.next;
			
			oddToSwitch = lastEven == null ? null : lastEven.next;
		}
		
		return head;
    }
}
