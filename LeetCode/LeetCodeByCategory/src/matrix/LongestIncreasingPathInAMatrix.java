package matrix;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import definition.Point;

//	Given an integer matrix, find the length of the longest increasing path.
//	
//	From each cell, you can either move to four directions: left, right, up or down. You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).
//	
//	Example 1:
//	
//	nums = [
//	  [9,9,4],
//	  [6,6,8],
//	  [2,1,1]
//	]
//	Return 4
//	The longest increasing path is [1, 2, 6, 9].
//	
//	Example 2:
//	
//	nums = [
//	  [3,4,5],
//	  [3,2,6],
//	  [2,2,1]
//	]
//	Return 4
//	The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.

/**
 * DFS + DP
 * 设P[i,j] 为以i,j为启点的最长路径的长度，那么
 * P[i,j] = P[k] + 1, 其中K是点(i,j)四周，且值大于(i,j)的点中路径最长的一个。
 * @author Dingp
 *
 */
public class LongestIncreasingPathInAMatrix {

	public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0){
        	return 0;
        }
        
        int result = 1;
        Map<Point, Integer> map = new HashMap<Point, Integer>();
        for (int i = 0; i < matrix.length; ++i){
        	for (int j = 0; j < matrix[0].length; ++j){
        		result = Math.max(result, dfs(matrix, new Point(i, j, matrix.length, matrix[0].length), map));
        	}
        }
        
        return result;
    }
	
	/**
	 * 计算以一个点出发，能走的最远距离。
	 * 
	 * 核心思想：
	 * 1. 对于四个方向，依次算出 如果能往该方向上走，能走的最远距离。 
	 *    此处的意思是以那个方向上的点为起点，进行递归运算。
	 * 2. 取出1里面的最大值
	 * 3. 跟新map里面的情况，返回这个最大值+1 (因为从该起点到那个方向上的点本身也走了一步)
	 * 4. 因为路径一定要是单调递增的，所以不需要一个Set来记录已经走过哪些点了。
	 * 
	 * @param matrix
	 * @param start 目前到达的点
	 * @param map 记录着已经被计算过的点的情况，其值是以该点为起点能走的最远距离
	 * @return 以该点出发能走的最远距离
	 */
	private int dfs(int[][] matrix, Point start, Map<Point, Integer> map) {
		
		if (map.containsKey(start)) {
			return map.get(start);
		}
		
		int longestPath = 0;
		
		Point up = start.moveUp();
		if (up != null 
			&& matrix[start.x][start.y] < matrix[up.x][up.y]){
			longestPath = Math.max(longestPath, dfs(matrix, up, map));
		}
		
		Point down = start.moveDown();
		if (down != null 
			&& matrix[start.x][start.y] < matrix[down.x][down.y]){
			longestPath = Math.max(longestPath, dfs(matrix, down, map));
		}
		
		Point left = start.moveLeft();
		if (left != null 
			&& matrix[start.x][start.y] < matrix[left.x][left.y]){
			longestPath = Math.max(longestPath, dfs(matrix, left, map));
		}
		
		Point right = start.moveRight();
		if (right != null 
			&& matrix[start.x][start.y] < matrix[right.x][right.y]){
			longestPath = Math.max(longestPath, dfs(matrix, right, map));
		}
		
		// 此处加1表示从该点到上下左右中的某一点的那一步。
		longestPath += 1;
		map.put(start, longestPath);
		
		return longestPath;
	}
	
	public static void main(String[] args){
		int[] row1 = new int[]{9, 9, 4};
		int[] row2 = new int[]{6, 6, 8};
		int[] row3 = new int[]{2, 2, 1};
		int[][] matrix = new int[][]{row1, row2, row3};
		
		LongestIncreasingPathInAMatrix l = new LongestIncreasingPathInAMatrix();
		System.out.println(l.longestIncreasingPath(matrix));
	}
}
