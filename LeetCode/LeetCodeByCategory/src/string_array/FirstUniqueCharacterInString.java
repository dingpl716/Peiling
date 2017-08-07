package string_array;

import java.util.Arrays;

//	Given a string, find the first non-repeating character in it and 
//	return it's index. If it doesn't exist, return -1.
//	
//	Examples:
//	
//	s = "leetcode"
//	return 0.
//	
//	s = "loveleetcode",
//	return 2.
//	Note: You may assume the string contain only lowercase letters.

/**
 * 核心思想，用一个数组存储每一个char出现的位置，
 * 一开始都为-1
 * 如果出现一次那么置为i所对应的位置
 * 如果数组中的值小于i且大于等于0，说明已经出现过，那么标为-2
 * 最后返回第一个大于等于0的数
 * @author Dingp
 *
 */
public class FirstUniqueCharacterInString {
    
	public int firstUniqChar(String s) {
        if (s == null || s.length() == 0) {
        	return -1;
        }
        
        int[] table = new int[26];
        Arrays.fill(table, -1);
        
        for (int i = 0; i < s.length(); ++i) {
        	int index = s.charAt(i) - 'a';
        	if (table[index] == -1) {
        		table[index] = i;
        	} else if (table[index] >=0 && table[index] < i) {
        		table[index] = -2;
        	}
        }
        
        int result = -1;
        for (int index : table) {
        	if (index >= 0) {
        		if (result < 0) {
        			result = index;
        		} else {
        			result = Math.min(result, index);
        		}
        	}
        }
        
        return result;
    }
}
