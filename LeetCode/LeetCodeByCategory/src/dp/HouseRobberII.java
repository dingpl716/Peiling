package dp;

//	After robbing those houses on that street, the thief has found himself 
//	a new place for his thievery so that he will not get too much attention. 
//	This time, all houses at this place are arranged in a circle. That means 
//	the first house is the neighbor of the last one. Meanwhile, the security 
//	system for these houses remain the same as for those in the previous street.
//	
//	Given a list of non-negative integers representing the amount of money of 
//	each house, determine the maximum amount of money you can rob tonight without alerting the police.

/**
 * 核心思想: 做两次HouseRobber即可
 * 
 * 用HouseRobberI里面的方法
 * 先抢  house 0 to n-2
 * 再抢  house 1 to n-1
 * 最后返回较大者即可
 * 
 * 
 * @author Dingp
 *
 */
public class HouseRobberII {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
        	return 0;
        }
        
        if (nums.length == 1) {
        	return nums[0];
        }
        
        int r1 = rob(nums, 0, nums.length - 2);
        int r2 = rob(nums, 1, nums.length - 1);
        
        return Math.max(r1, r2);
    }
    
    /**
     * 计算如果过从start抢到end(inclusive)，能得到的最大利益
     * @param nums
     * @param start
     * @param end
     * @return
     */
    private int rob(int[] nums, int start, int end) {
    	
    	int rob = nums[start];
    	int not = 0;
    	
    	for (int i = start + 1; i <= end; ++i) {
    		int robi = not + nums[i];
    		int noti = Math.max(rob, not);
    		rob = robi;
    		not = noti;
    	}
    	
    	return Math.max(rob, not);
    }
}
