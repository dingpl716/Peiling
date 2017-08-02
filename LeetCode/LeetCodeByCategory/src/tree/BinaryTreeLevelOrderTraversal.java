package tree;

import java.util.LinkedList;
import java.util.List;

import definition.TreeNode;

//	Given a binary tree, return the level order traversal of its nodes' 
//	values. (ie, from left to right, level by level).
//	
//	For example:
//	Given binary tree [3,9,20,null,null,15,7],
//	    3
//	   / \
//	  9  20
//	    /  \
//	   15   7
//	return its level order traversal as:
//	[
//	  [3],
//	  [9,20],
//	  [15,7]
//	]
		
public class BinaryTreeLevelOrderTraversal {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> results = new LinkedList<List<Integer>>();
        
        if (root == null) {
        	return results;
        }
        
        LinkedList<TreeNode> parents = new LinkedList<TreeNode>();
        LinkedList<TreeNode> children = new LinkedList<TreeNode>();
        
        parents.add(root);
        
        while (!parents.isEmpty()) {
        	results.add(getNumbers(parents));
        	
        	TreeNode node;
        	while(!parents.isEmpty()) {
        		node = parents.poll();
        		
        		if (node.left != null) {
        			children.add(node.left);
        		}
        		
        		if (node.right != null) {
        			children.add(node.right);
        		}
        	}
        	
        	LinkedList<TreeNode> tmp = parents;
        	parents = children;
        	children = tmp;
        }
        
        return results;
    }
    
    
    private List<Integer> getNumbers(LinkedList<TreeNode> list){
    	List<Integer> result = new LinkedList<Integer>();
    	for(TreeNode node : list) {
    		result.add(node.val);
    	}
    	
    	return result;
    }
}
