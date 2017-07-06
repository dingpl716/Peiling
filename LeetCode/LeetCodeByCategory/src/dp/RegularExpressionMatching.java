package dp;

//	Implement regular expression matching with support for '.' and '*'.
//	
//	'.' Matches any single character.
//	'*' Matches zero or more of the preceding element.
//	
//	The matching should cover the entire input string (not partial).
//	
//	The function prototype should be:
//	bool isMatch(const char *s, const char *p)
//	
//	Some examples:
//	isMatch("aa","a") → false
//	isMatch("aa","aa") → true
//	isMatch("aaa","aa") → false
//	isMatch("aa", "a*") → true
//	isMatch("aa", ".*") → true
//	isMatch("ab", ".*") → true
//	isMatch("aab", "c*a*b") → true

/**
 * 核心思想: DP
 * 
 * 这道题的一个核心点在于，遇到*的时候我们该怎么办。那既然*可以match 0次，1次很多次，
 * 那我们就需要把这三种情况都计算出来，最后只要这三种情况的其中一种match，那就match
 * 
 * 设M[i][j] 为 s从0到i的子串是否match p从0到j的子串，则有以下公式
 * if p[j] == s[i] 	then M[i][j] = M[i-1][j-1]
 * if p[j] == .		then M[i][j] = M[i-1][j-1]
 * if p[j] == *		then have two sub-conditions:
 * 		if p[j-1] != s[i] then M[i][j] = M[i][j-2] // 此时p[j]上的*解读为 p[j-1] match s[i] 0 次, 比如  cb, ca*, i指向b, j指向*
 * 		if p[j-1] == s[i] or p[j-1] == .
 * 		then M[i][j] = M[i-1][j] //此时p[j]上的*解读为 p[j-1] match s[i]很多次, 如 aaaa, c*a*, i指向最后一个a，j指向最后一个*
 * 		  or M[i][j] = M[i][j-1] //此时p[j]上的*解读为 p[j-1] match s[i]一次, 如abc, abc*, i指向c, j指向*
 * 		  or M[i][j] = M[i][j-2] // 此时p[j]上的*解读为 p[j-1] match s[i] 0 次, 比如  ab, ab*b, i指向b, j指向*
 * @author Dingp
 *
 */
public class RegularExpressionMatching {
	
	/**
	 * 在实现上面算法的时候还有一个地方需要考虑。那就是我们需要令
	 * m[0][0] = true, 你可以把m[0][0]理解为两个空串在进行比较
	 * 然后整体坐标要右移。
	 * 
	 * @param s
	 * @param p
	 * @return
	 */
    public boolean isMatch(String s, String p) {
       if (s == null || p == null){
    	   return false;
       }
       
       boolean[][] m = new boolean[s.length() + 1][p.length() + 1];
       m[0][0] = true;
       
       //初始化第一行,就是比较当s为空串时, p和它的match情况
       for(int i = 0; i < p.length(); ++i) {
    	   if (p.charAt(i) == '*' && m[0][i - 1]) { // 此时的*算0次
    		   m[0][i + 1] = true;
    	   }
       }
       
       for (int i = 0; i < s.length(); ++i) {
    	   for (int j = 0; j < p.length(); ++j) {
    		   if (p.charAt(j) == s.charAt(i)){
    			   m[i + 1][j + 1] = m[i][j];
    		   }
    		   
    		   if (p.charAt(j) == '.') {
    			   m[i + 1][j + 1] = m[i][j];
    		   }
    		   
    		   if (p.charAt(j) == '*') {
    			   if (p.charAt(j - 1) == s.charAt(i) || p.charAt(j - 1) == '.') {
    				   m[i + 1][j + 1] = (m[i][j + 1] || m[i + 1][j] || m[i+1][j-1]);
    			   }
    			   else {
    				   m[i + 1][j + 1] = m[i + 1][j - 1];
    			   }
    		   }
    	   }
       }
       
       return m[s.length()][p.length()];
    }
    
    public static void main(String[] args) {
    	RegularExpressionMatching r = new RegularExpressionMatching();
    	System.out.println(r.isMatch("aab", "c*a*b"));
    }
}
