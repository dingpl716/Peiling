package string_array;

import java.util.Arrays;
import java.util.Comparator;

//	Given a list of non negative integers, arrange them such that they form the largest number.
//	
//	For example, given [3, 30, 34, 5, 9], the largest formed number is 9534330.
//	
//	Note: The result may be very large, so you need to return a string instead of an integer.
public class LargestNumber {

	private static class IntegerComparator implements Comparator<Integer> {

		/**
		 * 因为我们之后要从大到小排序，所以反转这个自然序排序
		 */
		@Override
		public int compare(Integer arg0, Integer arg1) {
			
			return -1 * compareNatureOrder(arg0.toString(), arg1.toString());
		}
		
		/**
		 * 比较规程:
		 * 一位一位的对两个string做比较，
		 * 如果出现某一位上的数不一样，则数较大的那个string更大
		 * 如果这一位上相等的话，那么比较下一位
		 * 
		 * 如果遍历完了str0，都没找到不一样的数的话，那么说明str0一定是str1的前缀
		 * 此时我们需要递归的进行比较这个前缀（也就是str0）和str1剩下的部分
		 * @param str0
		 * @param str1
		 * @return
		 */
		private int compareNatureOrder(String str0, String str1) {
			
			// make sure the first argument is shorter.
			if (str0.length() > str1.length()){
				return -1 * compareNatureOrder(str1, str0);
			}
			
			int i;
			
			for (i = 0; i < str0.length(); ++i){
				// 如果某一位上更大的话，那么我们认为那一位大的这个数更大
				if (str0.charAt(i) > str1.charAt(i)){
					return 1;
				}
				
				// 反之则那个数更小
				else if (str0.charAt(i) < str1.charAt(i)){
					return -1;
				}
			}
			
			if (i < str1.length()) {
				// 此时str0是str1的前缀，那么我们需要递归的比较前缀部分和剩下的部分
				return compareNatureOrder(str1.substring(0, i), str1.substring(i, str1.length()));
			} else {
				// 两个数完全一样
				return 0;
			}
		}
	}
	
	public String largestNumber(int[] nums) {
		boolean metNonZeroNumber = false;
		
		Integer[] newNums = new Integer[nums.length];
		for (int i = 0; i < nums.length; ++i){
			if (nums[i] != 0) {
				metNonZeroNumber = true;
			}
			newNums[i] = nums[i];
		}
		
		if (!metNonZeroNumber){
			return "0";
		}
		
        Arrays.sort(newNums, new IntegerComparator());
        
        return convertToResult(newNums);
    }
	
	private String convertToResult(Integer[] nums){
		StringBuilder builder = new StringBuilder();
		for (Integer num : nums) {
			builder.append(num);
		}
		
		return builder.toString();
	}
	
	public static void main(String[] args){
		LargestNumber l = new LargestNumber();
		int[] nums = new int[]{3, 30, 34, 5, 9};
		System.out.println(l.largestNumber(nums));
	}
	
}
