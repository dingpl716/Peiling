package subSequence;

//	Find the contiguous subarray within an array (containing at least one number) which has the largest product.
//	
//	For example, given the array [2,3,-2,4],
//	the contiguous subarray [2,3] has the largest product = 6.

public class MaximumProductSubarray {
	
	// Solution 1: DP, O(n^2)
    public int maxProduct1(int[] nums) {
        if (nums.length == 0){
            return 0;    
        }
        
        if (nums.length == 1){
            return nums[0];
        }
        
        int[][] productMatrix = new int[nums.length][nums.length];
        int maxProduct = nums[0];

        for (int i = 0; i < nums.length; ++i) {
        	for (int j = i; j < nums.length; ++j) {
        		if (i == j){
        			productMatrix[i][j] = nums[i];
        		}
        		else{
        			productMatrix[i][j] = productMatrix[i][j-1] * nums[j];
        		}

        		maxProduct = productMatrix[i][j] > maxProduct ? productMatrix[i][j] : maxProduct;
        	}
        }
        
        return maxProduct;
    }

    // CurrentMin is the key to this solution.
    public int maxProduct2(int[] nums) {
    	int result =nums[0];
	    int curMax=nums[0];
	    int curMin=nums[0];

	    for(int i=1;i<nums.length;i++)
	    {
	        int temp = curMax;
	    	curMax=Math.max(curMax*nums[i], Math.max(curMin*nums[i],nums[i]));
	    	curMin=Math.min(temp*nums[i], Math.min(curMin*nums[i],nums[i]));
	    	result = Math.max(curMax, result);
	    }

	    return result;
    }
    
    // Devide this array into several sub arrays that do not contain zeros.
    // For each subarray there are three possible places where we could have the max product:
    // 1. product[start, end], means the product from start to end, the result is a positive value;
    // If the first situation is a negative value:
    // 2. product[start, rightMostNegative), means the product from start to rightMostNegative(exclusive) or
    // 3. product(leftMostNegative, end], means the product from leftMostNegative(exclusive) to end;
    public int maxProduct3(int[] nums){
    	if (nums.length == 0){
    		return 0;
    	}
    	
    	if (nums.length == 1){
    		return nums[0];
    	}
    	
    	int[] zeros = new int[nums.length];
    	
    	for (int i=0; i<zeros.length; ++i){
    		zeros[i] = -1;
    	}
    	
    	for (int i=0, j=0; i<nums.length; ++i){
    		if (nums[i] == 0){
    			zeros[j++] = i;
    		}
    	}
    	
    	int maxProduct = maxProduct(nums, 0, nums.length-1);
    	int temp = maxProduct(nums, 0, zeros[0] - 1);
    	maxProduct = Math.max(maxProduct, temp);
    	
    	for (int i=1; i<zeros.length+1; ++i){
    		if (zeros[i] < 0){
    			temp = maxProduct(nums, zeros[i-1] + 1, nums.length - 1);
    			maxProduct = Math.max(maxProduct, temp);
    			break;
    		}
    		
    		temp = maxProduct(nums, zeros[i-1] + 1, zeros[i] - 1);
    		maxProduct = Math.max(maxProduct, temp);
    	}
    	
    	return maxProduct;
    }
    
    // Returns the max product of subarry nums[start, end]
    // nums[start, end] should not have any zeros.
    // Three possible places where could have the max product:
    // 1. product[start, end], means the product from start to end, the result is a positive value;
    // If the first situation is a negative value:
    // 2. product[start, rightMostNegative), means the product from start to rightMostNegative(exclusive) or
    // 3. product(leftMostNegative, end), means the product from leftMostNegative(exclusive) to end;
    private int maxProduct(int[] nums, int start, int end) {
    	if (start > end){
    		return Integer.MIN_VALUE;
    	}
    	
    	if (start == end) {
    		return nums[start];
    	}

    	int product1 = product(nums, start, end);

    	if (product1 < 0) {
    		int leftNegative = start;
	    	for (int i = start; i <= end; ++i) {
	    		if (nums[i] < 0 ) {
	    			leftNegative = i;
	    			break;
	    		}
	    	}
	    	int productRight = product(nums, leftNegative + 1, end); 

	    	int rightNegative = end;
	    	for(int i = end; i >= start; --i){
	    		if (nums[i] < 0) {
	    			rightNegative = i;
	    			break;
	    		}
	    	}
	    	int productLeft = product(nums, start, rightNegative - 1);
	    	
	    	return Math.max(productRight, productLeft);
    	}
    	else {
    		return product1;
    	}   	
    }
    
    private int product(int[] nums, int start, int end){
    	if (start > end){
    		return Integer.MIN_VALUE;
    	}
    	
    	int product = nums[start];
    	for (int i = start + 1; i <= end; ++i){
    		product *= nums[i];
    	}
    	return product;
    }

    public static void main(String[] args){
    	MaximumProductSubarray ps = new MaximumProductSubarray();
    	
    	int[] nums = new int[]{0, 2, -1, 0, -2};
    	System.out.println(ps.maxProduct3(nums));
    }
}