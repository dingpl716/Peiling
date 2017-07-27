package tree;
import definition.TreeNode;

//	Given a binary tree, flatten it to a linked list in-place.
//	
//	For example,
//	Given
//	
//	         1
//	        / \
//	       2   5
//	      / \   \
//	     3   4   6
//	The flattened tree should look like:
//	   1
//	    \
//	     2
//	      \
//	       3
//	        \
//	         4
//	          \
//	           5
//	            \
//	             6

//	这道题可以先处理左树再处理右树，这样就可以少用一个中间变量，在递归时节约空间
public class FlattenBinaryTreeToLinkedList {
    public void flatten(TreeNode root) {
        flattenAndGetLast(root);
    }
       
//    If you notice carefully in the flattened tree, 
//    each node's right child points to the next node of a pre-order traversal.
//    根据以上提示，
//    如果一个node的right不空，那么应该是左链的最后一个node接上他的right
//    否则把左链移动到右边来
//    如果一个node的left不空，那么这个node之后应该跟他的left，也就是right指向left
//    否则忽略left
    
    private TreeNode flattenAndGetLast(TreeNode root) {
    	if(root == null)
    		return null;
    	if(root.left == null && root.right == null)
    		return root;
    	else if(root.left == null) {
    		return flattenAndGetLast(root.right);
    	}else if(root.right == null) {
    		root.right = root.left;
    		root.left = null;
    		return flattenAndGetLast(root.right);
    	}else {
    		TreeNode lastInLeft = flattenAndGetLast(root.left);
    		TreeNode lastInRight = flattenAndGetLast(root.right);
    		
    		lastInLeft.right = root.right;
    		lastInLeft.left = null;
    		root.right = root.left;
    		root.left = null;
    		
    		return lastInRight;
    	}
    }
}
