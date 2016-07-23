import java.util.ArrayList;
import java.util.Arrays;

//	Given a collection of candidate numbers (C) and a target number (T), 
//	find all unique combinations in C where the candidate numbers sums to T.
//	
//	Each number in C may only be used once in the combination.
//	
//	Note:
//	All numbers (including target) will be positive integers.
//	Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak).
//	The solution set must not contain duplicate combinations.
//	For example, given candidate set 10,1,2,7,6,1,5 and target 8, 
//	A solution set is: 
//	[1, 7] 
//	[1, 2, 5] 
//	[2, 6] 
//	[1, 1, 6] 

/*
 * 有重复的数字，但是结果集不能重复。
 * DFS， 
 * 
 * 易犯错误
 * 1. 没有更新currentSum
 * 2. 检查重复元素时，是num[i]和num[i+1]比较，
 * 不是和num[i-1] 比较
 */
public class CombinationSumII {
	
    public ArrayList<ArrayList<Integer>> combinationSum2(int[] num, int target) {
    	ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
    	ArrayList<Integer> tmp = new ArrayList<Integer>();
    	if(num == null || num.length == 0) {
    		result.add(tmp);
    		return result;
    	}
    	
    	Arrays.sort(num);
    	dfs(num, target, 0, 0, tmp, result);
    	return result;
    }
    
    private void dfs(int[] num, int target, int currentSum, int start,
    		ArrayList<Integer> tmp, ArrayList<ArrayList<Integer>> result) {
    	if(currentSum == target) {
    		result.add(new ArrayList<Integer>(tmp));
    		return;
    	}
    	if(currentSum > target)
    		return;
    	
    	for(int i=start; i<num.length; ++i) {
    		currentSum += num[i];
    		tmp.add(num[i]);
    		dfs(num, target, currentSum, i+1, tmp, result);
    		tmp.remove(tmp.size() - 1);
    		currentSum -= num[i];
    		while(i< num.length-1 && num[i] == num[i+1])
    			++i;
    	}
    }
}
