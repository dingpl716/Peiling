package string_array;
import java.util.ArrayList;

//	Given an input string, reverse the string word by word.
//	
//	For example,
//	Given s = "the sky is blue",
//	return "blue is sky the".
//	
//	click to show clarification.
//	
//	Clarification:
//	What constitutes a word?
//	A sequence of non-space characters constitutes a word.
//	Could the input string contain leading or trailing spaces?
//	Yes. However, your reversed string should not contain leading or trailing spaces.
//	How about multiple spaces between two words?
//	Reduce them to a single space in the reversed string.

//	已犯错误：
//	1. 忘记在词与词之间加空格
//	2. 只有不为空的token才需要附加一个空格
public class ReverseWordsInAString {
	
    public String reverseWords(String s) {
    	s = trim(s);
    	ArrayList<String> tokens = split(s, " ");
    	StringBuffer sb = new StringBuffer();
    	
    	if(tokens.size() != 0)
    		sb.append(tokens.get(tokens.size()-1));
    	for(int i=tokens.size()-2; i>=0; --i) {
    		if(tokens.get(i).length() != 0) {
    			sb.append(" ");
    			sb.append(tokens.get(i));
    		}
    	}
    	
    	return sb.toString();
    }
    
    private String trim(String s) {
    	int i;
    	for(i=0; i<s.length(); ++i) {
    		if(s.charAt(i) != ' ')
    			break;
    	}
    	if(i == s.length())
    		return "";
    	int j;
    	for(j=s.length()-1; j>=0; --j) {
    		if(s.charAt(j) != ' ')
    			break;
    	}
    	
    	return s.substring(i, j+1);
    }
    
    public ArrayList<String> split(String target, String exp) {
    	ArrayList<String> result = new ArrayList<String>();
    	int expStart = 0;
    	int wordStart = 0;
    	
    	while(expStart <= target.length() - exp.length()) {
    		if(expStart == target.length() - exp.length()){
    			result.add(target.substring(wordStart));
    			break;
    		}
    		if(target.subSequence(expStart, expStart+exp.length()).equals(exp)) {
    			result.add(target.substring(wordStart,expStart));
    			expStart += exp.length();
    			wordStart = expStart;
    		}else {
    			++expStart;
    		}
    	}
    	return result;
    }

}
