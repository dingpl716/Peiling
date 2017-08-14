package string_array;

import java.util.HashSet;
import java.util.Set;

//	Given an array of integers and an integer k, find out whether there are 
//	two distinct indices i and j in the array such that nums[i] = nums[j] 
//	and the absolute difference between i and j is at most k.
// 找到两个index，i和j,要求 i j相距最多为k， 且nums[i] = num[j]

/**
 * 核心思想，用一个set来维护这个size等于k的window
 * 参照 MaximalSlidingWindow, 在那里面我们用了一个stack来维护这个window
 * @author Dingp
 *
 */

public class ContainsDuplicateII {
	
    public boolean containsNearbyDuplicate(int[] nums, int k) {
    	if (nums == null || nums.length < 2 || k == 0) {
    		return false;
    	}
    	
    	Set<Integer> set = new HashSet<Integer>();
    	
    	for (int i = 0; i < nums.length; ++i) {
    		if (i > k) {
    			set.remove(nums[i - k - 1]);
    		}
    		
    		if (!set.add(nums[i])){
    			return true;
    		}
    	}
    	
    	return false;
    }
}
