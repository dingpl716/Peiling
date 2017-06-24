package dp;

//	Given an array of n integers where n > 1, nums, return an array output such that output[i] is 
//	equal to the product of all the elements of nums except nums[i].
//	
//	Solve it without division and in O(n).
//	
//	For example, given [1,2,3,4], return [24,12,8,6].
//	
//	Follow up:
//	Could you solve it with constant space complexity? 
//	(Note: The output array does not count as extra space for the purpose of space complexity analysis.)

/**
 * O(n) space 解法:
 * 先从左至右做乘法
 * 1		1
 * 1 2		2
 * 1 2 3	6
 * 1 2 3 4	24
 * 再从右至左做乘法
 * 4		4
 * 3 4		12
 * 2 3 4	24
 * 1 2 3 4	24
 * 
 * 然后要计算3的值时，只需 (1,2) * (4)
 * 
 * 
 * O(1) space 解法， DP，分别从左至右，从右至左做一遍
 * nums[i], res[i]
 * 从左至右 res[i] = res[i-1] * nums[i-1]
 * 从右至左 res[i] = res[i+1] * nums[i+1]
 * 
 * @author Dingp
 *
 */
public class ProductOfArrayExceptSelf {

}
