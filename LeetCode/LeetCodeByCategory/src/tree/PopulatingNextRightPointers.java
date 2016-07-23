package tree;
import definition.TreeLinkNode;


//	Populate each next pointer to point to its next right node. 
//	If there is no next right node, the next pointer should be set to NULL.
//	
//	Initially, all next pointers are set to NULL.
//	
//	Note:
//	
//	    You may only use constant extra space.
//	    You may assume that it is a perfect binary tree (ie, all leaves are at the same level, and every parent has two children).
//	
//	For example,
//	Given the following perfect binary tree,
//	
//	         1
//	       /  \
//	      2    3
//	     / \  / \
//	    4  5  6  7
//	
//	After calling your function, the tree should look like:
//	
//	         1 -> NULL
//	       /  \
//	      2 -> 3 -> NULL
//	     / \  / \
//	    4->5->6->7 -> NULL
/**
 * Recursion is not a correct solution!
 * Think each level as a linked list, and you will link the nodes 
 * using the previous level.
 * @author Peiling
 *
 */
public class PopulatingNextRightPointers {
    
	public void connect(TreeLinkNode root) {
        TreeLinkNode leftMost = root;
        
        // link the nodes level by level
        while(leftMost != null && (leftMost.left != null && leftMost.right != null)) {
            TreeLinkNode runner = leftMost; // the runner will run on each level
            while(runner != null) {
                runner.left.next = runner.right;
                
                if(runner.next != null)
                    runner.right.next = runner.next.left;
                
                runner = runner.next; // runner goes to the next
            }
            leftMost = leftMost.left; // go to the next level
        }
    }
}
