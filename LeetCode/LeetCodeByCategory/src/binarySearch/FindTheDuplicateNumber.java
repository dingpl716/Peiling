package binarySearch;

//	Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), 
//	prove that at least one duplicate number must exist. 
//	Assume that there is only one duplicate number, find the duplicate one.
//	
//	Note:
//	You must not modify the array (assume the array is read only).
//	You must use only constant, O(1) extra space.
//	Your runtime complexity should be less than O(n2).
//	There is only one duplicate number in the array, but it could be repeated more than once.

/**
 * 核心思想： 二分查找
 * 对于一个数组，假设其值是从1到k的话，
 * 小于等于m的数一定 <= m 个，
 * 所以根据这个条件来判断现在的middle到底是在duplicate的左边还是右边
 * 
 * @author peding
 *
 */
public class FindTheDuplicateNumber {

    public int findDuplicate(int[] nums) {
        if (nums == null || nums.length == 0){
        	return 0;
        }
        
        if (nums.length == 1 || nums.length == 2){
        	return nums[0];
        }
        
        int left = 1;
        int right = nums.length - 1; // The max value of the array.
        
        
        while (left < right){
        	int middle = (left + right) / 2;
        	int counts = countNumberNoLargerThanM(nums, middle);
        	if (counts > middle) {
        		right = middle;
        	}
        	else {
        		left = middle + 1;
        	}
        	
        }
        
        return left;
    }
    
    /**
     * Counts the numbers in the array, which are either smaller than or larger than middle.
     * @param nums
     * @param middle
     * @return 
     */
    private int countNumberNoLargerThanM(int[] nums, int middle){
    	int result = 0;
    	
    	for (int number : nums) {
    		if (number <= middle) {
    			++result;
    		}
    	}
    	
    	return result;
    }
}
