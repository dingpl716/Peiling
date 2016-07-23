package definition;

/**
 * Definition for singly-linked list.

 */

public class ListNode {
     public int val;
     public ListNode next;
     public ListNode(int x) {
         val = x;
         next = null;
     }
     
     public ListNode(){}
     
     public static ListNode arrayToList(int[] array) {
    	 ListNode head = new ListNode(array[0]);
    	 ListNode headCpy = head;
    	 for(int i=1; i<array.length; ++i) {
    		 head.next = new ListNode(array[i]);
    		 head = head.next;
    	 }
    	 
    	 return headCpy;
     }
     
}