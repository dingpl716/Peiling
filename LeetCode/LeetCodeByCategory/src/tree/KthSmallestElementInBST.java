package tree;

import definition.TreeNode;

//	Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.
//	
//	Note: 
//	You may assume k is always valid, 1 ? k ? BST's total elements.
//	
//	Follow up:
//	What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently? 
//	How would you optimize the kthSmallest routine?

/**
 * 此题和统计数的点的个数差不多
 * 此解法还可以被改进，因为当result被赋值以后，就可以直接输出了，不需要再往下算了
 * @author Dingp
 *
 */
public class KthSmallestElementInBST {

	int result;
	
    public int kthSmallest(TreeNode root, int k) {
        
    	dfs(root, k, 0);
    	return result;
    }
    
    /**
     * 计算，以baseIndex为基础，这个二叉树里面最后一个点的index数
     * 比如，baseIndex为5的话，这个二叉树总共只有三个点，则返回8
     * @param root
     * @param k
     * @param baseIndex
     * @return
     */
    private int dfs(TreeNode root, int k, int baseIndex) {
    	
    	if (root == null) {
    		return baseIndex;
    	}
    	
    	// root左子树中最大的index
    	int leftIndex = dfs(root.left, k, baseIndex);
    	
    	int currentIndex = leftIndex + 1;
    	if(currentIndex == k) {
    		result = root.val;
    	}
    	
    	return dfs(root.right, k, currentIndex);
    }
}
