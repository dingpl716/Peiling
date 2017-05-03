package tree;
import definition.TreeNode;

//	Two elements of a binary search tree (BST) are swapped by mistake.
//	
//	Recover the tree without changing its structure.
//	这两个被交换的点不一定是兄弟节点
//	Note:
//	A solution using O(n) space is pretty straight forward.
//	Could you devise a constant space solution?

//	Inorder traverse, keep the previous tree node,
//	Find first misplaced node by
//	if ( current.val < prev.val )
//	   Node first = prev;
//	
//	Find second by
//	if ( current.val < prev.val )
//	   Node second = current;
//	
//	After traversal, swap the values of first and second node. 
//	Only need two pointers, prev and current node. O(1) space.

public class RecoverBinarySearchTree {
	//解法1，中序遍历，这样的space不是O(1)的
//	用两个指针，prev和current来中序遍历这个树，
//	找到两个破坏递增的点，然后最后交换

	TreeNode break1;
	TreeNode break2;
	
	// root the 前一个值, prev 应该小于root
	TreeNode prev;
	
	public void recoverTree(TreeNode root) {
		inOrder(root);
		
		int tmp = break1.val;
		break1.val = break2.val;
		break2.val = tmp;
	}
	
	private void inOrder(TreeNode root){
		if (root == null){
			return;
		}
		
		inOrder(root.left);
		
		if (prev != null){
			if (root.val < prev.val){
				if (break1 == null){
					break1 = prev;
				}
				
				break2 = root;
			}
		}
		
		prev = root;
		inOrder(root.right);
	}
}
