package tree;
import definition.TreeNode;


//	Given a binary tree, determine if it is a valid binary search tree (BST).
//	
//	Assume a BST is defined as follows:
//	
//	The left subtree of a node contains only nodes with keys less than the node's key.
//	The right subtree of a node contains only nodes with keys greater than the node's key.
//	Both the left and right subtrees must also be binary search trees.


public class ValidateBinarySearchTree {
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, false, false, 0, 0);
    }
    
    // 这个实现的精髓就在于 isLeft 和 isRgith上，
//    	1. 根节点不是left，也不是right，所以刚传入时要写false，false
//    	2. isRight 和 isLeft要不断往下面传，因为一个节点他可能是祖父节点的右子树也同时是父节点的左子树
    private boolean isValidBST(TreeNode root, boolean isLeft, boolean isRight, int lmax, int rmin) {
    	if(root == null)
    		return true;
    	if(isLeft && root.val >= lmax)
    		return false;
    	if(isRight && root.val <= rmin)
    		return false;
    	// 这里要把isRight 和 isLeft 传下去
    	return isValidBST(root.left, true, isRight, root.val, rmin) 
    			&& isValidBST(root.right, isLeft, true, lmax, root.val);
    }
}
