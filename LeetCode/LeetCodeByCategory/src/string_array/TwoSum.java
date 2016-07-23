package string_array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

//	Given an array of integers, find two numbers such that 
//	they add up to a specific target number.
//	
//	The function twoSum should return indices of the two numbers 
//	such that they add up to the target, where index1 must be less 
//	than index2. Please note that your returned answers (both index1 and index2) are not zero-based.
//	
//	You may assume that each input would have exactly one solution.
//	
//	Input: numbers={2, 7, 11, 15}, target=9
//	Output: index1=1, index2=2

public class TwoSum {
//	哈希法的局限：不能处理有重复数字的情况
//	解决方法：如果遇到重复的key那么看这两个key相加是不是等于target，
//	如果是那么直接返回，如果是更新了也无所谓
	
//	最好的实现：
//	从0开始遍历数组，如果在map里面能找到target-numbers[i]
//	那么直接把这个对应的坐标取出来，在把目前的i放到result里面就可以返回了
//	如果没有target-numbers[i]，那么就放进去好了
//	我们不需要先全部hash之后，再检查是不是有符合条件的数，可以编hash边搜索
    public int[] twoSum(int[] numbers, int target) {
    	if(numbers == null || numbers.length<2)
    		return null;
    	
    	Map<Integer, Integer> map = new HashMap<Integer, Integer>();
    	int[] result = new int[2];
    	
    	for(int i=0; i<numbers.length; ++i) {
    		if(map.containsKey(target - numbers[i])) {
    				result[0] = map.get(target - numbers[i]) + 1;
    				result[1] = i + 1;
    				return result;
    		}else {
    			map.put(numbers[i], i);
    		}
    	}
    	
    	return result;
    }
    
    //解法二： 先排序，然后双指针，但是会很麻烦，因为排完序之后原始数组里面的坐标信息就全丢失了
    // 所以还需要用一个类来打包原数组中index和value，麻烦
    public int[] twoSum1(int[] numbers, int target) {
    	if(numbers == null || numbers.length<2)
    		return null;
    	
    	int[] newNumbers = new int[numbers.length];
    	for(int i=0; i<numbers.length; ++i)
    		newNumbers[i] = numbers[i];
    	Arrays.sort(newNumbers);
    	int i=0;
    	int j=numbers.length-1;
    	int[] result = new int[2];
    	while(i<=j) {
    		int tmp = newNumbers[i] + newNumbers[j];
    		if(tmp == target) {
    			result[0] = i;
    			result[1] = j;
    			break;
    		}else if (tmp < target)
    			++i;
    		else
    			--j;
    	}
    	
    	return result;
    }
}
