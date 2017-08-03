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
    	
    	// 从左往右，找到第一个破坏顺序的点，用lastSmall来记录他的前节点
    	// lastSmall 是在list开头的小于x的那部分的最后一个点
    	ListNode lastSmall = beforeHead;
    	while(lastSmall.next != null && lastSmall.next.val < x){
    		lastSmall = lastSmall.next;
    	}
    	
    	// beforeB的下一个永远是B
    	ListNode beforeB = lastSmall;
    	ListNode smallNodeToSwitch = beforeB.next;
    	
    	while(smallNodeToSwitch != null){
    		// 找到lastSmall以后，第一个小于x的点，记为smallNodeToSwitch
    		// 此时lastSmall到smallNodeToSwitch之间的点（不包括他们）全大于x
    		// 接下来我们要把smallNodeToSwitch插入到lastSmall之后
    		if (smallNodeToSwitch.val >= x){
    			beforeB = beforeB.next;
    			smallNodeToSwitch = smallNodeToSwitch.next;
    			continue;
    		}
    		
    		// 此时的list是这样的.....lastSmall -> big -> ... -> big -> beforeB -> smallNodeToSwitch -> .....
    		// 需要把它变成这样的 .....lastSmall -> smallNodeToSwitch -> big -> ... -> big -> beforeB -> .....
    		beforeB.next = smallNodeToSwitch.next;
			smallNodeToSwitch.next = lastSmall.next;
    		lastSmall.next = smallNodeToSwitch;
    		
    		// 更新lastSmall的位置，保证lastSmall的下一个节点就大于x
    		// 将smallNodeToSwitch移动到beforeB的下一个
    		lastSmall = smallNodeToSwitch;
    		smallNodeToSwitch = beforeB.next;
    	}
    	
    	return beforeHead.next;
    }
}
