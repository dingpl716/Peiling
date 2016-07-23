//	Given an unsorted integer array, find the first missing positive integer.
//	
//	For example,
//	Given [1,2,0] return 3,
//	and [3,4,-1,1] return 2.
//	
//	Your algorithm should run in O(n) time and uses constant space.
public class FirstMissingPositive {

	// using bit map
//    public int firstMissingPositive1(int[] A) {
//    	byte[] dict = new byte[Integer.MAX_VALUE/8 + 1];
//    	for(int i = 0; i<dict.length; ++i) {
//    		dict[i] &= 0;
//    	}
//    	dict[0] |= 1; 
//    	for(int i=0; i<A.length; ++i) {
//    		if(A[i] > 0) {
//    			int byteIndex = A[i]/8;
//    			int bitIndex = A[i]%8;
//    			dict[byteIndex] |= (1<<bitIndex); 
//    		}
//    	}
//    	
//    	for(int i=0; i<dict.length; ++i) {
//    		for(int j=0; j<8; ++j) {
//    		}
//    	}
//    }

//	贪心的策略，O（n）的循环把数字放到其对应的位置，即满足A[i]=i+1，就能保证每次交换就是有意义的。
//
//	如果当前位置已经存在正确的值，就不交换，否则会死循环。
//
//	最后在扫描一遍，如果当前位置上数字不对就输出，如果都正确，就没有漏的数字，输出n+1
	
	public int firstMissingPositive(int[] A) { 
		A = rearrange(A);
		for(int i=0; i<A.length; ++i) {
			if(A[i] != i+1)
				return i+1;
		}
		
		return A.length+1;
	}
	public int[] rearrange(int[] A) {
		for(int i=0; i<A.length; ++i){
			while(A[i]!=i+1 && A[i]>=1 && A[i]<=A.length && A[i]!=A[A[i]-1]) {
				int tmp = A[i];
				A[i] = A[tmp-1];
				A[tmp-1] = tmp;
			}
		}
		
		return A;
	}
}
