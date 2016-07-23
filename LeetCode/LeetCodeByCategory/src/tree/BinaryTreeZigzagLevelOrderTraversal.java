package tree;
import java.util.ArrayList;
import java.util.Stack;

import definition.TreeNode;


//	Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).
//	
//	For example:
//	Given binary tree {3,9,20,#,#,15,7},
//	    3
//	   / \
//	  9  20
//	    /  \
//	   15   7
//	return its zigzag level order traversal as:
//	[
//	  [3],
//	  [20,9],
//	  [15,7]
//	]

public class BinaryTreeZigzagLevelOrderTraversal {
    /**
     * two stacks
     * odd rounds push left then right
     * even rounds push right then left
     */
     
    public ArrayList<ArrayList<Integer>> zigzagLevelOrder1(TreeNode root) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> tmp = new ArrayList<Integer>();
        if(root == null)
            return result;
        
        // oddStack : push left then rigth to evenStack
        Stack<TreeNode> oddStack = new Stack<TreeNode>();
        // evenStack : push right then left to oddStack
        Stack<TreeNode> evenStack = new Stack<TreeNode>();
        oddStack.push(root);
        
        while(true) {
            if(oddStack.isEmpty() && evenStack.isEmpty())
                break;
            // get node from odd one by one add them to tmp, clear tmp at last
            else if(evenStack.isEmpty()) {
                while(!oddStack.isEmpty()) {
                    TreeNode node = oddStack.pop();
                    tmp.add(node.val);
                    if(node.left != null)
                        evenStack.push(node.left);
                    if(node.right != null)
                        evenStack.push(node.right);
                }
                result.add(new ArrayList<Integer>(tmp));
                tmp.clear();
            } else {
                while(!evenStack.isEmpty()) {
                    TreeNode node = evenStack.pop();
                    tmp.add(node.val);
                    if(node.right != null)
                        oddStack.push(node.right);
                    if(node.left != null)
                        oddStack.push(node.left);
                }
                result.add(new ArrayList<Integer>(tmp));
                tmp.clear();
            }
        }
        
        return result;
    }
    
    public ArrayList<ArrayList<Integer>> zigzagLevelOrder(TreeNode root) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> buffer = new ArrayList<Integer>();
        if(root == null)
            return result;
        
        Stack<TreeNode> source = new Stack<TreeNode>();
        Stack<TreeNode> target = new Stack<TreeNode>();
        Stack<TreeNode> tmp;
        // true : push left then rigth to evenStack
        // false : push right then left to oddStack
        boolean direction = true;
        source.push(root);
        while(!source.empty()) {
	        while(!source.empty()) {
	        	TreeNode node = source.pop();
	        	buffer.add(node.val);
	        	if(direction) {
	        		if(node.left != null)
	        			target.push(node.left);
	        		if(node.right != null)
	        			target.push(node.right);
	        	}else {
	        		if(node.right != null)
	        			target.push(node.right);
	        		if(node.left != null)
	        			target.push(node.left);
	        	}
	        }
	        result.add(new ArrayList<Integer>(buffer));
	        buffer.clear();
	        tmp = source;
	        source = target;
	        target = tmp;
	        direction = !direction;
        }
        return result;
    }
}
