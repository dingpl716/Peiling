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
		List<Integer> integers = new ArrayList<Integer>();
		
		if (root != null) {
			dfs(root, integers, 1);
		}
		
		return integers;
    }
	
	/**
	 * 当currentLevel大于integers的size时我们把root的val加入到integers里面，然后优先遍历右子书，接着左子树。
	 * @param root
	 * @param integers
	 * @param currentLevel
	 */
	private void dfs(TreeNode root, List<Integer> integers, int currentLevel) {
		if (root == null) {
			return;
		}

		if (currentLevel > integers.size()){
			integers.add(root.val);
		}
		
		dfs(root.right, integers, currentLevel + 1);
		dfs(root.left, integers, currentLevel + 1);
	}
}
