package design;

import java.util.HashMap;
import java.util.Map;

//	Design and implement a data structure for Least Frequently Used (LFU) cache. 
//	It should support the following operations: get and put.
//	
//	get(key) - Get the value (will always be positive) of the key 
//	if the key exists in the cache, otherwise return -1.
//	put(key, value) - Set or insert the value if the key is not 
//	already present. When the cache reaches its capacity, it should 
//	invalidate the least frequently used item before inserting a
//	new item. For the purpose of this problem, when there is a 
//	tie (i.e., two or more keys that have the same frequency), 
//	the least recently used key would be evicted.
//	
//	Follow up:
//	Could you do both operations in O(1) time complexity?
//	
//	Example:
//	
//	LFUCache cache = new LFUCache( 2 /* capacity */ );
//	cache.put(1, 1);
//	cache.put(2, 2);
//	cache.get(1);       // returns 1
//	cache.put(3, 3);    // evicts key 2
//	cache.get(2);       // returns -1 (not found)
//	cache.get(3);       // returns 3.
//	cache.put(4, 4);    // evicts key 1.
//	cache.get(1);       // returns -1 (not found)
//	cache.get(3);       // returns 3
//	cache.get(4);       // returns 4

/**
 * 核心思想: 按访问次数，对LRU分层
 * 1.首先我们要建立一个frequencyMap。frequencyMap的key是访问次数, value是LRU里面的那种双端循环队列
 * 2.刚put进来的元素，访问次数是1，之后每get或者put一次访问次数就加1
 * 3.然后我们需要一个dataMap，dataMap包含所有的数据，并且用来看是否reach capacity了
 * 4.每次get的时候，我们首先从dataMap里面取出对应的node，然后根据node的frequency，找到其在frequencyMap
 *   里面的位置，然后就是像LRU里面那样进行更改。
 *   
 * FreqMap的结构如下:
 * 频率      [key,value]
 *  1 -> [11,26]  <--------- leastFrequent
 *          |
 *  6 -> [4,22] <-> [6,19]
 *          |
 *  8 -> [2,2]  <-> [1,20] 
 *          |
 *  9 -> [10,9] 
 *          |
 *  12 -> [5,2] 
 * @author Dingp
 *
 */
public class LFUCache {
	
	// 数据节点, 这是一条循环双向链
	private static class Node {
		int key;
		int val;
		
		// 代表这个点被访问的次数
		int frequency;
		Node pre = null;
		Node next = null;
		
		private Node(int key, int val) {
			this.key = key;
			this.val = val;
			this.frequency = 1;
		}
	}
	
	// frequency节点, 每一个节点都包含一条与之frequency相对应的循环双向数据链
	// frequency节点本身是一个双向链，但是不循环！！！
	private static class FreqNode{
		int freq;
		
		// 数据链的头结点，也就是最近一次被访问的节点，
		// head.pre 是最早一次被访问的
		Node head = null;
		FreqNode pre = null;
		FreqNode next = null;
		
		private FreqNode(int freq) {
			this.freq = freq;
		}
	}

	// key是put，get方法中的key
	private Map<Integer, Node> dataMap = null;
	
	// key是frequency
	private Map<Integer, FreqNode> freqMap = null;
	
	// 指向frequency最小的那一层
	private FreqNode leastFrequentLevel = null;
	
	int capacity;
	
    public LFUCache(int capacity) {
    	dataMap = new HashMap<Integer, Node>(capacity * 2);
    	freqMap = new HashMap<Integer, FreqNode>(capacity * 2);
    	this.capacity = capacity;
    }
    
    public int get(int key) {
        
    	Node target = getNode(key);
    	
    	return target == null ? -1 : target.val;
    }
    
    public void put(int key, int value) {
    	if (capacity <=0 ) {
    		return;
    	}
    	
    	// 如果cache里面已经有这个key了，我们只需要跟新这个key
    	// 所对应的节点的value，并且更新它的frequency
    	if (dataMap.containsKey(key)) {
        	Node node = getNode(key);
        	node.val = value;
        	
        	return;
        }
    	
    	// 从这开始做真正的insert
    	// 如果越界，从min所指向的freq里面删除一个点
        if (dataMap.size() >= capacity && this.leastFrequentLevel != null) {
        	Node nodeToRemove = this.leastFrequentLevel.head.pre;
        	removeFromLevel(this.leastFrequentLevel, nodeToRemove);
        	
        	// 别忘了再dataMap里也要删除这个点
        	dataMap.remove(nodeToRemove.key);
        	
        	if (this.leastFrequentLevel.head == null) {
        		removeEmptyLevel(this.leastFrequentLevel);
        	}
        }
        
        // 新建一个点，放到dataMap里面去
        Node newNode = new Node(key, value);
        dataMap.put(key, newNode);
        
        // 把这个点放到freqMap里面
        insertNewNode(newNode);
    }
    
