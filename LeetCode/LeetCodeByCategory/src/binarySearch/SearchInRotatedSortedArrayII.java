package binarySearch;

//Follow up for "Search in Rotated Sorted Array":
//What if duplicates are allowed?
//
//Would this affect the run-time complexity? How and why?
//
//Write a function to determine if a given target is in the array.

/*
 * This will affect! e.x [1,1,3,1] or [1,3,1,1,1]
 * when the left equals to the middle, we don't know which 
 * part is sorted, so we can only reduce one by one
 */

public class SearchInRotatedSortedArrayII {

    public boolean search(int[] A, int target) {
        if(A == null || A.length == 0)
            return false;
        int left = 0;
        int right = A.length - 1;
        
        while(left <= right) {
            int middle = (left + right) / 2;
            if(A[middle] == target)
                return true;
            
            // left half is sorted
            if(A[left] < A[middle]){
                if(A[left] <= target && target < A[middle])
                    right = middle - 1;
                else
                    left = middle + 1;
            } else if (A[left] > A[middle]) { // right half is sorted
                if(A[middle] < target && target <= A[right])
                    left = middle + 1;
                else
                    right = middle -1;
            } else {
                left++;  // 减小已经访问过的区域
            }
        }
        
        return false;
    }
    
    
    public boolean search2(int[] A, int target) {
        if(A == null || A.length == 0)
            return false;
        int left = 0;
        int right = A.length - 1;
        
        while (left < right){
        	int middle = (left + right) / 2;
        	if (A[middle] == target){
        		return true;
        	}
        	
        	if (A[middle] > A[right]){
        		if(A[left] <= target && target < A[middle]){
        			right = middle;
        		}else{
        			left = middle + 1;
        		}
        		
        	}else if ( A[middle] < A[left]){
        		if (A[middle] < target && target <= A[right]){
        			left = middle + 1;
        		}
        		else{
        			right = middle;
        		}
        	}
        	else {
        		++left;
        	}
        }
        
        return A[left] == target;
    }
}
