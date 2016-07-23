//	Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.
//	
//	For example,
//	Given:
//	s1 = "aabcc",
//	s2 = "dbbca",
//	
//	When s3 = "aadbbcbcac", return true.
//	When s3 = "aadbbbaccc", return false.
public class InterleavingString {

	// 法1， 递归/dfs, 超时
    public boolean isInterleave1(String s1, String s2, String s3) {
        if(s3 == null || s3.length() == 0)
        	return true;
        if(s1 == null || s1.length() == 0)
        	return s3.equals(s2);
        if(s2 == null || s2.length() == 0)
        	return s3.equals(s1);
        if(s1.length() + s2.length() != s3.length())
        	return false;
        return isInterleave(s1, 0, s2, 0, s3);
    }
    
    private boolean isInterleave(String s1, int i1, String s2, int i2, String s3) {
    	if(i1 == s1.length() && i2 == s2.length())
    		return true;
    	else if(i1 == s1.length()) {
    		if(s3.charAt(i1 + i2) == s2.charAt(i2))
    			return isInterleave(s1, i1, s2, i2+1, s3);
    		else
    			return false;
    	}else if(i2 == s2.length()) {
    		if(s3.charAt(i1 + i2) == s1.charAt(i1))
    			return isInterleave(s1, i1+1, s2, i2, s3);
    		else
    			return false;
    	}else {
    		if(s3.charAt(i1 + i2) == s1.charAt(i1))
    			// 如果为true，直接返回，没必要做下面的递归了，
    			// 此为剪枝，虽然加上这个剪枝，仍然会超时
    			if(isInterleave(s1, i1+1, s2, i2, s3))
    				return true;;
    		
    		if(s3.charAt(i1 + i2) == s2.charAt(i2))
    			if(isInterleave(s1, i1, s2, i2+1, s3))
    				return true;
    		
    		return false;
    	}
    }
    
//    法二，动态规划, S3 = aadbbcbcac
//     "" a a b c c  s1
//  ""  T T T F F F 
//   d  F F T T F F
//   b  F F T T T T
//   b  F F 
//   c  F
//   a  F
//   s2
//    matrix[i][j] 表示 s3的[0,i+j] 是否是 s1的[0,j] 和s2的[0,i]interleaving string
//    matrix[i][j] = T 的两种可能 
//                      1.matrix[i-1][j] == true && s2(i) == s3(i+j)
//    				    2.matrix[i][j-1] == true && s1(j) == s3(i+j)
//    其余为false
    public boolean isInterleave(String s1, String s2, String s3) {
        if(s3 == null || s3.length() == 0)
        	return true;
        if(s1 == null || s1.length() == 0)
        	return s3.equals(s2);
        if(s2 == null || s2.length() == 0)
        	return s3.equals(s1);
        if(s1.length() + s2.length() != s3.length())
        	return false;
        
        boolean[][] matrix = new boolean[2][s1.length()+1];
        matrix[0][0] = true;
        for(int i=1; i<matrix[0].length; ++i) {
        	matrix[0][i] = matrix[0][i-1] && (s1.charAt(i-1) == s3.charAt(i-1));
        }
        
        // 已犯错误！！ 这里i应该和s2的length相比，而不是和matrix.length相比
        for(int i=1; i<=s2.length(); ++i) {
        	matrix[i%2][0] = matrix[(i-1)%2][0] && (s2.charAt(i-1) == s3.charAt(i-1));
        	for(int j=1; j<matrix[0].length; ++j) {
        		if((matrix[(i-1)%2][j]) && (s2.charAt(i-1) == s3.charAt(i+j-1)))
        			matrix[i%2][j] = true;
        		else if(matrix[i%2][j-1] && (s1.charAt(j-1) == s3.charAt(i+j-1)))
        			matrix[i%2][j] = true;
        		else
        			matrix[i%2][j] = false;
        	}
        }
        
        return matrix[s2.length()%2][s1.length()];
    }
    
}