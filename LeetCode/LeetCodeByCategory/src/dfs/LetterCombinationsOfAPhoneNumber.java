package dfs;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import util.Util;

//	Given a digit string, return all possible letter combinations that the number could represent.
//	
//	A mapping of digit to letters (just like on the telephone buttons) is given below.
//	
//	Input:Digit string "23"
//	Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].


public class LetterCombinationsOfAPhoneNumber {
	private static Map<Character, String[]> digit2Letter;
	{
		digit2Letter = new HashMap<Character, String[]>();
        digit2Letter.put('1', new String[]{""});
        digit2Letter.put('2', new String[]{"a","b", "c"});
        digit2Letter.put('3', new String[]{"d","e", "f"});
        digit2Letter.put('4', new String[]{"g","h", "i"});
        digit2Letter.put('5', new String[]{"j","k", "l"});
        digit2Letter.put('6', new String[]{"m","n", "o"});
        digit2Letter.put('7', new String[]{"p","q", "r", "s"});
        digit2Letter.put('8', new String[]{"t", "u","v"});
        digit2Letter.put('9', new String[]{"w","x", "y", "z"});
        digit2Letter.put('0', new String[]{" "});
	}
	
    public ArrayList<String> letterCombinations(String digits) {
        
        ArrayList<String> result = new ArrayList<String>();
        result.add("");
        if(digits == null || digits.length() == 0)
        	return result;
        StringBuffer buffer = new StringBuffer();
        letterCombinations(digits, buffer, result);
        return result;
    }
    
    private void letterCombinations(String digits, StringBuffer buffer, 
    		ArrayList<String> result) {
    	
    	if(buffer.length() == digits.length()) {
    		result.add(buffer.toString());
    		return ;
    	}
    	String[] letters = digit2Letter.get(digits.charAt(buffer.length()));
    	for(int i=0; i<letters.length; ++i) {
    		buffer.append(letters[i]);
    		letterCombinations(digits, buffer, result);
    		buffer.deleteCharAt(buffer.length()-1);
    	}
    }
    
    public static void main(String[] args) {
    	LetterCombinationsOfAPhoneNumber l = new LetterCombinationsOfAPhoneNumber();
    	String digits = "23";
    	List<String> result = l.letterCombinations(digits);
    	Util.printList(result);
    }
}
