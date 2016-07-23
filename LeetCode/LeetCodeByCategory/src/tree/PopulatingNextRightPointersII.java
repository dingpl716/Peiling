package tree;
import definition.TreeLinkNode;


//	Follow up for problem "Populating Next Right Pointers in Each Node".
//	
//	What if the given tree could be any binary tree? Would your previous solution still work?
//	
//	Note:
//	
//	    You may only use constant extra space.
//	
//	For example,
//	Given the following binary tree,
//	
//	         1
//	       /  \
//	      2    3
//	     / \    \
//	    4   5    7
//	
//	After calling your function, the tree should look like:
//	
//	         1 -> NULL
//	       /  \
//	      2 -> 3 -> NULL
//	     / \    \
//	    4-> 5 -> 7 -> NULL

public class PopulatingNextRightPointersII {

    public void connect(TreeLinkNode root) {
        TreeLinkNode leftMost = root;
        
        while(leftMost != null) {
            TreeLinkNode runner = leftMost;
            while(runner != null) {
                if(runner.left == null && runner.right == null) {
                    ;
                } else if (runner.right == null) {
                    runner.left.next = getNextNode(runner.next);
                } else if (runner.left == null) {
                    runner.right.next = getNextNode(runner.next);
                }else {
                    runner.left.next = runner.right;
                    runner.right.next = getNextNode(runner.next);
                }
                
                runner = runner.next;
            }
            leftMost = getNextNode(leftMost);
        }
    }
    
    // do a linear search on this level, 
    // return the first child node whose parent is on this level
    private TreeLinkNode getNextNode(TreeLinkNode runner) {
        
        while(runner != null){
            if(runner.left != null)
                return runner.left;
            if(runner.right != null)
                return runner.right;
            runner = runner.next;
        }
        
        return null;
    }
}
