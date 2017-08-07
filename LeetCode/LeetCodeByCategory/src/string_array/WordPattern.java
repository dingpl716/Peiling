package string_array;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

//	Given a pattern and a string str, find if str follows the same pattern.
//	
//	Here follow means a full match, such that there is a bijection 
//	between a letter in pattern and a non-empty word in str.
//	
//	Examples:
//	pattern = "abba", str = "dog cat cat dog" should return true.
//	pattern = "abba", str = "dog cat cat fish" should return false.
//	pattern = "aaaa", str = "dog cat cat dog" should return false.
//	pattern = "abba", str = "dog dog dog dog" should return false.
//	Notes:
//	You may assume pattern contains only lowercase letters, 
//	and str contains lowercase letters separated by a single space.

public class WordPattern {

    public boolean wordPattern(String pattern, String str) {
        if (pattern == null && str == null) {
        	return true;
        }
        
        if (pattern == null || str == null) {
        	return false;
        }
    	
    	Map<Character, String> letter2Word = new HashMap<Character, String>();
    	Map<String, Character> word2Letter = new HashMap<String, Character>();
    	
    	String[] words = str.trim().split(" ");
    	if (pattern.length() != words.length) {
    		return false;
    	}
    	
    	for (int i = 0; i < words.length; ++i) {
    		char letter = pattern.charAt(i);
    		String word = words[i];
    		
    		if (letter2Word.containsKey(letter) || word2Letter.containsKey(word)) {
    			
    			String mappedWord = null;
    			if (letter2Word.containsKey(letter)) {
    				mappedWord = letter2Word.get(letter);
    			}
    			
    			Character mappedLetter = null;
    			if (word2Letter.containsKey(word)) {
    				mappedLetter = word2Letter.get(word); 
    			}
    			
    			// 注意 这里不能写成 mappedLetter != letter, 或者 letter != mappedLetter
    			// 这样写会有NullPointerException
    			if (mappedLetter == null || !word.equals(mappedWord)) {
    				return false;
    			}
    			
    		} else {
    			letter2Word.put(letter, word);
    			word2Letter.put(word, letter);
    			
    		}
    	}
    	
    	return true;
    }
    
    public static void main(String[] args) {
    	WordPattern w = new WordPattern();
    	String pattern = "abba";
    	String str = "dog cat cat fish";
    	System.out.println(w.wordPattern(pattern, str));
    }
}
