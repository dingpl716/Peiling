package list;
import definition.ListNode;

//	Given a sorted linked list, delete all nodes that have duplicate numbers, 
//	leaving only distinct numbers from the original list.
//	
//	For example,
//	Given 1->2->3->3->4->4->5, return 1->2->5.
//	Given 1->1->1->2->3, return 2->3.

public class RemoveDuplicatesFromSortedListII {
    
	/**
	 * 一个更好的实现.
	 * 以pre.next作为基准，用diff来找与之不相同的点，然后pre会直接指向diff，从而
	 * 删除pre和diff之间的elements
	 * 和RemoveDuplicatesFromSortedList里面类似，但是我们这里用pre.next来做基准
	 * @param head
	 * @return
	 */
	public ListNode deleteDuplicates(ListNode head) {
        if(head == null || head.next == null)
            return head;
        
        ListNode preHead = new ListNode(Integer.MIN_VALUE);
        preHead.next = head;
        
        // 以pre.next作为基准，用diff来找与之不相同的点，然后pre会直接指向diff，从而
        // 删除pre和diff之间的elements
        // 和RemoveDuplicatesFromSortedList里面类似，但是我们这里用pre.next来做基准
        ListNode pre = preHead;
        ListNode diff = null;
        
        while (pre.next != null) {
        	diff = pre.next.next;
        	
        	// 把diff移到和pre.next不想等的点上
        	while (diff != null && pre.next.val == diff.val) {
        		diff = diff.next;
        	}
        	
        	// 如果他们两个相等，说明我们没有移动过diff，
        	// 也就是说pre和diff之间没有重复，那么此时需要移动pre
        	if (pre.next.next == diff) {
        		pre = pre.next;
        	} else {
        		// 如果不相等，那说明有重复，需要去重
        		pre.next = diff;
        	}
        }
        
        return preHead.next;
	}
	
	public ListNode deleteDuplicates2(ListNode head) {
        if(head == null || head.next == null)
            return head;
        
        ListNode preHead = new ListNode(Integer.MIN_VALUE);
        preHead.next = head;
        
        // 用last来找重复的链，然后pre会直接指向last的next，从而
        // 删除pre和last之间的elements
        ListNode pre = preHead;
        ListNode last = pre.next;
        boolean hasDuplication = false;
        while(true) {
        	// 这里要考虑最后一个节点是重复节点的情况，所以要加上hasDuplication的检查
        	if(!hasDuplication && (last == null || last.next == null))
        		break;
        	// move because no duplication
        	if(!hasDuplication && last.val != last.next.val){
        		pre = pre.next;
        		last = last.next;
        		continue;
        	}
        	// move only last, because duplication
        	// 因为last.next有可能是null了，所以要加上last.next != null的检查
        	if(last.next != null && last.val == last.next.val) {
        		hasDuplication = true;
        		last = last.next;
        		continue;
        	}
        	// stop, need to remove the middle chain
        	// 因为last.next有可能是null了，所以要加上last.next != null的检查
        	if(hasDuplication && (last.next == null || last.val != last.next.val)) {
        		pre.next = last.next;
        		last.next = null;
        		last = pre.next;
        		hasDuplication = false;
        		continue;
        	}
        }
        
        return preHead.next;
    }
}
