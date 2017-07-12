package util;

import java.util.List;
import java.util.function.Consumer;

public class Util {
	
	public static void PrintList(List list){
		
		for(int i = 0; i<list.size(); ++i){
			System.out.println(list.get(i));
			System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		}
	}
	
	public static void timmer(Consumer consumer){
		long startTime = System.currentTimeMillis();
		consumer.accept(null);
		long endTime = System.currentTimeMillis();
		System.out.println(endTime - startTime + "ms");
	}
	
	
	public static int[] countingSort(int[] nums){
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
}