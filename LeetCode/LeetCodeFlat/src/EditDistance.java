//	Given two words word1 and word2, find the minimum number of steps 
//	required to convert word1 to word2. (each operation is counted as 1 step.)
//	
//	You have the following 3 operations permitted on a word:
//	
//	a) Insert a character
//	b) Delete a character
//	c) Replace a character

//	对于编辑距离d(i,j)，即计算A(i)到B(j)之间的编辑距离，
//	此时，设A(i)形式是somestr1c；B(i)形如somestr2d的话，
//		将somestr1变成somestr2的编辑距离已知是d(i-1,j-1)
//		将somestr1c变成somestr2的编辑距离已知是d(i,j-1)
//		将somestr1变成somestr2d的编辑距离已知是d(i-1,j)
//	那么利用这三个变量，就可以递推出d(i,j)了：
//		如果c==d，显然编辑距离和d(i-1,j-1)是一样的
//	如果c!=d，情况稍微复杂一点，
//		如果将c替换成d，编辑距离是somestr1变成somestr2的编辑距离 + 1，也就是d(i-1,j-1) + 1
//		如果在c后面添加一个字d，编辑距离就应该是somestr1c变成somestr2的编辑距离 + 1，也就是d(i,j-1) + 1
//		如果将c删除了，那就是要将somestr1编辑成somestr2d，距离就是d(i-1,j) + 1
//	那最后只需要看着三种谁最小，就采用对应的编辑方案了。
//	递推公式出来了：
//	dp[i][j] =  dp[i-1][j-1]   if (A[i] == B[j])
//	      or = min(dp[i][j-1], dp[i-1][j], dp[i-1][j-1]) +1;
//	初始条件： dp[0][j] = j and dp[i][0] = i

/*
 * 这里的二维数组要是(word1.length+1)*(word2.length+1)
 * 千万不能写成word1.length*word2.length 这样的话就不够了
 * 因为之后我们是从d[1][1]开始计算，d[1][1]实际上是代表
 * 字符串的第0个字符，这里要注意
 */
public class EditDistance {
	// 用了一个m*n的数组
    public int minDistance1(String word1, String word2) {
        if(word1 == null || word1.length() == 0) {
        	if(word2 == null)
        		return 0;
        	else
        		return word2.length();
        }
        if(word2 == null || word2.length() == 0) {
        	if(word1 == null)
        		return 0;
        	else
        		return word1.length();
        }
        
        int len1 = word1.length();
        int len2 = word2.length();
        int[][] d = new int[len1+1][len2+1];
        for(int i=0; i<len1+1; ++i) {
        	d[i][0] = i;
        }
        for(int j=0; j<len2+1; ++j) {
    		d[0][j] = j;
    	}
        for(int i=1; i<len1+1; ++i) {
        	for(int j=1; j<len2+1; ++j) {
        		if(word1.charAt(i-1) == word2.charAt(j-1))
        			d[i][j] = d[i-1][j-1];
        		else {
        			d[i][j] = min(new int[]{d[i-1][j-1], d[i][j-1], d[i-1][j]}) + 1;
        		}
        	}
        }
        
        return d[len1][len2];
    }
    
    // 只用一个两行的二维数组
    /*
     * 易犯错误： 在每一行开始的时候，必须要先初始化d[i][0]！！！！！！
     */
    public int minDistance(String word1, String word2) {
        if(word1 == null || word1.length() == 0) {
        	if(word2 == null)
        		return 0;
        	else
        		return word2.length();
        }
        if(word2 == null || word2.length() == 0) {
        	if(word1 == null)
        		return 0;
        	else
        		return word1.length();
        }
        
        int len1 = word1.length();
        int len2 = word2.length();
        int[][] d = new int[2][len2+1];
        for(int j=0; j<len2+1; ++j) 
    		d[0][j] = j;
        
        for(int i=1; i<len1+1; ++i) {
        	d[i%2][0] = i;  
        	for(int j=1; j<len2+1; ++j) {
        		if(word1.charAt(i-1) == word2.charAt(j-1))
        			d[i%2][j] = d[(i-1)%2][j-1];
        		else
        			d[i%2][j] = min(new int[]{d[(i-1)%2][j],d[i%2][j-1],d[(i-1)%2][j-1]}) + 1;
        	}
        }
        
        return d[len1%2][len2];
    }
    private int min(int[] d) {
    	int min = d[0];
    	for(int dis : d){
    		if(dis < min)
    			min = dis;
    	}
    	return min;
    }
}
