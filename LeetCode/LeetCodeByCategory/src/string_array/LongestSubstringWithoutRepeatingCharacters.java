package string_array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

//	Given a string, find the length of the longest substring without repeating characters. 
//	For example, the longest substring without repeating letters for "abcabcbb" is "abc", 
//	which the length is 3. 
//	For "bbbbb" the longest substring is "b", with the length of 1.

public class LongestSubstringWithoutRepeatingCharacters {
	
	/*
	 * 错误想法，用一个hashset来维护visitedLetter上，如果遇见了重复的
	 * 字母，那么就清空这个集合，然后从重复的这个点开始重新，计算。
	 * 错误例子： a b c a d ，如果在第二个a清空集合，重新开始计算的话，那么返回的
	 * 最长子串是abc，但实际上是bcad
	 */
    public int lengthOfLongestSubstring1(String s) {
    	if(s == null || s.length() == 0)
    		return 0;
    	if(s.length() == 1)
    		return 1;
    	
    	int start = 0;
    	int maxLength = 0;
    	int runner = 0;
    	Set<Character> visitedLetters = new HashSet<Character>();
    	while(runner < s.length()) {
    		if(visitedLetters.contains(s.charAt(runner))) {
    			start = runner;
    			visitedLetters.clear();
    			continue;
    		}
    		
    		visitedLetters.add(s.charAt(runner));
    		maxLength = maxLength > (runner - start + 1) ? maxLength : (runner - start + 1);
    		++runner;
    	}
    	return maxLength;
    }
    
    /*
     * 正确做法，用LinkedHashMap<Char, Index>来维护visitedLetter
     * 如果遇到重复的，比如说x，那么就好把map里面x以前，包括x的所有项
     * 给删掉，然后从x的下一项开始重新计算
     * LettCode 里面不能用LinkedHashMap
     */
    public int lengthOfLongestSubstring2(String s) {
    	if(s == null || s.length() == 0)
    		return 0;
    	if(s.length() == 1)
    		return 1;
    	int start = 0;
    	int maxLength = 0;
    	int runner = 0;
    	Map<Character, Integer> visitedLetters = new LinkedHashMap<Character, Integer>();
    	while(runner < s.length()) {
    		Character c = s.charAt(runner);
    		if(visitedLetters.containsKey(c)) {
    			int newStart = visitedLetters.get(c) + 1;
    			Iterator<Map.Entry<Character, Integer>> itr=visitedLetters.entrySet().iterator();
    			for(int i=0; i<=(newStart - start); ++i) {
    				itr.next();
    				itr.remove();
    			}
    			visitedLetters.put(s.charAt(runner), runner);
    			start = newStart;
    			++runner;
    		}else {
    			visitedLetters.put(s.charAt(runner), runner);
    			maxLength = maxLength > (runner - start + 1) ? maxLength : (runner - start + 1);
        		++runner;
    		}
    	}
    	
    	return maxLength;
    }
    
    /*
     * LettCode 里面不能用LinkedHashMap,改用一般hashmap
     */
    public int lengthOfLongestSubstring3(String s) {
    	if(s == null || s.length() == 0)
    		return 0;
    	if(s.length() == 1)
    		return 1;
    	int start = 0;
    	int maxLength = 0;
    	int runner = 0;
    	Map<Character, Integer> visitedLetters = new HashMap<Character, Integer>();
    	while(runner < s.length()) {
    		Character c = s.charAt(runner);
    		if(visitedLetters.containsKey(c)) {
    			int dupIndex = visitedLetters.get(c);
    			for(Iterator<Map.Entry<Character, Integer>> itr=visitedLetters.entrySet().iterator(); itr.hasNext(); ) {
    				Map.Entry<Character, Integer> e = itr.next();
    				if(e.getValue() <= dupIndex)
    				itr.remove();
    			}
    			visitedLetters.put(c, runner);
    			start = dupIndex + 1; //！！记得更新start
    			++runner; // !!记得更新runner
    		}else {
    			visitedLetters.put(c, runner);
    			maxLength = maxLength > (runner - start + 1) ? maxLength : (runner - start + 1);
        		++runner;
    		}
    	}
    	
    	return maxLength;
    }
    
    /*
     * 可以不用hashmap，直接用一个数组来处理，好处在于
     * 如果遇到重复的元素，我们必须遍历一次hashmap来删除重复之前包括
     * 重复的字符。但是如果用数组的话，只需要用一个start来维护这个
     * 数组里面哪个区间是我们真正想要的即可。具体做法如下：
     * 1.建立一个数组，数组的下标表示这个字符的ASCII码，元素表示其在字符串中的位置即可
     * 2.start是在原字符串中的下标，用来表示子串的起始位置
     * 3.对于这个数据，初始值是-1，当我们遇到一个新的字符c，这个字符c重复的条件是：
     * table[c] >= start，即在start之后出现过才算，因为start之前的我们视为被删除领导元素
     */
    public int lengthOfLongestSubstring(String s) {
		int length = s.length();
		if (length == 0) {
			return 0;
		}
		int [] countTable = new int[256];
		Arrays.fill(countTable, -1);
		int max = 1;
		int start = 0;
		int runner = 1;
		
		countTable[s.charAt(0)] = 0;
		while (runner < length) {
			//Has reached a duplicate char
			if (countTable[s.charAt(runner)] >= start) {
				start = countTable[s.charAt(runner)] + 1;				
			}
			max = Math.max(max, runner - start + 1);
			countTable[s.charAt(runner)] = runner;  //!!! 这里要更新字符出现的位置！！
			runner++;
		}
		return max;
	}
}
