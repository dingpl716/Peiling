package list;

import definition.ListNode;

//	Given a singly linked list, determine if it is a palindrome.
//	
//	Follow up:
//	Could you do it in O(n) time and O(1) space?

/**
 * 翻转后半部分list，然后再翻转回去
 * 注意，这里严格来讲其实不是O(1)的空间复杂度了，因为在理论上讲
 * 我们是允许更改input的。也就是说O(1)是要求在不更改input的前提
 * 下只用constant的extral space。一旦更改了input就不算了。
 * 
 * 1 - 2 - 3 - 2 - 1
 * 1 - 2 - 3 - 1 - 2
 * 
 * 1 - 2 - 2 - 1
 * 1 - 2 - 1 - 2
 * @author Dingp
 *
 */
public class PalindromeLinkedList {
    
	public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
        	return true;
        }
        
        int length = getLength(head);
        ListNode half = null;
        if ((length & 1) == 1) {
        	half = moveSteps(head, length / 2);
        } else {
        	half = moveSteps(head, (length / 2) - 1);
        }
        
        reverse(half);
        
    	half = half.next;
        while (half != null) {
        	if (head.val != half.val) {
        		return false;
        	}
        	head = head.next;
        	half = half.next;
        }
        
        return true;
    }
	
	private int getLength(ListNode head) {
		int result = 0;
		
		while(head != null) {
			head = head.next;
			++result;
		}
		
		return result;
	}
	
	private ListNode moveSteps(ListNode head, int steps) {
		ListNode runner = head;
		for (int i = 0; i < steps; ++i) {
			runner = runner.next;
		}
		
		return runner;
	}
	
	/**
	 * 不翻转head，只翻转[head.next, tail]的这部分
	 * @param head
	 * @return
	 */
	private void reverse(ListNode head) {
		ListNode cur = head.next;
		ListNode post = null;
		if (cur != null) {
			post = cur.next;
		}
		
		while (post != null) {
			ListNode tmp = post.next;
			post.next = cur;
			cur = post;
			post = tmp;
		}
		
		head.next.next = null;
		head.next = cur;
	}
	
	public static void main(String[] args) {
		PalindromeLinkedList p = new PalindromeLinkedList();
		ListNode l1 = new ListNode(1);
		ListNode l2 = new ListNode(2);
		l1.next = l2;
		
		System.out.println(p.isPalindrome(l1));
	}
}
