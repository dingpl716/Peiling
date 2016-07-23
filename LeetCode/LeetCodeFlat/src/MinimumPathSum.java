
/**
 * Given a m x n grid filled with non-negative numbers, 
 * find a path from top left to bottom right which minimizes 
 * the sum of all numbers along its path.
 * Note: You can only move either down or right at any point in time.
 * @author Peiling
 *
 */
public class MinimumPathSum {
	
	public int minPathSum1(int[][] grid) {
		int m = grid.length;
		int n = grid[0].length;
		
		int[][] path = new int[m][n];
		
		for(int i=0; i<m; ++i){
			for(int j=0; j<n; ++j){
				path[i][j] = 0;
			}
		}
		path[0][0] = grid[0][0];
		
		for(int i=1; i <m; ++i) {
			path[i][0] = path[i-1][0] + grid[i][0];
		}
		
		for(int i=1; i <n; ++i) {
			path[0][i] = path[0][i-1] + grid[0][i];
		}
		
		for(int i=1; i<m; ++i) {
			for(int j=1; j<n; ++j) {
				if(path[i-1][j]<path[i][j-1])
					path[i][j] = grid[i][j] + path[i-1][j];
				else
					path[i][j] = grid[i][j] + path[i][j-1];
			}
		}
		return path[m-1][n-1];
    }
	
	//rolling the array instead of using the whole array
//	已犯错误：
//	1. grid里面的i不能%上2
	public int minPathSum2(int[][] grid) {
		int m = grid.length;
		int n = grid[0].length;
		
		int[][] path = new int[2][n];
		path[0][0] = grid[0][0];
		for(int i=1; i<n ;++i) {
			path[0][i] = path[0][i-1] + grid[0][i];
		}
		
		for(int i=1; i<m; ++i){
			path[i%2][0] = path[(i-1)%2][0] + grid[i%2][0];
			for(int j=1; j<n; ++j) {
				if(path[(i-1)%2][j] < path[i%2][j-1])
					path[i%2][j] =  grid[i][j] + path[(i-1)%2][j];
				else 
					path[i%2][j] =  grid[i][j] + path[i%2][j-1];
			}
		}
		
		return path[(m-1)%2][n-1];
	}
	
}
