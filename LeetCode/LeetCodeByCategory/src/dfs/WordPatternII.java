package dfs;

import java.util.HashSet;
import java.util.Set;

//	Given a pattern and a string str, find if str follows the same pattern.
//	
//	Here follow means a full match, such that there is a bijection between 
//	a letter in pattern and a non-empty substring in str.
//	
//	Examples:
//	pattern = "abab", str = "redblueredblue" should return true.
//	pattern = "aaaa", str = "asdasdasdasd" should return true.
//	pattern = "aabb", str = "xyzabcxzyabc" should return false.
//	Notes:
//	You may assume both pattern and str contains only lowercase letters.

/**
 * 核心想法：dfs加剪枝，这道题其实就是做partition，可以参考WordBreak 和 PalindromePartitioning来看
 * 以pattern = "abab", str = "redblueredblue" should return true. 为例
 * 先让a map to r, b map to e, 然后发现 a 不能再map to d， 此时让 b map to ed
 * @author Dingp
 *
 */
public class WordPatternII {
	
	public boolean wordPatternMatch(String pattern, String str) {
		
		if (pattern == null && str == null) {
			return true;
		}
		
		if (pattern == null || str == null) {
			return false;
		}
		
		// 在这做了两次优化，
		// letter2Word可以直接用一个string数组，而不需要用map
		// word2Letter可以只有一个set，而不需要用map
		String[] letter2Word  = new String[26];
		Set<String> word2Letter = new HashSet<String>();
		
		return dfs(pattern, 0, str, 0, letter2Word, word2Letter, false);
    }
	
	private boolean dfs(String pattern, int patternIndex, String str, int strIndex,
			String[] letter2Word,
			Set<String> word2Letter,
			boolean found) {
		if (found) {
			return true;
		} else if (patternIndex >= pattern.length() && strIndex >= str.length()) {
			return true;
		} else if (patternIndex >= pattern.length() || strIndex >= str.length()) {
			return found;
		} else {
			Character letter = pattern.charAt(patternIndex);
			
			for (int i = strIndex; i < str.length(); ++i) {
				String word = str.substring(strIndex, i + 1);
				
				// 如果已经map过这个letter了
				if (letter2Word[letter - 'a'] != null) {
					String mappedWord = letter2Word[letter - 'a'];
					
					// 如果 letter -> word 和 word -> letter 正好map过了，那么往下一层走. 
					// 这个时候不在i++了，因为i++之后的新的word一定不能再等于mappedWord了
					if (mappedWord.equals(word)) {
						found = dfs(pattern, patternIndex + 1, str, i + 1, letter2Word, word2Letter, found);
						return found;
					} else if (mappedWord.startsWith(word)) {
						// 如果 mappedWord starts with word, 那么i++，例如已存在 a -> xyz，而此时word = xy
						continue;
					} else {
						// 如果 mappedWord 不 starts with word，那么不需要再让i++了，因为我们不需要再验证之后的word了
						break;
					}
				}
				
				// 如果已经map过这个word了,因为前面implementation的关系，此时letter一定没有被映射过，那么i++
				if (word2Letter.contains(word)) {
					continue;
				}
				
				// 如果letter -> word, word -> letter 都不存在，那么映射之，再往下一层走
				letter2Word[letter -'a'] = word;
				word2Letter.add(word);
				
				found = dfs(pattern, patternIndex + 1, str, i + 1, letter2Word, word2Letter, found);
				
				letter2Word[letter -'a'] = null;
				word2Letter.remove(word);
				
				if (found) {
					return found;
				}
			}
			
			return found;
		}
	}
	
	public static void main(String[] args) {
		WordPatternII w = new WordPatternII();
		
		String pattern1 = "abab"; String str1 = "redblueredblue";
		String pattern2 = "aaaa"; String str2 = "asdasdasdasd";
		String pattern3 = "aabb"; String str3 = "xyzabcxzyabc";
		System.out.println(w.wordPatternMatch(pattern1, str1));
		System.out.println(w.wordPatternMatch(pattern2, str2));
		System.out.println(w.wordPatternMatch(pattern3, str3));
	}
}
