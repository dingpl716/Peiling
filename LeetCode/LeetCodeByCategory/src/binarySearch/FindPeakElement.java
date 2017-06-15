package binarySearch;

//	A peak element is an element that is greater than its neighbors.
//	
//	Given an input array where num[i] ≠ num[i+1], find a peak element and return its index.
//	
//	The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.
//	
//	You may imagine that num[-1] = num[n] = -∞.
//	
//	For example, in array [1, 2, 3, 1], 3 is a peak element and your function should return the index number 2.
		
public class FindPeakElement {
	
	public int findPeakElement(int[] nums) {
        if (nums == null || nums.length == 0) {
        	return 0;
        }
        
        if (nums.length == 1){
        	return 0;
        }
        
        int left = 0;
        int right = nums.length - 1;
        while(left < right){
        	int mid1 = (left + right) / 2;
        	int mid2 = mid1 + 1;
        	
        	// 此时mid2为可能的峰值，所以舍弃mid1的那一部分
        	if (nums[mid1] < nums[mid2]) {
        		left = mid2;
        	}
        	
        	// 此时mid1为可能的峰值，所以舍弃mid2的那一部分 
        	else {
        		right = mid1;
        	}
        }
        
        return left;
    }
}