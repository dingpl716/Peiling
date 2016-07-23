package string_array;

//	Follow up for "Find Minimum in Rotated Sorted Array":
//	What if duplicates are allowed?
//	
//	Would this affect the run-time complexity? How and why?
//	Suppose a sorted array is rotated at some pivot unknown to you beforehand.
//	
//	(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
//	
//	Find the minimum element.
//	
//	The array may contain duplicates.

//	1. 有重复之后middle可以同时等于左右两边，所以不能再无脑的往左边逼近了
//	2. 因为这样时间复杂度降低了，最差会到O(N)
public class FindMinimumInRotatedSortedArrayII {
	
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
    		
    		// 网右逼近的条件:
    		// 1. nums[middle] > nums[right], 断点在右边
    		else if (nums[middle] > nums[right]){  // middle < right的话，这部分一定是有序的。
    			left = middle + 1;
    		}
    		
    		// 往左逼近条件:
    		// 1. nums[middle] < nums[left], 断点在左边
    		// 2. nums[middle] > nums[left], 数组没有rotate， 左边较小
    		else if ( nums[middle] < nums[left] ||
    				nums[middle] > nums[left] ){
    			right = middle;  // 因为middle永远到不了right, 所以right 不能等于middle - 1
    		}
    		
    		// 此时middle一定同时等于right和left
    		// 此时既可以往左也可以往右了。
    		// 因为我们返回的是left，所以用right来缩小规模， 这样可以
    		// 避免当left指向最小值的时候，移动left
    		else{
    			--right;
    		}
    	}
    	
    	return nums[left];
	}
	
	public static void main(String[] args){
		int[] nums1 = new int[]{2, 2, 2, 1};
		int[] nums11 = new int[]{2, 1, 1, 1};
		
		int[] nums2 = new int[]{3, 4, 1, 2,};
		int[] nums21 = new int[]{3, 4,4,4,4, 1, 2,};
		int[] nums3 = new int[]{4, 1, 2, 3};
		int[] nums31 = new int[]{4, 1,1,1,1,1, 2, 3};
		int[] nums4 = new int[]{2, 3, 4, 1};
		int[] nums5 = new int[]{1, 2, 3, 4};
		int[] nums51 = new int[]{1,1,1,1,1,1, 2, 3, 4};
		int[] nums6 = new int[]{4, 3, 2, 1};
		
		// 这种情况是在之前的条件里不会出现的
		int[] nums7 = new int[]{3,3,3,3,3,3,1,3};
		int[] nums8 = new int[]{3,1,3,3,3,3,3,3};
		
		FindMinimumInRotatedSortedArrayII instance = new FindMinimumInRotatedSortedArrayII();
		System.out.println(instance.findMin(nums1));
		System.out.println(instance.findMin(nums11));
		System.out.println(instance.findMin(nums2));
		System.out.println(instance.findMin(nums21));
		System.out.println(instance.findMin(nums3));
		System.out.println(instance.findMin(nums31));
		System.out.println(instance.findMin(nums4));
		System.out.println(instance.findMin(nums5));
		System.out.println(instance.findMin(nums51));
		System.out.println(instance.findMin(nums7));
		System.out.println(instance.findMin(nums8));
	}
}
