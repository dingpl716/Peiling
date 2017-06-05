package string_array;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

//	Find the length of the longest substring T of a given string (consists of lowercase letters only) such that every character in T appears no less than k times.
//	
//	Example 1:
//	
//	Input:
//	s = "aaabb", k = 3
//	
//	Output:
//	3
//	
//	The longest substring is "aaa", as 'a' is repeated 3 times.
//	Example 2:
//	
//	Input:
//	s = "ababbc", k = 2
//	
//	Output:
//	5
//	
//	The longest substring is "ababb", as 'a' is repeated 2 times and 'b' is repeated 3 times.

/**
 * 双指针一头一尾开始。
 * 初始的时候，i指向开头，j指向末尾，并且用一个map来记录从i到j的substring中每一个char的出现次数
 * 当map中的char全部大于等于k，或者为0的时候返回结果。
 * 当i and/or j指向一个不大于k的char时，i++ and/or j--
 * 当i与j都指向一个满足条件的char时，（但此时i到j并不满足条件）做以下事情：
 * 如果i像右移动m步到达第一个不满足条件的char，j像左移动n步到达第一个不满足条件的char，如果m<n移动i，反之移动j
 * 如果m=n，同时移动i和j。此举是为了尽可能少的排除满足条件的char
 * 
 * 
 * @author Dingp
 *
 */
public class LongestSubstringWithAtLeastKRepeatingCharacters {

	public int longestSubstring(String s, int k) {
        int result = 0;
        HashMap<Character, Integer> appearances = initMap(s);
        
        int i = 0;
        int j = s.length() - 1;
        
        while((j - i + 1) >= k){
        	
        	// 挪动i，让i指向满足条件的char，同时也排除了那些不满足条件的char
        	while(i < s.length() && appearances.get(s.charAt(i)) < k){
        		appearances.put(s.charAt(i), appearances.get(s.charAt(i)) - 1);
        		++i;
        	}
        	
        	// 挪动j，让j指向满足条件的char，同时也排除了那些不满足条件的char
        	while(j >=0 && appearances.get(s.charAt(j)) < k){
        		appearances.put(s.charAt(j), appearances.get(s.charAt(j)) - 1);
        		--j;
        	}
        	
        	if (i > j){
        		return 0;
        	}
        	
        	if (meetRequirement(appearances, k)){
        		// 此时i，j都指向满足条件的char，如果此时整个[i,j]都满足条件的话，返回
            	return j - i + 1;
            }
        	else {
        		// 此时i，j都指向满足条件的char，
        		// 但是如果i到j里面有不符合条件的char，那么我们需要考虑到底移动哪一个
        		int rightSteps = getSteps(s, appearances, k, i, true);
        		int leftSteps = getSteps(s, appearances, k, j, false);
        		
        		if (rightSteps > leftSteps){
        			j = moveSteps(s, appearances, j, leftSteps, false);
        		}
        		else {
        			i = moveSteps(s, appearances, i, rightSteps, true);
        		}
        	}
        }
        
        return 0;
    }
	
	private boolean meetRequirement(Map<Character, Integer> appearances, int k){
		for (char c = 'a'; c <= 'z'; ++c){
			if (appearances.get(c) != 0 && appearances.get(c) < k){
				return false;
			}
		}
		
		return true;
	}
	
	private HashMap<Character, Integer> initMap(String s){
		HashMap<Character, Integer> appearances = new HashMap<Character, Integer>();
		
		for (char c = 'a'; c <= 'z'; ++c){
			appearances.put(c, 0);
		}
		
		for (char c : s.toCharArray()){
			appearances.put(c, appearances.get(c) + 1);
		}
		
		return appearances;
	}

	/**
	 * Get the steps to move to reach the first char whose appearance is smaller than k.
	 * @param s The string.
	 * @param appearances The appearance of chars.
	 * @param k The target k.
	 * @param start The starting point.
	 * @param moveRight A boolean to indicate the moving direction.
	 * @return
	 */
	private int getSteps(String s, Map<Character, Integer> appearances, int k, int start, boolean moveRight){
		int result = 0;
		
		while(0 <= start && start < s.length() &&
				appearances.get(s.charAt(start)) >= k){
			++result;
			if (moveRight){
				++start;
			}
			else {
				--start;
			}
		}
		
		return result;
	}

	private int moveSteps(String s, Map<Character, Integer> appearances, int start, int steps, boolean moveRight){
		
		for (int i = 0; i < steps; ++i){
			appearances.put(s.charAt(start), appearances.get(s.charAt(start)) - 1);
			if (moveRight){
				++start;
			}
			else {
				--start;
			}
		}
		
		return start;
	}

	public static void main(String[] args){
		String s = "ababacb";
		int k = 3;
		
		LongestSubstringWithAtLeastKRepeatingCharacters l = new LongestSubstringWithAtLeastKRepeatingCharacters();
		
		System.out.println(l.longestSubstring(s, k));
	}
}
