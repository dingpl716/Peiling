package string_array;

import java.util.HashMap;
import java.util.Map;

//	Given a string, determine if a permutation of the string could form a palindrome.
//	
//	For example,
//	"code" -> False, "aab" -> True, "carerac" -> True.

/**
 * 核心思想:
 * 能排列成palindrome的string里面，只能有小于等于1个出现频率为奇数次的letter
 * @author Dingp
 *
 */
public class PalindromePermutation {
	
	public boolean canPermutePalindrome(String s) {
		
		if (s == null || s.length() == 0) {
			return true;
		}
		
		Map<Character, Integer> map = getLetterAppearance(s);
		int oddLetter = 0;
		for(Character c : map.keySet()) {
			int appearance = map.get(c);
			if ((appearance & 1) == 1) {
				++oddLetter;
				if (oddLetter > 1) {
					return false;
				}
			}
		}
		
		return true;
    }
	
	private Map<Character, Integer> getLetterAppearance(String s) {
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		
		for (char c : s.toCharArray()) {
			if (map.containsKey(c)) {
				map.replace(c, map.get(c) + 1);
			} else {
				map.put(c, 1);
			}
		}
		
		return map;
	}
}
