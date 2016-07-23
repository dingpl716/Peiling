package dfs;

import java.util.HashSet;
import java.util.Set;

//	Write a program to solve a Sudoku puzzle by filling the empty cells.
//	
//	Empty cells are indicated by the character '.'.
//	
//	You may assume that there will be only one unique solution.
public class SudokuSolver {
//	用27个set来分别存储9行，9列，9个block中数字出现的情况
//	然后递归处理，
//	每次递归，找到一个empty的数字，
//	从1-9开始试
//	如果可以放进去一个数字就放进去并且递归下一层
//	如果一个数字也放不进去了，就返回false
//	在放下个数字之前记得删除这次放的这个数字
	
    public void solveSudoku(char[][] board) {
    	Set<Integer>[] rows = new HashSet[9];
    	Set<Integer>[] cols = new HashSet[9];
    	Set<Integer>[] blocks = new HashSet[9];
    	for(int i=0; i<9; ++i) {
    		rows[i] = new HashSet<Integer>();
    		cols[i] = new HashSet<Integer>();
    		blocks[i] = new HashSet<Integer>();
    	}
    	for(int i=0; i<9; ++i) {
    		for(int j=0; j<9; ++j) {
    			if(board[i][j] != '.') {
    				int target = board[i][j] - '0';
    				rows[i].add(target);
    				cols[j].add(target);
    				blocks[(i/3)*3 + j/3].add(target);
    			}
    		}
    	}
    	
    	dfs(board, rows, cols, blocks, 0);
    }
    
    private boolean dfs(char[][] board, Set<Integer>[] rows, Set<Integer>[] cols,
    		Set<Integer>[] blocks, int index) {
    	while(index < 81 && board[index/9][index%9] != '.')
    		++index;
    	if(index >= 81) 
    		return true;
    	
    	int x = index / 9;
    	int y = index % 9;
    	for(char num = '1'; num<='9'; ++num) {
    		int target = num - '0';
    		if(!rows[x].contains(target) && !cols[y].contains(target) && 
    				!blocks[(x/3)*3 + y/3].contains(target)) {
    			rows[x].add(target);
				cols[y].add(target);
				blocks[(x/3)*3 + y/3].add(target);
				board[x][y] = num;
				
				if(dfs(board, rows, cols, blocks, index+1))
					return true;
				rows[x].remove(target);
				cols[y].remove(target);
				blocks[(x/3)*3 + y/3].remove(target);
				board[x][y] = '.';
    		}
    	}
    	return false;
    }
}
