package matrix;

//	Given an integer n, generate a square matrix 
//	filled with elements from 1 to n2 in spiral order.
//	
//	For example,
//	Given n = 3,
//	
//	You should return the following matrix:
//	[
//	 [ 1, 2, 3 ],
//	 [ 8, 9, 4 ],
//	 [ 7, 6, 5 ]
//	]
public class SpiralMatrixII {
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        if(n == 0)
            return matrix;
        // 1 == right, 2 == down, 3 == left, 4 == up
        int dir = 1;    //这里必须要用一个dir
        int m = 1;
        int i = 0;
        int j = 0;
        matrix[i][j] = m++;
        while(m <= n*n) {
            if(dir == 1) {
                if(j< n-1 && matrix[i][j+1] == 0)
                    matrix[i][++j] = m++;
                else
                    dir = 2;
            }else if(dir == 2) {
                if(i<n-1 && matrix[i+1][j] == 0)
                    matrix[++i][j] = m++;
                else
                    dir = 3;
            }else if(dir == 3) {
                if(j>0 && matrix[i][j-1] == 0) 
                    matrix[i][--j] = m++;
                else
                    dir = 4;
            }else {
                if(i>0 && matrix[i-1][j] == 0)
                    matrix[--i][j] = m++;
                else
                    dir = 1;
            }
        }
        return matrix;
    }
}
