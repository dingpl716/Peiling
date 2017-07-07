package string_array;

//	Shuffle a set of numbers without duplicates.
//	
//	Example:
//	
//	// Init an array with set 1, 2, and 3.
//	int[] nums = {1,2,3};
//	Solution solution = new Solution(nums);
//	
//	// Shuffle the array [1,2,3] and return its result. 
//	// Any permutation of [1,2,3] must equally likely to be returned.
//	solution.shuffle();
//	
//	// Resets the array back to its original configuration [1,2,3].
//	solution.reset();
//	
//	// Returns the random shuffling of array [1,2,3].
//	solution.shuffle();

/**
 * Shuffle的核心思想
 * i从1开始到n
 * 然后每一次都从0到i(inclusive),的范围内取出一个数j,再交换i,和j的位置.
 * 记住j也有可能是i自己
 * 分析：我们以第一个元素shuffle过后还是在第一个的位置为例
 * 要保证第一个元素位置不变，必须要让他在每次的shuffle中都不发生位置改变
 * 则其概率为 1/2 * 2/3 * 3/4 .... = 1/n
 * @author Dingp
 *
 */
public class ShuffleAnArray {

}
