package dfs;

import java.util.ArrayList;
import java.util.List;

//	Find all possible combinations of k numbers that add up to a number n, given that only numbers 
//  from 1 to 9 can be used and each combination should be a unique set of numbers.
//	
//	
//	Example 1:
//	
//	Input: k = 3, n = 7
//	
//	Output:
//	
//	[[1,2,4]]
//	
//	Example 2:
//	
//	Input: k = 3, n = 9
//	
//	Output:
//	
//	[[1,2,6], [1,3,5], [2,3,4]]

public class CombinationSumIII {

	/**
	 * 找k个数，和为n， 只能从1到9里面选，结果集不能重复，不能重复使用数字
	 * @param k
	 * @param n
	 * @return
	 */
    public List<List<Integer>> combinationSum3(int k, int n) {
    	List<List<Integer>> results = new ArrayList<List<Integer>>();
		List<Integer> buff = new ArrayList<Integer>();
		
		if (k <= 0 || n <= 0 || n > 9*k){
			return results;
		}
		
		combinationSum3(k, n, 1, 0, results, buff);
		return results;
    }
    
    /**
     * 选出一个数，加到buff里面，然后选下一个
     * @param k
     * @param n
     * @param start 现在应该从哪开始选
     * @param currentSum 现在buff里面的和
     * @param results
     * @param buff
     */
    private void combinationSum3(int k, int n,
    		int start,
    		int currentSum,
    		List<List<Integer>> results, List<Integer> buff) {
    	
    	if (buff.size() == k
    			&& currentSum == n){
    		results.add(new ArrayList<Integer>(buff));
    		return;
    	}
    	
    	// i 是我们即将选出来的数， i从start到9
    	// 剪枝条件：
    	// 1. 当currentSum + i > n 的时候就不用再继续往下了,也就是说把选出来之后，sum已经比n大太多了，没比较继续往下看了
    	// 2. 当currentSum + i 再 加上后面的k - (buff.size() + 1)个数的和 < n, 没必要继续往下了， 这个条件是说
    	// 把i选出来之后，再把后面最大的几个数加起来，如果和都还是小于n，那就没必要继续往下了 -- 此项条件待验证
    	for (int i = start; i <= 9 && currentSum + i <= n; ++i){
    		buff.add(i);
    		combinationSum3(k, n, i + 1, currentSum + i, results, buff);
    		buff.remove(buff.size() - 1);
    	}
    }
    
    /**
     * 返回从9开始，往前m个数的和
     * @param m
     * @return
     */
    private int sumLatestNumbers(int m){
    	int result = 0;
    	int current = 9;
    	
    	for (int i = 0; i < m; ++i){
    		result += current--;
    	}
    	
    	return result;
    }
}
