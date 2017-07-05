package binarySearch;

//Suppose a sorted array is rotated at some pivot unknown to you beforehand.
//
//(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
//
//Find the minimum element.
//
//You may assume no duplicate exists in the array.

// 1. 最小值实际是一个断点， 需要找的是nums[i]<nums[i-1]里的i
// 2. 用binary search逼近
// 3. 如果中间值大于右边的值，那么断点在右边
// 4. 如果中间值小于左边的值，那么断点在左边
// 5. 一定要往无序的一边靠拢。
public class FindMinimumInRotatedSortedArray {

    public int findMin(int[] nums) {
    	if (nums.length == 0){
    		return Integer.MIN_VALUE;
    	}
    	
    	if (nums.length == 1){
    		return nums[0];
    	}
    	
    	int left = 0;
    	int right = nums.length - 1;
    	while (left < right){
    		// 用middle来寻找最大值，
    		// 永远到不了right， 所以middle + 1 是安全的，不会越界。
    		int middle = (left + right) / 2; 
    		if (nums[middle] > nums[middle + 1]) {
    			return nums[middle + 1];
    		}
    		
    		// 只有当断点在右边的时候，往右边逼近
    		else if (nums[middle] > nums[right]){
    			left = middle + 1;
    			continue;
    		}
    		
    		// 其余情况全部往左边逼近,可能情况:
    		// 1.(nums[middle] < nums[left])， 断点在左边
    		// 2. nums[middle] > nums[left], 数组没rotate, 左边是较小的一边
    		else {
    			right = middle;
    			continue;
    		}
    	}
    	
    	return nums[left];
    }
    
	public static void main(String[] args){
		int[] nums1 = new int[]{2, 1};
		int[] nums2 = new int[]{3, 4, 1, 2,};
		int[] nums3 = new int[]{4, 1, 2, 3};
		int[] nums4 = new int[]{2, 3, 4, 1};
		int[] nums5 = new int[]{1, 2, 3, 4};
		int[] nums6 = new int[]{4, 3, 2, 1};
		
		FindMinimumInRotatedSortedArray instance = new FindMinimumInRotatedSortedArray();
		System.out.println(instance.findMin(nums1));
		System.out.println(instance.findMin(nums2));
		System.out.println(instance.findMin(nums3));
		System.out.println(instance.findMin(nums4));
		System.out.println(instance.findMin(nums5));
	}

}
