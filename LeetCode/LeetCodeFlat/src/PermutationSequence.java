import java.util.ArrayList;

//	The set [1,2,3,…,n] contains a total of n! unique permutations.
//	
//	By listing and labeling all of the permutations in order,
//	We get the following sequence (ie, for n = 3):
//	
//	"123"
//	"132"
//	"213"
//	"231"
//	"312"
//	"321"
//	Given n and k, return the kth permutation sequence.
//	
//	Note: Given n will be between 1 and 9 inclusive.

//	思路：
//	所以就把所有的permutation分组
//	以1为开头的permutation有(n-1)!个，
//	以2为开头的permutation有(n-1)!个，
//	....
//	以n为开头的permutation有(n-1)!个，
//	所以k/(n-1)!是第一个数字
//	
//	同样，
//	在以1为开头的permutation里面有(n-2)!个是一个2开头的
//	....
public class PermutationSequence {
	
    public String getPermutation(int n, int k) {
    	k = k-1;
    	StringBuffer result = new StringBuffer();
    	// in each round we get a candidate from this list, and remove it
    	ArrayList<Integer> candidates = new ArrayList<Integer>();
    	for(int i=1; i<=n; ++i)
    		candidates.add(i);
    	
    	int round=1;
    	while(round <= n) {
    		int factorial = getFactorial(n-round);
    		int index = k/factorial;
    		result.append(candidates.get(index));
    		candidates.remove(index);
    		k = k % factorial;
    		++round;
    	}
        
    	return result.toString();
    }
    
    int getFactorial(int n) {
    	int result = 1;
    	for(int i=1; i<=n; ++i) {
    		result *= i;
    	}
    	
    	return result;
    }
}
