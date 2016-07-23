package dfs;
import java.util.ArrayList;
import java.util.Arrays;

//	Given a set of candidate numbers (C) and a target number (T), 
//  find all unique combinations in C where the candidate numbers sums to T.
//	
//	The same repeated number may be chosen from C unlimited number of times.
//	
//	Note:
//	All numbers (including target) will be positive integers.
//	Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak).
//	The solution set must not contain duplicate combinations.
//	For example, given candidate set 2,3,6,7 and target 7, 
//	A solution set is: 
//	[7] 
//	[2, 2, 3] 
/*
 * 易犯错误：
 * 1. 除了要每次删除tmp的最后一个元素外，同样需要从currentSum中减去candidates[i]
 * 2. 必须传入上一次开始的地方，这样才能保证没用重复！
 * 3. currentSum和target不能同时变化，不能一边减小target，一边增大currentSum
 */
public class CombinationSum {
	public ArrayList<ArrayList<Integer>> combinationSum(int[] candidates,
			int target) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> tmp = new ArrayList<Integer>();

		if (candidates == null || candidates.length == 0)
			return result;

		Arrays.sort(candidates);

		combinationSum(candidates, target, 0, tmp, result);
		return result;
	}

	private void combinationSum(int[] candidates, int target,
			int preStart, ArrayList<Integer> tmp,
			ArrayList<ArrayList<Integer>> result) {

		if (target == 0) {
			result.add(new ArrayList<Integer>(tmp));
		} else {
			for (int i = preStart; i < candidates.length; ++i) {
				target -= candidates[i];
				if (target < 0) {
					target += candidates[i];
					break;
				}
				tmp.add(candidates[i]);
				combinationSum(candidates, target, i, tmp, result);
				tmp.remove(tmp.size() - 1); // remove from the buffer
				target += candidates[i]; // !!! also need to remove from the
												// current sum
			}
		}
	}
	
	/*
	 * 从上一层开始的位置开始查看，如果currentSum>target 了那么没必要继续了
	 * 如果currentSum等于target了那么就加如result里面， 否则就一个一个的试，只要currentSum<target, 就继续往下试
	 */
	private void combinationSum2(int[] candidates, int target, int currentSum,
			int preStart, ArrayList<Integer> tmp,
			ArrayList<ArrayList<Integer>> result) {

		if (target == currentSum) {
			result.add(new ArrayList<Integer>(tmp));
		} else {
			for (int i = preStart; i < candidates.length; ++i) {
				currentSum += candidates[i];
				if (currentSum > target) {
					currentSum -= candidates[i];
					break;
				}
				tmp.add(candidates[i]);
				combinationSum(candidates, target, i, tmp, result);
				tmp.remove(tmp.size() - 1); // remove from the buffer
				currentSum -= candidates[i]; // !!! also need to remove from the
												// current sum
			}
		}
	}

}
