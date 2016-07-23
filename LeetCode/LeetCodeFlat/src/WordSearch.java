import java.util.HashSet;
import java.util.Set;

//	Given a 2D board and a word, find if the word exists in the grid.
//	
//	The word can be constructed from letters of sequentially adjacent cell, 
//	where "adjacent" cells are those horizontally or vertically neighboring. 
//	The same letter cell may not be used more than once.
//	
//	For example,
//	Given board =
//	
//	[
//	  ["ABCE"],
//	  ["SFCS"],
//	  ["ADEE"]
//	]
//	word = "ABCCED", -> returns true,
//	word = "SEE", -> returns true,
//	word = "ABCB", -> returns false.

public class WordSearch {
	public static class Index {
		int x;
		int y;
		Index(int i, int j) {
			x = i;
			y = j;
		}
		
		@Override
		public int hashCode() {
			int prime = 127;
			return x*prime + y;
		}
		
		@Override
		public boolean equals(Object o) {
			if(o == null)
				return false;
			if(this == o)
				return true;
			Index i = (Index) o;
			return (this.x == i.x && this.y == i.y);
		}
		
	}
	private Set<Index> visited;
	
    public boolean exist(char[][] board, String word) {
    	if(word == null || word.length() == 0)
    		return true;
    	if(board == null || board.length == 0)
    		return false;
    	
    	visited =  new HashSet<Index>();
    	for(int i=0; i<board.length; ++i) {
    		for(int j=0; j<board[0].length; ++j) {
    			if(dfs(board, word, 0, i, j))
    				return true;
    			else
    				visited.clear();
    		}
    	}
    	
    	return false;
    }
    
    // index denotes the current node
    // if target == word.length, return true;
    private boolean dfs(char[][] board, String word, int target, int i, int j) {
    	if(target == word.length())
    		return true;
    	if(i < 0 || i > board.length-1)
    		return false;
    	if(j < 0 || j > board[0].length-1)
    		return false;
    	Index index = new Index(i, j);
    	if(visited.contains(index))
    		return false;
    	if(board[i][j] != word.charAt(target))
    		return false;
    	visited.add(index);
    	boolean res = dfs(board, word, target+1, i-1, j) ||
    				  dfs(board, word, target+1, i+1, j) ||
    				  dfs(board, word, target+1, i, j+1) ||
    				  dfs(board, word, target+1, i, j-1);
    	// 忘记了逆操作！！！！！！！！！！！！！！！
    	visited.remove(index);
    	return res;
    }
}
