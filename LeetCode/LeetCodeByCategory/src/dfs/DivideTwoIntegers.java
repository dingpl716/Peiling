package dfs;

//	Divide two integers without using multiplication, division and mod operator.
//	
//	If it is overflow, return MAX_INT.

/**
 * 核心思想：dfs 和 binary search
 * 
 * 1. 因题目要求，我们只能用加法，减法了
 * 2. 如果one by one的加时间上不允许
 * 3. 所以我们要1， 2， 4， 8的这样尝试
 * 4. 因为正数的绝对值比负数小, 所以我们把除数和被除数都变成负数来计算
 * 5. 一定要注意加减法越界的情况
 * 
 * @author Dingp
 *
 */
public class DivideTwoIntegers {

    public int divide(int dividend, int divisor) {
    	if (divisor == 0) {
    		return Integer.MAX_VALUE;
    	}
    	
    	if (dividend == 0) {
    		return 0;
    	}
    	
    	if (dividend == Integer.MIN_VALUE && divisor == -1) {
    		return Integer.MAX_VALUE;
    	}
    	
    	boolean isPositive = true;
    	
    	if ((dividend < 0 && divisor > 0)
			|| (dividend > 0 && divisor < 0)){
    		isPositive = false;
    	}
    	
    	if (dividend > 0) {
    		dividend = 0 - dividend;
    	}
    	
    	if (divisor > 0) {
    		divisor = 0 - divisor;
    	}
    	
    	int result = dfs(dividend, divisor);
    	
    	if (!isPositive) {
    		result = 0 - result;
    	}
    	
    	return result;
    }
    
    /**
     * 只处理同为负的情况, 数传进来之前保证都是负数
     * @param dividend
     * @param divisor
     * @return
     */
    private int dfs(int dividend, int divisor) {
    	if (dividend == divisor) {
    		return 1;
    	}
    	
    	int powerOfDivisor = divisor;
    	int count = 1;
    	
		if (dividend > divisor) {
			return 0;
		}
		
		while ((powerOfDivisor + powerOfDivisor < 0) && // 此处必须要加上这个判断，不然相加的结果可能会从负数变为正数
				(powerOfDivisor + powerOfDivisor > dividend)) {
			powerOfDivisor += powerOfDivisor;
			count += count;
		}
    	
    	return count + dfs(dividend - powerOfDivisor, divisor);
    }
    
    public static void main(String[] args) {
    	DivideTwoIntegers d = new DivideTwoIntegers();
    	System.out.println(d.divide(2147483647, 3));
    }
}
