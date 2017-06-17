package tree;

import java.util.LinkedList;
import java.util.Queue;

import definition.TreeNode;

public class SerializeAndDeserializeBinaryTree {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder builder = new StringBuilder();
        
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        
        while (!queue.isEmpty()){
        	TreeNode currentNode = queue.poll();
        	if (currentNode != null) {
        		builder.append(currentNode.val);
        		queue.add(currentNode.left);
        		queue.add(currentNode.right);
        	}
        	else {
        		builder.append("null");
        	}
        	builder.append(',');
        }
        
        if (builder.charAt(builder.length() - 1) == ','){
        	builder.deleteCharAt(builder.length() - 1);
        }
        
        return builder.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.length() == 0) {
        	return null;
        }
        
        String[] nodes = data.split(",");
        if (nodes == null || nodes.length == 0) {
        	return null;
        }
        
        if (nodes[0].equals("null")){
        	return null;
        }
        
        TreeNode root = new TreeNode(Integer.parseInt(nodes[0]));
        
        // 这个queue里面的每个元素都是父节点
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        
        // i指向父节点的左孩子节点。
        int i = 1;
        
        // 每一轮都从queue里面取出一个节点为父节点,
        // 然后取data[i]	作为左孩子节点， data[i+1]作为右孩子节点
        // 如果左右孩子节点不为null，则需要继续加入进队列
        while(!queue.isEmpty()){
        	TreeNode fatherNode = queue.poll();
        	if (fatherNode != null){
        		TreeNode leftChild = null;
        		if (!nodes[i].equals("null")){
        			leftChild = new TreeNode(Integer.parseInt(nodes[i]));
        			fatherNode.left = leftChild;
        			queue.add(leftChild);
        		}
        		
        		
        		TreeNode rightChild = null;
        		if (!nodes[i+1].equals("null")){
        			rightChild = new TreeNode(Integer.parseInt(nodes[i+1]));
        			fatherNode.right = rightChild;
        			queue.add(rightChild);
        		}
        		
        		i += 2;
        	}
        }
        
        return root;
    }
    
    public static void main(String[] args){
    	TreeNode t5 = new TreeNode(5);
    	TreeNode t4 = new TreeNode(4);
    	TreeNode t3 = new TreeNode(3, t4, t5);
    	TreeNode t2 = new TreeNode(2);
    	TreeNode t1 = new TreeNode(1, t2, t3);
    	
    	SerializeAndDeserializeBinaryTree s = new SerializeAndDeserializeBinaryTree();
    	String s1 = s.serialize(t1);
    	System.out.println(s1);
    	
    	TreeNode t11 = s.deserialize(s1);
    	String s11 = s.serialize(t11);
    	System.out.println(s11);
    	
    	
    }
}
