import java.util.LinkedList;
import java.util.Queue;

//	Given a 2D board containing 'X' and 'O', capture all regions surrounded by 'X'.
//	
//	A region is captured by flipping all 'O's into 'X's in that surrounded region .
//	
//	For example,
//	X X X X
//	X O O X
//	X X O X
//	X O X X
//	After running your function, the board should be:
//	
//	X X X X
//	X X X X
//	X X X X
//	X O X X

//	solution:
//	从四周开始计算，
//	对在边上的每一个0做如下事：
//	check这个0的四周，如果他四周有其他0，mark之，然后继续往下查，
//	直到没有0或者到边界为止。
//	最后把没有mark的全改为x，有mark的保留为0
public class SurroundedRegions {
    public void solve2(char[][] board) {
        if(board == null)
        	return;
        int row = board.length;
        if(row < 3)
        	return;
        int col = board[0].length;
        if(col < 3)
        	return;
        
        for(int i=0; i<row; ++i) {
        	if(board[i][0] == 'O')
        		markO(board, i, 0);
        	if(board[i][col-1] == 'O')
        		markO(board, i, col-1);
        }
        for(int i=1; i<col-1; ++i) {
        	if(board[0][i] == 'O')
        		markO(board, 0, i);
        	if(board[row-1][i] == 'O')
        		markO(board, row-1, i);
        }
        
        for(int i=0; i<row; ++i) {
        	for(int j=0; j<col; ++j) {
        		if(board[i][j] == '0')
        			board[i][j] = 'O';
        		else
        			board[i][j] = 'X';
        	}
        }
        
    }
    
    /**
     * check这个o的四周，如果他四周（只有上下左右，斜着的不算）
     * 有其他o，mark他们为0，然后继续往下查，
     * 直到没有0或者到边界为止。
     * 
     * 递归，内存溢出了
     * @param board
     * @param x, y current o's index
     */
    public void markO(char[][] board, int x, int y) {
    	board[x][y] = '0';
    	// up
    	if(x-1 >=0 && board[x-1][y] == 'O') {
    		markO(board, x-1, y);
    	}
    	// down
    	if(x+1 < board.length && board[x+1][y] == 'O') {
    		markO(board, x+1, y);
    	}
    	// left
    	if(y-1 >=0 && board[x][y-1] == 'O') {
    		markO(board, x, y-1);
    	}
    	// right
    	if(y+1 < board[0].length && board[x][y+1] == 'O') {
    		markO(board, x, y+1);
    	}
    }
    
    /*
     * 递归，内存溢出了。试试用queue
     */
    public void solve(char[][] board) {
        if(board == null)
        	return;
        int row = board.length;
        if(row < 3)
        	return;
        int col = board[0].length;
        if(col < 3)
        	return;
        
        Queue<Integer> xq = new LinkedList<Integer>();
        Queue<Integer> yq = new LinkedList<Integer>();
        for(int i=0; i<row; ++i) {
        	if(board[i][0] == 'O') {
        		xq.add(i);
        		yq.add(0);
        	}
        	if(board[i][col-1] == 'O'){
        		xq.add(i);
        		yq.add(col-1);
        	}
        }
        for(int i=1; i<col-1; ++i) {
        	if(board[0][i] == 'O') {
        		xq.add(0);
        		yq.add(i);
        	}
        	if(board[row-1][i] == 'O') {
        		xq.add(row-1);
        		yq.add(i);
        	}
        }
        
        while(!xq.isEmpty()) {
        	int x = xq.poll();
        	int y = yq.poll();
        	board[x][y] = '0';
        	if(x-1 >=0 && board[x-1][y] == 'O') {
        		xq.add(x-1);
        		yq.add(y);
        	}
        	if(x+1 < board.length && board[x+1][y] == 'O') {
        		xq.add(x+1);
        		yq.add(y);
        	}
        	if(y-1 >=0 && board[x][y-1] == 'O') {
        		xq.add(x);
        		yq.add(y-1);
        	}
        	if(y+1 < board[0].length && board[x][y+1] == 'O') {
        		xq.add(x);
        		yq.add(y+1);
        	}
        }
        
        for(int i=0; i<row; ++i) {
        	for(int j=0; j<col; ++j) {
        		if(board[i][j] == '0')
        			board[i][j] = 'O';
        		else
        			board[i][j] = 'X';
        	}
        }
    }
}
