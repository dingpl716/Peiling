//	Given a string s, partition s such that every substring of the partition is a palindrome.
//	
//	Return the minimum cuts needed for a palindrome partitioning of s.
//	
//	For example, given s = "aab",
//	Return 1 since the palindrome partitioning ["aa","b"] could be produced using 1 cut.

public class PalindromePartitioningII {
//	思路: DP
//	设P[i][j]为[i,j]是否为palindrome，那么
//	P[i][j] = (s[i] == s[j] && P[i+1][j-1])
//	注意，上面这个P[i+1][j-1]的意义就是[i,j]的中间部分，如果中间部分为true，且两头又相等，那么自然P[i][j]=true
//	因为上面这个公式的特殊性，其计算过程不像平时计算DP那样一行一行的计算。
//	而是最开始初始化正对角线为全真，也就是每个一个字符都是palindrome
//	然后往右上方，一条对角线一条对角线的计算，直到到达最右上角（表示整个字符串是否为回文）
	
//	设D[i]为[i, end]这段中，最小的cut数，并且设 i<j<=end 那么
//	如果[i,j]是回文，则有 D[i] = D[j]+1，就相当于是在j处再cut一刀，
//	但是j有一个范围，所以我们要取出最小值，所以
//	D[i] = min(D[j]+1)
    public int minCut(String s) {
        if(s == null || s.length() < 2)
        	return 0;
        
        boolean[][] P = new boolean[s.length()][s.length()];
        int[] D = new int[s.length()+1];
        // worst case: we have to cut every single character.
        for(int i=0; i<D.length; ++i)
        	D[i] = s.length() - i - 1;
        
        for(int i=P.length-1; i>=0; --i) {
        	for(int j=i; j<P.length; ++j) {
        		if(s.charAt(i) == s.charAt(j) && (j-i<2 || P[i+1][j-1])) {
        			P[i][j] = true;
        			D[i] = Math.min(D[i], D[j+1]+1);
        		}
        	}
        }
        
        return D[0];
    }
}
