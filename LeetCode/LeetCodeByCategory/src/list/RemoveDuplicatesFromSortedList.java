package list;

import definition.ListNode;

//	Given a sorted linked list, delete all duplicates such that each element appear only once.
//	
//	For example,
//	Given 1->1->2, return 1->2.
//	Given 1->1->2->3->3, return 1->2->3.

public class RemoveDuplicatesFromSortedList {
	
	/**
	 * 用一个begin来作为比较基准，begin从头节点开始。
	 * 用一个diff来不停的和begin做比较，diff需要停在和begin不同的点或者null上
	 * 然后我们直接删除begin和diff之间的点就行，我们可以让begin直接指向diff,或者让begin直接指向null
	 * @param head
	 * @return
	 */
	public ListNode deleteDuplicates(ListNode head) {
		if(head == null || head.next == null)
            return head;
		
		ListNode begin = head;
		ListNode diff = null;
		
		while (begin != null) {
			diff = begin.next;
			while(diff != null && diff.val == begin.val) {
				diff = diff.next;
			}
			
			begin.next = diff;
			begin = diff;
		}
		
		return head;
	}
}
