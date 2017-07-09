package twoPointers;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

//	You are given a string, S, and a list of words, L, 
//	that are all of the same length. Find all starting 
//	indices of substring(s) in S that is a concatenation 
//	of each word in L exactly once and without any intervening characters.
//	
//	For example, given:
//	S: "barfoothefoobarman"
//	L: ["foo", "bar"]
//	
//	You should return the indices: [0,9].
//	(order does not matter).

public class SubstringWithConcatenationOfAllWords {
	
//	思路：
//	1.需要一个start来记录起始位置，这个start会被放进result里面
//	2.一个wordStart来记录每个单词的起始位置，然后一个runner，这个wordstart和runner就代表了一个单词
//	3.一个数组来记录当前L被匹配的情况，当所有都被匹配之后，把start加入list里面
//	4.当把start加入到list后，他需要挪到下一个可能的符合条件的位置上。
    public ArrayList<Integer> findSubstring(String S, String[] L) {
    	ArrayList<Integer> result = new ArrayList<Integer>();
        if(S == null || S.length() == 0)
        	return result;
        if(L == null || L.length == 0)
        	return result;
        
        int totalLength = totalLength(L);
        // i stands for start
        for(int i=0; i<= S.length() - totalLength; ++i) {
        	Map<String, Integer> map = initMap(L);
        	int nonZeroCount = map.size();
        	int wordStart = i;
        	for(int runner = i+1; runner <= i+totalLength; ++runner) {
        		String word = S.substring(wordStart, runner);
        		if(map.containsKey(word)) {
        			// 如果这个词的频率为0，说明之前我们已经map过这个词了，那么如果他再次出现，说明他是多余的
        			int currentFreq = map.get(word);
        			if(currentFreq == 0)
        				break;
        			else if(currentFreq == 1) {
        				map.put(word, currentFreq - 1);
        				--nonZeroCount;
        				if(nonZeroCount == 0)
        					result.add(i);
        			}else {
        				map.put(word, currentFreq - 1);
        			}
        			wordStart = runner;
        			++runner;
        		}
        	}
        }
        
        return result;
    }
    
    private Map<String, Integer> initMap(String[] L) {
    	Map<String, Integer> map = new HashMap<String, Integer>();
    	for(String l : L) {
    		if(map.containsKey(l))
    			map.put(l, map.get(l) + 1);
    		else
    			map.put(l, 1);
    	}
    	
    	return map;
    }
    
    private int totalLength(String[] L) {
    	int result = 0;
    	for(String l: L)
    		result += l.length();
    	
    	return result;
    }
}
