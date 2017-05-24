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
	
	/**
	 * 让小于x的点在大于等于x的点的前面
	 * @param head
	 * @param x
	 * @return
	 */
    public ListNode partition(ListNode head, int x) {
    	if (head == null){
    		return null;
    	}
    	
    	ListNode beforeHead = new ListNode();
    	beforeHead.next = head;
    	
    	// 从左往右，找到第一个破坏顺序的点，用A来记录他的前节点
    	ListNode A = beforeHead;
    	while(A.next != null && A.next.val < x){
    		A = A.next;
    	}
    	
    	// beforeB的下一个永远是B
    	ListNode beforeB = A;
    	ListNode B = beforeB.next;
    	
    	while(B != null){
    		// 找到A以后，第一个小于x的点，记为B
    		// 此时A到B之间的点（不包括A,B）全大于x
    		// 接下来我们要整体移动这一段
    		if (B.val >= x){
    			beforeB = beforeB.next;
    			B = B.next;
    			continue;
    		}
    		
    		// 此时的list是这样的.....A -> xxxxx -> beforeB -> B -> .....
    		// 需要把它变成这样的 .....A -> B -> xxxxx -> beforeB -> .....
    		beforeB.next = B.next;
			B.next = A.next;
    		A.next = B;
    		
    		// 更新A的位置，保证A的下一个节点就大于x
    		// 将B移动到beforeB的下一个
    		A = B;
    		B = beforeB.next;
    	}
    	
    	return beforeHead.next;
    }
}
