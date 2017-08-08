package tree;
import definition.TreeNode;

//	Given a binary tree where all the right nodes are either leaf nodes with a sibling 
//	(a left node that shares the same parent node) or empty, 
//	flip it upside down and turn it into a tree where the original right nodes turned into left leaf nodes. 
//	Return the new root.
//	For example:
//	Given a binary tree {1,2,3,4,5},
//	    1
//	   / \
//	  2   3
//	 / \
//	4   5
//	return the root of the binary tree [4,5,2,#,#,3,1].
//	   4
//	  / \
//	 5   2
//	    / \
//	   3   1  
public class BinaryTreeUpsideDown {

	private TreeNode newRoot;
	// in-order 
	// 1. 左子树的最右后代变成newParent
	// 2. 自己，变成newParent的右子树
	// 3. 现在的右child变成newParent的leftChild
	public TreeNode upsideDownBinaryTree(TreeNode tree){
		TreeNode result = getLeftMost(tree);
		inOrder(tree, null);
		return result;
	}
	
	/**
	 * 虽然是inorder但是不需要对右子树做任何操作。
	 * @param tree
	 * @param parent
	 */
	private void inOrder(TreeNode tree, TreeNode parent){
		if (tree == null || (tree.left == null && tree.right == null)){
			return;
		}
		
		inOrder(tree.left, tree);
		
		// 1. 左子树的最右后代变成newParent
		TreeNode newParent = getRightMost(tree.left);
		
		// 2. 自己，也就是currentParent变成newParent的右子树
		newParent.right = tree;
		
		// 3. 现在的右child变成newParent的leftChild
		newParent.left = tree.right;
		
		tree.left = null;
		tree.right = null;
		if (parent != null){
			parent.left = newParent;
		}
	}
	
	private TreeNode getRightMost(TreeNode node){
		TreeNode result = node;
		while(result.right != null){
			result = result.right;
		}
		
		return result;
	}
	
	private TreeNode getLeftMost(TreeNode node){
		if (node == null) {
            return null;
        }
		
		TreeNode result = node;
		while(result.left != null){
			result = result.left;
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
