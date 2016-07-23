package string_array;
//	Given a string S, find the longest palindromic substring in S. 
//	You may assume that the maximum length of S is 1000, 
//	and there exists one unique longest palindromic substring.
public class LongestPalindromicSubstring {

    public String longestPalindrome1(String s) {
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
    
    //二维DP
//    P[i][j] 表明s[i,j]是不是palindrome,
//    P[i][j] = (s[i] == s[j]) && P[i+1][j-1];
//    最后遍历这个二维数组，找到Max(j-i) P[i][j] == true;
    public String longestPalindrome(String s) {
    	if(s == null || s.length() == 0)
    		return s;
    	boolean[][] P = new boolean[s.length()][s.length()];
    	for(int i=0; i<P.length; ++i) {
    		for(int j=0; j<P[0].length; ++j){
    			P[i][j] = false;
    		}
    	}
    	
    	int start = 0;
    	int end = 0;
    	for(int i=P.length-1; i>=0; --i) {
    		for(int j=i; j<P[0].length; ++j) {
    			if(i == j)
    				P[i][j] = true;
    			else if(j == i+1)
    				P[i][j] = s.charAt(i) == s.charAt(j);
    			else 
    				P[i][j] = (s.charAt(i) == s.charAt(j)) && P[i+1][j-1];
    			if(P[i][j] && j-i > end - start) {
    				start = i;
    				end = j;
    			}
    				
    		}
    	}
    	
    	return s.substring(start, end+1);
    }
}
