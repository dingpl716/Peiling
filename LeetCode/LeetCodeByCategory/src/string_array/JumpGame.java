package string_array;
//	Given an array of non-negative integers, you are initially positioned 
//	at the first index of the array.
//	
//	Each element in the array represents your maximum jump length at that position.
//	
//	Determine if you are able to reach the last index.
//	
//	For example:
//	A = [2,3,1,1,4], return true.
//	
//	A = [3,2,1,0,4], return false.

/**
 * bound case:
 * if A[last] == 0 (last 0 is at last)
 *  then we just need to reach A[last]
 * if A[last] != 0 (last 0 is not at last index)
 *  then we must jump over last 0;
 * 
 * another way to think:
 * no matter what A[last] is,
 * the last 0 is not at last index,
 * and we must jump over all 0 including last 0
 */
 
public class JumpGame {
    public boolean canJump(int[] A) {
    	if(A == null)
    		return true;
    	if(A[0] == 0)
    		return false;
    	
    	// The furthest we can jump
    	int maxCover = 0;
    	for(int i=0; i<=maxCover && i<A.length; ++i){
    		if(A[i] + i > maxCover)
    			maxCover = A[i] + i;
    		if(maxCover >= A.length - 1)
    			return true;
    	}
    	return false;
    }

}