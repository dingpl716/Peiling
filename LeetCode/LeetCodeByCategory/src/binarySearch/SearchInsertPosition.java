package binarySearch;
//	Given a sorted array and a target value, return the 
//	index if the target is found. If not, return the 
//	index where it would be if it were inserted in order.
//	
//	You may assume no duplicates in the array.
//	
//	Here are few examples.
//	[1,3,5,6], 5 �� 2
//	[1,3,5,6], 2 �� 1
//	[1,3,5,6], 7 �� 4
//	[1,3,5,6], 0 �� 0

public class SearchInsertPosition {
	// if find the target in the array, then return the index
	// if find the first number which is greater than target, return the index
	// if finish the array, return the length 
    public int searchInsert(int[] A, int target) {
        if(A == null || A.length == 0)
        	return 0;
        for(int i=0; i<A.length; ++i) {
        	if(A[i] == target || A[i]> target)
        		return i;
        }
        
        return A.length;
    }
}
