package tree;

import definition.TreeNode;

//	Given a complete binary tree, count the number of nodes.
//	
//	Definition of a complete binary tree from Wikipedia:
//	In a complete binary tree every level, except possibly the last, 
//	is completely filled, and all nodes in the last level are as far left 
//	as possible. It can have between 1 and 2h nodes inclusive at the last level h.

/**
 * 核心思想:
 * 完全二叉树有以下两个特性：
 * 1. 最多可能有2^n - 1 个节点，n为高度
 * 2. 左右子树也一定是完全二叉树
 * @author Dingp
 *
 */
public class CountCompleteTreeNodes {
	
    public int countNodes(TreeNode root) {
    	if (root == null) {
    		return 0;
    	}
    	
    	int leftDepth = getLeftDepth(root);
    	int rightDepth = getRightDepth(root);
    	
    	// 左右相等为满二叉树,节点数为2^n - 1
    	if (leftDepth == rightDepth) {
    		return (1 << leftDepth) - 1; 
    	} else {
    		// 左右子树分别也是完全二叉树，所以递归调用
    		return countNodes(root.left) + countNodes(root.right) + 1;
    	}
    }
    
    /**
     * 求出最左边路径的高度，包括父节点在内
     * @param root
     * @return
     */
    private int getLeftDepth(TreeNode root) {
    	int depth = 0;
    	while (root != null) {
    		root = root.left;
    		++depth;
    	}
    	
    	return depth;
    }
    
    private int getRightDepth(TreeNode root) {
    	int depth = 0;
    	while (root != null) {
    		root = root.right;
    		++depth;
    	}
    	
    	return depth;
    }
}
