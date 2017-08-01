package list;
//	Given a sorted array, remove the duplicates in place such 
//	that each element appear only once and return the new length.
//	
//	Do not allocate extra space for another array, 
//	you must do this in place with constant memory.
//	
//	For example,
//	Given input array A = [1,1,2],
//	
//	Your function should return length = 2, and A is now [1,2].

//	两个指针，left，right，一个k值，
//	if right == left, right++, k++
//	else, shift A[right, end] to the left
public class RemoveDuplicatesFromSortedArray {

//	已犯错误：
//	1. arrayCopy那不是往左shift k个数，而是length - right个数，
//	相当于就是要把从right到end的数都shift
//	2. shift完后，理论上来说right应该是left+1，但是因为for循环本身要把right+1
//	所以这里right应该是left
    public int removeDuplicates(int[] A) {
    	if(A == null || A.length == 0)
    		return 0;
    	
    	int length = A.length;
    	int left = 0;
    	int duplicateCount = 0;
    	for(int right = 1; right < length; ++right) {
    		if(A[left] == A[right]) {
    			++duplicateCount;
    			continue;
    		}

    		if(duplicateCount != 0)
    			System.arraycopy(A, right, A, left+1, length - right);
    		length -= duplicateCount;
    		left = left+1;
    		right = left;
    		duplicateCount = 0;
    	}
    	
    	return length - duplicateCount;
    }
}
