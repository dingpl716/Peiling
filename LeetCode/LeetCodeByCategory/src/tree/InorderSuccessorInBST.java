package tree;

import definition.TreeNode;

//	Given a binary search tree and a node in it, find the in-order successor of that node in the BST.
//	
//	Note: If the given node has no in-order successor in the tree, return null.
public class InorderSuccessorInBST {

	TreeNode result = null;
	
	public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
		dfs(root, p, null);
		
		return result;
	}
	
	/**
	 * 中序遍历以root为根的子树，返回这颗子树的最后一个节点。
	 * @param root
	 * @param p
	 * @param previous
	 * @return
	 */
	private TreeNode dfs(TreeNode root, TreeNode p, TreeNode previous) {
		
		if (result != null){
			return root;
		}
		
		if (root == null) {
			return null;
		}
		
		if (root.left != null) {
			previous = dfs(root.left, p, previous);
		}
		
		if (previous == p) {
			result = root;
		}
		
		TreeNode lastNode = root;
		if (root.right != null) {
			lastNode = dfs(root.right, p, root);
		}
		
		return lastNode;
	}
}
