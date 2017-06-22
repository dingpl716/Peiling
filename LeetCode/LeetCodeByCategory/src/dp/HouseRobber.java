package dp;

//	You are a professional robber planning to rob houses along a street. 
//	Each house has a certain amount of money stashed, the only constraint 
//	stopping you from robbing each of them is that adjacent houses have 
//	security system connected and it will automatically contact the police 
//	if two adjacent houses were broken into on the same night.
//	
//	Given a list of non-negative integers representing the amount of money of each house, 
//	determine the maximum amount of money you can rob tonight without alerting the police.

/**
 * 核心思想：DP，对于每一种房子都只有两种可能，抢或是不抢。
 * 设rob[i]表示，如果我们抢了第i个房子时能拿到的最多的钱
 * 设not[i] 表示，如果我们不强第i个房子时能拿到的最多的钱
 * 
 * rob[0] = nums[0]
 * not[0] = 0
 * 
 * rob[1] = not[0] + nums[1] // 抢1时，就一定不能抢0
 * not[1] = Max(rob(0), not(0)) // 不抢1的话，就既可以抢0，也可以不抢0
 * 
 * rob[2] = not[1] + nums[2] 
 * not[2] = Max(rob[1], not[1])
 * 
 *  进一步改进：只需要用两个变量rob 和 not来分别记录rob和不rob前面一个屋子的情况即可，
 *  不需要只用两个数组。 这样可以做到O(1)的space
 * @author peding
 *
 */
public class HouseRobber {

    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
        	return 0;
        }
        
        if (nums.length == 1) {
        	return nums[0];
        }
        
        int[] rob = new int[nums.length];
        rob[0] = nums[0];
        
        int[] not = new int[nums.length];
        not[0] = 0;
        
        for (int i = 1; i < nums.length; ++i) {
        	rob[i] = not[i - 1] + nums[i];
        	not[i] = Math.max(rob[i - 1], not[i - 1]);
        }
        
        return Math.max(rob[nums.length - 1], not[nums.length - 1]);
    }
}
