package matrix;

//	Write an efficient algorithm that searches for a value in an m x n matrix. 
//	This matrix has the following properties:
//	
//	Integers in each row are sorted in ascending from left to right.
//	Integers in each column are sorted in ascending from top to bottom.
//	For example,
//	
//	Consider the following matrix:
//	
//	[
//	  [1,   4,  7, 11, 15],
//	  [2,   5,  8, 12, 19],
//	  [3,   6,  9, 16, 22],
//	  [10, 13, 14, 17, 24],
//	  [18, 21, 23, 26, 30]
//	]
//	Given target = 5, return true.
//	
//	Given target = 20, return false.

/**
 * 从右上角开始搜索，如果当前元素大于target，则往左走一个，如果小于target则往下走一个。
 * 类似的遍历方法：Kth Smallest Element in a Sorted Matrix 的binary search 法
 * @author peding
 *
 */
public class SearchA2DMatrixII {

    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0){
        	return false;
        }
        
        int rows = matrix.length;
        int cols = matrix[0].length;
        
        int i = 0;
        int j = cols - 1;
        
        while (i < rows && j >= 0){
        	if (matrix[i][j] == target){
        		return true;
        	}
        	else if (matrix[i][j] > target){
        		--j;
        	}
        	else {
        		++i;
        	}
        }
        
        return false;
    }
}
