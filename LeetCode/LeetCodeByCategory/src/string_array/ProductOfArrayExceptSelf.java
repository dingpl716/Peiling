package string_array;

//	Given an array of n integers where n > 1, nums, return an array 
//	output such that output[i] is equal to the product of all the elements of nums except nums[i].
//	
//	Solve it without division and in O(n).
//	
//	For example, given [1,2,3,4], return [24,12,8,6].
//	
//	Follow up:
//	Could you solve it with constant space complexity? 
//	(Note: The output array does not count as extra space for the purpose of space complexity analysis.)

/**
 * 核心思想,要有DP的思维, nums[i]的值被除res[i]以外的所有其他res所需要
 * @author Dingp
 *
 */
public class ProductOfArrayExceptSelf {

    public int[] productExceptSelf(int[] nums) {
        if (nums == null) {
        	return null;
        }
        
        int[] res = new int[nums.length];
        if (res.length == 0) {
        	return res;
        }
        
        // 在这里置1的原因是，在从右向左扫描时我们需要做res[i] *= product
        // 如果不置1，那就永远为0了
        res[0] = 1;
        
        // 从左边进行迭代连乘，可以保证现在res[i]上的乘积有nums[i]以前的所有数
        for (int i = 1; i < res.length; ++i) {
        	res[i] = nums[i - 1] * res[i - 1];
        }
        
        // 从右边进行迭代连乘，可以保证现在res[i]上有nums[i]以后的所有数
        int product = 1;
        for (int i = res.length - 1; i >= 0; --i) {
        	res[i] *= product;
        	product *= nums[i];
        }
        
        return res;
    }
    
    public static void main(String[] args) {
    	ProductOfArrayExceptSelf p = new ProductOfArrayExceptSelf();
    	int[] nums = new int[]{0, 0};
    	p.productExceptSelf(nums);
    }
}
