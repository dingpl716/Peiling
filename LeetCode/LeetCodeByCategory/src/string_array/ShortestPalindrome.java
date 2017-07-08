package string_array;

//	Given a string S, you are allowed to convert it to a palindrome by adding characters in front of it. 
//	Find and return the shortest palindrome you can find by performing this transformation.
//	
//	For example:
//	
//	Given "aacecaaa", return "aaacecaaa".
//	
//	Given "abcd", return "dcbabcd".
		
/**
 * 核心思想，DP
 * 1. 在s的某处切一刀，使得左边的那一部分一定是一个回文，右边的那一部分随意
 * 2. 并且左边的回文部分一定要尽可能的长，此处可以用DP
 * 3. 然后翻转右边的部分，再加到s的前面即可
 * @param s
 * @return
 */
public class ShortestPalindrome {

    public String shortestPalindrome(String s) {
    
    	if (s == null || s.length() < 2) {
    		return s;
    	}
    	
    	int index = longestPalindrome(s);
    	
    	String rightPart = s.substring(index + 1);
    	rightPart = new StringBuilder(rightPart).reverse().toString();
    	return rightPart + s;
    }
    
    //二维DP
	//  P[i][j] 表明s[i,j]是不是palindrome,
	//  P[i][j] = (s[i] == s[j]) && P[i+1][j-1];
	//  最后遍历这个二维数组，找到Max(j-i) P[i][j] == true;
    public int longestPalindrome(String s) {
		boolean[][] P = new boolean[s.length()][s.length()];
		
		for(int i=P.length-1; i>=0; --i) {
			for(int j=i; j<P[0].length; ++j) {
				if(i == j)
					P[i][j] = true;
				else if(j == i+1)
					P[i][j] = s.charAt(i) == s.charAt(j);
				else 
					P[i][j] = (s.charAt(i) == s.charAt(j)) && P[i+1][j-1];
			}
		}
		
		for (int i = P[0].length - 1; i >= 0; --i) {
			if (P[0][i]) {
				return i;
			}
		}
		
		return 0;
    }
  
    public static void main(String[] args) {
    	ShortestPalindrome s = new ShortestPalindrome();
    	System.out.println(s.shortestPalindrome("aacecaaa"));
    	System.out.println(s.shortestPalindrome("abcd"));
    }
}
