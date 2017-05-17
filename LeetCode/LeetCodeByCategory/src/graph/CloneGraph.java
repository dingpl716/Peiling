package graph;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import definition.UndirectedGraphNode;

//Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors.

//	Clone Graph，采用DFS，同时用一个Map记录原先节点和现在节点的映射，顺便可以用来做visited check。
//	一开始有两个错误，一是null的时候也要返回null，
//	二是有可能节点0也有指向0本身的邻居，所以在对neighbors做遍历前要先放到map里。

//	在这里面，map是old node -> new node 的映射。我们在每复制一个oldnode的时候，就把这个
//	映射放到map里面。当我们遇到另外一个old node的时候，如果在map里面有这个node，说明我们
//	之前已经复制过这个node了，所以只需要从map里面取出来就行了
public class CloneGraph {
	
	/************************************* recursion implementation ************************************/
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
    	Map<UndirectedGraphNode, UndirectedGraphNode> map
    	= new HashMap<UndirectedGraphNode, UndirectedGraphNode>();
    	return cloneNode(node, map);
    }
    
    
    private UndirectedGraphNode cloneNode(UndirectedGraphNode node,
    		Map<UndirectedGraphNode, UndirectedGraphNode> map) {
    	
    	if(node == null)
    		return null;
    	if(map.containsKey(node))
    		return map.get(node);
    	
    	UndirectedGraphNode copy = new UndirectedGraphNode(node.label);
    	map.put(node, copy);
    	for(UndirectedGraphNode neighbor : node.neighbors) {
    		copy.neighbors.add(cloneNode(neighbor, map));
    	}
    	return copy;
    }
    
    /************************************* loop implementation ************************************/
    public UndirectedGraphNode cloneGraph1(UndirectedGraphNode node) {
    	if(node == null)
    		return null;
    	
    	// oldNode -> newNode 
    	// 在这里面的oldNode，都是已经被克隆了，或者说是被new过了，但是他们的边edge可能还没有,也就是邻居可能还没有
    	Map<UndirectedGraphNode, UndirectedGraphNode> map 
    	= new HashMap<UndirectedGraphNode, UndirectedGraphNode>();
    	
    	// nodes need to be cloned
    	// 这个queue里面也都装的是边还没有被克隆边的node
    	Queue<UndirectedGraphNode> queue = new LinkedList<UndirectedGraphNode>();
    	
    	// 克隆点
    	UndirectedGraphNode newStart = new UndirectedGraphNode(node.label);
    	map.put(node, newStart);
    	queue.add(node);
    	
    	while(!queue.isEmpty()) {
    		UndirectedGraphNode oldNode = queue.poll();
    		UndirectedGraphNode newNode = map.get(oldNode);
    		ArrayList<UndirectedGraphNode> oldNeighbors = oldNode.neighbors;
    		for(UndirectedGraphNode oldNeighbor : oldNeighbors) {
    			if(map.containsKey(oldNeighbor)) {
    				newNode.neighbors.add(map.get(oldNeighbor));
    			} else {
    				UndirectedGraphNode newNeighbor = new UndirectedGraphNode(oldNeighbor.label);
    				map.put(oldNeighbor, newNeighbor);
    				queue.add(oldNeighbor);
    				newNode.neighbors.add(newNeighbor);
    			}
    		}
    	}
    	return newStart;
    }
}
