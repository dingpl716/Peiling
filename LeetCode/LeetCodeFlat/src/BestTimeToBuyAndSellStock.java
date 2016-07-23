//	Say you have an array for which the ith element is the price of a given stock on day i.
//	

public class BestTimeToBuyAndSellStock {
	/*
	 *  我的方法，(value, day)先按value排序，然后头尾指针,i,j
	 *  如果满足i.day < j.day的话返回,如果不是的话
	 *  检查i, j-1 以及 i+1, j 这两组，选出满足条件，并且最大的
	 *  如果还是没有的话，那么减小的i+1和j-1了
	 */
	
    public int maxProfit1(int[] prices) {
    	return 0;
    }
    
//	If you were only permitted to complete at most one transaction 
//	(ie, buy one and sell one share of the stock), 
//	design an algorithm to find the maximum profit.
//	    这个很简单，一次扫描完成。只需要找到最大增长即可。
//	    从前往后，用当前价格减去此前最低价格，就是在当前点卖出股票能获得的最高利润。
//	    扫描的过程中更新最大利润和最低价格就行了。
//	    O(n)
    public int maxProfitI(int[] prices) {
    	if(prices == null || prices.length == 1)
    		return 0;
    	int lowestPrice = prices[0];
    	int maxProfit = Integer.MIN_VALUE;
    	for(int i=1; i<prices.length; ++i) {
    		int profit = prices[i] - lowestPrice;
    		if(profit > maxProfit)
    			profit = maxProfit;
    		if(prices[i] < lowestPrice)
    			lowestPrice = prices[i];
    	}
    	return maxProfit;
    }
    
//    Design an algorithm to find the maximum profit. 
//    You may complete as many transactions as you like 
//    (ie, buy one and sell one share of the stock multiple times). 
//    However, you may not engage in multiple transactions at the same time 
//    (ie, you must sell the stock before you buy again).   
    // 这个题就是照到所以的上升子序列， 之后i > i-1就可以把这部分收益计入最终结果
    public int maxProfitII(int[] prices) {
        int result = 0;
        if(prices == null || prices.length == 1)
        	return 0;
        for(int i=1; i<prices.length; ++i) {
        	if(prices[i] > prices[i-1])
        		result += prices[i] - prices[i-1];
        }
        return result;
    }
    
//    Design an algorithm to find the maximum profit. 
//    You may complete at most two transactions.
//
//    Note:
//    You may not engage in multiple transactions at the same time 
//    (ie, you must sell the stock before you buy again).
    // 一维动态规划
//    找寻一个点j，将原来的price[0..n-1]分割为price[0..j]和price[j..n-1]，分别求两段的最大profit。
//    进行优化：
//    对于点j+1，求price[0..j+1]的最大profit时，很多工作是重复的，在求price[0..j]的最大profit中已经做过了。
//    类似于Best Time to Buy and Sell Stock，可以在O(1)的时间从price[0..j]推出price[0..j+1]的最大profit。
//    但是如何从price[j..n-1]推出price[j+1..n-1]？反过来思考，我们可以用O(1)的时间由price[j+1..n-1]推出price[j..n-1]。
//    最终算法：
//    数组l[i]记录了price[0..i]的最大profit，
//    数组r[i]记录了price[i..n]的最大profit。
//    已知l[i]，求l[i+1]是简单的，同样已知r[i]，求r[i-1]也很容易。
//    最后，我们再用O(n)的时间找出最大的l[i]+r[i]，即为题目所求。
    public int maxProfitIII(int[] prices) {
    	if(prices == null || prices.length <=1)
    		return 0;
    	
    	int[] left = new int[prices.length];
    	int[] right = new int[prices.length];
    	for(int i=0; i<prices.length; ++i) {
    		left[i] = Integer.MIN_VALUE;
    		right[i] = Integer.MIN_VALUE;
    	}
    	int leftLowest = prices[0];
    	int maxProfit = Integer.MIN_VALUE;
    	// 计算left部分
    	for(int i=0; i<prices.length; ++i) {
    		int profit = prices[i] - leftLowest;
    		if(profit > maxProfit) 
    			maxProfit = profit;
    		left[i] = maxProfit;
    		if(prices[i] < leftLowest)
    			leftLowest = prices[i];
    	}
    	
    	//计算right
    	maxProfit = Integer.MIN_VALUE;
    	int rightHighest = prices[prices.length-1];
    	for(int i=prices.length-1; i>=0; --i) {
    		int profit = rightHighest - prices[i];
    		if(profit > maxProfit)
    			maxProfit = profit;
    		right[i] = maxProfit;
    		if(prices[i] > rightHighest)
    			rightHighest = prices[i];
    	}
    	
    	// 找出最终答案
    	maxProfit = Integer.MIN_VALUE;
    	for(int i=0; i<prices.length; ++i) {
    		if(maxProfit < left[i] + right[i])
    			maxProfit = left[i] + right[i];
    	}
    	return maxProfit;
    }
}
