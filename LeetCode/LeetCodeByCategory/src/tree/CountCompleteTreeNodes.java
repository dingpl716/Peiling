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
 * 对于一个完全二叉树而言，其最左边的层数一定大于等于最后边的层数，
 * 如果等于的话，那么是一个满二叉树，直接返回2^层数 - 1即可
 * 如果大于的话，那么我们知道第一个叶节点一定在右子树上，而且第一个页节点的兄弟节点就是最后一个非叶节点
 * 假设最后一个非叶节点是第k个节点的话，那么总节点是为2k 或者 2k+1 
 * @author Dingp
 *
 */
public class CountCompleteTreeNodes {
	
    public int countNodes(TreeNode root) {
    	return 0;
    }
    
    /**
     * 
     * @param father The father of root node.
     * @param root The root node.
     * @param rootLevel The level of root node.
     * @param rootIndex The index of root node.
     * @return
     */
    private int countNodes(TreeNode father, TreeNode root, int rootLevel, int rootIndex) {
    	int leftChildrenLevel = getLeftChildrenLevel(root, rootLevel);
    	int rightChildrenLevel = getRightChildrenLevel(root, rootLevel);
    	
    	// 遇到满二叉树了,那么总节点数等于他兄弟节点最右边的子孙节点的index数
    	if (leftChildrenLevel == rightChildrenLevel) {
    		return -1;
    	}
    	
    	// 如果左右子树的level不相等的话，那么右边一定小于左边
    	else {
    		return countNodes(root, root.right, rootLevel + 1, rootIndex * 2 + 1);
    	}
    }
    
    private int getLeftChildrenLevel(TreeNode root, int fatherLevel){
    	if (root == null){
    		return fatherLevel;
    	}
    	else {
    		return getLeftChildrenLevel(root.left, fatherLevel + 1);
    	}
    }
    
    private int getRightChildrenLevel(TreeNode root, int fatherLevel){
    	if (root == null){
    		return fatherLevel;
    	}
    	else {
    		return getRightChildrenLevel(root.right, fatherLevel + 1);
    	}
    }
    
    private int getMostRightChildIndex(TreeNode root, int rootIndex){
    	if (root == null){
    		return rootIndex;
    	}
    	
    	if (root.right != null){
    		return getMostRightChildIndex(root.right, 2 * rootIndex + 1);
    	}else {
    		
    	}
    	
    	return 0;
    }
}
