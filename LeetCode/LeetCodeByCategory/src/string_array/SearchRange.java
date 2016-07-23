package string_array;

public class SearchRange {
	
	/**
	 *  int mid = (left + right)/2
	 *  so, left <= mid < right
	 *  so, in order to make sure that we can decrease the searching range
	 *  left has to be mid+1, 
	 *  but right could be mid or mir - 1
	 *  in this case, it should be mid
	 */
    public int[] searchRange(int[] A, int target) {
        int[] result = {-1, -1};
        if(A == null || A.length == 0)
            return result;
        
        int left = 0;
        int right = A.length-1;
        
        while(left < right) {
            int mid = (left+right)/2;
            if(target <= A[mid])
                right = mid;
            else
                left = mid + 1;
        }
        
        // now left must point to target if it exists
        if(A[left] == target)
            result[0] = left;
        else
            return result;
            
        left = result[0];
        right = A.length;  // here, right doesn't start from A.length-1, think about when [2,2] target == 2
        while(left < right) {
            int mid = (left+right)/2;
            if(A[mid] <= target)
                left = mid + 1;
            else
                right = mid;
        }
        
        result[1] = right - 1;
        return result;
    }
}
