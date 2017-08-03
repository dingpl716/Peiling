package list;

import definition.ListNode;
import util.Util;

//	You are given two non-empty linked lists representing two non-negative integers. 
//	The digits are stored in reverse order and each of their nodes contain a single 
//	digit. Add the two numbers and return it as a linked list.
//	
//	You may assume the two numbers do not contain any leading zero, except the number 0 itself.
//	
//	Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
//	Output: 7 -> 0 -> 8

public class AddTwoNumbers {
	
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null){
        	return null;
        }
        
        if (l1 == null) {
        	return l2;
        }
        
        if (l2 == null) {
        	return l1;
        }
        
        ListNode result = new ListNode(Integer.MIN_VALUE);
        ListNode runner = result;
        int carry = 0;
        
        while (l1 != null && l2 != null) {
        	int sum = l1.val + l2.val + carry;
        	ListNode digit = new ListNode(sum % 10);
        	carry = sum / 10;
        	runner.next = digit;
        	runner = runner.next;
        	l1 = l1.next;
        	l2 = l2.next;
        }
        
        while (l1 != null) {
        	int sum = l1.val + carry;
        	ListNode digit = new ListNode(sum % 10);
        	carry = sum / 10;
        	runner.next = digit;
        	runner = runner.next;
        	l1 = l1.next;
        }
        
        while (l2 != null) {
        	int sum = l2.val + carry;
        	ListNode digit = new ListNode(sum % 10);
        	carry = sum / 10;
        	runner.next = digit;
        	runner = runner.next;
        	l2 = l2.next;
        }
        
        // 一定要注意处理最后的进位
        if (carry != 0) {
        	ListNode digit = new ListNode(carry);
        	runner.next = digit;
        	runner = runner.next;
        }
        
        return result.next;
    }
    
    public static void main(String[] args) {
    	ListNode l1 = new ListNode(9);
    	ListNode runner = l1;
    	for (int i = 0; i < 20; ++i) {
    		ListNode l = new ListNode(9);
    		runner.next = l;
    		runner = runner.next;
    	}
    	
    	ListNode l2 = new ListNode(1);
    	
    	AddTwoNumbers a = new AddTwoNumbers();
    	Util.timmer((object) -> {a.addTwoNumbers(l1, l2);});
    }
}
