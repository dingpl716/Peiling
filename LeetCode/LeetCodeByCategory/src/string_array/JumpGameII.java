package string_array;
//	Given an array of non-negative integers, you are initially positioned at the first index of the array.
//	
//	Each element in the array represents your maximum jump length at that position.
//	
//	Your goal is to reach the last index in the minimum number of jumps.
//	
//	For example:
//	Given array A = [2,3,1,1,4]
//	
//	The minimum number of jumps to reach the last index is 2. 
//	(Jump 1 step from index 0 to 1, then 3 steps to the last index.)

//	两个指针，start 和 end， 刚开始的时候他们都指向0
//	当end<A.length 的时候做以下事情
//	找到从start到end这个区间里面，能够到达的最远的点，
//	扫描完这个区间之后，自然start = end+1， 而end=这个最远的点
//	如果在扫描中有任何一个点能到达最后的点，那么直接结束
public class JumpGameII {

    public int jump1(int[] A) {
        if(A == null)
        	return -1;
        if(A.length < 2)
        	return 0;
        
        int step = 0;
        int start = 0;
        int end = 0;
        while(end < A.length) {
        	++step;
        	int farPoint = 0;
        	for(int i=start; i<=end; ++i){
        		if(A[i] + i >= A.length - 1)
        			return step;
        		if((A[i] + i) > farPoint)
        			farPoint = A[i] + i;
        	}
        	start = end+1;
        	end = farPoint;
        }
        
        return -1;
    }
}
