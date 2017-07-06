package divideAndConquer;

import definition.ListNode;

//	Sort a linked list in O(n log n) time using constant space complexity.

public class SortList {

    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
        	return head;
        }
        
        return mergeSort(head);
    }
    
    private ListNode mergeSort(ListNode head) {
    	if (head.next == null) {
    		return head;
    	}
    	
    	ListNode middle = getMiddle(head);
    	
    	// 这个地方一定要断链
    	ListNode afterMiddle = middle.next;
    	middle.next = null;
    	
    	ListNode left = mergeSort(head);
    	ListNode right = mergeSort(afterMiddle);
    	
    	return merge(left, right);
    }
    
    private ListNode getMiddle(ListNode head) {
    	ListNode faster = head;
    	ListNode slower = head;
    	
    	faster = moveForward(faster);
    	faster = moveForward(faster);
    	
    	while(faster != null) {
    		faster = moveForward(faster);
    		faster = moveForward(faster);
    		slower = moveForward(slower);
    	}
    	
    	return slower;
    }
    
    private ListNode moveForward(ListNode node) {
    	if (node == null) {
    		return null;
    	}else {
    		return node.next;
    	}
    }
    
    private ListNode merge(ListNode left, ListNode right) {
    	ListNode preHead = new ListNode(Integer.MIN_VALUE);
    	ListNode runner = preHead;
    	
    	while(left != null && right != null) {
    		if (left.val < right.val) {
    			runner.next = left;
    			left = left.next;
    		} else {
    			runner.next = right;
    			right = right.next;
    		}
    		
    		runner = runner.next;
    	}
    	
    	if (left != null) {
    		runner.next = left;
    	}
    	
    	if (right != null) {
    		runner.next = right;
    	}
    	
    	return preHead.next;
    }
    
	public static void main(String[] args) {
		SortList s = new SortList();
		
		ListNode l1 = new ListNode(1);
		ListNode l2 = new ListNode(2);
		ListNode l3 = new ListNode(3);
		l3.next = l2;
		l2.next = l1;
		
		ListNode head = s.sortList(l3);
		
		while (head != null) {
			System.out.println(head.val);
			head = head.next;
		}
	}

}
