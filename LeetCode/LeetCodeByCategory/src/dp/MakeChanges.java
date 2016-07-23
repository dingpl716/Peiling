package dp;

import java.util.Iterator;
import java.util.Set;

//给一个数的集合，表示你可以用的硬币的面值，在给定一个数值，
//要求返回最少的硬币数，并且这些硬币面值的和等于这个给定的数值

//c表示面值，cost[c]表示c需要的硬币数
//cost[c] = 1 + min{cons[c-d]} for all d in this coin set
public class MakeChanges {

	public int makeChanges(Set<Integer> coins, int value) {
		int[] cost = new int[value];
		
		for(int i=1; i<value; ++i) {
			cost[i] = 1 + findMin(coins, cost, value);
		}
		
		return cost[cost.length-1];
	}
	
	private int findMin(Set<Integer> coins, int[] cost, int value) {
		int result = Integer.MAX_VALUE;
		for(Iterator<Integer> itr = coins.iterator(); itr.hasNext();) {
			int d = itr.next();
			if(value - d >= 0) {
				int tmp = cost[value - d];
				if(tmp < result)
					result = tmp;
			}
		}
		return result;
	}
}
