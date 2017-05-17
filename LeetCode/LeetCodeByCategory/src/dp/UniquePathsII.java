package dp;

//	Follow up for "Unique Paths":
//	
//	Now consider if some obstacles are added to the grids. How many unique paths would there be?
//	
//	An obstacle and empty space is marked as 1 and 0 respectively in the grid.
//	
//	For example,
//	There is one obstacle in the middle of a 3x3 grid as illustrated below.
//	
//	[
//	  [0,0,0],
//	  [0,1,0],
//	  [0,0,0]
//	]
//	The total number of unique paths is 2.

public class UniquePathsII {

	public int uniquePathsWithObstacles(int[][] obstacleGrid) {
     
		int[][] p = new int[obstacleGrid.length][obstacleGrid[0].length];
		
		for (int i = 0; i < p.length; ++i){
			for (int j = 0; j < p[0].length; ++j){
				if (obstacleGrid[i][j] == 1){
					p[i][j] = 0;
				}
				else {
					if (i == 0 && j == 0){
						p[i][j] = obstacleGrid[i][j] == 0 ? 1 : 0;
					}
					else if (i == 0){
						p[i][j] = p[i][j - 1];
					}
					else if (j == 0){
						p[i][j] = p[i - 1][j];
					}
					else {
						p[i][j] = p[i-1][j] + p[i][j-1];
					}
				}
			}
		}
		
		return p[obstacleGrid.length - 1][obstacleGrid[0].length - 1];
    }
	
	public static void main(String[] args){
		int[][] o = new int[2][1];
		o[0] = new int[]{0};
		o[1] = new int[]{0};
		
		UniquePathsII up2 = new UniquePathsII();
		int result = up2.uniquePathsWithObstacles(o);
		System.out.println(result);
	}
}
