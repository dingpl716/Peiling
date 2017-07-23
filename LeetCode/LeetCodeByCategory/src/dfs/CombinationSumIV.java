package dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;

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
//  target = 1
//  (1) -- 1的个数
//  target = 2
//	(1, 1) -- 
//  (2) -- 2的个数
//  target = 3
//	(1, 1, 1)
//	(1, 2) 
//	(2, 1)
//	(3) -- 3 的个数
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
		int resultdp = cs.combinationSumDP(nums, target);
		
		System.out.println(result);
		System.out.println(resultdp);
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
		result = 0;
		
		if (nums != null){
			Arrays.sort(nums);
			permutation(nums, target, 0);
		}
		
		return result;
    }
	
	private void permutation(int[] nums, int target, int currentSum) {
		
		if (currentSum == target){
    		++result;
    		return;
		}
		
		for (int i = 0; i < nums.length  && currentSum + nums[i] <= target; ++i){
			permutation(nums, target, currentSum + nums[i]);
		}
	}

	/**
	 * num = [1, 2, 3]
	 * target = 4
	 * 
	 * 设S[t]是当target是t的时候解法的数量，那么
	 * S[t] = sum(S[k]) + T, 其中 0 < k < t
	 * 	并且      S[k] = 0 if t - k 不在array里面
	 *        T = 1, if t 在array里面，else 0 
	 * S[0] = 0, 因为全是正数
	 * S[1] = 1 if 1 is in the arry, other wise 0
	 * S[2] = S[1] + S[2], if 2 is in the array,
	 * S[3] = S[1] + S[2] + S[3], if 3 is in the array
	 * 
	 * 这个问题和word break非常像，跟找零钱，跳台阶是一个道理。
	 * @param nums
	 * @param target
	 * @return
	 */
	public int combinationSumDP(int[] nums, int target) {
		
		// 用一个数组sum来标记每一个target的解法数量
		int[] sum = new int[target + 1];
		for (int i = 0; i < sum.length; ++i){
			sum[i] = 0;
		}
		
		HashSet<Integer> set = convertToSet(nums);
				
		for (int t = 1; t <= target; ++t){
			// S[t]
			int sumt = 0;
			
			// 把S[1]到s[t-1]的结果统计起来
			for (int k = 1; k < t; ++k){
				if (set.contains(t - k)){
					sumt += sum[k];
				}
			}
			
			// 如果t本身也在数组里面的话，那么在加1
			if (set.contains(t)){
				sumt += 1;
			}
			
			sum[t] = sumt;
		}
		
		return sum[target];
	}
	
	private HashSet<Integer> convertToSet(int[] nums){
		HashSet<Integer> set = new HashSet<Integer>();
		for (int i = 0; i < nums.length; ++i){
			set.add(nums[i]);
		}
		
		return set;
	}
}
