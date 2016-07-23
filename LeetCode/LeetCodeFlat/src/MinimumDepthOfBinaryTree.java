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
            int leftDepth = minDepth(root.left);
            int rightDepth = minDepth(root.right); 
            return  leftDepth < rightDepth ? leftDepth + 1 : rightDepth + 1;
        }else if(root.right == null)
            return 1 + minDepth(root.left) ;
         else if(root.left == null)
            return 1 + minDepth(root.right);
        else
            return 1;
    }
    
    public int minDepth(TreeNode root){
    	if(root == null)
    		return 0;
    	if(root.left == null && root.right == null)
    		return 1;
    	else if(root.left == null)
    		return minDepth(root.right) + 1;
    	else if(root.right == null)
    		return minDepth(root.left) + 1;
    	else{
    		return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
    	}
    }
}
