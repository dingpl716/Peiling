package dp;
import java.util.Set;

//	Given a string s and a dictionary of words dict, determine if 
//	s can be segmented into a space-separated sequence of one or more dictionary words.
//	
//	For example, given
//	s = "leetcode",
//	dict = ["leet", "code"].
//	
//	Return true because "leetcode" can be segmented as "leet code".

// dfs
public class WordBreak {
	
	// DFS 超时了
    public boolean wordBreak1(String s, Set<String> dict) {
    	if(dict == null || dict.size() == 0)
    		return false;
    	if(s == null || s.length() == 0)
    		return true;
    	
        for(int i=1; i<=s.length(); ++i) {
        	if(wordBreak(s.substring(0, i), s.substring(i), dict) == true)	
				return true;
        }
        
        return false;
    }
    
    /**
     * 如果，sub在dict里面，那么递归，把rest切分了然后递归
     * @param sub
     * @param rest
     * @param dict
     * @return
     */
    private boolean wordBreak(String sub, String rest, Set<String> dict) {
    	if(dict.contains(sub)) {
    		if(rest == null || rest.length() == 0)
    			return true;
    		for(int i=1; i<=rest.length(); ++i) {
    			if(wordBreak(rest.substring(0, i), rest.substring(i), dict) == true)	
    				return true;
    		}
    		return false;
    	}else
    		return false;
    }
    
    
//    一个DP问题。定义possible[i] 为S字符串上[0,i]的子串是否可以被segmented by dictionary.
//
//    那么
//
//    possible[i]	  = true      if  S[0,i]在dictionary里面
//
//                    = true      if   possible[k] == true 并且 S[k+1,i]在dictionary里面， 0 <= k < i
//
//                    = false     if    no such k exist.
    public boolean wordBreak(String s, Set<String> dict) {
    	boolean[] p = new boolean[s.length()];
    	for(int i=0; i<s.length(); ++i)
    		p[i] = false;
    	
    	for(int i=0; i<s.length(); ++i) {
    		if(dict.contains(s.substring(0, i+1))) {
    			p[i] = true;
    			continue;
    		}
    		for(int k=0; k<i; ++k) {
   				p[i] = p[k] && dict.contains(s.substring(k+1, i+1));
   				if(p[i])
   					break;
    		}
    	}
    	return p[p.length-1];
    }
}
