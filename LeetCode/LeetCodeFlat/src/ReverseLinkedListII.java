import definition.ListNode;


//	Reverse a linked list from position m to n. Do it in-place and in one-pass.
//	
//	For example:
//	Given 1->2->3->4->5->NULL, m = 2 and n = 4,
//	
//	return 1->4->3->2->5->NULL.
//	
//	Note:
//	Given m, n satisfy the following condition:
//	1 ≤ m ≤ n ≤ length of list.

//	分三步走，
//	1. 找到m的前一个 pre
//	2. 用双指针 cur 和 post 翻转m到n， tmp = post.next, post.next = cur, post,cur,tmp = next
//	3. 连接pre链，cur链和post链。
public class ReverseLinkedListII {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if(head == null || m == n)
        	return head;
        
        ListNode preStart = new ListNode(Integer.MIN_VALUE);
        preStart.next = head;
        //1. 找到m的前一个 pre
        ListNode pre = preStart;
        for(int i=1; i<m; ++i){
        	pre = pre.next;
        }
        
        //2 reverse
        ListNode cur = pre.next;
        ListNode post = cur.next;
        for(int i=0; i<n-m; ++i) {
        	ListNode tmp = post.next;
        	post.next = cur;
        	cur = post;
        	post = tmp;
        }
        
        //concatenate
        pre.next.next = post;
        pre.next = cur;
        
        return preStart.next;
    }
}
