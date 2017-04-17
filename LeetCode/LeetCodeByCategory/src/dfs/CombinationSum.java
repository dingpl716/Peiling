package dfs;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Given a set of candidate numbers (C) (without duplicates) and a target number (T), 
// find all unique combinations in C where the candidate numbers sums to T.
//
// The same repeated number may be chosen from C unlimited number of times.
//
// Note:
// All numbers (including target) will be positive integers.
// The solution set must not contain duplicate combinations.
// For example, given candidate set [2, 3, 6, 7] and target 7,  
// A solution set is: 
//	[7] 
//	[2, 2, 3] 
/*
 * 条件:
 * C里面没有重复数字
 * C里面的数字能被重复使用
 * 
 */
public class CombinationSum {
	
	public List<List<Integer>> combinationSum(int[] candidates, int target) {
		List<List<Integer>> results = new ArrayList<List<Integer>>();
		List<Integer> buff = new ArrayList<Integer>();
		
		if (candidates != null){
			Arrays.sort(candidates);
			
			combinationSum(candidates, target, 0, 0, results, buff);
		}
		
		return results;
	}
	
	/**
	 * 选出一个数加到buff里面
	 * @param candidates
	 * @param target
	 * @param preLevelStart 上一层被选了的那个数的index
	 * @param currentSum 目前buff里面数的和
	 * @param results
	 * @param buff
	 */
	private void combinationSum(int[] candidates,
			int target,
			int preLevelStart,
			int currentSum,
			List<List<Integer>> results,
			List<Integer> buff){
		
		if (currentSum == target){
			results.add(new ArrayList<Integer>(buff));
			return;
		}
		
		// i是我们即将选出来的那个数, i的范围应该是[preLevelStart, candidates.length-1]
		// 剪枝条件：currentSum + candidates[i]
		for (int i = preLevelStart; i < candidates.length && currentSum + candidates[i] <= target; ++i){
			buff.add(candidates[i]);
			combinationSum(candidates, target, i, currentSum + candidates[i], results, buff);
			buff.remove(buff.size() - 1);
		}
	}

	/**
	 * 选出一个数加到buff里面
	 * 当Candidates里面有重复的数，并且Candidates里面的数能被重复使用的时候
	 * @param candidates Candidates 里面有重复的数据
	 * @param target
	 * @param preLevelStart 上一层被选了的那个数的index
	 * @param currentSum 目前buff里面数的和
	 * @param results
	 * @param buff
	 */
	private void combinationSumWithDup(int[] candidates,
			int target,
			int preLevelStart,
			int currentSum,
			List<List<Integer>> results,
			List<Integer> buff){
		
		if (currentSum == target){
			results.add(new ArrayList<Integer>(buff));
			return;
		}
		
		// i 是我们即将要选出的那个数的index
		// 因为candidates里面有重复的，所以当candidates[i] == candidates[i+1]的时候我们要一直往后走
		// i < candidates.length - 1
		for (int i = preLevelStart; i < candidates.length && currentSum + candidates[i] <= target; ++i){
			if (i < candidates.length - 1 
				&& candidates[i] == candidates[i + 1]) {
				continue;
			}
			
			buff.add(candidates[i]);
			combinationSumWithDup(candidates, target, i, currentSum + candidates[i], results, buff);
			buff.remove(buff.size() - 1);
		}
	}

}
