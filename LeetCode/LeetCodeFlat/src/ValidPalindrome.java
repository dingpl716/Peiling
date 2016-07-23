//Given a string, determine if it is a palindrome, 
//considering only alphanumeric characters and ignoring cases.
//
//For example,
//"A man, a plan, a canal: Panama" is a palindrome.
//"race a car" is not a palindrome.
public class ValidPalindrome {
//	注意：
//	空字符串，
//	全是invalid的char
    public boolean isPalindrome(String s) {
        if(s == null || s.length() < 2)
        	return true;
        
        int left = 0;
        int right = s.length()-1;
        while(left <= right) {
        	while(left < s.length() && !isChar(s.charAt(left)))
        		++left;
        	while(right >= 0 && !isChar(s.charAt(right)))
        		--right;
        	if(left > s.length() - 1 || right < 0)
        		break;
        	Character lc = s.charAt(left);
        	Character rc = s.charAt(right);
        	if(Character.toLowerCase(lc) != Character.toLowerCase(rc))
        		return false;
        	++left;
        	--right;
        }
        return true;
    }
    
    private boolean isChar(char c) {
    	return (('a' <= c && c <= 'z') || 
    			('A' <= c && c <= 'Z') ||
    			('0' <= c && c <= '9'));
    }
}
