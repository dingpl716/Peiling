package graph;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Queue;

//	Given two words (start and end), and a dictionary, 
//	find the length of shortest transformation sequence from start to end, such that:
//	
//	Only one letter can be changed at a time
//	Each intermediate word must exist in the dictionary
//	For example,
//	
//	Given:
//	start = "hit"
//	end = "cog"
//	dict = ["hot","dot","dog","lot","log"]
//	As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
//	return its length 5.
//	
//	Note:
//	Return 0 if there is no such transformation sequence.
//	All words have the same length.
//	All words contain only lowercase alphabetic characters.

// 用BFS， 快于DFS，因为BFS只要遇到了target，那么肯定就是最短的
public class WordLadder {
	
    public int ladderLength1(String start, String end, HashSet<String> dict) {
        if(start.equals(end))
        	return 0;
        if(dict == null || dict.size() == 0)
        	return 0;
        
        ArrayList<String> source = new ArrayList<String>();
        ArrayList<String> target = new ArrayList<String>();
        HashSet<String> met = new HashSet<String>();
        source.add(start);
        int result = 1;
        while(!source.isEmpty()) {
        	++result;
    	    // 因为hashset的查找速率为1，所以在dict里面找距离为1的词的时候，不要遍历这个hashset，不然的话会做 start.size * dict.size次比较
    	    // 应该改为，对每一个字符试着从a到z替换，然后看替换之后的词在set里面没，这样只用做start.size * 26次比较
    		for(Iterator<String> itr=source.iterator(); itr.hasNext();) {
    			String current = itr.next();
    			char[] chars = current.toCharArray();
    			for(int i=0; i<chars.length; ++i) {
    				for(char c='a'; c<='z'; ++c) { // 需要用c来替换i
    					if(c == chars[i])
    						continue;
    					char tmp = chars[i];
    					chars[i] = c;
    					String newStr = new String(chars);
    					if(newStr.equals(end))
    						return result;
    					if(dict.contains(newStr) && !met.contains(newStr)) {
    						met.add(newStr);
    						target.add(newStr);
    					}
    					chars[i] = tmp;
    				}
    			}
    		}
    		// swap source and target
    		source.clear();
    		ArrayList<String> tmp = target;
    		target = source;
    		source = tmp;
        }
        return 0;
    }
}
