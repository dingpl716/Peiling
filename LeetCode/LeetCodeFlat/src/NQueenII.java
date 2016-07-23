public class NQueenII {
	
    public int totalNQueens(int n) {
        if(n == 1)
        	return 1;
        if(n == 0 || n<4)
        	return 0;
        
        int[] board = new int[n];
        for(int i=0; i<n; ++i)
        	board[i] = -1;
        return place(0, n, 0, board);
        
    }
    
    
    private int place(int rowNum, int n, int result, int[] board) {
        if(rowNum == n) {
            return ++result;
        }    
        for(int colNum=0; colNum<n; colNum++) {
            if(canPlace(rowNum, colNum, n, board)) {
                updateBoard(rowNum, colNum, board);
                int tmp = place(rowNum+1, n, result, board);
                result = result > tmp ? result : tmp;
            }
            clearBoard(rowNum, board);
        }
        
        return result;
    }
    
    private void updateBoard(int rowNum, int colNum, int[] board) {
    	board[rowNum] = colNum;
    }
    
    private void clearBoard(int rowNum, int[] board) {
    	board[rowNum] = -1;
    }
    
    private boolean canPlace(int rowNum, int colNum, int n, int[] board) {
        // check row
        if(board[rowNum] != -1)
            return false;
        
        for(int i=0; i<n; ++i){
        	if(board[i] == -1)
        		continue;
        	// check col
            if(board[i] == colNum)
                return false;
            // check diag
            if(Math.abs(rowNum - i) == Math.abs(colNum - board[i]))
            	return false;
        }
        
        return true;
    }
}