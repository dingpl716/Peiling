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
	private TreeNode break1;
	private TreeNode break2;
	private TreeNode break1Prev;
	private TreeNode prev;
	
    public void recoverTree(TreeNode root) {
        if(root == null)
        	return;
        
        inorder(root);
        if(break2 == null) {
        	int tmp = break1.val;
        	break1.val = break1Prev.val;
        	break1Prev.val = tmp;
        }else {
        	int tmp = break2.val;
        	break2.val = break1Prev.val;
        	break1Prev.val = tmp;
        }
    }
    
    private void inorder(TreeNode root) {
    	if(root == null) {
    		return;
    	}
    	
    	if(root.left != null) 
    		inorder(root.left);

    	if(prev != null){
			if(root.val < prev.val) {
				if(break1 == null) {
					break1 = root;
					break1Prev = prev;
				}else
					break2 = root;
			}
		}
		prev = root;
		
		if(root.right != null)
			inorder(root.right);
    }
}
