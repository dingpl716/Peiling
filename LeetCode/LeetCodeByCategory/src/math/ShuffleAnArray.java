package math;

import java.util.Random;

/**
 * 让第x个元素和前面的0到x-1个元素中的一个等概率的swap
 * @author Dingp
 *
 */
public class ShuffleAnArray {

	private int[] copyOfOriginalArray = null;
	private Random random = null;
	
    public ShuffleAnArray(int[] nums) {
    	random = new Random();
    	if (nums != null){
    		this.copyOfOriginalArray = nums.clone();
    	}
    }
    
    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        return this.copyOfOriginalArray;
    }
    
    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
    	int[] result = copyOfOriginalArray.clone();
        for (int i = 1; i < result.length; ++i){
        	int j = random.nextInt(i + 1);
        	swap(result, i, j);
        }
        
        return result;
    }
    
    private void swap(int[] array, int i, int j){
    	int tmp = array[i];
    	array[i] = array[j];
    	array[j]= tmp;
    }
}
