package string_array;
//	Given a string s consists of upper/lower-case alphabets 
//	and empty space characters ' ', return the length of last word in the string.
//	
//	If the last word does not exist, return 0.
//	
//	Note: A word is defined as a character sequence 
//	consists of non-space characters only.
//	
//	For example, 
//	Given s = "Hello World",
//	return 5.
//	N种情况： *表示任意多个空格
//	"*word"
//	"word*"
//	"*word*"
//	""
//	"*"
//	"word"
public class LengthOfLastWord {
    public int lengthOfLastWord(String s) {
        if(s == null || s.length() == 0)
            return 0;
            
        int right = s.length()-1;
        
        while(right >= 0) {
            if( s.charAt(right) != ' ')
                break;
            else
                --right;
        }
        if(right < 0)
            return 0;

        int left = right;
        while(left >= 0) {
            if(s.charAt(left) != ' ')
                --left;
            else
                break;
        }
        
        return right - left;
    }
}
