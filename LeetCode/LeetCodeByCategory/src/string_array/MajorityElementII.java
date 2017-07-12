package string_array;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import util.Util;

//	Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times. 
//	The algorithm should run in linear time and in O(1) space.

/**
 * 核心思想:
 * 在MajorityElementII中我们要求出一个出现次数超过你n/2的数，我们用了一个计数器。
 * 那么在这到题中我们要找出出现次数超过n/3的数，那最多可能有两个，那么我们需要用两个计数器。
 * @author peding
 *
 */
public class MajorityElementII {
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> results = new ArrayList<Integer>();
        
        if (nums == null || nums.length == 0) {
        	return results;
        }
        
        if (nums.length == 1) {
        	results.add(nums[0]);
        	return results;
        }
        
        int candidate1 = nums[0];
        int count1 = 1;
        
        int indexOfCandidate2 = indexOfCandidate2(nums);
        if(indexOfCandidate2 == nums.length) {
        	results.add(candidate1);
        	return results;
        }
        
        int candidate2 = nums[indexOfCandidate2];
        int count2 = 1;
        
        for (int i = 1; i < nums.length; ++i) {
        	if (i == indexOfCandidate2) {
        		continue;
        	}
        	
        	int num = nums[i];
        	
        	if (num == candidate1) {
        		count1++;
        	}else if (num == candidate2) {
        		count2++;
        	}else if (count1 == 0) {
        		candidate1 = num;
        		count1 = 1;
        	}else if (count2 == 0) {
        		candidate2 = num;
        		count2 = 1;
        	} else {
        		count1--;
        		count2--;
        	}
        }
        
        if (countOf(nums, candidate1) > nums.length / 3) {
        	results.add(candidate1);
        }
        
        if (countOf(nums, candidate2) > nums.length / 3) {
        	results.add(candidate2);
        }
        
        return results;
    }

    private int indexOfCandidate2(int[] nums) {
    	for (int i = 1; i < nums.length; ++i) {
    		if (nums[i] != nums[0]) {
    			return i;
    		}
    	}
    	
    	return nums.length;
    }
    
    private int countOf(int[] nums, int target) {
    	int count = 0;
    	for (int num : nums) {
    		if (num == target) {
    			count++;
    		}
    	}
    	
    	return count;
    }
    public static void main(String[] args){
    	MajorityElementII m = new MajorityElementII();
    	Util.PrintList(m.majorityElement(new int[]{2, 3, 2}));
    }
}
