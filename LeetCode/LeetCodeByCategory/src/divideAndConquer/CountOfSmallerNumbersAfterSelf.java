package divideAndConquer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//	You are given an integer array nums and you have to return a new counts array. 
//	The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].
//	
//	Example:
//	
//	Given nums = [5, 2, 6, 1]
//	
//	To the right of 5 there are 2 smaller elements (2 and 1).
//	To the right of 2 there is only 1 smaller element (1).
//	To the right of 6 there is 1 smaller element (1).
//	To the right of 1 there is 0 smaller element.
//	Return the array [2, 1, 1, 0].

/**
 *  
 * @author peding
 *
 */

public class CountOfSmallerNumbersAfterSelf {

    public List<Integer> countSmaller(int[] nums) {

    	if ( nums == null || nums.length == 0) {
    		return new ArrayList<Integer>();
    	}
    	
    	Integer[] count = new Integer[nums.length];
    	Integer[] indexes = new Integer[nums.length];
    	
    	mergeSort(nums, indexes, count, 0, nums.length - 1);
    	
    	return Arrays.asList(count);
    }
    
    private void mergeSort(int[] nums, Integer[] indexes, Integer count[], int start, int end) {
    	if (start == end) {
    		return;
    	}
    	
    	int mid = (start + end) / 2;
    	
    	mergeSort(nums, indexes, count, start, mid);
    	mergeSort(nums, indexes, count, mid + 1, end);
    	
    	merge(nums, indexes, count, start, end);
    }
    
    /**
     * 
     * @param nums 原始数组。 nums是无序的，是有nums[indexes]才是有序的
     * @param indexes 这个数组里面存储的是nums数组的坐标, 我们按坐标对应的数的大小来排列这些坐标
     * @param count count[i] 表示在nums里面，i 以后的比nums[i] 小的数的个数，也是最后的返回结果
     * @param start nums的index，也是indexes数组的物理坐标
     * @param end nums的index，也是indexes数组的物理坐标
     */
    private void merge(int[] nums, Integer[] indexes, Integer[] count, int start, int end) {
    	int mid = (start + end) / 2;
    	
    	// indexes数组的物理坐标，用来merge indexes数组中的前后两断
    	int leftIndexInIndexes = start;
    	int rightIndexInIndexes = mid + 1;
    	
    	// 新的indexes数组
    	Integer[] newIndexes = new Integer[end - start + 1];
    	
    	// new indexes 数组中的坐标，用来做merge时，向newIndexes数组中插入数据用
    	int indexInNewIndexes = 0;
    	
    	// 用来记录从右半部分插入到新indexes数组中的数的个数
    	int numbersFromRight = 0;
    	
    	// 从这儿开始做merger
    	// 先交叉insert left 和 right到newIndexes里面
    	while (leftIndexInIndexes <= mid && rightIndexInIndexes <= end) {
    		
    		// 先从indexes数组中取出nums数组的坐标
    		int leftIndexInNums = indexes[leftIndexInIndexes];
    		int rightIndexInNums = indexes[rightIndexInIndexes];
    		
    		// 实际上比较的是nums中元素的大小
    		// 如果右侧对应的值小，那么把右侧的坐标插入到newIndexes里面
    		if(nums[leftIndexInNums] > nums[rightIndexInNums]){
    			newIndexes[indexInNewIndexes] = rightIndexInNums;
    			rightIndexInIndexes++;
    			
    			numbersFromRight++;
    		}
    		else {
    			newIndexes[indexInNewIndexes] = leftIndexInNums;
    			leftIndexInIndexes++;
    			
    			count[leftIndexInNums] += numbersFromRight;
    		}
    		
    		indexInNewIndexes++;
    	}
    	
    	// 如果left部分还有剩余，全部插入到newIndexes里面
    	
    	
    	// 如果right部分还有剩余，全部插入到newIndexes里面
    }
}
