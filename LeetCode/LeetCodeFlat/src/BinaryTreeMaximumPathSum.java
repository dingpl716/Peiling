import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import definition.TreeNode;

//	Given a binary tree, find the maximum path sum.
//	
//	The path may start and end at any node in the tree.
//	
//	For example:
//	Given the below binary tree,
//	
//	       1
//	      / \
//	     2   3
//	Return 6.

//	对于一个节点，先计算这个节点，到他左子树的所有节点里面，pathsum最大的值，
//	记为leftSum, 同样这样计算右子树里面的值记为rightSum, 
// 然后返回pathSum + rightSum + node.val最大的那个点

//已犯错误，没有考虑负数点的情况
public class BinaryTreeMaximumPathSum {
	
    public int maxPathSum(TreeNode root) {
        if(root == null)
        	return 0;
        Map<TreeNode, Integer[]> map = new HashMap<TreeNode, Integer[]>();
        calcPathSum(root, map);
        
        int result = Integer.MIN_VALUE;
        for(Iterator<Map.Entry<TreeNode, Integer[]>> i=map.entrySet().iterator(); i.hasNext();) {
        	Map.Entry<TreeNode, Integer[]> e = i.next();
        	TreeNode node = e.getKey();
        	Integer[] sums = e.getValue();
        	int tmp = node.val + sums[0] + sums[1];
        	if(tmp > result)
        		result = tmp;
        }
        return result;
    }
    
//    通过后续遍历来计算
//	对于一个节点，先计算这个节点，到他左子树的所有节点里面，pathsum最大的值，
//	记为leftSum, 同样这样计算右子树里面的值记为rightSum,
//  返回这个左右中较大的值
    // 如果任意一条子树返回的值小于0，那么就把这个值置为0，因为小于零了我就不在往下走了
    private int calcPathSum(TreeNode node, Map<TreeNode, Integer[]> map) {
    	if(node == null)
    		return 0; 
    	if(node.left == null && node.right == null) {
    		map.put(node, new Integer[]{0, 0});
    		return node.val;
    	}else if(node.right == null) {
    		Integer leftSum = calcPathSum(node.left, map);
    		if(leftSum < 0)
    			leftSum = 0;
    		map.put(node, new Integer[]{leftSum, 0});
    		return leftSum + node.val;
    	}else if(node.left == null) {
    		Integer rightSum = calcPathSum(node.right, map);
    		if(rightSum < 0)
    			rightSum = 0;
    		map.put(node, new Integer[]{0, rightSum});
    		return node.val + rightSum;
    	}else {
    		Integer leftSum = calcPathSum(node.left, map);
    		if(leftSum < 0)
    			leftSum = 0;
    		Integer rightSum = calcPathSum(node.right, map);
    		if(rightSum < 0)
    			rightSum = 0;
    		map.put(node, new Integer[]{leftSum, rightSum});
    		int sum = leftSum > rightSum ? leftSum : rightSum;
    		return sum + node.val;
    	}
    }
}
