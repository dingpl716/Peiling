package string_array;

import java.util.HashMap;
import java.util.Map;

//	Given a string you need to print longest possible substring that has exactly M unique characters. If there are more than one substring of longest possible length, then print any one of them.
//	
//	Examples:
//	
//	"aabbcc", k = 1
//	Max substring can be any one from {"aa" , "bb" , "cc"}.
//	
//	"aabbcc", k = 2
//	Max substring can be any one from {"aabb" , "bbcc"}.
//	
//	"aabbcc", k = 3
//	There are substrings with exactly 3 unique characters
//	{"aabbcc" , "abbcc" , "aabbc" , "abbc" }
//	Max is "aabbcc" with length 6.
//	
//	"aaabbb", k = 3
//	There are only two unique characters, thus show error message.

/**
 * 参照 LongestSubstringWithAtLeastKRepeatingCharacters
 * 双指针，一头一尾。
 * 先用一个map记录下每个character的情况，当map里面刚好有k个不同的character的时候返回
 * 双指针i,j一头一尾扫描，如果i指向的char的出现次数少则i++，否则j--
 * @author Dingp
 *
 */
public class LongestSubstringWithAtMostKDistinctCharacters {

	public String kUniques(String s, int k){
		if (s == null || s.length() == 0){
			return s;
		}
		
		if (k < 1) {
			return "";
		}
		
		Map<Character, Integer> charMap = initCharMap(s);
		
		if (charMap.size() < k){
			return null;
		}
		else if (charMap.size() == k){
			return s;
		}
		
		int i = 0;
		int j = s.length() - 1;
		
		while (i <= j){
			if (charMap.get(s.charAt(i)) < charMap.get(s.charAt(j))){
				removeFromCharMap(charMap, s.charAt(i));
				++i;
			}
			else {
				removeFromCharMap(charMap, s.charAt(j));
				--j;
			}
			
			if (charMap.size() == k){
				return s.substring(i, j+1);
			}
		}
		
		return null;
	}
	
	private Map<Character, Integer> initCharMap(String s){
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		
		for (char c : s.toCharArray()){
			if (map.containsKey(c)){
				map.put(c, map.get(c) + 1);
			}
			else {
				map.put(c, 1);
			}
		}
		
		return map;
	}
	
	private void removeFromCharMap(Map<Character, Integer> map, char c){
		if (map.get(c) == 1){
			map.remove(c);
		}
		else {
			map.put(c, map.get(c) - 1);
		}
	}

	public static void main(String[] args){
		LongestSubstringWithAtMostKDistinctCharacters l = new LongestSubstringWithAtMostKDistinctCharacters();
		
		String s1 = "aabbcc";
		String s2 = "abbccccccccccccc";
		String s3 = "abbbbbbbbbbbbbbc";
		String s4 = "ababababa";
		
		System.out.println(l.kUniques(s1, 1));
		System.out.println(l.kUniques(s1, 2));
		System.out.println(l.kUniques(s1, 3));
		
		System.out.println("===================================");
		
		System.out.println(l.kUniques(s2, 1));
		System.out.println(l.kUniques(s2, 2));
		System.out.println(l.kUniques(s2, 3));
		System.out.println("===================================");
		
		System.out.println(l.kUniques(s3, 1));
		System.out.println(l.kUniques(s3, 2));
		System.out.println(l.kUniques(s3, 3));
		System.out.println("===================================");
		
		System.out.println(l.kUniques(s4, 1));
		System.out.println(l.kUniques(s4, 2));
		System.out.println(l.kUniques(s4, 3));
		
	}
}
