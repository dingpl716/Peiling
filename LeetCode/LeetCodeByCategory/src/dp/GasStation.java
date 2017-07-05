package dp;
//	There are N gas stations along a circular route, 
//	where the amount of gas at station i is gas[i].
//	
//	You have a car with an unlimited gas tank and it 
//	costs cost[i] of gas to travel from station i to its next station (i+1). 
//	You begin the journey with an empty tank at one of the gas stations.
//	
//	Return the starting gas station's index if you can travel around the circuit once, otherwise return -1.
//	
//	Note:
//	The solution is guaranteed to be unique.

/*
 * O(n)的复杂度
 * diff[i] = gas[i] - cost[i]
 * 对这个数组求和，如果小于零那么肯定没有解
 * 在求和的同时，追踪和大于0的子数组的开头，如果最后
 * 这个数组的总和大于零那么返回这个开头，不然返回-1
 */
public class GasStation {

    public int canCompleteCircuit(int[] gas, int[] cost) {
        if(gas == null || cost == null)
        	return -1;
        if(gas.length == 0 && cost.length == 0)
        	return 0;
        
        int[] diff = new int[gas.length];
        for(int i=0; i<diff.length; ++i) {
        	diff[i] = gas[i] - cost[i];
        }
        
        int leftGas = 0;
        int sumSubArray = 0;
        int start = 0;
        for(int i=0; i<diff.length; ++i) {
        	leftGas += diff[i];
        	sumSubArray += diff[i];
        	if(sumSubArray < 0) {
        		start = i+1;
        		sumSubArray = 0;
        	}
        }
        
        if(leftGas < 0)
        	return -1;
        else
        	return start;
    }
}
