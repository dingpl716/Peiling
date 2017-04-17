package dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//	Given an integer array with all positive numbers and no duplicates, 
//  find the number of possible combinations that add up to a positive integer target.
//	
//	Example:
//	
//	nums = [1, 2, 3]
//	target = 4
//	
//	The possible combination ways are:
//	(1, 1, 1, 1)
//	(1, 1, 2)
//	(1, 2, 1)
//	(1, 3)
//	(2, 1, 1)
//	(2, 2)
//	(3, 1)
//	
//	Note that different sequences are counted as different combinations.
//	
//	Therefore the output is 7.
//	Follow up:
//	What if negative numbers are allowed in the given array?
//	How does it change the problem?
//	What limitation we need to add to the question to allow negative numbers?

public class CombinationSumIV {
    
	public static void main(String[] args) {
		int[] nums = new int[] {1, 2, 3};
		int target = 32;
		CombinationSumIV cs = new CombinationSumIV();
		int result = cs.combinationSum4(nums, target);
		
		System.out.println(result);
	}
	
	private int result = 0;
	
	/**
     * 从nums里面找一些数，要求和为target。 返回有多少种可能性，这是一个全排列问题
     * 也就是说不同的顺序认为是不同的
     * @param nums 没有重复数字，数字可以重复使用，数字全为正
     * @param target
     * @return
     */
	public int combinationSum4(int[] nums, int target) {
		List<Integer> buff = new ArrayList<Integer>();
		
		result = 0;
		
		if (nums != null){
			Arrays.sort(nums);
			permutation(nums, target, 0, buff);
		}
		
		return result;
    }
	
	private void permutation(int[] nums, int target, 
			int currentSum,
			List<Integer> buff) {
		
		if (currentSum == target){
    		++result;
    		return;
		}
		
		for (int i = 0; i < nums.length  && currentSum + nums[i] <= target; ++i){
			buff.add(i);
			permutation(nums, target, currentSum + nums[i], buff);
			buff.remove(buff.size() - 1);
		}
	}
}
