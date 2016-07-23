package string_array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

//	Given an array S of n integers, are there elements a, b, c, 
//	and d in S such that a + b + c + d = target? Find all unique 
//	quadruplets in the array which gives the sum of target.
//	
//	Note:
//	Elements in a quadruplet (a,b,c,d) must be in non-descending order. (ie, a ≤ b ≤ c ≤ d)
//	The solution set must not contain duplicate quadruplets.
//	    For example, given array S = {1 0 -1 0 -2 2}, and target = 0.
//	
//	    A solution set is:
//	    (-1,  0, 0, 1)
//	    (-2, -1, 1, 2)
//	    (-2,  0, 0, 2)
public class Sum4 {
//	思路：n^2 的复杂度
//	首先把所有pair Hash了，sum(num[i], num[j]) -> (i, j)
//	然后就是two sum的方法
	
	private static class Quadruplet {
		int n1;
		int n2;
		int n3;
		int n4;
		
		public Quadruplet(int n1, int n2, int n3, int n4) {
			this.n1 = n1;
			this.n2 = n2;
			this.n3 = n3;
			this.n4 = n4;
		}
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + n1;
			result = prime * result + n2;
			result = prime * result + n3;
			result = prime * result + n4;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Quadruplet other = (Quadruplet) obj;
			if (n1 != other.n1)
				return false;
			if (n2 != other.n2)
				return false;
			if (n3 != other.n3)
				return false;
			if (n4 != other.n4)
				return false;
			return true;
		}
		
		
	}
    public ArrayList<ArrayList<Integer>> fourSum(int[] num, int target) {
    	Set<Quadruplet> result = new HashSet<>();
    	if(num == null || num.length < 4)
    		return new ArrayList<ArrayList<Integer>>();
    	// hash all the pairs, there are n^2 pairs totally
    	Map<Integer, ArrayList<int []>> map = hashPairs(num);
    	
    	for(Iterator<Map.Entry<Integer, ArrayList<int []>>> itr = map.entrySet().iterator(); itr.hasNext();) {
    		Map.Entry<Integer, ArrayList<int []>> entry1 = itr.next();
    		int n1 = entry1.getKey();
    		ArrayList<int []> pairs1 = entry1.getValue();
    		if(pairs1 == null)
    			continue;
    		if(map.containsKey(target - n1)) {
    			ArrayList<int []> pairs2 = map.get(target - n1);
    			for(int i=0; i<pairs1.size(); ++i) {
    				int[] pair1 = pairs1.get(i); 
    				for(int j=0; j<pairs2.size(); ++j) {
    					int[] pair2 = pairs2.get(j);
    					if(isNotOverlaped(pair1, pair2)) {
    						addToResult(result, num, pair1, pair2);
    					}
    				}
    			}
    			itr.remove();
    		}
    	}
    	return parseResult(result);
    }
    
    private Map<Integer, ArrayList<int []>> hashPairs(int[] num) {
    	Map<Integer, ArrayList<int []>> map = new HashMap<Integer, ArrayList<int []>>();
    	for(int i=0; i<num.length-1; ++i) {
    		for(int j=i+1; j<num.length; ++j) {
    			int sum = num[i] + num[j];
    			if(map.containsKey(sum)) {
    				ArrayList<int[]> pairs = map.get(sum);
					pairs.add(new int[]{i, j});
    			}else {
    				ArrayList<int[]> pairs = new ArrayList<int[]>();
    				pairs.add(new int[]{i, j});
    				map.put(sum, pairs);
    			}
    		}
    	}
    	
    	return map;
    }
    
    private boolean isNotOverlaped(int[] pair1, int[] pair2) {
    	return pair1[0] != pair2[0] && 
    		   pair1[0] != pair2[1] && 
    		   pair1[1] != pair2[0] && 
    		   pair1[1] != pair2[1];
    }
    
    private void addToResult(Set<Quadruplet> result, int[] num, int[] pair1, int[] pair2) {
    	int[] tmp = new int[]{num[pair1[0]], num[pair1[1]], num[pair2[0]], num[pair2[1]]};
    	Arrays.sort(tmp);
    	Quadruplet quadruplet = new Quadruplet(tmp[0], tmp[1], tmp[2], tmp[3]);
    	result.add(quadruplet);
    }
    
    private ArrayList<ArrayList<Integer>> parseResult(Set<Quadruplet> tmp) {
    	ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
    	for(Iterator<Quadruplet> itr = tmp.iterator(); itr.hasNext();) {
    		Quadruplet q = itr.next();
    		ArrayList<Integer> quad = new ArrayList<Integer>();
    		quad.add(q.n1);
    		quad.add(q.n2);
    		quad.add(q.n3);
    		quad.add(q.n4);
    		result.add(quad);
    	}
    	
    	return result;
    }
}
