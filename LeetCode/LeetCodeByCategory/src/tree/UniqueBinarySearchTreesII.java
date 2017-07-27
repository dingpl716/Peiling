package tree;
import java.util.ArrayList;

import definition.TreeNode;

//	Given n, generate all structurally unique BST's (binary search trees) that store values 1...n.
//	
//	For example,
//	Given n = 3, your program should return all 5 unique BST's shown below.
//	
//	   1         3     3      2      1
//	    \       /     /      / \      \
//	     3     2     1      1   3      2
//	    /     /       \                 \
//	   2     1         2                 3

/**
 * 和UniqueBinarySearchTree I 一起看
 * 这是一个自底向上的过程
 * 先构建所有可能的左子树，再构建所有可能的有子树，
 * 然后类似于I里面左子树*右子树的方式，构建所有以他们为子节点的父节点
 * 把这些父节点全部加入到results里面
 * 返回results，这个results就是上一层调用的，且需要返回的所有可能的左子树，或者是右子树
 */ 
public class UniqueBinarySearchTreesII {

	public ArrayList<TreeNode> generateTrees(int n) {
		return generateTrees(1, n);
	}

	/**
	 * 构建以left为最小值，right为最大值的所有树
	 * @param left 能取到的最小值
	 * @param right 能取到的最大值
	 * @return
	 */
	private ArrayList<TreeNode> generateTrees(int left, int right) {
		ArrayList<TreeNode> results = new ArrayList<TreeNode>();
		if (left > right) {
			results.add(null);
			return results;
		}
		
		// 左子树乘以右子树的过程
		for (int i = left; i <= right; i++) {
			ArrayList<TreeNode> lefts = generateTrees(left, i-1);
			ArrayList<TreeNode> rights = generateTrees(i+1, right);
			for (int j = 0; j < lefts.size(); j++) {
				for (int k = 0; k < rights.size(); k++) {
					TreeNode root = new TreeNode(i);
					root.left = lefts.get(j);
					root.right = rights.get(k);
					results.add(root);
				}
			}
		}
		return results;
	}
}
