package dfs;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import util.Util;


public class CombinationsAndPermutations {
	
/**
 *  Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.
	For example,
	If n = 4 and k = 2, a solution is: 
	[
	  [2,4],
	  [3,4],
	  [2,3],
	  [1,2],
	  [1,3],
	  [1,4],
	]
	从1到n里面选k个数出来进行组合，然后打印出所有的可能
 * @author Peiling
 *
 */
    public List<List<Integer>> combination(int n, int k) {
    	List<List<Integer>> cb = new ArrayList<List<Integer>>();
        List<Integer> buff = new ArrayList<Integer>();
        if(n * k == 0)
            return cb;
        
        dfsCombination(n, k, 0, cb, buff);
        return cb;
    }
    
    /**
     * 
     * @param n
     * @param k
     * @param preLevelStart 表示上一次选的那个数是从第几开始的
     * @param results
     * @param buff
     */
    private void dfsCombination(int n, int k, int preLevleStart,
    		List<List<Integer>> results,
            List<Integer> buff) {
    	
    	if (buff.size() == k){
    		results.add(new ArrayList<Integer>(buff));
    		return;
    	}
    	
        // 因为是组合 所以只管看后面的数，不用管前面的数了，比如在1,2,3,4,5,6,7里面
        // 如果这次我选了3，那么下一个数就只能从4开始选了，并且还必须给后面
        // 的数流出足够多的数来，比如一共选3个数，我第二个数就只能选到6，不能选7了
    	// k - buff.size(); 还需要选的数
    	// n - i + 1 剩下的数
    	for (int i = preLevleStart + 1; n - i + 1>= k - buff.size(); ++i) {
    		buff.add(i);
    		dfsCombination(n, k, i, results, buff);
    		buff.remove(buff.size() - 1);
    	}
    }
    
    /**
     * 从1到n里面选k个数出来进行组合，然后打印出所有的可能
     * 如 n = 3, k = 2
     * output: 1,2	1,3	2,3	2,1	3,1	3,2
     * @param n
     * @param k
     * @return
     */
    public ArrayList<ArrayList<Integer>> permutate(int n, int k) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        if(n * k == 0)
            return result;
        ArrayList<Integer> buffer = new ArrayList<Integer>();
        Set<Integer> had = new HashSet<Integer>();
        choosePer(n, k, buffer, had, result);
        return result;
    }
    
    // dfs
    private void choosePer(int n, int k, ArrayList<Integer> buffer,
		    Set<Integer> had, ArrayList<ArrayList<Integer>> result) {
    	if(buffer.size() == k) {
    		result.add(new ArrayList<Integer>(buffer));
    		return;
    	}
    	for(int i=1; i<=n; ++i) {
    		if(had.contains(i))
    			continue;
    		buffer.add(i);
    		had.add(i);
    		choosePer(n, k, buffer, had, result);
    		buffer.remove(buffer.size()-1);
    		had.remove(i);
    	}
    }
    
    public static void main(String[] args) {
    	CombinationsAndPermutations c = new CombinationsAndPermutations();
    	List<List<Integer>> comb = c.combination(4, 2);

    	Util.printListOfList(comb);
    }
}
