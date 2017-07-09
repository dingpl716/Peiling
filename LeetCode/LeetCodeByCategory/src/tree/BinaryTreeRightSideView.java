package tree;

import java.util.ArrayList;
import java.util.List;

import definition.TreeNode;

//	Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.
//	
//	For example:
//	Given the following binary tree,
//	   1            <---
//	 /   \
//	2     3         <---
//	 \     \
//	  5     4       <---
//	You should return [1, 3, 4].

public class BinaryTreeRightSideView {
	
	public List<Integer> rightSideView(TreeNode root) {
		List<Integer> results = new ArrayList<Integer>();
		
		if (root != null) {
			dfs(root, results, 1);
		}
		
		return results;
    }
	
	/**
	 * 当currentLevel大于integers的size时我们把root的val加入到integers里面，然后优先遍历右子书，接着左子树。
	 * @param root
	 * @param results
	 * @param currentLevel
	 */
	private void dfs(TreeNode root, List<Integer> results, int currentLevel) {
		if (root == null) {
			return;
		}

		if (currentLevel > results.size()){
			results.add(root.val);
		}
		
		dfs(root.right, results, currentLevel + 1);
		dfs(root.left, results, currentLevel + 1);
	}
}
