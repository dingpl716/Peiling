package math;
//	Description:
//	
//	Count the number of prime numbers less than a non-negative number, n.

import java.util.LinkedList;

/**
 * 核心思想:
 * 1. 这个数组的实现，比那个linkedlist的实现要快很多
 * 2. 在数组实现中，外层for循环只用看i = 3, 5, 7, 9, 11的情况
 * 3. 在内层for循环中，j应该从i开始计算，然后j += 2. 在这里，不需要考虑j < i的情况，因为我们之前肯定已经计算过了
 * 4. 
 * @author Dingp
 *
 */
public class CountPrimes {
	private static final int sqt_Integer_Max = (int)Math.sqrt((double)Integer.MAX_VALUE);
	
	public boolean[] countPrimes(int n) {
		if (n <= 2) {
        	return null;
        }
		
		boolean[] notPrime = new boolean[n];
		
		int count = 1;
		for (int i = 3; i < n; i += 2) {
			if (!notPrime[i]) {
				++count;
			}
			
			int j = i;
			while (((double)j *i) < n) {
				notPrime[i * j] = true;
				j += 2;
			}
		}
		
		return notPrime;
	}
	
	public boolean[] countPrimes3(int n) {
		if (n <= 2) {
        	return null;
        }
		
		boolean[] notPrime = new boolean[n];
		
		int count = 1;
		for (int i = 3; i < n; i += 2) {
			if (!notPrime[i]) {
				++count;
			}
			
			int j = 3;
			for (; i * j < n; j += 2) {
				notPrime[i * j] = true;
			}
		}
		
		return notPrime;
	}
	
    public int countPrimes2(int n) {
        if (n <= 2) {
        	return 0;
        }
        
        LinkedList<Integer> primeNumbers = new LinkedList<Integer>();
        
        for (int candidate = 3; candidate < n; candidate += 2) {
        	isPrimeNumber(candidate, primeNumbers);
        }
        
        return primeNumbers.size() + 1;
    }
    
    private boolean isPrimeNumber(int number, LinkedList<Integer> primeNumbers) {
    	
    	double squareRoot = Math.sqrt((double)number);
    	
    	for (Integer primeNumber : primeNumbers) {
    		if (primeNumber > squareRoot) {
    			break;
    		}
    		if (number % primeNumber == 0) {
    			return false;
    		}
    	}
    	
    	primeNumbers.add(number);
    	
    	return true;
    }
    
    public static void main(String[] args) {
    	CountPrimes c = new CountPrimes();
    	int n = 499979;
    	boolean[] wrong = c.countPrimes(n);
    	boolean[] correct = c.countPrimes3(n);
    	
    	for (int i = 0; i < wrong.length; ++i) {
    		if (wrong[i] != correct[i]) {
    			System.out.println("wrong  :" + wrong[i] + " " + i);
    			System.out.println("correct:" + correct[i] + " " + i);
    		}
    		
    	}
    }
}
