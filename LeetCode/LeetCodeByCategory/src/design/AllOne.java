package design;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

//	Implement a data structure supporting the following operations:
//	
//	Inc(Key) - Inserts a new key with value 1. Or increments an existing key by 1. 
//	Key is guaranteed to be a non-empty string.
//	
//	Dec(Key) - If Key's value is 1, remove it from the data structure. 
//	Otherwise decrements an existing key by 1. If the key does not exist, 
//	this function does nothing. Key is guaranteed to be a non-empty string.
//	
//	GetMaxKey() - Returns one of the keys with maximal value. If no element exists, return an empty string "".
//	
//	GetMinKey() - Returns one of the keys with minimal value. If no element exists, return an empty string "".
//	
//	Challenge: Perform all these in O(1) time complexity.

/**
 * 核心思想，map加双向链表，用双向链表把map的value给连接起来。此题和LFU类似
 * map: String - > Node, Node 包含一个int value表示它对应的值，还包含所有值为此value的key
 * 
 * min的变化情况:
 * 1. 如果我们在map中新增了entry，那么min此时应该被无条件的设置我这个entry
 * 2. 如果min中此时的Keys全部被inc移除了，那么min = min.next
 * 
 * max的变化情况：
 * 1. 如果我们把map中已有的一条entry increase了很多次，且现在它的value > max, 那么max = 这个Node
 * 2. 如果max中此时的keys全部被dec给移除了，那么max = max.pre
 * 
 * Map的结构如下:
 * 
 * key1 |
 *      | -> Node(1, [key1, key2])     <- min 
 * key2 |      |
 *             |
 * key3   -> Node(2, [key3])
 *             | 
 * key4 |      |
 *      | -> Node(3, [key4, key5])     <- max
 * key5 |
 * 
 * @author Dingp
 *
 */
public class AllOne {

	private static class Node{
		int value;
		Set<String> keys = null;
		Node pre = null;
		Node next = null;
		
		private Node() {
			this(1);
		}
		
		private Node(int val) {
			value = val;
			keys = new HashSet<String>();
		}
		
		private void addKey(String key) {
			keys.add(key);
		}
		
		private void removeKey(String key) {
			if (keys.contains(key)){
				keys.remove(key);
			}
		}
		
		private String getFirst() {
			if (!keys.isEmpty()) {
				String result = null;
				for (String key : keys) {
					result = key;
					break;
				}
				
				return result;
			}
			
			return "";
		}
	}

	private Map<String, Node> map;
	
	private Node min;
	
	private Node max;
	
	/** Initialize your data structure here. */
    public AllOne() {
        map = new HashMap<String, Node>();
    }
    
    /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
    public void inc(String key) {
    	if (map.containsKey(key)) {
    		moveDown(key);
    	} else {
    		addKey(key);
    	}
    }
    
    /** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
    public void dec(String key) {
        if (map.containsKey(key)) {
        	Node node = map.get(key);
        	if (node.value > 1) {
        		moveUp(key);
        	} else {
        		removeKey(key);
        	}
        }
    }
    
    /**
     * 在这个data structure中新增一个之前不存在的key
     * @param key
     */
    private void addKey(String key) {
    	
    	Node newNode = null;
    	if (min == null) {
    		newNode = new Node();
    		min = newNode;
    	} else {
    		if (min.value == 1) {
    			newNode = min;
    		} else {
    			newNode = new Node();
    			newNode.next = min;
    			min.pre = newNode;
    			min = newNode;
    		}
    	}
    	
    	newNode.addKey(key);
    	
    	if (max == null) {
    		max = newNode;
    	}
    	
    	map.put(key, newNode);
    }
    
    /**
     * 在这个data structure中删除一个key
     * @param key
     */
    private void removeKey(String key) {
    	Node node = map.get(key);
    	node.removeKey(key);
    	
    	if (node.keys.isEmpty()) {
    		removeEmptyNode(node);
    	}
    	
    	map.remove(key);
    }
    
    /**
     * 将一个不包含任何key的node删除，同时update min 和 max
     * 因为node是要被删除的点，所以如果node == min 或者max的话就一定要移动min或者max
     * @param node
     */
    private void removeEmptyNode(Node node) {
    	if (node.next == null && node.pre == null) {
    		min = null;
    		max = null;
    		return;
    	}
    
    	// 因为node是要被删除的点，所以如果node == min 的话就一定要移动min
    	if (node == min ) {
    		if (node.pre != null) {
    			min = node.pre;
    		} else {
    			min = node.next;
    		}
    	}
    	
    	// 因为node是要被删除的点，所以如果node == max的话就一定要移动max
    	if (node == max) {
    		if (node.next != null) {
    			max = node.next;
    		} else {
    			max = node.pre;
    		}
    	}
    	
    	if (node.pre != null) {
    		node.pre.next = node.next;
    	}
    	
    	if (node.next != null) {
    		node.next.pre = node.pre;
    	}
    	
    	node.pre = null;
    	node.next = null;
    	
    	if (min != null) {
    		min.pre = null;
    	}
    	
    	if (max != null) {
    		max.next = null;
    	}
    }
    
