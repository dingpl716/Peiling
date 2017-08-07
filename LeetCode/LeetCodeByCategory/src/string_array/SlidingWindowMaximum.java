package string_array;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

//	Given an array nums, there is a sliding window of size k which is moving 
//	from the very left of the array to the very right. You can only see the k 
//	numbers in the window. Each time the sliding window moves right by one position.
//	
//	For example,
//	Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.
//	
//	Window position                Max
//	---------------               -----
//	[1  3  -1] -3  5  3  6  7       3
//	 1 [3  -1  -3] 5  3  6  7       3
//	 1  3 [-1  -3  5] 3  6  7       5
//	 1  3  -1 [-3  5  3] 6  7       5
//	 1  3  -1  -3 [5  3  6] 7       6
//	 1  3  -1  -3  5 [3  6  7]      7
//	Therefore, return the max sliding window as [3,3,5,5,6,7].
//	
//	Note: 
//	You may assume k is always valid, ie: 1 ≤ k ≤ input array's size for non-empty array.
//	
//	Follow up:
//	Could you solve it in linear time?

/**
 * 这道题和 largest rectangle in histogram中用一个递减的stack的思路有点像
 * 核心思想:
 * 1. 用一个双端队列来存储坐标
 * 2. i从0到n-1进行扫描
 * 3. 每次update i的时候， 我们都从队列的头部，也就是左边，把超出范围的元素弹出
 * 4. 并且在每次update i的时候，我们都从尾部把小于nums[i]的元素弹出，只保留比它大的元素，再从尾部插入nums[i]
 * @author Dingp
 *
 */
public class SlidingWindowMaximum {
	
	public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
        	return new int[0];
        }
        
        ArrayList<Integer> result = new ArrayList<Integer>(nums.length);
        
        int index = 0;
        int currentMax = Integer.MIN_VALUE;
        Deque<Integer> queue = new LinkedList<Integer>();
        
        while (index < nums.length) {
        	if (index >= k) {
        		while (!queue.isEmpty() && index - queue.peekFirst() >= k){
        			int tmp = queue.pollFirst();
        			if (nums[tmp] == currentMax) {
        				if (queue.isEmpty()) {
        					currentMax = Integer.MIN_VALUE;
        				} else {
        					currentMax = nums[queue.peekFirst()];
        				}
        			}
        		}
        	}
        	
        	while (!queue.isEmpty() && nums[queue.peekLast()] < nums[index]){
    			int tmp = queue.pollLast();
    			if (nums[tmp] == currentMax) {
    				if (queue.isEmpty()) {
    					currentMax = Integer.MIN_VALUE;
    				} else {
    					currentMax = nums[index];
    				}
    			}
    		}
        	
        	if (queue.isEmpty()) {
        		currentMax = nums[index];
        	}
        	
        	queue.addLast(index);
        	
        	if (index >= k - 1) {
        		result.add(currentMax);
        	}
        	
        	++index;
        }
        
        return toArray(result);
    }
	
	private int[] toArray(ArrayList<Integer> list) {
		int[] result = new int[list.size()];
		
		for (int i = 0; i < result.length; ++i) {
			result[i] = list.get(i);
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		SlidingWindowMaximum s = new SlidingWindowMaximum();
		
		int[] nums = new int[] {1, -1};
		int k = 1;
		s.maxSlidingWindow(nums, k);
	}
}
