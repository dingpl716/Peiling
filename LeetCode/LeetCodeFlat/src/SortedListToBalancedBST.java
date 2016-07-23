import definition.ListNode;
import definition.TreeNode;


public class SortedListToBalancedBST {
	
	private class ListNodePack {
		ListNode head;
		
		ListNodePack(ListNode h){
			head = h;
		}
	}
	
    public TreeNode sortedListToBST(ListNode head) {
        if(head == null)
        	return null;
        
        int length = getListLength(head);
        return sortedListToBST(head, 1, length);
    }
    
    private int getListLength(ListNode head) {
    	int i=0;
    	while(head!=null) {
    		++i;
    		head = head.next;
    	}
    	
    	return i;
    }
    
    private TreeNode sortedListToBST(ListNode head, int start, int end) {
    	ListNodePack lnp = new ListNodePack(head);
    	
    	int middle = (start + end)>>1;
    	TreeNode leftChild = sortedListToBST(lnp, start, middle-1, true);
    	TreeNode parent = new TreeNode(lnp.head.val);
    	parent.left = leftChild;
    	
    	lnp.head = lnp.head.next;
    	
    	TreeNode rightChild = sortedListToBST(lnp, middle+1, end, false);
    	parent.right = rightChild;
    	return parent;
    }
    
    private TreeNode sortedListToBST(ListNodePack lnp, int start, int end, boolean creatingLeft) {
    	if(start == end){
    		TreeNode tree = new TreeNode(lnp.head.val);
    		lnp.head = lnp.head.next;
    		return tree;
    	}
    	if(start > end)
    		return null;
    	
    	int middle;
    	if(creatingLeft == true)  //if is creating left tree
    		middle = (start + end + 1)>>1;
    	else	// if is creating right tree
    		middle = (start + end)>>1;
    		
    	TreeNode leftChild = sortedListToBST(lnp, start, middle-1, true);
    	TreeNode parent = new TreeNode(lnp.head.val);
    	parent.left = leftChild;
  
    	lnp.head = lnp.head.next;

    	TreeNode rightChild = sortedListToBST(lnp, middle+1, end, false);
    	parent.right = rightChild;
    	
    	return parent;
    }
}
