package dp;

import definition.TreeNode;

//	The thief has found himself a new place for his thievery again. 
//	There is only one entrance to this area, called the "root." Besides 
//	the root, each house has one and only one parent house. After a tour, 
//	the smart thief realized that "all houses in this place forms a binary tree". 
//	It will automatically contact the police if two directly-linked houses 
//	were broken into on the same night.
//	
//	Determine the maximum amount of money the thief can rob tonight without alerting the police.
//	
//	Example 1:
//	     3
//	    / \
//	   2   3
//	    \   \ 
//	     3   1
//	Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
//	Example 2:
//	     3
//	    / \
//	   4   5
//	  / \   \ 
//	 1   3   1
//	Maximum amount of money the thief can rob = 4 + 5 = 9.
public class HouseRobberIII {
	
    public int rob(TreeNode root) {
        int[] result = dfs(root);
        
        return Math.max(result[0], result[1]);
    }
    
    /**
     * postOrder traversal
     * 
     * @param root
     * @return 第一个element是R代表抢了这个点做带来的价值，第二个是N代表不抢这个点带来的价值
     */
    private int[] dfs(TreeNode root) {
    	if (root == null) {
    		return new int[] {0, 0};
    	}
    	
    	int[] left = dfs(root.left);
    	int[] right = dfs(root.right);
    	
    	// 抢了root，就不能抢左右孩子节点了
    	int r = left[1] + right[1] + root.val;
    	
    	/*
    	 * 不抢root的话，可以有很多种选择
    	 * 1. 左右都抢
    	 * 2. 左右都不抢
    	 * 3. 抢左不抢右
    	 * 4. 抢右不抢左
    	 */
    	int n = Math.max(left[0] + right[0], left[1] + right[1]);
    	n = Math.max(n, left[0] + right[1]);
    	n = Math.max(n, left[1] + right[0]);
    	
    	return new int[] {r, n};
    }
}
