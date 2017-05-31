package string_array;
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
    public int candy1(int[] ratings) {
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


    /*********** 新解法 *****************/
    /**
     * 先找到rating里面的所有极小值。所谓极小值就是一个点同时小于或等于其左右两边的值。
     * 找到这些极小值之后，对每一个极小值做以下事情：
     * 1. 给这个极小值发一个糖果。
     * 2. 如果左边有点那么往左边发糖果，一直发到一个极大值rating为止。每次如果rating
     *    上涨，就增加一个糖果，不然就保持糖果不变。（糖果不可能减小）。如果在一个孩子身上
     *    已经发过一个糖果，那么比对现在发的糖果和他已经拥有的糖果，取较大者
     * 3. 如果右边有小孩，往右边以同样的策略发糖果。
     * 4. 求出所有小孩的糖果的和。
     * 注：两个极小值之间只可能有一个极大值。
     * @param ratings
     * @return
     */
    public int candy(int[] ratings) {
    	if (ratings == null || ratings.length == 0){
    		return 0;
    	}
    	
    	if (ratings.length == 1){
    		return 1;
    	}
    	
    	// 表示每个孩子会拿到的糖果数
    	int[] candies = new int[ratings.length];
    	
    	// The indexes of minimas, not the values of them.
    	ArrayList<Integer> minimas = findMinimas(ratings);
    	
    	if (minimas.size() == 1) {
    		candies[minimas.get(0)] = 1;
    		distributeCandyLeft(minimas.get(0), -1, ratings, candies);
    		distributeCandyRight(minimas.get(0), ratings.length + 1,  ratings, candies);
    	}
    	else {
    		for(int i = 0; i < minimas.size() ; ++i){
    			// 为每个极小值对应的孩子发一个糖果。
    			int minimaRatingIndex = minimas.get(i);
    			candies[minimaRatingIndex] = 1;
    			
    			// 往其左右两边发糖果。
    			if (i == 0){
    				distributeCandyLeft(minimaRatingIndex, -1, ratings, candies);
    				distributeCandyRight(minimaRatingIndex, minimas.get(i + 1),  ratings, candies);
    			} else if (i == minimas.size() - 1){
    				distributeCandyLeft(minimaRatingIndex, minimas.get(i - 1), ratings, candies);
    				distributeCandyRight(minimaRatingIndex, ratings.length + 1,  ratings, candies);
    			} else {
    				distributeCandyLeft(minimaRatingIndex, minimas.get(i - 1), ratings, candies);
    				distributeCandyRight(minimaRatingIndex, minimas.size() + 1,  ratings, candies);
    			}
    		}
    	}
    	
    	// 最后求和
    	int result = 0;
    	for (int candy : candies){
    		result += candy;
    	}
    	
    	return result;
    }
    
    /**
     * 找到rating里面的极小值的坐标
     * @param ratings
     * @return int[]，里面是rating里面所有极小值的坐标。
     */
    private ArrayList<Integer> findMinimas(int[] ratings){
    	
    	// Worst case: all values in ratings are the same, then all points are minimas.
    	ArrayList<Integer> minimas = new ArrayList<Integer>(); 
    	
    	for (int i = 0; i < ratings.length; ++i){
    		if (i == 0){
    			if (ratings[i] <= ratings[i + 1]) {
    				minimas.add(i);
    			}
    		}
    		else if (i == ratings.length - 1){
				if (ratings[i] <= ratings[i - 1]){
					minimas.add(i);
				}
    		}
    		else {
    			if (ratings[i] <= ratings[i - 1]
				&& ratings[i] <= ratings[i + 1]){
    				minimas.add(i);
    			}
    		}
    	}
    	
    	return minimas;
    }

    /**
     * 往minimaRatingIndex左边的孩子派发糖果。
     * @param minimaRatingIndex rating极小值的坐标
     * @param previousMinimaRatingIndex minimaRatingIndex之前的那个极小值的坐标
     * @param ratings 
     * @param candies
     */
    private void distributeCandyLeft(int minimaRatingIndex, int previousMinimaRatingIndex, int[] ratings, int[] candies){
    	// 两个个坐标是相互挨着的，我们每一次都尝试着为leftIndex发糖果。
    	int rightIndex = minimaRatingIndex;
    	int leftIndex = rightIndex - 1;
    	
    	// 此处发糖果的时候不能超过前一个极小值。
    	while (leftIndex >= 0 && leftIndex > previousMinimaRatingIndex){
    		if (ratings[leftIndex] < ratings[rightIndex]){
    			break;
    		} else if (ratings[leftIndex] == ratings[rightIndex]){
    			candies[leftIndex] = Math.max(candies[leftIndex], candies[rightIndex]);
    		} else {
    			candies[leftIndex] = Math.max(candies[leftIndex], candies[rightIndex] + 1);
    		}
    		
    		--rightIndex;
    		--leftIndex; 
    	}
    }
    
    /**
     * 往minimaRatingIndex右边的孩子派发糖果。
     * @param minimaRatingIndex rating极小值的坐标
     * @param ratings 
     * @param candies
     */
    private void distributeCandyRight(int minimaRatingIndex, int nextMinimaRatingIndex, int[] ratings, int[] candies){
    	// 两个个坐标是相互挨着的，我们每一次都尝试着为rightIndex发糖果。
    	int leftIndex = minimaRatingIndex;
    	int rightIndex = leftIndex + 1;
    	
    	while (rightIndex < ratings.length && rightIndex < nextMinimaRatingIndex){
    		if (ratings[rightIndex] < ratings[leftIndex]){
    			break;
    		} else if (ratings[rightIndex] == ratings[leftIndex]){
    			candies[rightIndex] = Math.max(candies[rightIndex], candies[leftIndex]);
    		} else {
    			candies[rightIndex] = Math.max(candies[rightIndex], candies[leftIndex] + 1);
    		}
    		
    		++rightIndex;
    		++leftIndex; 
    	}
    }

    public static void main(String[] args) {
    	int[] ratings = new int[]{1, 2, 4, 4, 3};
    	Candy candy = new Candy();
    	int result = candy.candy(ratings);
    	System.out.println(result);
    }
}
