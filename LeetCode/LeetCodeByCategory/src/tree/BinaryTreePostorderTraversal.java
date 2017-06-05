package tree;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import definition.TreeNode;

//	Given a binary tree, return the postorder traversal of its nodes' values.
//	
//	For example:
//	Given binary tree {1,#,2,3},
//	   1
//	    \
//	     2
//	    /
//	   3
//	return [3,2,1].
//	
//	Note: Recursive solution is trivial, could you do it iteratively?

public class BinaryTreePostorderTraversal {
	
	/**
	 * 先将自己压栈， 然后分别压入右节点、左节点
	 * 如果没有左右子节点，那么就输出
	 * 如果已经压过了，也输出
	 * @param root
	 * @return
	 */
	public static ArrayList<Integer> postorderTraversal(TreeNode root) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        
        Stack<TreeNode> stack = new Stack<TreeNode>();
        Set<TreeNode> hasBeenPushed = new HashSet<TreeNode>();
        ArrayList<Integer> result = new ArrayList<Integer>();
        
        if(root == null)
            return result;
        
        stack.push(root);
        
        while(!stack.empty()) {
            TreeNode node = stack.peek();
            if(node.left ==null && node.right == null) { //if the node is a leaf, then just add it to the tail
                result.add(node.val);
                stack.pop();
                continue;
            }else {
                if(hasBeenPushed.contains(node)) { //if it is the second time to see the node
                    result.add(node.val);
                    stack.pop();
                    continue;
                }else {  // if it's the first time to see the node 
                    if(node.right != null)
                        stack.push(node.right);
                    if(node.left != null)
                        stack.push(node.left);
                    hasBeenPushed.add(node);
                }
            }
        }
        return result;
    }
}
