import java.util.HashMap;
import java.util.Map;

//	Design and implement a data structure for Least Recently Used (LRU) cache. 
//	It should support the following operations: get and set.
//	
//	get(key) - Get the value (will always be positive) of the key 
//	if the key exists in the cache, otherwise return -1.
//	
//	set(key, value) - Set or insert the value if the key is not 
//	already present. When the cache reached its capacity, it should invalidate 
//	the least recently used item before inserting a new item.

public class LRUCache {
	
	// 一个双向链表，用来记录cache被使用的情况
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
	
	// 这个是用来存放数据的地方
	private Map<Integer, Node> cache;
	// 这个是用来存放数据被使用的情况
	// 最开始这个是一个空的，也就是没有任何cache被使用，
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
	    		detach(node);
	    		attach(node);
    		}
    		return node.value;
    	}else
    		return -1;
    }
    
    public void set(int key, int value) {
    	if(cache.containsKey(key)) {
    		// update value
    		Node node = cache.get(key);
    		node.value = value;
    		if(node != head){
    			detach(node);
    			attach(node);
    		}
    	}else {
    		// add a new node
    		if(cache.size() < capacity) {
    			Node node = new Node(key, value);
    			cache.put(key, node);
    			attach(node);
    		}else {
    			Node node = new Node(key, value);
    			cache.put(key, node);
    			cache.remove(head.prev.key);
    			detach(head.prev);
    			attach(node);
    		}
    	}
    }
    
    // remove this node from the list
    private void detach(Node node) {
    	//如果这个node头尾都指向自己，那么说明整个cache里面就只有这么一个node
    	if(node.next == node && node.prev == node) {
    		head = null;
    		return;
    	}
    	if(node.prev != null)
    		node.prev.next = node.next;
    	if(node.next != null)
    		node.next.prev = node.prev;
    	node.next = null;
    	node.prev = null;	
    }
    
    // 把这个node加到队头
    private void attach(Node node) {
    	// 如果head是null的话，那么说明现在整个cache都是空的
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
