package string_array;
import java.util.ArrayList;
import java.util.Arrays;

//	Given an array S of n integers, are there elements a, b, c in S 
//	such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.
//	
//	Note:
//	Elements in a triplet (a,b,c) must be in non-descending order. (ie, a ≤ b ≤ c)
//	The solution set must not contain duplicate triplets.
//	    For example, given array S = {-1 0 1 2 -1 -4},
//	
//	    A solution set is:
//	    (-1, 0, 1)
//	    (-1, -1, 2)

//先排序，然后DFS
public class Sum3 {
	
    public ArrayList<ArrayList<Integer>> threeSum1(int[] num) {
    	ArrayList<ArrayList<Integer>> results = new ArrayList<ArrayList<Integer>>();
    	ArrayList<Integer> tmp = new ArrayList<Integer>();
    	if(num == null || num.length < 3) {
    		return results;
    	}
    	
    	Arrays.sort(num);
    	if(num[0] > 0 || num[num.length-1] < 0) {
    		return results;
    	}
    	dfs(num, 0, tmp, results);
		return results;
    }
    
    private void dfs(int[] num, int start, ArrayList<Integer> tmp,
    		ArrayList<ArrayList<Integer>> results) {
    	if(sum(tmp) > 0)
			return;
    	if(tmp.size() == 3) {
    		if(tmp.get(0) + tmp.get(1) + tmp.get(2) == 0)
    			results.add(new ArrayList<Integer>(tmp));
    		return;
    	}
    	for(int i=start; i<=num.length-3+tmp.size(); ++i) {
    		tmp.add(num[i]);
    		dfs(num, i+1, tmp, results);
    		tmp.remove(tmp.size()-1);
    		while(i<num.length-1 && num[i] == num[i+1])
    			++i;
    	}
    }
    
    private int sum(ArrayList<Integer> a) {
    	int result = 0;
    	for(Integer i : a)
    		result +=i;
    	return result;
    }
    
    // 双指针法
//    为了去重，必须对left和i进行重复性检查
    public ArrayList<ArrayList<Integer>> threeSum(int[] num) {
    	ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
    	if(num == null || num.length < 3)
    		return result;
    	
    	Arrays.sort(num);
    	for(int left=0; left<=num.length-3; ++left) {
    		int right = num.length-1;
    		for(int i=left+1; i<right;) {
    			int sum = num[left]+num[i]+num[right];
    			if(sum == 0){
    				ArrayList<Integer> buff = new ArrayList<Integer>();
    				buff.add(num[left]);
    				buff.add(num[i]);
    				buff.add(num[right]);
    				result.add(buff);
    				int j = i+1;
    				while(j<num.length && num[i] == num[j])
    					++j;
    				i=j;
    			}else if(sum > 0)
    				--right;
    			 else 
    				++i;
    		}
    		while(left<=num.length-2 && num[left] == num[left+1])
    			++left;
    	}
    	return result;
    }
}
