

//	连个指针，left，right，一个k值，
//	if right == left, right++, k++
//	else, shift A[right, end] to the left by k
public class RemoveDuplicatesFromSortedArray {

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
