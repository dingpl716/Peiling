package tree;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

import definition.TreeNode;

/**
 * 核心思想，用两个栈来存放node，一个栈用来存放node本身，一个栈存放node的身份，是左孩子还是右孩子
 * push node的时候先push它的右孩子，然后push自己，接着递归的push左孩子
 * getNext的时候，从stack里面pop出来一个，如果这个节点是左孩子节点，那么直接输出，因为它之前已经被递归的push过了，
 * 如果这个节点是右孩子节点，那么需要在对这个点做push，然后再返回
 * @author peding
 *
 */
public class BSTIterator {

	// leftChild 这个flag的实际作用是表明，对应的节点是否有被递归的push过
	private static final Integer leftChild = -1;
	private static final Integer rightChild = 1;
	
	Stack<TreeNode> nodeStack;
	Stack<Integer> identityStack;
	TreeNode next = null;
	
    public BSTIterator(TreeNode root) {
        nodeStack = new Stack<TreeNode>();
        identityStack = new Stack<Integer>();
        pushNode(root, leftChild);
        next = getNext();
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return next != null;
    }

    /** @return the next smallest number */
    public int next() {
        int tmp = next.val;
        next = getNext();
        return tmp;
    }
    
    private TreeNode getNext(){
    	if (nodeStack.isEmpty()) {
    		return null;
    	}
    	
    	TreeNode top = nodeStack.pop();
    	Integer identity = identityStack.pop();
    	
    	if (identity == leftChild) {
    		return top;
    	} else {
    		pushNode(top, leftChild);
    		return getNext();
    	}
    }
    
    /**
     * 先push右子树，再push自己，最后再递归的push左子树
     * @param root
     */
    private void pushNode(TreeNode root, Integer identity) {
    	if (root == null) {
    		return;
    	}
    	
		nodeStack.push(root.right);
		identityStack.push(rightChild);
    	
		nodeStack.push(root);
		identityStack.push(identity);
    	
		pushNode(root.left, leftChild);
    }
}

/**
 * Your BSTIterator will be called like this:
 * BSTIterator i = new BSTIterator(root);
 * while (i.hasNext()) v[f()] = i.next();
 */
