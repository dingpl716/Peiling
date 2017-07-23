package dfs;
import java.util.ArrayList;
import java.util.Arrays;

//	Given a set of distinct integers, S, return all possible subsets.
//	
//	Note:
//	Elements in a subset must be in non-descending order.
//	The solution set must not contain duplicate subsets.
//	For example,
//	If S = [1,2,3], a solution is:
//	[
//	 [3],
//	 [1],
//	 [2],
//	 [1,2,3],
//	 [1,3],
//	 [2,3],
//	 [1,2],
//	 []
//	]
public class Subsets {
    
    // dfs
    // 用一个buffer来存目前的状况
    // 任何时候都可以把这个buffer加入的result里面
    // 用一个start来标目前的情况，只要不超过数组长度，我就可以往buffer里面加东西
    public ArrayList<ArrayList<Integer>> subsets(int[] S) {
    	ArrayList<Integer> buffer = new ArrayList<Integer>();
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        Arrays.sort(S);
        dfs(S, 0, buffer, result);
    	return result;
    }
    
    private void dfs(int[] S, int start, ArrayList<Integer> buffer, ArrayList<ArrayList<Integer>> result) {
    	result.add(new ArrayList<Integer>(buffer));
    	if(start == S.length)
    		return;
    	for(int i=start; i<S.length; ++i) {
    		buffer.add(S[i]);
    		dfs(S, i+1, buffer, result);
    		buffer.remove(buffer.size()-1);
    	}
    }
    
	/**
	 * 不好的方法
	 * @param S
	 * @return
	 */
    public ArrayList<ArrayList<Integer>> subsets2(int[] S) {
        ArrayList<Integer> empty = new ArrayList<Integer>();
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        result.add(empty);  // empty set is always a sub set
        
        if(S == null || S.length == 0)
            return result;
        
        Arrays.sort(S);    
        // the array itself is also a subset
        result.add(arrayToList(S));
        
        // enumerate all the combinations, i is the number of intergers 
        // that are going to be choosed
        for(int i=1; i<S.length; ++i) 
            choose(S, i, result);
        
        return result;
    }
    
    private ArrayList<Integer> arrayToList(int[] s) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        for(int i=0; i<s.length; ++i) 
            list.add(s[i]);
        
        return list;
    }
    
    // choose k numbers in s, and put them into result
    private void choose(int[] s, int k, ArrayList<ArrayList<Integer>> result) {
        ArrayList<Integer> tmp = new ArrayList<Integer>();
        choose(s, k, 0, -1, result, tmp);
    }
    
    
    private void choose(int[] s, int k, int hasChosen, int preStart,  
                    ArrayList<ArrayList<Integer>> result, ArrayList<Integer> tmp) {
        if(hasChosen >= k) {
            result.add(new ArrayList<Integer>(tmp));
            return;
        }else {
            for(int i=preStart+1; i<=s.length+hasChosen-k; ++i) {
                tmp.add(s[i]);
                choose(s, k, hasChosen+1, i, result, tmp);
                tmp.remove(tmp.size()-1);
            }
        }
    }

}
