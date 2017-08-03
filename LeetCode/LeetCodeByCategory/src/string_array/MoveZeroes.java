package string_array;

//	Given an array nums, write a function to move all 0's to the end
//	of it while maintaining the relative order of the non-zero elements.
//	
//	For example, given nums = [0, 1, 0, 3, 12], after calling your 
//	function, nums should be [1, 3, 12, 0, 0].
//	
//	Note:
//	You must do this in-place without making a copy of the array.
//	Minimize the total number of operations.

public class MoveZeroes {

	/**
	 * 核心思想，
	 * 用一个index来标识现在不为0的数应该放在哪，
	 * 然后往后找到不为0的数，放到之前标识的那个位置
	 * 然后把这个位置置0.
	 * @param nums
	 */
	public void moveZeroes(int[] nums) {
        if (nums == null || nums.length == 0){
        	return;
        }
        
        // 不为0的数应该放置的坐标
        int nonZero = 0;
        
        // 找到第一个0所在的位置，之后我们会把它之后的
        // 不为0的数放到这上面来
        while(nonZero < nums.length) {
        	if (nums[nonZero] != 0) {
        		++nonZero;
        	} else {
        		break;
        	}
        }
        
        // 如果整个数组都不为0,那么直接退出了
        if (nonZero >= nums.length) {
        	return;
        }
        
        for (int i = 0; i < nums.length; ++i) {
        	if (i <= nonZero) {
        		continue;
        	}
        	
        	if (nums[i] != 0) {
        		nums[nonZero] = nums[i];
        		nums[i] = 0;
        		++nonZero;
        	}
        }
    }
}
