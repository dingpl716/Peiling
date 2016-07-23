package dfs;
import java.util.ArrayList;
import java.util.Arrays;
//	Given a collection of integers that might contain duplicates, S, 
//	return all possible subsets.
//	
//	Note:
//	Elements in a subset must be in non-descending order.
//	The solution set must not contain duplicate subsets.
//	For example,
//	If S = [1,2,2], a solution is:
//	
//	[
//	  [2],
//	  [1],
//	  [1,2,2],
//	  [2,2],
//	  [1,2],
//	  []
//	]

public class SubsetsII {
    public ArrayList<ArrayList<Integer>> subsetsWithDup(int[] num) {
        ArrayList<Integer> empty = new ArrayList<Integer>();
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> tmp = new ArrayList<Integer>();
        
        result.add(empty);  // empty set is always a sub set
        
        if(num == null || num.length == 0)
            return result;
        
        Arrays.sort(num);    
        choose(num, -1, result, tmp);
        return result;
    }
    
    /**
     * DFS choose
     * @param s
     * @param k
     * @param hasChosen
     * @param preStart
     * @param result
     * @param tmp
     */
    private void choose(int[] s,  int preStart, ArrayList<ArrayList<Integer>> result, 
    				ArrayList<Integer> tmp) {
    	
        for(int i=preStart+1; i<s.length; ++i) {
            tmp.add(s[i]);
            result.add(new ArrayList<Integer> (tmp));
            choose(s, i, result, tmp);
            tmp.remove(tmp.size()-1);
            while(i<s.length-1 && s[i] == s[i+1]) // remove duplicate elements
            	++i;
        }
    }
}
