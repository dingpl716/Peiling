//	Given a string S and a string T, count the number of distinct subsequences of T in S.
//	
//	A subsequence of a string is a new string which is formed from the original 
//	string by deleting some (can be none) of the characters without disturbing 
//	\the relative positions of the remaining characters. 
//	(ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not).
//	
//	Here is an example:
//	S = "rabbbit", T = "rabbit"
//	
//	Return 3.

//	给定两个字符串S和T，求S有多少个不同的子串与T相同。S的子串定义为在S中任意去掉0个或者多个字符形成的串。
//	DP求解，给出下面二维数组dp[][], 横轴是母串，纵轴是子串，dpl[i][j]表示
//	子串的[0,i]在母串的[0，j]中出现的次数。有如下递推公式。
//	dp[0][j] = 1, 空串在母串中永远出现一次
//	dp[1到i][0] = 0 非空串不会在空的母串中出现，
//	dp[i][j] = dp[i][j-1] if 子串的i不等于母串的j，
//	dp[i][j] = dp[i][j-1] + dp[i-1][j-1] if 子串的i等于母串的j
//	  "" r a b b b i t
//	"" 1 1 1 1 1 1 1 1
//	r  0 1 1 1 1 1 1 1
//	a  0 0 1 1 1 1 1 1
//	b  0 0 0 1 2 3 3 3
//	b  0 0 0 0 1 3 3 3
//	i  0 0 0 0 0 0 3 3
//	t  0 0 0 0 0 0 0 3  
public class DistinctSubsequences {
	
    public int numDistinct(String S, String T) {
    	if(S == null || S.length() == 0)
    		return 0;
    	if(T == null || T.length() == 0)
    		return 1;
    	
    	int[][] dp = new int[2][S.length()+1];
    	for(int i=0; i<S.length()+1; ++i)
    		dp[0][i] = 1;
    	for(int i=1; i<T.length()+1; ++i) {
    		dp[i%2][0] = 0;
    		for(int j=1; j<S.length()+1; ++j) {
    			dp[i%2][j] = dp[i%2][j-1];
    			if(T.charAt(i-1) == S.charAt(j-1))
    				dp[i%2][j] += dp[(i-1)%2][j-1];
    		}
    	}
    	
    	return dp[T.length()%2][S.length()];
    }
}
