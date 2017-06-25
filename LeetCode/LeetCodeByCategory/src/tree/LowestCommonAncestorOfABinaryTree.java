package tree;

import definition.TreeNode;

//	Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
//	
//	According to the definition of LCA on Wikipedia: 
//	“The lowest common ancestor is defined between two nodes v and w as 
//	the lowest node in T that has both v and w as descendants 
//	(where we allow a node to be a descendant of itself).”
//	
//	        _______3______
//	       /              \
//	    ___5__          ___1__
//	   /      \        /      \
//	   6      _2       0       8
//	         /  \
//	         7   4
//	For example, the lowest common ancestor (LCA) of nodes 5 and 1 is 3. 
//	Another example is LCA of nodes 5 and 4 is 5, since a node can be a 
//	descendant of itself according to the LCA definition.

/**
 * 核心思想：因为是找lowest的ancestor，所以需要是一个自底像上的过程,所以我们需要用后续遍历来做
 * 因为后续遍历是有子节点先做子节点，然后再做本节点。然后DFS本事是用来看p, q是否分属于root的两侧
 * 
 * @author Dingp
 *
 */
public class LowestCommonAncestorOfABinaryTree {

	private TreeNode lca = null;
	
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        dfs(root, p, q);
        
        return lca;
    }
    
    /**
     * 如果在root下面同时找到p, q则返回2
     * 如果只找到其中一个，返回1
     * 如果一个都没找到 返回0
     * @param root
     * @param p
     * @param q
     * @return
     */
    private int dfs(TreeNode root, TreeNode p, TreeNode q) {
    	if (root == null) {
    		return 0;
    	}
    	
    	// 表示在左子树里面能找到多少个
    	int left = dfs(root.left, p, q);
    	
    	// 表示在右子树里面能找到多少个
    	int right = dfs(root.right, p, q);
    	
    	// 表示在当前节点是否找到一个
    	int current = 0;
    	if (root == p || root == q){
    		current += 1;
    	}
    	
    	if (current == 0) {
    		if (left == 1 && right == 1) {
    			lca = root;
    		}
    	} else {
    		if (left == 1 || right == 1){
    			lca = root;
    		}
    	}
    	
    	return current + left + right;
    }
}