    /**
     * 尝试通过一个key读取一个数据节点，
     * 如果存在这个数据节点，那么久把节点调到下面一个frequency里，
     * 因为我们增加了一次这个点的访问。
     * @param key
     * @return
     */
    private Node getNode(int key) {
    	if (!dataMap.containsKey(key)) {
        	return null;
        }
        
        // 1. 把要读取的node找到
    	Node target = dataMap.get(key);
    	
    	// 2. 把target对应的这一层找出来
    	FreqNode level = freqMap.get(target.frequency);
    	
    	// 3. 把target从目前的这一层移动到下一层
    	moveDown(level, target);
    	
    	return target;
    }
    
    /**
     * 把target，挪动到currentLevel的下一层
     * @param currentLevel
     * @param target
     */
    private void moveDown(FreqNode currentLevel, Node target) {
    	// 1. 将target从currentLevel删除
    	currentLevel = removeFromLevel(currentLevel, target);
    	
    	// 2. 增加target的访问次数
    	target.frequency += 1;
    	
    	// 3.如果currentLevel没有下一层，或者下一层对应的frequency大于target，
    	// 那么我们需要新建一层，并且把currentLevel和nextLevel连接起来
    	if (currentLevel.next == null || currentLevel.next.freq > target.frequency) {
    		createNewLevel(currentLevel);
    	} 
    	
    	// 把这个target放到下一层去
    	setAsHead(currentLevel.next, target);
    	
    	// 4.如果当前层没有元素了，那么删除这一层
    	if(currentLevel.head == null) {
    		removeEmptyLevel(currentLevel);
    	}
    }
    
    /**
     * 把target从freq的这一层中删除, 并返回freq
     * @param freq
     * @param target
     */
    private FreqNode removeFromLevel(FreqNode freq, Node target) {
    	if (freq == null || freq.head == null) {
    		return freq;
    	}
    	
    	// 当这一层只有一个元素的时候
    	if (freq.head.next == freq.head && freq.head.pre == freq.head) {
    		freq.head = null;
    		return freq;
    	}
    	
    	Node newHead = freq.head;
    	
    	// 如果要删除的target正好是head
    	if (freq.head == target) {
    		newHead = freq.head.next;
    	}
    	
		target.next.pre = target.pre;
		target.pre.next = target.next;
		target.next = null;
		target.pre = null;
    	
    	freq.head = newHead;
    	return freq;
    }
    
    /**
     * 把target加到freq所包含的链的最前面
     * @param freq
     * @param target
     * @return
     */
    private void setAsHead(FreqNode freq, Node target) {
    	if (freq.head == null) {
    		target.next = target;
    		target.pre = target;
    	} else {
    		target.next = freq.head;
    		target.pre = freq.head.pre;
    		
    		freq.head.pre.next = target;
    		freq.head.pre = target;
    	}
    	
    	freq.head = target;
    }

    /**
     * 把currentLevel这一层删除
     * @param freq
     */
    private void removeEmptyLevel(FreqNode currentLevel) {
    	
    	// 如果这一层正好是min指向的那一层，那么我们需要先将min移动到下一层
		if (this.leastFrequentLevel == currentLevel) {
			this.leastFrequentLevel = currentLevel.next;
		}
		
		if (currentLevel.pre != null) {
			currentLevel.pre.next = currentLevel.next;
		}
		
		if (currentLevel.next != null) {
			currentLevel.next.pre = currentLevel.pre;
		}
		
		currentLevel.pre = null;
		currentLevel.next = null;
		
		this.freqMap.remove(currentLevel.freq);
    }
    
