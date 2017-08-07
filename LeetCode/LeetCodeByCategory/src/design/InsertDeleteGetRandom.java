package design;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

//	Design a data structure that supports all following operations in average O(1) time.
//	
//	insert(val): Inserts an item val to the set if not already present.
//	remove(val): Removes an item val from the set if present.
//	getRandom: Returns a random element from current set of elements. Each element must have the same probability of being returned.
//	Example:
//	
//	// Init an empty set.
//	RandomizedSet randomSet = new RandomizedSet();
//	
//	// Inserts 1 to the set. Returns true as 1 was inserted successfully.
//	randomSet.insert(1);
//	
//	// Returns false as 2 does not exist in the set.
//	randomSet.remove(2);
//	
//	// Inserts 2 to the set, returns true. Set now contains [1,2].
//	randomSet.insert(2);
//	
//	// getRandom should return either 1 or 2 randomly.
//	randomSet.getRandom();
//	
//	// Removes 1 from the set, returns true. Set now contains [2].
//	randomSet.remove(1);
//	
//	// 2 was already in the set, so return false.
//	randomSet.insert(2);
//	
//	// Since 2 is the only number in the set, getRandom always return 2.
//	randomSet.getRandom();

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */

/**
 * 核心思想:
 * 1. 要实现O(1)的getRandom， 那么久必须要求我们能够直接，随机的访问一个集合中的任意元素，那么就必须要用使用array
 * 2. 用一个map来记录这个array中的数：number -> index。map的key是array元素的值，map的value是array元素的index
 * 3. remove的时候把array中最后一个元素直接放到要删除的那个元素的index上，然后删除array的最后一个，并且从map中把数删除
 * @author Dingp
 *
 */
public class InsertDeleteGetRandom {
	
	private ArrayList<Integer> list = null;
	
	// key是list的value, value是list的index
	private HashMap<Integer, Integer> map = null;
	private Random random = null;
	
    /** Initialize your data structure here. */
    public InsertDeleteGetRandom() {
    	list = new ArrayList<Integer>();
    	map = new HashMap<Integer, Integer>();
    	random = new Random();
    }
    
    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if (!map.containsKey(val)) {
        	list.add(val);
        	map.put(val, list.size() - 1);
        	return true;
        } else {
        	return false;
        }
    }
    
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if (!map.containsKey(val)) {
        	return false;
        } else {
        	// 找到待删除的数的index
        	int indexToRemove = map.get(val);
        	int lastVal = list.get(list.size() - 1);
        	
        	// 把list最后面一个数直接放到要删除的index上
        	list.set(indexToRemove, lastVal);
        	
        	// 把list最后一个元素删除，把map中对应的值删除
        	list.remove(list.size() - 1);
        	map.remove(val);
        	
        	// 更新最后一个元素的index
        	map.replace(lastVal, indexToRemove);
        	return true;
        }
    }
    
    /** Get a random element from the set. */
    public int getRandom() {
    	if (list.isEmpty()) {
    		return 0;
    	} else {
    		return list.get(random.nextInt(list.size()));
    	}
    }
}
