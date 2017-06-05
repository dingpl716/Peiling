package string_array;
//	Write an efficient algorithm that searches for a value in an m x n matrix. 
//	This matrix has the following properties:
//	
//	Integers in each row are sorted from left to right.
//	The first integer of each row is greater than the last integer of the previous row.
//	For example,
//	
//	Consider the following matrix:
//	
//	[
//	  [1,   3,  5,  7],
//	  [10, 11, 16, 20],
//	  [23, 30, 34, 50]
//	]
public class SearchMatrix {

	/**
	 * O(n) = m+logn = m
	 * 不是最优的
	 * @param matrix
	 * @param target
	 * @return
	 */
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix == null)
            return false;
            
        int m = matrix.length;
        int n = matrix[0].length;
        
        for(int i=0; i<m; ++i) {
            if(matrix[i][0] == target || matrix[i][n-1] == target)
                return true;
            if(matrix[i][0] < target && matrix[i][n-1] > target) 
                return quickFind(matrix, i, target);
        }
        
        return false;
    }
    
    private boolean quickFind(int[][] matrix, int rowNumber, int target) {
        int length = matrix[0].length;
        return quickFind(matrix, rowNumber, target, 0, length-1);
    }
    
    private boolean quickFind(int[][] matrix, int rowNumber, int target, int left, int right) {
        if(left > right)
            return false;
        int middle = (left + right)/2;
        if(matrix[rowNumber][middle] == target)
            return true;
        else if(matrix[rowNumber][middle] > target)
            return quickFind(matrix, rowNumber, target, left, middle-1);
        else
            return quickFind(matrix, rowNumber, target, middle+1, right);
    }
    
    
    /**
     * O(n) = log(m*n)= log(n^2) = 2logn = logn
     * 把整个二维数组作为一个一位数组来考虑
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix1(int[][] matrix, int target) {
        if(matrix == null)
            return false;
        
        int rowNum = matrix.length;
        if(rowNum == 0)
            return false;
        int colNum = matrix[0].length;
        if(colNum == 0)
            return false;
            
        return quickFind(matrix, target, 0, 0, rowNum-1, colNum-1);
    }
    
    private boolean quickFind(int[][] matrix, int target, int leftRow, int leftCol, int rightRow, int rightCol) {
        if(leftRow > rightRow)
            return false;
        if (leftRow == rightRow && leftCol > rightCol)
            return false;
        
        int middleRow = calMiddleRow(matrix, leftRow, leftCol, rightRow, rightCol);
        int middleCol = calMiddleCol(matrix, leftRow, leftCol, rightRow, rightCol);
        if(matrix[middleRow][middleCol] == target)
            return true;
        else if (matrix[middleRow][middleCol] > target)
            return quickFind(matrix, target, leftRow, leftCol, 
                        rowNumMinOne(matrix, middleRow, middleCol), colNumMinOne(matrix, middleRow,middleCol));
        else
            return quickFind(matrix, target,rowNumPlusOne(matrix, middleRow, middleCol), 
                        colNumPlusOne(matrix, middleRow,middleCol), rightRow, rightCol);
    }
    
    private int calMiddleRow(int[][]matrix, int leftRow, int leftCol, int rightRow, int rightCol) {
        int colNum = matrix[0].length;
        
        int leftIndex = leftRow * colNum + leftCol;
        int rightIndex = rightRow * colNum + rightCol;
        int middleIndex = (leftIndex+rightIndex) / 2;
        
        return middleIndex/colNum;
    }
    
    private int calMiddleCol(int[][]matrix, int leftRow, int leftCol, int rightRow, int rightCol) {
        int colNum = matrix[0].length;
        
        int leftIndex = leftRow * colNum + leftCol;
        int rightIndex = rightRow * colNum + rightCol;
        int middleIndex = (leftIndex+rightIndex) / 2;
        
        return middleIndex%colNum;
    }
    
    private int rowNumMinOne(int[][] matrix, int row, int col) {
        if(col > 0)
            return row;
        else
            return row-1;
    }
    
    private int colNumMinOne(int[][] matrix, int row, int col) {
        if(col > 0)
            return col - 1;
        else
            return matrix[0].length - 1;
    }
    
    private int rowNumPlusOne(int[][] matrix, int row, int col) {
        if(col < matrix[0].length-1)
            return row;
        else
            return row + 1;
    }
    
    private int colNumPlusOne(int[][] matrix, int row, int col) {
        if(col < matrix[0].length-1)
            return col+1;
        else
            return 0;
    }    	
    
    
    public boolean searchMatrix2(int[][] matrix, int target) {
    	int row = searchRow(matrix, target);
    	if(row == -1)
    		return false;
    	
    	int left = 0;
    	int right = matrix[0].length;
    	while(left <= right) {
    		int middle = (left + right)/2;
    		if(matrix[row][middle] == target)
    			return true;
    		else if(matrix[row][middle] < target)
    			left = middle + 1;
    		else
    			right = middle - 1;
    	}
    	
    	return false;
    }
    
    
//    rowi[0]<= target < row(i+1)[0]
    private int searchRow(int[][] matrix, int target) {
    	int left = 0;
    	int right = matrix.length-1;
    	
    	while(left <= right) {
    		int middle = (left+right)/2;
    		if(matrix[middle][0] <= target && ( middle+1>=matrix.length || target < matrix[middle+1][0]))
    			return middle;
    		else if (matrix[middle][0] > target) 
    			right = middle - 1;
    		else
    			left = middle + 1;
    	}
    	
    	return -1;
    }

    public boolean searchMatrix3(int[][] matrix, int target) {
        if(matrix == null)
            return false;
        
        int rowNum = matrix.length;
        if(rowNum == 0)
            return false;
        int colNum = matrix[0].length;
        if(colNum == 0)
            return false;
        
        int left = 0;
        int right = rowNum * colNum - 1;
        
        while(left <= right) {
        	int middle = (left + right) / 2;
        	int[] indexes = getTwoDimensionIndexes(matrix, middle);
        	int row = indexes[0];
        	int col = indexes[1];
        	if (matrix[row][col] == target){
        		return true;
        	} 
        	else if (matrix[row][col] < target){
        		left = middle;
        	}
        	else {
        		right = middle;
        	}
        }
        
        return false;
    }
    
    private int[] getTwoDimensionIndexes(int[][] matrix, int index){
    	return new int[]{index/matrix[0].length, index%matrix[0].length};
    }
    
    /**
     * 
     * @param matrix 一个m*n的二维数组
     * @param row
     * @param col
     * @return [0, m*n-1];
     */
    private int getOneDimensionIndex(int[][] matrix, int row, int col){
    	return row * matrix[0].length + col;
    }
}
