package matrix;

import java.util.HashSet;
import java.util.Set;

import definition.Point;

//	Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.
//	
//	Example 1:
//	
//	11110
//	11010
//	11000
//	00000
//	Answer: 1
//	
//	Example 2:
//	
//	11000
//	11000
//	00100
//	00011
//	Answer: 3

public class NumberOfIslands {

	public int numIslands(char[][] grid) {
    
		if (grid == null || grid.length == 0){
			return 0;
		}
		
		Set<Point> hasMet = new HashSet<Point>();
		
		int number = 0;
		
		// 从每个点出发，如果遇到陆地了就把number加1
		for (int i = 0; i < grid.length; ++i){
			for (int j = 0; j < grid[0].length; ++j){
				if (dfs(grid, new Point(i, j, grid.length, grid[0].length), hasMet, false)) {
					++number;
				}
			}
		}
		
		return number;
    }
	
	/**
	 * 以dfs的方式走遍所有相连的陆地，并且返回true，如果没遇到陆地返回false
	 * @param grid
	 * @param currentPoint
	 * @param hasMet 已走过的陆地，以免重复
	 * @param metLand
	 * @return
	 */
	private boolean dfs(char[][] grid, Point currentPoint, Set<Point> hasMet, boolean metLand) {
		
		if(!canGoTo(grid, currentPoint, hasMet)){
			return metLand;
		}
		
		hasMet.add(currentPoint);
		metLand = true;
		
		Point up = currentPoint.moveUp();
		Point down = currentPoint.moveDown();
		Point left = currentPoint.moveLeft();
		Point right = currentPoint.moveRight();
		
		metLand = dfs(grid, up, hasMet, metLand);
		metLand = dfs(grid, down, hasMet, metLand);
		metLand = dfs(grid, left, hasMet, metLand);
		metLand = dfs(grid, right, hasMet, metLand);
		
		return metLand;
	}
	
	/**
	 * Determines if we can land on the destination point.
	 * @param grid
	 * @param destination
	 * @param hasMet
	 * @return
	 */
	private boolean canGoTo(char[][] grid, Point destination, Set<Point> hasMet) {
		// Cannot go across the board
		if (destination == null) {
			return false;
		}

		// Already went there before.
		if (hasMet.contains(destination)){
			return false;
		}
		
		// Cannot go to the water.
		if (grid[destination.x][destination.y] == '0'){
			return false;
		}
		
		return true;
	}
}
