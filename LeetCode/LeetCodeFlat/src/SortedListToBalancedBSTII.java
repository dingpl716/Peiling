import definition.ListNode;
import definition.TreeNode;


// a better implementation than SortedListToBalancedBST
//自底向上构建，任然用left和right来标志，但是此时他们不是用来
//random access array的。而只是用来判断现在是否应该new一个node
//出来。再用一个变量存储目前访问到的list上的node，每一个new出来
//的treenode都是用这个变量指向的val生成的。每次用了这个值之后
//这个变量应该往后挪一位
public class SortedListToBalancedBSTII {

	private ListNode currentNode;
	
    public TreeNode sortedListToBST(ListNode head) {
    	currentNode = head;
    	
    	int length = getListLength(head);
    	return sortedListToBST(1, length);
    }
    
    private int getListLength(ListNode head) {
    	int i=0;
    	while(head!=null) {
    		++i;
    		head = head.next;
    	}
    	return i;
    }
    
    private TreeNode sortedListToBST(int left, int right) {
    	if(currentNode == null)
    		return null;
    	if(left == right) {
			TreeNode node = new TreeNode(currentNode.val);
			currentNode = currentNode.next;
			return node;
    	}else if (left > right)
    		return null;
    	else {
    		int middle = (left+right)/2;
    		TreeNode leftChild = sortedListToBST(left, middle-1);
    		TreeNode parent = new TreeNode(currentNode.val);
			currentNode = currentNode.next;
			TreeNode rightChild = sortedListToBST(middle+1, right);
			
			parent.left = leftChild;
			parent.right = rightChild;
			return parent;
    	}
    }
}
