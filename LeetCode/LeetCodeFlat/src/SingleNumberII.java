//	Given an array of integers, every element 
//	appears three times except for one. Find that single one.
//	
//	Note:
//	Your algorithm should have a linear runtime complexity. 
//	Could you implement it without using extra memory?

public class SingleNumberII {
//	除了一个数字以外，其他的都出现了3次，如果我们把那个特殊的数剔除，
//	并把剩下的数于每一位来加和的话，每一位上1出现的次数必然都是3的倍数。
//	所以，解法就在这里，将每一位数字分解到32个bit上，
//	统计每一个bit上1出现的次数。最后对于每一个bit上1出现的个数对3取模，
//	剩下的就是结果
	
	/*
	 * 已犯错误：
	 * 1. A.length == 1的时候应该返回A[0]
	 * 2. bitVector记录的是从低位到高位的情况，
	 * 所以在最后还原的时候，应该从31到0扫描bitVector
	 */
    public int singleNumber(int[] A) {
    	if(A == null)
    		return 0;
    	if(A.length == 1)
    		return A[0];
    	if(A.length < 4)
    		return 0;
    	int[] bitVector = new int[32];
    	for(int i=0; i<A.length; ++i) {
    		for(int j=0; j<32; ++j) {
    			if(((A[i]>>j) & 1) == 1)
    				++bitVector[j];
    		}
    	}
    	
    	int result = 0;
    	for(int i=31; i>=0; --i) {
    		if(bitVector[i]%3 != 0)
    			result = (result << 1) + 1;
    		else
    			result = result << 1;
    	}
    	
    	return result;
    }
}
