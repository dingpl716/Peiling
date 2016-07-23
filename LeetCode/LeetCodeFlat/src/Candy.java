import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;

//	There are N children standing in a line. Each child is assigned a rating value.
//	
//	You are giving candies to these children subjected to the following requirements:
//	
//	Each child must have at least one candy.
//	Children with a higher rating get more candies than their neighbors.
//	What is the minimum candies you must give?

// solution:
//	1.或得一个rating -> list of (index)的map,
//	这个map是根据rating排序的
//	2.从低到高遍历这个map,
//	取得每一个对应的list，
//	遍历这个list,对每一个index，找到他周围的最大的糖果数，然后再这基础上加1
// 	初始时所有糖果数都是0
public class Candy {
    public int candy(int[] ratings) {
        if(ratings == null || ratings.length == 0)
        	return 0;
        if(ratings.length == 1)
        	return 1;
        
        int[] tmp = new int[ratings.length];
    	for(int i=0; i<tmp.length; ++i)
    		tmp[i] = 0;
    	
    	TreeMap<Integer, ArrayList<Integer>> map = new TreeMap<Integer, ArrayList<Integer>>();
        for(int i=0; i<ratings.length; ++i) {
        	ArrayList<Integer> indexes = map.get(ratings[i]);
        	if(indexes == null)
        		indexes = new ArrayList<Integer>();
    		indexes.add(i);
        	map.put(ratings[i], indexes);
        }
        
        for(Iterator<Map.Entry<Integer, ArrayList<Integer>>> i=map.entrySet().iterator(); i.hasNext();) {
        	Map.Entry<Integer, ArrayList<Integer>> e = i.next();
        	assignCandy(ratings, tmp, e.getKey(), e.getValue());
        }
        
        int result = 0;
        for(int i=0; i<tmp.length; ++i) 
        	result += tmp[i];
        return result;
    }
    
    // boundary case: ratings = [3,3,3,3,3,3,3] or ratings = [5,5,5,3,3,3]; 
    // ratings = [1,2,2,2,2]
    private void assignCandy(int[] ratings, int[] tmp, int rating, List<Integer> indexes) {
    	for(Iterator<Integer> i=indexes.iterator(); i.hasNext(); ) {
    		int index = i.next();
    		if(index > 0 && index < tmp.length-1 ) {
    			if(rating == ratings[index-1]) {
//    				如果后面一个已经被赋值了，说明后面的rating一定比目前的小，所以目前的应该比后面的多1个
//    				如果后面的没有被复制，说明后面的rating一定比目前的大，而且后面的为0，而目前的应该为1
    				tmp[index] = tmp[index + 1] + 1;
    			}else {
    				tmp[index] = Math.max(tmp[index-1], tmp[index+1]) + 1;
    			}
    		} else if (index == 0) { 
//    			如果tmp[1]已经被赋值了，那么说明tmp[1]的rating一定比tmp[0]的大，所以直接对tmp[0]附上tmp[1]+1
//    			如果tmp[1]还没被复制，那么tmp[1]的rating小于tmp[0]，且此时tmp[1]=0,而tmp[0]应该为1
    			tmp[index] = tmp[index+1] + 1;
    		} else {
//    			如果最后一个人小于等于前面一个，那么按题意，他应该为1
    			if(rating <= ratings[index - 1])
    				tmp[index] = 1;
    			else
//    				如果最后一个人的rating大于前面一个，那么他应该等于前面一个加1
    				tmp[index] = tmp[index - 1] + 1;
    		}
    	}
    }
}
