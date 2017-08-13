package string_array;

//	Given an input string, reverse the string word by word. 
//	A word is defined as a sequence of non-space characters.
//	
//	The input string does not contain leading or trailing 
//	spaces and the words are always separated by a single space.
//	
//	For example,
//	Given s = "the sky is blue",
//	return "blue is sky the".
//	
//	Could you do it in-place without allocating extra space?
//	
//	Related problem: Rotate Array

/**
 * 核心思想:
 * 1. 先reverse整个句子
 * 2. 然后reverse逐个单词
 * 
 * Given s =        "the sky is blue",
 * reverse整个句子：  "eulb si yks eht"
 * reverse每个单词：  "blue is sky the"
 * 
 * @author Dingp
 *
 */
public class ReverseWordsInAStringII {
    
	public void reverseWords(char[] s) {
        if (s == null || s.length < 2) {
        	return;
        }
        
        reverse(s, 0, s.length - 1);
        
        int start = 0;
        
        while (start < s.length) {
        	int end = start + 1;
        	while(end < s.length && s[end] != ' ') {
        		++end;
        	}
        	
        	reverse(s, start, end - 1);
        	start = end + 1;
        }
    }
	
	
	private void reverse(char[] s, int left, int right) {
		while (left < right) {
			char tmp = s[left];
			s[left] = s[right];
			s[right] = tmp;
			++left;
			--right;
		}
	}
}
