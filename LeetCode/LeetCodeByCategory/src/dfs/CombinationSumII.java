package dfs;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
 * 条件：
 * C里面有重复的数字，但是结果集不能重复。
 * C里面的数字不能被重复使用
 * 
 * 易犯错误
 * 1. 没有更新currentSum
 * 2. 检查重复元素时，是num[i]和num[i+1]比较，
 * 不是和num[i-1] 比较
 */
public class CombinationSumII {
	
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
		List<List<Integer>> results = new ArrayList<List<Integer>>();
		List<Integer> buff = new ArrayList<Integer>();
		
		if (candidates != null){
			Arrays.sort(candidates);
			
			dfs(candidates, target, 0, 0, results, buff);
		}
		
		return results;
    }
    
	/**
	 * 选出一个数加到buff里面
	 * 当num里面有重复的数
	 * 当num里面的数不能被重复使用的时候
	 * @param num
	 * @param target
	 * @param currentSum buff里面目前的和
	 * @param start 这一层应该从哪个index开始
	 * @param tmp
	 * @param result
	 */
    private void dfs(int[] num, int target, int currentSum, int start,
    		List<List<Integer>> results,
			List<Integer> buff) {
    	if(currentSum == target) {
    		results.add(new ArrayList<Integer>(buff));
    		return;
    	}
    	
    	// i是即将选出来的数
    	// 剪枝条件：currentSum + candidates[i] <= target, 如果已经超了就没有必要再往下了。
    	for(int i = start; i < num.length && currentSum + num[i] <= target; ++i) {
    		buff.add(num[i]);
    		dfs(num, target, currentSum + num[i], i+1, results, buff);
    		buff.remove(buff.size() - 1);
    		
    		// 必须再尝试把i加进去之后，再去重
    		while(i< num.length-1 && num[i] == num[i+1])
    			++i;
    	}
    }
}
