package tree;
import definition.TreeNode;

/**
 * Given a binary tree, determine if it is height-balanced.
 * For this problem, a height-balanced binary tree is defined 
 * as a binary tree in which the depth of the two subtrees of 
 * every node never differ by more than 1.
 * @author Peiling
 *
 */
public class BalancedBinaryTree {
	
	private class CurrentDepth {
        int depth = 0;
        
        CurrentDepth(int d) {
            depth = d;
        }
        
        CurrentDepth( ) {}
    }
    
    public boolean isBalanced(TreeNode root) {
        if(root == null)
            return true;
        
        return isBalance(root) >= 0 ? true : false;
    }
    
    /**
     * 在这里用后续遍历方能达到最快时间复杂度，但是因为需要额外
     * 记录以每个节点为根的子树的高度，所以空间消耗增大了
     * 
     * 如果左子树平衡，并且右子树平衡，那么求出左右子树的高度差，
     * 如果这个差的绝对值小于2，那么以这个节点为根的子树平衡，
     * 并且当前高度为，左右子树中高度较大者加1
     * @param root
     * @param cd
     * @return
     */
    private boolean isBalanced(TreeNode root, CurrentDepth cd) {
        if(root == null){
            cd.depth = 0;
            return true;
        }
        CurrentDepth leftDepth = new CurrentDepth();
        CurrentDepth rightDepth = new CurrentDepth();
        
        //左右子树都平衡，并且，左右子树的深度差不超过2
        if(isBalanced(root.left, leftDepth) == true && isBalanced(root.right, rightDepth) == true){
            int diff = leftDepth.depth - rightDepth.depth; 
            if( diff < 2 && diff > -2) {
            	// 当前深度为最深的子树深度加1
                cd.depth = leftDepth.depth > rightDepth.depth ? leftDepth.depth + 1 : rightDepth.depth + 1;
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * 上一方法的变种，不需要使用一个类来记录当前高度。 但是仍然在每次递归之前
     * 申请了一个int变量，所以仍然是增加了空间复杂度
     * @param root
     * @return if balanced， 现在的高度
     * 		   if not, -1
     */
    private int isBalance(TreeNode root) {
    	if(root == null) 
    		return 0;
    	
    	int leftDepth = isBalance(root.left);
    	int rightDepth = isBalance(root.right);
    	if(leftDepth >= 0 && rightDepth >= 0) {
    		int diff = Math.abs(leftDepth - rightDepth);
    		if(diff < 2) {
    			return leftDepth > rightDepth ? leftDepth + 1 : rightDepth + 1;
    		}
    	}
    	
    	return -1;
    }
}
