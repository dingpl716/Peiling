package dp;

//	Given a 2D binary matrix filled with 0's and 1's, 
//	find the largest square containing only 1's and return its area.
//	
//	For example, given the following matrix:

/**
 * 核心思想：DP
 * 设P[i][j]为，当以matrix[i][j]为正方形的右下角点的时候，此时正方形能
 * 达到的最大边长是多少。
 * 注意,P[i][j]的物理意义并不是累加的，而是定点的。也就是说P[i][j]所代表
 * 的那个正方形， 一定是包含matrix[i][j]这个点的。
 * 那么当matrix[i][j] = 0的时候，P[i][j]
 * 自然也等于0.因为题目要求整个正方形都必须全是1。
 * 
 * 所以我们有以下递推公式
 * P[i][j] = 	if matrix[i][j] = 0 then 0 
 * 				if matrix[i][j] = 1 then min(P[i-1][j], P[i][j-1], P[i-1][j-1]) + 1
 * 
 * 这个取三者最小值的意义在于，matrix[i][j]的左边，上边以及左上方都必须全是1.
 * 那么取三者的最小值就可以很自然的排除那些为0的情况。
 * 而且题目要求是正方形，所以取三者最小值可以保证我们找出的区域是正方形，而不是长方形
 * 
 * 在递推的时候我们需要用一个值来保存当前的最大值，因为P[i][j]不是全局最大值！！！
 * @author Dingp
 *
 */
public class MaximalSquare {

    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0) {
        	return 0;
        }
        
        int[][] P = new int[matrix.length][matrix[0].length];
        int result = 0;
        
        for (int i = 0; i < matrix.length; ++i) {
        	for (int j = 0; j < matrix[0].length; ++j) {
        		if (matrix[i][j] == '0') {
        			P[i][j] = 0;
        		} else {
        			if (i == 0 || j == 0) {
        				P[i][j] = matrix[i][j] - '0';
        			} else {
        				int min = Math.min(P[i-1][j-1], P[i-1][j]);
        				min = Math.min(min, P[i][j-1]);
        				P[i][j] = min + 1;
        			}
        			
        			result = Math.max(result, P[i][j]);
        		}
        	}
        }
        
        return result * result;
    }
    
    public static void main(String[] args) {
    	MaximalSquare m = new MaximalSquare();
    	
    }
}