    /**
     * 在currentLevel下，增加一层level，新的level的frequency比currentLevel的大1
     * @param currentLevel
     * @return
     */
    private FreqNode createNewLevel(FreqNode currentLevel) {
    	FreqNode newLevel = new FreqNode(currentLevel.freq + 1);
    	
    	// 记住这一条链一定要改！！！！！！！！！
    	if (currentLevel.next != null) {
    		currentLevel.next.pre = newLevel;
    	}
    	
		newLevel.pre = currentLevel;
		newLevel.next = currentLevel.next;
		currentLevel.next = newLevel;
		
		this.freqMap.put(newLevel.freq, newLevel);
		return newLevel;
    }

    /**
     * 把newNode放到freqMap里去
     * @param newNode
     */
    private void insertNewNode(Node newNode) {
    	FreqNode level = null;
    	
    	if (freqMap.containsKey(newNode.frequency)) {
    		level = freqMap.get(newNode.frequency);
    	} else {
    		// 如果在freqMap里面找不到这一层，说明freqMap里面的所有层的frequency都大于当前target的frequency
    		// 当前target的frequency为1
    		// 此时我们需要新建一层，frequency为0的level, 并把这一层作为最小层
    		level = new FreqNode(newNode.frequency);
    		level.next = leastFrequentLevel;
    		if (leastFrequentLevel != null) {
    			leastFrequentLevel.pre = level;
    		}
    		leastFrequentLevel = level;
    		freqMap.put(level.freq, level);
    	}
    	
    	setAsHead(level, newNode);
    }

    public void print() {
    	
    	FreqNode levelRunner = this.leastFrequentLevel;
    	while (levelRunner != null) {
    		printLevel(levelRunner);
    		levelRunner = levelRunner.next;
    	}
    }
    
    private void printLevel(FreqNode level) {
    	StringBuilder sb = new StringBuilder();
    	sb.append(level.freq + " -> " + "[" + level.head.key + "," + level.head.val + "," + level.head.frequency + "]" + " ");
    	Node runner = level.head.next;
    	while (runner != level.head) {
    		sb.append("[" + runner.key + "," + runner.val + "," + runner.frequency + "]" + " ");
    		runner = runner.next;
    	}
    	
    	System.out.println(sb.toString());
    }
    
    public static void main(String[] args) {
    	LFUCache c = new LFUCache(10);
    	
    	String[] commands = {"put","put","put","put","put","get","put","get","get","put","get","put","put","put","get","put","get","get","get","get","put","put","get","get","get","put","put","get","put","get","put","get","get","get","put","put","put","get","put","get","get","put","put","get","put","put","put","put","get","put","put","get","put","put","get","put","put","put","put","put","get","put","put","get","put","get","get","get","put","get","get","put","put","put","put","get","put","put","put","put","get","get","get","put","put","put","get","put","put","put","get","put","put","put","get","get","get","put","put","put","put","get","put","put","put","put","put","put","put"};
    	int[][] operators = {{10,13},{3,17},{6,11},{10,5},{9,10},{13},{2,19},{2},{3},{5,25},{8},{9,22},{5,5},{1,30},{11},{9,12},{7},{5},{8},{9},{4,30},{9,3},{9},{10},{10},{6,14},{3,1},{3},{10,11},{8},{2,14},{1},{5},{4},{11,4},{12,24},{5,18},{13},{7,23},{8},{12},{3,27},{2,12},{5},{2,9},{13,4},{8,18},{1,7},{6},{9,29},{8,21},{5},{6,30},{1,12},{10},{4,15},{7,22},{11,26},{8,17},{9,29},{5},{3,4},{11,30},{12},{4,29},{3},{9},{6},{3,4},{1},{10},{3,29},{10,28},{1,20},{11,13},{3},{3,12},{3,8},{10,9},{3,26},{8},{7},{5},{13,17},{2,27},{11,15},{12},{9,19},{2,15},{3,16},{1},{12,17},{9,1},{6,19},{4},{5},{5},{8,1},{11,7},{5,2},{9,28},{1},{2,2},{7,4},{4,22},{7,24},{9,26},{13,28},{11,26}};
    	
    	for(int i = 0; i < commands.length; ++i) {
    		System.out.println("iteration : " + i);
    		if (i == 51) {
    			System.out.println("wrong");
    		}
    		if (commands[i].equals("put")) {
    			System.out.println("put " + operators[i][0] + " " + operators[i][1]);
    			c.put(operators[i][0], operators[i][1]);
    		}else {
    			System.out.println("get " + operators[i][0]);
    			c.get(operators[i][0]);
    		}
    		c.print();
    		System.out.println("==================================================");
    	}
    	
    }
}
