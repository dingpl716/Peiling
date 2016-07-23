//	Given a string S, find the longest palindromic substring in S. 
//	You may assume that the maximum length of S is 1000, 
//	and there exists one unique longest palindromic substring.
public class LongestPalindromicSubstring {

    public String longestPalindrome(String s) {
    	String result = "";
        for(int i=0; i<s.length(); ++i) {
        	String tmp = getPalindromeSubString(s, i);
        	if(tmp.length() > result.length())
        		result = tmp;
        }
        
        for(int i=0; i<s.length()-1; ++i){
        	String tmp = getPalindromeSubString(s, i, i+1);
        	if(tmp.length() > result.length())
        		result = tmp;
        }
        
        return result;
    }
    
    private String getPalindromeSubString(String s, int middle) {
    	int left = middle;
		int right = middle;
    	while(left >=0 && right <s.length() && s.charAt(left) == s.charAt(right)){
    		--left;
    		++right;
    	}
    	return s.substring(left+1, right);
    }
    
    private String getPalindromeSubString(String s, int left, int right) {
    	while(left >=0 && right <s.length() && s.charAt(left)== s.charAt(right)){
    		--left;
    		++right;
    	}
    	
    	return s.substring(left+1, right);
    }
}