    /**
     * 把一个key从当前node挪到下一层node上
     * @param key
     */
    private void moveDown(String key) {
    	// 把key从当前node中取出来
    	Node currentNode = map.get(key);
    	currentNode.removeKey(key);
    	
    	// 锁定下一层的node， 如果下一层的node不符合要求，那么要新建之
    	Node nextNode = currentNode.next;
    	if (nextNode == null || nextNode.value - currentNode.value > 1) {
    		nextNode = createNextNode(currentNode);
    	}
    	
    	// 把key加到下一层的node上去
    	nextNode.addKey(key);
    	map.replace(key, nextNode); // 千万不要忘了放回map里面去啊！！！！

    	if (currentNode.keys.isEmpty()) {
    		removeEmptyNode(currentNode);
    	}
    }
    
    /**
     * 把一个key从当前node挪到上一层node上
     * @param key
     */
    private void moveUp(String key) {
    	// 把key从当前node中取出来
    	Node currentNode = map.get(key);
    	currentNode.removeKey(key);
    	
    	// 锁定上一层的node， 如果下一层的node不符合要求，那么要新建之
    	Node previousNode = currentNode.pre;
    	if (previousNode == null || currentNode.value - previousNode.value > 1) {
    		previousNode = createPreNode(currentNode);
    	}
    	
    	// 把key加到上一层的node上去
    	previousNode.addKey(key);
    	map.replace(key, previousNode); // 千万不要忘了放回map里面去啊！！！！
    	
    	if (currentNode.keys.isEmpty()) {
    		removeEmptyNode(currentNode);
    	}
    	
    }
    
    /**
     * 在当前node的后面新建一个node
     * @param currentNode
     * @return
     */
    private Node createNextNode(Node currentNode) {
    	Node newNode = new Node(currentNode.value + 1);
    	
    	newNode.next = currentNode.next;
    	newNode.pre = currentNode;
    	
    	if (currentNode.next != null) {
    		currentNode.next.pre = newNode;
    	}
    	
    	currentNode.next = newNode;
    	
    	if (max == currentNode) {
    		max = newNode;
    	}
    	
    	return newNode;
    }
    
    /**
     * 在当前node的前面新建一个node
     * @param currentNode
     * @return
     */
    private Node createPreNode(Node currentNode) {
    	Node newNode = new Node(currentNode.value - 1);
    	
    	newNode.pre = currentNode.pre;
    	newNode.next = currentNode;
    	
    	if (currentNode.pre != null) {
    		currentNode.pre.next = newNode;
    	}
    	
    	currentNode.pre = newNode;
    	
    	if (min == currentNode) {
    		min = newNode;
    	}
    	
    	return newNode;
    }
    /** Returns one of the keys with maximal value. */
    public String getMaxKey() {
    	if (map.isEmpty() || max == null) {
    		return "";
    	} else {
    		return max.getFirst();
    	}
    }
    
    /** Returns one of the keys with Minimal value. */
    public String getMinKey() {
        if (map.isEmpty() || min == null) {
        	return "";
        } else {
        	return min.getFirst();
        }
    }

    public static void main(String[] args) {
    	AllOne a = new AllOne();
    	String[] commands = {"inc","inc","inc","inc","inc","inc","dec", "dec","getMinKey","dec","getMaxKey","getMinKey"};
    	String[] keys = {"a","b","b","c","c","c","b","b","a"};
    	
    	int j = 0;
    	for (int i = 0; i < commands.length; ++i) {
    		if (commands[i].equals("inc")) {
    			a.inc(keys[j++]);
    		} else if (commands[i].equals("dec")) {
    			a.dec(keys[j++]);
    		} else if (commands[i].equals("getMaxKey")) {
    			System.out.println(a.getMaxKey());
    		} else {
    			System.out.println(a.getMinKey());
    		}
    	}
    	
//    	a.inc("hello");
//    	a.inc("hello");
//    	a.inc("leet");
//    	System.out.println(a.getMaxKey());
//    	System.out.println(a.getMinKey());
    }
}
