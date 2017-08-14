package string_array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import util.Util;

//	Given a list of unique words, find all pairs of distinct indices (i, j)
//	in the given list, so that the concatenation of the two words, 
//	i.e. words[i] + words[j] is a palindrome.
//	
//	Example 1:
//	Given words = ["bat", "tab", "cat"]
//	Return [[0, 1], [1, 0]]
//	The palindromes are ["battab", "tabbat"]
//	Example 2:
//	Given words = ["abcd", "dcba", "lls", "s", "sssll"]
//	Return [[0, 1], [1, 0], [3, 2], [2, 4]]
//	The palindromes are ["dcbaabcd", "abcddcba", "slls", "llssssll"]

/**
 * 核心思想：map
 * 1. 首先把整个array映射了，string -> index的映射
 * 2. 对于每一个单词，我们都把它分成[0,j] [j+1,n]左右两个部分，0<=j<=n
 * 3. 如果左半部分是palindrome，并且map里面有右半部分的reverse，那么这两个index是可以的
 * 4. 对右半部分做同样的操作。
 * 5. 总时间复杂度O(n*k^2) k为单词的平均长度
 * 
 * @author Dingp
 *
 */
public class PalindromePairs {
	
	public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        
        if (words == null || words.length < 2) {
        	return result;
        }
        
        Map<String, Integer> map = new HashMap<String, Integer>();
        for (int i = 0; i < words.length; ++i) {
        	map.put(words[i], i);
        }
        
        for (int i = 0; i < words.length; ++i) {
        	String word = words[i];
        	for (int j = 0; j <= word.length(); ++j) {
        		String left = word.substring(0,j);
        		String right = word.substring(j);
        		
        		if(isPalindrome(left)) {
        			String rightReverse = new StringBuilder(right).reverse().toString();
        			
        			if (map.containsKey(rightReverse)) {
        				int otherIndex = map.get(rightReverse);
        				if (i != otherIndex) {
        					List<Integer> pair = new ArrayList<Integer>();
        					pair.add(otherIndex);
        					pair.add(i);
        					result.add(pair);
        				}
        			}
        		}
        		
        		// 假设现在word = aaa
        		// 当j = 0 的时候, left = "", right = "aaa"
        		// 当j = 4的时候， left = "aaa", right = ""
        		if(isPalindrome(right) && !right.isEmpty()) {
        			String leftReverse = new StringBuilder(left).reverse().toString();
        			if (map.containsKey(leftReverse)) {
        				int otherIndex = map.get(leftReverse);
        				if (i != otherIndex){
        					List<Integer> pair = new ArrayList<Integer>();
        					pair.add(i);
        					pair.add(otherIndex);
        					result.add(pair);
        				}
        			}
        		}
        	}
        }
        
        return result;
    }
	
	private boolean isPalindrome(String word) {
		int left = 0;
		int right = word.length() - 1;
		
		while(left < right) {
			if (word.charAt(left++) != word.charAt(right--)){
				return false;
			}
		}
		
		return true;
	}
	
	public static void main(String[] args) {
		PalindromePairs p = new PalindromePairs();
		//String[] words = {"abcd", "dcba", "lls", "s", "sssll"};
		String[] words = {"a", ""};
		List<List<Integer>> results = p.palindromePairs(words);
		Util.printListOfList(results);
	}
}
