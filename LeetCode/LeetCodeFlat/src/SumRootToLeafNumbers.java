import definition.TreeNode;


//	Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.
//	
//	An example is the root-to-leaf path 1->2->3 which represents the number 123.
//	
//	Find the total sum of all root-to-leaf numbers.
//	
//	For example,
//	
//	    1
//	   / \
//	  2   3
//	
//	The root-to-leaf path 1->2 represents the number 12.
//	The root-to-leaf path 1->3 represents the number 13.
//	
//	Return the sum = 12 + 13 = 25. 

public class SumRootToLeafNumbers {
    public int sumNumbers(TreeNode root) {
        if(root == null)
            return 0;
            
        return sumNumbers(root, 0, 0);
    }
    
    private int sumNumbers(TreeNode root, int tmp, int result) {
        //at leaf, update result
        tmp = tmp*10 + root.val;
        
        if(root.left == null && root.right == null) {
            result += tmp;
            return result;
        } else if(root.right == null) {
            return sumNumbers(root.left, tmp, result);            
        } else if(root.left == null) {
            return sumNumbers(root.right, tmp, result);
        } else {
            return sumNumbers(root.left, tmp, result) + sumNumbers(root.right, tmp, result);
        }
    }
}
