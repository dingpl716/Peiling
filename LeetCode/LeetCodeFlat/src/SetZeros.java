
/**
 * Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in place.
 * Did you use extra space?
 * A straight forward solution using O(mn) space is probably a bad idea.
 * A simple improvement uses O(m + n) space, but still not the best solution.
 * Could you devise a constant space solution?
 * 解题点就在于清空标志位存在哪里的问题。
 * 可以创建O(m+n)的数组来存储，但此题是希望复用已有资源。
 * 这里可以选择第一行和第一列来存储标志位。
   	1.先确定第一行和第一列是否需要清零
	2.扫描剩下的矩阵元素，如果遇到了0，就将对应的第一行和第一列上的元素赋值为0
	3.根据第一行和第一列的信息，已经可以讲剩下的矩阵元素赋值为结果所需的值了
	4.根据1中确定的状态，处理第一行和第一列。
 * @author Peiling
 *
 */
public class SetZeros {
    public void setZeroes(int[][] matrix) {
        if(matrix == null)
            return;
            
        int m = matrix.length;
        int n = matrix[0].length;
        boolean setFirstRow = false;
        boolean setFirstCol = false;
        
        for(int i=0; i<m; i++) {
            if(matrix[i][0] == 0)
                setFirstCol = true;
        }
        
        for(int i=0; i<n; ++i) {
            if(matrix[0][i] == 0)
                setFirstRow = true;
        }
        
        for(int i=0; i<m; ++i) {
            for(int j=0; j<n; ++j) {
                if(matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }        
            }
        }
        
        for(int i=1; i<m; ++i) {
            if(matrix[i][0] == 0)
                setRow(matrix, i);
        }
        
        for(int i=1; i<n; ++i) {
            if(matrix[0][i] == 0)
                setCol(matrix, i);
        }
        
        if(setFirstRow == true)
            setRow(matrix, 0);
        if(setFirstCol == true)
            setCol(matrix, 0);
    }
    
    private void setRow(int[][] matrix, int row) {
        int n = matrix[0].length;
        for(int i=0; i<n; ++i)
            matrix[row][i] = 0;
    }
    
    private void setCol(int[][] matrix, int col) {
        int m = matrix.length;
        for(int i=0; i<m; ++i)
            matrix[i][col] = 0;
    }
}
