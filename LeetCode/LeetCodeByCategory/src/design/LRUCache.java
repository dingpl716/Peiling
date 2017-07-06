package design;
import java.util.HashMap;
import java.util.Map;

//	Design and implement a data structure for Least Recently Used (LRU) cache. 
//	It should support the following operations: get and set.
//	
//	get(key) - Get the value (will always be positive) of the key 
//	if the key exists in the cache, otherwise return -1.
//	
//	put(key, value) - Set or insert the value if the key is not 
//	already present. When the cache reached its capacity, it should invalidate 
//	the least recently used item before inserting a new item.

public class LRUCache {
	
	// This is a circle doubly linked list
	// to track the most recently used and the
	// least recently used data.
	private static class Node {
		Node prev;
		Node next;
		int key;
		int value;
		
		Node(int k, int v) {
			key = k;
			value = v;
			prev = null;
			next = null;
		}
	}
	
	// The cache.
	private Map<Integer, Node> cache;
	
	// This is a circle doubly linked list.
	// The head is the most recently used node.
	// The head's next is the secondly recently used node.
	// The head's prev is the least recently used node.
	private Node head;
	
	private int capacity;
	
    public LRUCache(int capacity) {
    	cache = new HashMap<Integer, LRUCache.Node>(capacity*2);
    	this.capacity= capacity; 
    }
    
    // if we hit a value we set it as the head 
    public int get(int key) {
    	if(cache.containsKey(key)) {
    		Node node = cache.get(key);
    		if(node != head) {
	    		removeFromList(node);
	    		setAsHead(node);
    		}
    		return node.value;
    	}else
    		return -1;
    }
    
    public void put(int key, int value) {
    	if(cache.containsKey(key)) {
    		// update value
    		Node node = cache.get(key);
    		node.value = value;
    		if(node != head){
    			removeFromList(node);
    			setAsHead(node);
    		}
    	}else {
    		// add a new node
    		if(cache.size() < capacity) {
    			Node node = new Node(key, value);
    			cache.put(key, node);
    			setAsHead(node);
    		}else {
    			Node node = new Node(key, value);
    			cache.put(key, node);
    			cache.remove(head.prev.key);
    			
    			// The head's prev is the least recently used node
    			// so remove it from the list.
    			removeFromList(head.prev);
    			setAsHead(node);
    		}
    	}
    }
    
    // remove this node from the list
    private void removeFromList(Node node) {
    	//In this case there is only one node in the list
    	//which is the node passed in.
    	if(node.next == node && node.prev == node) {
    		head = null;
    		return;
    	}
    	
    	// Let the prev node of this node directly point to next node of this node
    	if(node.prev != null)
    		node.prev.next = node.next;
    	
    	// Let the next node of this node directly point to prev node of this node.
    	if(node.next != null)
    		node.next.prev = node.prev;
    	node.next = null;
    	node.prev = null;	
    }
    
    // Set the node as the head of the list.
    private void setAsHead(Node node) {
    	// when there isn't any node in the list.
    	if(head == null) {
    		head = node;
    		head.prev = head;
    		head.next = head;
    	}else {
    		node.next = head;
    		node.prev = head.prev;
    		head.prev = node;
    		node.prev.next = node;
    		head = node;
    	}
    }
}
