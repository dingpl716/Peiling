package matrix;

/**
 * You are given an n x n 2D matrix representing an image.
 * Rotate the image by 90 degrees (clockwise).
 * @author Peiling
 *	向右旋转90度：先延反对角线对折，再延水平中线对折
 * 	向左旋转90度： 先延正对角线对折，再延水平中线对折
 *  旋转180度	：先延水平中线对折，再延垂直中线对折
 */
public class RotateImage {

	
    public void rotate(int[][] matrix) {
    	diagonalFold(matrix);
    	horizontalFold(matrix);
    }
    
    private void diagonalFold(int[][] matrix) {
    	int tmp;
    	int length = matrix.length;
    	for(int i=0; i<length-1; ++i) {
    		for(int j=0; j<length-1-i; ++j) {
    			tmp = matrix[i][j];
    			matrix[i][j] = matrix[length-j-1][length-i-1];
    			matrix[length-j-1][length-i-1] = tmp;
    		}
    	}
    }
    
    private void horizontalFold(int[][] matrix) {
    	int tmp;
    	int length = matrix.length;
    	for(int i=0; i<length/2; ++i) {
    		for(int j=0; j<length; ++j) {
    			tmp = matrix[i][j];
    			matrix[i][j] = matrix[length-i-1][j];
    			matrix[length-i-1][j] = tmp;
    		}
    	}
    }
    
    /**
     * 顺时针旋转一个m*n的矩阵90度：以3*4为例
     * 第0 row -> 第2 col
     * 第1 row -> 第1 col
     * 第2 row -> 第0 col
     * 第i row -> 第m-i-1 col
     * @param matrix
     * @return
     */
    public int[][] rotateMN(int[][] matrix) {
    	if(matrix == null || matrix.length == 0)
    		return null;
    	int m = matrix.length;
    	int n = matrix[0].length;
    	if(n == 0)
    		return matrix;
    	
    	int[][] output = new int[n][m];
    	for(int i=0; i<m; ++i) {
    		for(int j=0; j<n; ++j) {
    			output[j][m-i-1] = matrix[i][j];
    		}
    	}
    	
    	return output;
    }
}
