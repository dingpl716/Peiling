package binarySearch;

//	Given an array which consists of non-negative integers and an integer m, 
//	you can split the array into m non-empty continuous subarrays. 
//	Write an algorithm to minimize the largest sum among these m subarrays.
//	
//	Note:
//	If n is the length of array, assume the following constraints are satisfied:
//	
//	1 ≤ n ≤ 1000
//	1 ≤ m ≤ min(50, n)
//	Examples:
//	
//	Input:
//	nums = [7,2,5,10,8]
//	m = 2
//	
//	Output:
//	18
//	
//	Explanation:
//	There are four ways to split nums into two subarrays.
//	The best way is to split it into [7,2,5] and [10,8],
//	where the largest sum among the two subarrays is only 18.

/**
 * 核心思想：
 * 1. 这个需要我们返回的largest sum是有一个界限的，它介于数组中最大的元素和数组所有元素之和之间: Max(nums) <= result <= Sum(nums)
 * 2. 根据以上条件，我们可以采用二分法查找。 left = Max(nums), right = Sum(nums), middle = (left + right) / 2
 * 3. 确定一个middle之后我们要干以下的事情
 *    3.1 将整个数组划分为n个子数组，并且每个子数组的和尽可能最大，但是又不能超过middle。
 *    3.2 如果此时n > m的话，表明我们划分的太细了，也就是说middle太小了， 我们需要 left = middle
 *    3.2 如果此时n < m的话
 * @author peding
 *
 */
public class SplitArrayLargestSum {
	
	public int splitArray(int[] nums, int m) {
        if (nums == null || nums.length == 0) {
        	return 0;
        }
        
        if (nums.length == 1){
        	return nums[0];
        }
        
        int maxNumber = Integer.MIN_VALUE;
        int sum = 0;
        for (int number : nums) {
        	maxNumber = Math.max(maxNumber, number);
        	sum += number;
        }
        
        int left = maxNumber;
        int right = sum;
        while(left < right){
        	int middle = (left + right) / 2;
        	int numberOfSubArrays = cutArrayNoLargerThan(nums, m, middle);
        	
        	// 如果此时的子数组个数 <= m，说明我们有可能已经找到正确的值了，
        	// 也有可能是现在的middle比正确的值大一点点，所以我们要继续缩小middle
        	// 但此时不能是middle - 1， 因为middle有可能是正确的值
        	if (numberOfSubArrays <= m) {
        		right = middle;
        	}
        	
        	// 如果子数组的个数大于m,说明划分的太细了，也就是说我们一定没有找到正确的值，
        	// 而且现在的middle太小了，因此才会划分的太细
        	else {
        		left = middle + 1;
        	}
        }
        
        return left;
    }
	
	/**
	 * Cut the array into n sub-arrays so that each sub-array's sum is 
	 * as big as possible, but is not larger than middle. Return the
	 * total number of sub-arrays.
	 * @param nums
	 * @param m The expected total number of sub-arrays.
	 * @param middle The max value that each sub-array's sum can be.
	 * @return
	 */
	private int cutArrayNoLargerThan(int[] nums, int m, int middle) {
		int count = 1;
		int sum = 0;
		
		for (int number : nums){
			sum += number;
			
			if (sum > middle) {
				sum = number;
				count++;
			}
		}
		
		return count;
	}

	public static void main(String[] args){
		SplitArrayLargestSum s = new SplitArrayLargestSum();
		int[] nums = new int[] {7, 2, 5, 10, 8};
		System.out.print(s.splitArray(nums, 2));
	}
}
