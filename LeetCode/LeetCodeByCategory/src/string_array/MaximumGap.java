package string_array;

//	Given an unsorted array, find the maximum difference between the successive elements in its sorted form.
//	
//	Try to solve it in linear time/space.
//	
//	Return 0 if the array contains less than 2 elements.
//	
//	You may assume all elements in the array are non-negative integers and fit in the 32-bit signed integer range.

public class MaximumGap {
    public int maximumGap(int[] nums) {
        if (nums == null || nums.length < 2) {
        	return 0;
        }
        
        int[] sortedNums = countingSort(nums);
        
        int result = sortedNums[1] - sortedNums[0];
        for (int i = 2; i < sortedNums.length; ++i){
        	result = Math.max(result, sortedNums[i] - sortedNums[i -1]);
        }
        
        return result;
    }
    
	private int[] countingSort(int[] nums){
		if (nums == null || nums.length < 2) {
			return nums;
		}
		
		int maxNum = nums[0];
		for (int i = 1; i < nums.length; ++i) {
			maxNum = Math.max(maxNum, nums[i]);
		}
		
		// counting的坐标是nums里面的值
		// 统计每个数的出现次数
		int[] counting = new int[maxNum + 1];
		for (int num : nums){
			++counting[num];
		}
		
		// 累计出现次数
		for (int i = 1; i < counting.length; ++i) {
			counting[i] += counting[i - 1];
		}
		
		// 将nums里面的值直接插入到result里面
		int[] result = new int[nums.length];
		for (int num : nums) {
			result[counting[num] - 1] = num;
			counting[num]--;
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		int[] nums = new int[]{1, 10000};
		MaximumGap m = new MaximumGap();
		
		System.out.println(m.maximumGap(nums));
	}
}
