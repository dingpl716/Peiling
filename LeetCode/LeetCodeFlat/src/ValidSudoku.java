import java.util.HashSet;
import java.util.Set;


public class ValidSudoku {
    public boolean isValidSudoku(char[][] board) {
        if(board == null || board.length != 9 || board[0].length != 9)
        	return false;
        
        return isColValid(board) && isRowValid(board) && isSquValid(board);
    }
    
    private boolean isColValid(char[][] board) {
        for(int j=0; j<board[0].length; ++j) {
            Set<Integer> existed = new HashSet<Integer>();
            for(int i=0; i<board.length; ++i) {
				if(checkLocalArea(existed, board[i][j]) == false)
					return false;
            }
        }
        return true;
    }
    
    private boolean isRowValid(char[][] board) {
    	for(int i=0; i<board.length; ++i) {
    		Set<Integer> existed = new HashSet<Integer>();
    		for(int j=0; j<board[0].length; ++j) {
				if(checkLocalArea(existed, board[i][j]) == false)
					return false;
    		}
    	}
    	
    	return true;
    }
    
    // ！！！ 注意这个地方的坐标计算
    private boolean isSquValid(char[][] board) {
    	for(int startX=0; startX<9; startX+=3) {
    		for(int startY=0; startY<9; startY+=3) {
    			Set<Integer> existed = new HashSet<Integer>();
    			for(int i=startX; i<startX+3; ++i) {
    				for(int j=startY; j<startY+3; ++j) {
    					if(checkLocalArea(existed, board[i][j]) == false)
    						return false;
    				}
    			}
    		}
    	}
    	
    	return true;
    }
    
    private boolean checkLocalArea(Set<Integer> existed, char target) {
    	if(target == '.')
    		return true;
    	int num = Integer.parseInt(target+"");
    	if(num<0 || num>9)
    		return false;
    	if(existed.contains(num))
    		return false;
    	else {
    		existed.add(num);
    		return true;
    	}
    }
}
