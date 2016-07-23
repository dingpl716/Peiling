import definition.TreeNode;

//	Given a binary tree, check whether it is
//	a mirror of itself (ie, symmetric around its center).
//	
//	For example, this binary tree is symmetric:
//	
//	    1
//	   / \
//	  2   2
//	 / \ / \
//	3  4 4  3
//	But the following is not:
//	    1
//	   / \
//	  2   2
//	   \   \
//	   3    3
//	Note:
//	Bonus points if you could solve it both recursively and iteratively.
public class SymmetricTree {
	/*********** recursion implementation ****************************/
    public boolean isSymmetric(TreeNode root) {
    	if(root == null)
    		return true;
        return isSymmetric(root.left, root.right);
    }
    
    private boolean isSymmetric(TreeNode left, TreeNode right) {
    	if(left == null && right == null)
    		return true;
    	if(left != null && right != null) {
    		if(left.val != right.val)
    			return false;
    		return  isSymmetric(left.right, right.left) &&
    				isSymmetric(left.left, right.right);
    	}else
    		return false;
    }
    
    /*************** loop implementation ***********************/
//    想法：
//    用一个arraylis来存储每一层的node，就像lever order traversal一样
//    每一层如果是对称的，那么这个array list一定能从中间刨开，并且左右相等
}
