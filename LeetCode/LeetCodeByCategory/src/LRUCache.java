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
	
	// һ��˫������������¼cache��ʹ�õ����
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
	
	// ���������������ݵĵط�
	private Map<Integer, Node> cache;
	// ���������������ݱ�ʹ�õ����
	// �ʼ�����һ���յģ�Ҳ����û���κ�cache��ʹ�ã�
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
    	//������nodeͷβ��ָ���Լ�����ô˵������cache�����ֻ����ôһ��node
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
    
    // �����node�ӵ���ͷ
    private void attach(Node node) {
    	// ���head��null�Ļ�����ô˵����������cache���ǿյ�
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
