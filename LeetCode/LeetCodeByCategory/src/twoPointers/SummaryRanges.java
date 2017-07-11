package twoPointers;

import java.util.ArrayList;
import java.util.List;

//	Given a sorted integer array without duplicates, return the summary of its ranges.
//	
//	For example, given [0,1,2,4,5,7], return ["0->2","4->5","7"].

/**
 * 核心思想: 双指针
 * 一个指向开头，一个在跑，然后不停的和前一个比较，
 * 如果有断点，不连续，则说明一个range出现
 * 
 * @author peding
 *
 */
public class SummaryRanges {
	
    public List<String> summaryRanges(int[] nums) {
        List<String> results = new ArrayList<String>();
    	
    	if (nums == null || nums.length == 0) {
        	return results;
        }
    	
    	if (nums.length == 1) {
    		results.add(Integer.toString(nums[0]));
    		return results;
    	}
    	
    	int start = 0;
    	int runner = start + 1;
    	int previousNumber = nums[start];
    	
    	while (runner < nums.length) {
    		if (nums[runner] == previousNumber + 1) {
    			previousNumber = nums[runner];
    			++runner;
    			continue;
    		} else {
    			String result = nums[start] + "";
    			if (runner > start + 1) {
    				result  = result + "->" + nums[runner - 1];
    			}
    			
    			results.add(result);
    			
    			start = runner;
    			previousNumber = nums[start];
    			++runner;
    		}
    	}
    	
    	if (start < nums.length) {
    		String result = nums[start] + "";
    		
    		if (start < nums.length - 1) {
    			result = result + "->" + nums[nums.length-1];
    		}
    		
    		results.add(result);
    	}
    	
    	return results;
    }
}
