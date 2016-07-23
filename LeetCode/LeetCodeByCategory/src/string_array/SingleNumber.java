package string_array;
//	Given an array of integers, every element 
//	appears twice except for one. Find that single one.
//	
//	Note:
//	Your algorithm should have a linear runtime complexity. 
//	Could you implement it without using extra memory?
public class SingleNumber {
//	异或， 因为x^x = 0
//	所以那些重复的（出现两次的）数字彼此异或之后就全为0了
//	只剩下那个出现一次的数字
    public int singleNumber(int[] A) {
    	if(A == null || A.length == 0)
    		return 0;
    	int result = 0;
    	for(int i = 0; i<A.length; ++i)
    		result ^= A[i];
    	
    	return result;
    }
    
    public static void main(String args[]) {
    	int A[] = new int[] {1,1,2,2,3,3,4,5,4};
    	SingleNumber sn = new SingleNumber();
    	System.out.println(sn.singleNumber(A));
    }
}
