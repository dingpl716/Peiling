package list;
import definition.ListNode;

//	Given a linked list and a value x, partition it such that
//	all nodes less than x come before nodes greater than or equal to x.
//	
//	You should preserve the original relative order
//	of the nodes in each of the two partitions.
//	
//	For example,
//	Given 1->4->3->2->5->2 and x = 3,
//	return 1->2->2->4->3->5.

// 这道题有很多border case需要考虑 比如 
// 1. 所有节点都小于或大于x
// 2. 头结点大于X --- 需要改变头结点
// 3. 前面的节点全大于x，最后一个节点小于x，还有反过来的情况

// 这道题最简便的方法，是给这个list再加上一个头结点preStart，然后返回preStart.next
public class PartitionList {
    public ListNode partition1(ListNode head, int x) {
        if(head == null)
            return null;
        
        ListNode begin = head;
        ListNode smaller = head.next;
        ListNode last = head;
        if(begin.val >= x) {  // the first node >= x
            while(smaller != null && smaller.val >= x) {
                smaller = smaller.next;
                last = last.next;
            }
            if(smaller == null)  // all elements >= x
                return head;
            
            // here we will modifiy the head node
            last.next = smaller.next;
            smaller.next = begin;
            head = smaller;
            begin = smaller;
            smaller = last.next;
        }else {
            ListNode preBegin = head;
            begin = head.next;
            while(begin!= null && begin.val < x) {
                begin = begin.next;
                preBegin = preBegin.next;
            }
            if(begin == null) // all elements < x
                return head;
            if(begin.next == null) // all nodes < x except last node
                return head;
            begin = preBegin;
        }
        
        smaller = begin.next;
        last = begin;
        while(smaller !=null) {
            if( smaller.val >= x) {
                smaller = smaller.next;
                last = last.next;
                continue;
            }
            last.next = smaller.next;
            smaller.next = begin.next;
            begin.next = smaller;
            begin = begin.next;
            smaller = last.next;
        }
        
        return head;
    }
    
    public ListNode partition2(ListNode head, int x) {
    	if(head == null)
    		return null;
    	// 增加一个头结点
    	ListNode preStart = new ListNode(Integer.MIN_VALUE);
    	preStart.next = head;
    	
    	ListNode begin = preStart;
    	while(begin.next != null && begin.next.val < x)
    		begin = begin.next;
    	
    	ListNode smaller = begin.next;
    	ListNode last = begin;
    	while(smaller != null) {
    		if(smaller.val >= x) {
    			smaller = smaller.next;
    			last = last.next;
    			continue;
    		}
    		
    		last.next = smaller.next;
    		smaller.next = begin.next;
    		begin.next = smaller;
    		begin = begin.next;
    		smaller = last.next;
    	}
    	
    	return preStart.next;
    }
    
}
