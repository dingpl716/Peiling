package dp;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

//	You are given coins of different denominations and a total amount of money amount. 
//	Write a function to compute the fewest number of coins that you need to make up that amount. 
//	If that amount of money cannot be made up by any combination of the coins, return -1.
//	
//	Example 1:
//	coins = [1, 2, 5], amount = 11
//	return 3 (11 = 5 + 5 + 1)
//	
//	Example 2:
//	coins = [2], amount = 3
//	return -1.
//	
//	Note:
//	You may assume that you have an infinite number of each kind of coin.

//给一个数的集合，表示你可以用的硬币的面值，在给定一个数值，
//要求返回最少的硬币数，并且这些硬币面值的和等于这个给定的数值

//c表示面值，cost[c]表示c需要的硬币数
//cost[c] = 1 + min{cons[c-d]} for all d in this coin set
public class CoinChange {

    public int coinChange(int[] coins, int amount) {
        
        Set<Integer> coinsSet = convertToSet(coins);
        
        int[] cost = new int[amount + 1];
        cost[0] = 0;
        for (int i = 1; i < cost.length; ++i) {
        	if (coinsSet.contains(i)) {
        		cost[i] = 1;
        	} else {
        		cost[i] = -1;
        	}
        }
        
        
		for(int i=1; i<cost.length; ++i) {
			if (cost[i] != 1) {
				int minCost = findMin(coinsSet, cost, i);
				if (minCost >= 0) {
					cost[i] = 1 + minCost;
				}
			}
		}
		
		return cost[cost.length-1];
    }
    
    /**
     * 
     * @param coinsSet
     * @param cost
     * @param currentAmount The current amount we are calculating for.
     * @return
     */
	private int findMin(Set<Integer> coinsSet, int[] cost, int currentAmount) {
		int result = cost[currentAmount];
		
		for(Integer coin : coinsSet) {
			int anotherCoin = currentAmount - coin;
			if(anotherCoin > 0) {
				int newCoinCost = cost[anotherCoin];
				
				// If the coinsSet does not have the combination of coin + anohterCoin 
				if (newCoinCost < 0) {
					continue;
				} else {
					if (result > 0) {
						result = Math.min(result, newCoinCost);
					} else {
						result = newCoinCost;
					}
				}
				
			}
		}
		
		return result;
	}
	
	private Set<Integer> convertToSet(int[] coins){
	    Set<Integer> result = new HashSet<Integer>();
	    
	    if (coins != null) {
	        for(int coin : coins) {
	            result.add(coin);
	        }
	    }
	        
	    return result;
	}

	public static void main(String[] args) {
		CoinChange c = new CoinChange();
		System.out.println(c.coinChange(new int[] {1, 2, 5}, 11));
	}
}
