import java.util.ArrayList;

//N皇后问题，如何在N*N的棋盘上摆放N个皇后，并且他们互相之间不能攻击
//根据题意可以得出：
//1.每一行上肯定有且仅有1个皇后
//2.每一列上可定有且仅有1个皇后
//3.每组对角线上肯定有且仅有1个皇后

//正对角线性质： row - col = 定值[-(N-1), N-1];
//负对角线性质: row + col = 定值[0, 2N-2];
public class NQueens {

	// n - number of queens
    public ArrayList<String[]> solveNQueens(int n) {
    	ArrayList<String[]> result = new ArrayList<String[]>();
    	if(n == 1) {
    		result.add(new String[] {"Q"});
    		return result;
    	}
    	if(n < 4)
    		return result;
    	String[] board = initBoard(n);
    	
    	int[] column = new int[n];
    	int[] diagonal1 = new int[2*n - 1];
    	int[] diagonal2	= new int[2*n - 1];
    	place(0, n, column, diagonal1, diagonal2, board, result);
    	return result;
    }
    
    private String[] initBoard(int n) {
    	String[] board = new String[n];
    	StringBuffer buffer = new StringBuffer();
    	for(int i=0; i<n; ++i)
    		buffer.append('.');
    	for(int i=0; i<n; ++i) {
    		board[i] = buffer.toString();
    	}
    	return board;
    }
    /**
     * place the queen on rowNum 
     * 也是一个dfs算法，其实只要是递归，就都是dfs
     * 
     * @param rowNum
     * @param n
     */
    private void place(int rowNum, int n, int[] column, int[] diagonal1, int[] diagonal2,
    						String[] board, ArrayList<String[]> result) {
    	if(rowNum == n) {
    		result.add((String[])board.clone());
    		return;
    	}
    	
    	// for each column on this row, put the queen on it if we can
    	// then recursively check next row
    	for(int colNum=0; colNum<n; ++colNum) {
    		if(canPutOn(board, n, rowNum, colNum, column, diagonal1, diagonal2)) {
    			board[rowNum] = generateRow(n, colNum);
    			update(n, rowNum, colNum, column, diagonal1, diagonal2);
    			place(rowNum+1, n, column, diagonal1, diagonal2, board, result);
    			//before checking next col on this row
    			// we need to clear this row
    			board[rowNum] = clearRow(n);
    			clear(n, rowNum, colNum, column, diagonal1, diagonal2);
    		}
    	}
    	
    }
    
    /**
     * Check if we can put the queen on [ rowNum, colNum]
     * We are going to check the entire rowNum and colNum
     * and two diagnoals  
     * @param rowNum
     * @param colNum
     * @return
     */
    private boolean canPutOn2(String[] board,int n, int rowNum, int colNum) {
    	// check row
    	for(int i=0; i<n; ++i) {
    		if(board[rowNum].charAt(i) == 'Q')
    			return false;
    	}
    	
    	// check col
    	for(int i=0; i<rowNum; ++i) {
    		if(board[i].charAt(colNum) == 'Q')
    			return false;
    	}
    	
    	// check diag left-up to right-down
    	for(int i=rowNum+1, j=colNum+1; i<n&&j<n; ++i, ++j) {
			if(board[i].charAt(j) == 'Q')
				return false;
    	}
    	// check diag  right-down to left-up
    	for(int i=rowNum-1, j=colNum-1; i>=0&&j>=0; --i, --j) {
			if(board[i].charAt(j) == 'Q')
				return false;
    	}
    	
    	// check dia left-down to right-up
    	for(int i=rowNum-1, j=colNum+1; i>=0 && j<0; --i, ++j) {
			if(board[i].charAt(j) == 'Q')
				return false;
    	}
    	
    	for(int i=rowNum+1,j=colNum-1; i<n && j>=0; ++i, --j) {
			if(board[i].charAt(j) == 'Q')
				return false;
    	}
    	
    	return true;
    }
    
    /**
     * 查看是否能在board[rowNum,colNum]处放置皇后
     * column : 列的使用情况
     * diagonal1 : 正对角线使用情况
     * diagonal2 ： 负对角线使用情况
     * @return
     */
    private boolean canPutOn(String[] board,int n, int rowNum, int colNum,
    		int[] column, int[] diagonal1, int[] diagonal2) {
    	if(column[colNum] == 1)
    		return false;
    	//正对角线性质： row - col = 定值[-(N-1), N-1];
    	if(diagonal1[rowNum - colNum + n - 1] == 1)
    		return false;
    	//负对角线性质: row + col = 定值[0, 2N-2];
    	if(diagonal2[rowNum + colNum] == 1)
    		return false;
    	return true;
    }
    
    private String generateRow(int n, int colNum) {
    	StringBuffer buffer = new StringBuffer();
    	
    	for(int i=0; i<colNum; ++i)
    		buffer.append('.');
    	buffer.append('Q');
    	for(int i=colNum+1; i<n; ++i)
    		buffer.append('.');
    	
    	return buffer.toString();
    }
    
    private void update(int n, int rowNum, int colNum, int[] column, int[] diagonal1, int[] diagonal2) {
    	column[colNum] = 1;
    	diagonal1[rowNum - colNum + n - 1] = 1;
    	diagonal2[rowNum + colNum] = 1;
    }
    
    private void clear(int n, int rowNum, int colNum, int[] column, int[] diagonal1, int[] diagonal2) {
    	column[colNum] = 0;
    	diagonal1[rowNum - colNum + n - 1] = 0;
    	diagonal2[rowNum + colNum] = 0;
    }
    
    private String clearRow(int n) {
    	StringBuffer buffer = new StringBuffer();
    	for(int i=0; i<n; ++i)
    		buffer.append('.');
    	return buffer.toString();
    }
}
