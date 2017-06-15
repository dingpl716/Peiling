package twoPointers;

//	Given an array of n positive integers and a positive integer s, 
//	find the minimal length of a contiguous subarray of which the sum ≥ s. 
//	If there isn't one, return 0 instead.
//	
//	For example, given the array [2,3,1,2,4,3] and s = 7,
//	the subarray [4,3] has the minimal length under the problem constraint.

/**
 * 双指针，j在前，i在后。用一个数来记录currentSum，如果currentSum大于等于s了，那么就是
 * 一个潜在的答案，比较currentLength和j-i。 j是exclusive的
 * 如果currentSum >= s 那么 ++i， 否则++j
 * @author peding
 *
 */
public class MinimumSizeSubarraySum {

    public int minSubArrayLen(int s, int[] nums) {
        if (nums == null || nums.length == 0) {
        	return 0;
        }
        
        int i = 0;
        int j = i;
        int currentSum = 0;
        int currentLength = nums.length + 1;
        
        while (j < nums.length){
        	while (j < nums.length && currentSum < s){
        		currentSum += nums[j++];
        	}
        	
        	if (currentSum >= s){
        		currentLength = Math.min(currentLength, j - i);
        	}
        	
        	while (i < j && currentSum >= s){
        		currentSum -= nums[i++];
        		if (currentSum >= s){
        			currentLength = Math.min(currentLength, j - i);
        		}
        	}
        	
        	if (currentSum >= s){
    			currentLength = Math.min(currentLength, j - i);
    		}
        	
        }
        
        return currentLength > nums.length ? 0 : currentLength;
    }
}
