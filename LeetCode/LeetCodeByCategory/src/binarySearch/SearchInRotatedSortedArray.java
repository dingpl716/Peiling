package binarySearch;

//Suppose a sorted array is rotated at some pivot unknown to you beforehand.
//
//(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
//
//You are given a target value to search. If found in the array return its index, otherwise return -1.
//
//You may assume no duplicate exists in the array.

public class SearchInRotatedSortedArray {

    public int search(int[] A, int target) {
        if(A == null || A.length == 0)
            return -1;
            
        int left = 0;
        int right = A.length - 1;
        
        while(left <= right) {
            int middle = (left + right)/2;
            if(A[middle] == target)
                return middle;
            
            // the left part is sorted
            if(A[left]<=A[middle]) { 
                if(A[left]<=target && target < A[middle])
                    right = middle - 1; // if the target is in the sorted part, we can discard half of the array
                else
                    left = middle + 1; 
            }else { // the right part is sorted
                if(A[middle] < target && target <= A[right])
                    left = middle + 1;
                else
                    right = middle - 1;
            }
        }
        
        return -1;
    }
}
