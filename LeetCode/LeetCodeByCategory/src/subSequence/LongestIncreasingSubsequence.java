package subSequence;

//	Given an unsorted array of integers, find the length of longest increasing subsequence.
//	
//	For example,
//	Given [10, 9, 2, 5, 3, 7, 101, 18],
//	The longest increasing subsequence is [2, 3, 7, 101], therefore the length is 4. 
//	Note that there may be more than one LIS combination, it is only necessary for you to return the length.
//	
//	Your algorithm should run in O(n2) complexity.
//	
//	Follow up: Could you improve it to O(n log n) time complexity?
		
/**
 * 创建一个数组 tail, 长度为n. tail[i]表示当LIS的长度为i时，其末尾子串的值，这个值要尽可能的小。
 * 然后用一个数len来记录目前的LIS的长度
 * 从头到尾依次扫描数组nums，当扫描到第i位的时候我们可能需要做两件事情
 * 1. 当nums[i] > tail[len]的时候，说明此时的number大于最长LIS的末尾，那么LIS此时应该被扩展
 * 2. 当nums[i] < tail[len]的时候，说明此时的number小于最长LIS的末尾，那么我们需要跟新tail的情况。
 *    我们此时需要找到tail里面第一个大于nums[i]的元素，并且把nums[i]赋予它。 这一步的意义在于保持
 *    tail[i]的值一定是当LIS的length为i时，LIS的最后一个元素的取值是尽可能小的。
 *    因为tail是一个从左至右有序的数组，所以我们可以用binary search找到这个第一个大于nums[i]的值
 * 
 * @author Dingp
 *
 */
public class LongestIncreasingSubsequence {

	public int lengthOfLIS(int[] nums) {
    
		if (nums == null || nums.length == 0) {
			return 0;
		}
		
		int[] tail = new int[nums.length];
		tail[0] = nums[0];
		int result = 1;

		for (int i = 1; i < nums.length; ++i){
			if (nums[i] > tail[result - 1]) {
				tail[result] = nums[i];
				++result;
			}
			else {
				int pos = getThePositionInTail(tail, nums[i], result);
				tail[pos] = nums[i];
			}
		}
		return result;
    }
	
	/**
	 * 在tail数组中，在0到length-1的范围内找出第一个大于target的值，并返回其坐标
	 * @param tail
	 * @param target
	 * @param length
	 * @return
	 */
	private int getThePositionInTail(int[] tail, int target, int length){
		int left = 0;
		int right = length - 1;
		while (left < right){
			int mid = (left + right) / 2;
			if (tail[mid] >= target) {
				right = mid;
			}
			else {
				left = mid + 1;
			}
		}
		
		return left;
	}
}
