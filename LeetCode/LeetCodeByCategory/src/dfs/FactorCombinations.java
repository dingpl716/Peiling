package dfs;

import java.util.ArrayList;
import java.util.List;

import util.Util;

//	Numbers can be regarded as product of its factors. For example,
//	
//	8 = 2 x 2 x 2;
//	  = 2 x 4.
//	Write a function that takes an integer n and return all possible combinations of its factors.
//	
//	Note: 
//	You may assume that n is always positive.
//	Factors should be greater than 1 and less than n.
//	Examples: 
//	input: 1
//	output: 
//	[]
//	input: 37
//	output: 
//	[]
//	input: 12
//	output:
//	[
//	  [2, 6],
//	  [2, 2, 3],
//	  [3, 4]
//	]
//	input: 32
//	output:
//	[
//	  [2, 16],
//	  [2, 2, 8],
//	  [2, 2, 2, 4],
//	  [2, 2, 2, 2, 2],
//	  [2, 4, 4],
//	  [4, 8]
//	]
		
public class FactorCombinations {
	public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> results = new ArrayList<List<Integer>>();
        ArrayList<Integer> buffer = new ArrayList<Integer>();
        
        dfs2(n, 2, results, buffer);
        
        return results;
    }
	
	/**
	 * 
	 * @param remaining 剩下的需要求factor的值
	 * @param start i的起始值，也就是说所有小于start的factor我们都不考虑了，这是为了去重
	 * @param results
	 * @param buffer
	 */
	private void dfs(int remaining, int start, List<List<Integer>> results, ArrayList<Integer> buffer) {
		if (remaining <= 1) {
			if (buffer.size() > 1) {
				results.add(new ArrayList<Integer>(buffer));
			}
			return;
		}
		
		/*
		 * 假设上一轮的的remaining是16
		 * 那么这一轮的start，remaining的可能情况就是
		 * 2, 8
		 * 4, 4
		 * 8, 2
		 * 为了防止重复的情况
		 * 8， 2就不考虑了
		 * 所以我们要以上一轮的i作为这一轮的start，并且要让start小于remaining
		 * 这一点可以参考求小于n的质数的时候我们只考虑小于n的平方根的可能情况。
		 */
		// double sqrt = Math.sqrt((double)remaining);
		for (int i = start; i <= remaining; ++i) {
			if (remaining % i == 0) {
				buffer.add(i);
				dfs(remaining / i, i, results, buffer);
				buffer.remove(buffer.size() - 1);
			}
		}
	}
	
	/**
	 * 比上面要快的多的实现
	 * 执行过程如下： 初始输入为16
	 * 
	 * 第一层的输入16
	 * start = 2, i = 2, remaining = 8 - > result add [2, 8]
	 * 
	 * 	第二层remaining = 8
	 * 	start = 2, i = 2, remaining = 4 - > result add [2, 2, 4]
	 * 
	 * 		第三层 remaining = 4
	 * 		start = 2, i = 2, remainging = 2 - > result add [2, 2, 2, 2]
	 * 
	 * 		第三层 remaining = 4， i = 3, 大于remaining平方根，退出
	 * 
	 * 	第二层remaining = 8
	 * 	start = 2, i = 3, 大于remaining的平方根, 退出
	 * 
	 * 第一层remaining = 16
	 * start = 2, i = 3不能整除, i = 4 -> result add[4, 4]
	 * 
	 * @param remaining
	 * @param start
	 * @param results
	 * @param buffer
	 */
	private void dfs2(int remaining, int start, List<List<Integer>> results, ArrayList<Integer> buffer) {
		// 只要remaining > 1我们就可以把它加入到result里面，比如对于32， 我们可以把 2,16加入到result里面 
		if (remaining > 1 && buffer.size() > 0) {
			buffer.add(remaining);
			results.add(new ArrayList<Integer>(buffer));
			buffer.remove(buffer.size() - 1);
		}
		
		double sqrt = Math.sqrt((double)remaining);
		for (int i = start; i <= sqrt; ++i) {
			if (remaining % i == 0) {
				buffer.add(i);
				dfs2(remaining / i, i, results, buffer);
				buffer.remove(buffer.size() - 1);
			}
		}
	}
	
	public static void main(String[] args) {
		FactorCombinations f = new FactorCombinations();
		List<List<Integer>> results = f.getFactors(32);
		Util.printListOfList(results);
	}
}
