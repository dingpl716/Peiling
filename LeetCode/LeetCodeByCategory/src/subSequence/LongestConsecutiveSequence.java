package subSequence;
import java.util.HashSet;
import java.util.Set;

//	Given an unsorted array of integers, find the length
//	of the longest consecutive elements sequence.
//	
//	For example,
//	Given [100, 4, 200, 1, 3, 2],
//	The longest consecutive elements sequence 
//	is [1, 2, 3, 4]. Return its length: 4.
//	
//	Your algorithm should run in O(n) complexity.

public class LongestConsecutiveSequence {

//	建立一个num——>index的map
//	然后扫描这个num数组，没遇到一个num
//	我们就先查看num-1在不在map里面，如果在看num-2在不在，
//	一直这样循环到找不到为止，
//	然后看num+1,同样循环。
//	在我每遇到一个数之后就删除之，以免重复
    public int longestConsecutive(int[] num) {
    	if(num == null || num.length == 0)
    		return 0;
    	Set<Integer> map = new HashSet<Integer>();
    	for(int i=0; i<num.length; ++i)
    		map.add(num[i]);
    	int maxLength = 1;
    	for(int i=0; i<num.length; ++i) {
    		int number = num[i];
    		int length = 1;
    		while(map.contains(number-1)) {
    			++length;
    			map.remove(number - 1);
    			number = number - 1;
    		}
    		number = num[i];
    		while(map.contains(number + 1)){
    			++length;
    			map.remove(number + 1);
    			number = number + 1;
    		}
    		if(length > maxLength)
    			maxLength = length;
    		map.remove(num[i]);
    	}
    	return maxLength;
    }
    
    
    
	/**
	 * 此方法只适合数组中的数全部大于零的情况
	 * @param num
	 * @return
	 */
    public int longestConsecutive2(int[] num) {
        if(num == null || num.length == 0)
            return 0;
        if(num.length == 1)
            return 1;
        int max = getMax(num);
        int[] map = new int[max];
        // record the appearance of each number
        for(int i=0; i<num.length; ++i)
            ++map[num[i]];
        return getLongestConsecutive(map);
    }
    
    private int getMax(int[] num) {
        int max = num[0];
        for(int i=1; i<num.length; ++i) {
            if(max < num[i])
                max = num[i];
        }
        return max;
    }
    
    private int getLongestConsecutive(int[] map) {
        int result = 0;
        int start = 0;
        int end = 0;
        
        while(true) {
            if(start >= map.length)
                break;
            //move start
            while(start < map.length && map[start] == 0)
                ++start;
            //move end
            end = start + 1;
            while(end < map.length && map[end] != 0)
                ++end;
            result = (end-start) > result ? (end-start) : result;
            start = end;
        }
        return result;
    }
}
