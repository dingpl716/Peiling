package definition;

// Definition for binary tree
public class TreeNode {
	 public int val;
     public TreeNode left;
     public TreeNode right;
     
     public TreeNode(int x) 
     { 
    	 this(x, null, null); 
     }
     
     public TreeNode(int x, TreeNode leftChild, TreeNode rightChild){
    	 val = x;
    	 left = leftChild;
    	 right = rightChild;
     }
     
 }
