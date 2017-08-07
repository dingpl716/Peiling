package string_array;

//	Given two strings s and t, write a function to determine if t is an anagram of s.
//	
//	For example,
//	s = "anagram", t = "nagaram", return true.
//	s = "rat", t = "car", return false.
//	
//	Note:
//	You may assume the string contains only lowercase alphabets.
//	
//	Follow up:
//	What if the inputs contain unicode characters? How would you adapt your solution to such case?
		
public class ValidAnagram {

    public boolean isAnagram(String s, String t) {
    	if (s == null && t == null) {
    		return true;
    	} else if (s == null || t == null) {
    		return false;
    	} else if (s.length() != t.length()){
    		return false;
    	} else {
    		int[] chars1 = getChars(s);
    		int[] chars2 = getChars(t);
    		
    		return areEqual(chars1, chars2);
    	}
    }
    
    private int[] getChars(String s) {
    	int[] result = new int[26];
    	for (int i = 0; i < s.length(); ++i) {
    		++result[s.charAt(i) - 'a'];
    	}
    	
    	return result;
    }
    
    private boolean areEqual(int[] table1, int[] table2) {
    	for (int i = 0; i < table1.length; ++i) {
    		if (table1[i] != table2[i]) {
    			return false;
    		}
    	}
    	
    	return true;
    }
}
