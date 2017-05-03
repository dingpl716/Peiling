package tree;
import definition.TreeNode;

//	Given a binary tree, find its minimum depth.
//	
//	The minimum depth is the number of nodes along 
//	the shortest path from the root node down to the nearest leaf node.

public class MinimumDepthOfBinaryTree {
	
    public int minDepth1(TreeNode root) {
        if(root == null)
            return 0;
        if(root.left != null && root.right != null) {
            int leftDepth = minDepth1(root.left);
            int rightDepth = minDepth1(root.right); 
            return  leftDepth < rightDepth ? leftDepth + 1 : rightDepth + 1;
        }else if(root.right == null)
            return 1 + minDepth1(root.left) ;
         else if(root.left == null)
            return 1 + minDepth1(root.right);
        else
            return 1;
    }
    
    public int minDepth2(TreeNode root){
    	if(root == null)
    		return 0;
    	if(root.left == null && root.right == null)
    		return 1;
    	else if(root.left == null)
    		return minDepth2(root.right) + 1;
    	else if(root.right == null)
    		return minDepth2(root.left) + 1;
    	else{
    		return Math.min(minDepth2(root.left), minDepth2(root.right)) + 1;
    	}
    }
    
    public int minDepth3(TreeNode root) {
    	if(root == null)
    		return 0;
    	return Math.min(minDepth3(root.left), minDepth3(root.right)) + 1;
    }
}
