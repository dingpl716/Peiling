package tree;
import java.util.ArrayList;

import definition.TreeNode;

//	Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.
//	
//	For example:
//	Given the below binary tree and sum = 22,
//	              5
//	             / \
//	            4   8
//	           /   / \
//	          11  13  4
//	         /  \    / \
//	        7    2  5   1
//	return
//	[
//	   [5,4,11,2],
//	   [5,8,4,5]
//	]

//	注意负数！！！
public class PathSumII {
    public ArrayList<ArrayList<Integer>> pathSum(TreeNode root, int sum) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> tmp = new ArrayList<Integer>();
        int currentSum = 0;
        
        if(root == null)
            return result;
        
        pathSum(root, sum, result, tmp, currentSum);
        
        return result;
    }
    
    private void pathSum(TreeNode root, int target, ArrayList<ArrayList<Integer>> result,
                            ArrayList<Integer> tmp, int currentSum) {
        
        currentSum += root.val;
        tmp.add(root.val);
        if(root.left == null && root.right == null) {//leaf
            if(currentSum == target) {
                result.add((ArrayList<Integer>)tmp.clone());
            }
        } else if(root.right == null) {
            
            pathSum(root.left, target, result, tmp, currentSum);
            
        } else if(root.left == null) {
            pathSum(root.right, target, result, tmp, currentSum);
        } else {
            pathSum(root.left, target, result, tmp, currentSum);
            pathSum(root.right, target, result, tmp, currentSum);
        }
        
        tmp.remove(tmp.size() -1);
    }
}
