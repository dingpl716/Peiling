package string_array;
//	Given a sorted array of integers, find the starting and ending position of a given target value.
//	
//	Your algorithm's runtime complexity must be in the order of O(log n).
//	
//	If the target is not found in the array, return [-1, -1].
//	
//	For example,
//	Given [5, 7, 7, 8, 8, 10] and target value 8,
//	return [3, 4].

public class SearchForARange {
	
//	思路一：
//	先坐二分查找，然后向左再向右探测，找到边界即可
//	但是这个方法的最差时间复杂度是O(n)
	
//	思路二：
//	上面的思路之所以会出现O(n)，就是因为在找到一个target
//	之后，我们做的是线性查找，其实我们可以继续二分查找。
//	也就是找到一个target如果他两边都是target的话，那么
//	继续二分，知道找到他的左边，或是右边不为target。
//	所以先找到一个左边的边界，再找到一个右边的边界。
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
     // here, right doesn't start from A.length-1, think about when [2,2] target == 2
//        因为mid永远到不了right，所以如果right=length-1的话，那么就不可能到得了最后一个元素了
        right = A.length;  
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
