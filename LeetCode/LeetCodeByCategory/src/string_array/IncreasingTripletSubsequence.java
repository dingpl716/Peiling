package string_array;

//	Given an unsorted array return whether an increasing subsequence of length 3 exists or not in the array.
//	
//	Formally the function should:
//	Return true if there exists i, j, k 
//	such that arr[i] < arr[j] < arr[k] given 0 ≤ i < j < k ≤ n-1 else return false.
//	Your algorithm should run in O(n) time complexity and O(1) space complexity.
//	
//	Examples:
//	Given [1, 2, 3, 4, 5],
//	return true.
//	
//	Given [5, 4, 3, 2, 1],
//	return false.

public class IncreasingTripletSubsequence {

	public boolean increasingTriplet(int[] nums) {
		if (nums == null || nums.length < 3){
			return false;
		}
		
		int small = Integer.MAX_VALUE;
		int medium = Integer.MAX_VALUE;
		
		for (int n : nums){
			// If n <= small,尽可能让small更小
			if (n <= small){
				small = n;
			}
			// If small < n <= medium, 尽可能让medium更小,
			// 同时保证medium大于small.
			else if (n <= medium){
				medium = n;
			}
			else {
				// 如果找到比medium大的数，则返回
				return true;
			}
		}
		
		return false;
    }
}
