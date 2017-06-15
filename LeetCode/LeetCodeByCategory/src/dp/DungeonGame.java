package dp;
//	The demons had captured the princess (P) and imprisoned her in the bottom-right corner of a dungeon. 
//	The dungeon consists of M x N rooms laid out in a 2D grid. Our valiant knight (K) was initially 
//  positioned in the top-left room and must fight his way through the dungeon to rescue the princess.
//	
//	The knight has an initial health point represented by a positive integer. If at any point his health 
//  point drops to 0 or below, he dies immediately.
//	
//	Some of the rooms are guarded by demons, so the knight loses health (negative integers) upon entering these rooms; 
//  other rooms are either empty (0's) or contain magic orbs that increase the knight's health (positive integers).
//	
//	In order to reach the princess as quickly as possible, the knight decides to move only rightward or downward in each step.
//	
//	
//	Write a function to determine the knight's minimum initial health so that he is able to rescue the princess.
//	
//	For example, given the dungeon below, the initial health of the knight must be at least 7 if he follows 
//  the optimal path RIGHT-> RIGHT -> DOWN -> DOWN.
//	
//	-2(K)	-3		3
//	-5		-10		1
//	10		30		-5(P)
//	
//	Notes:
//	
//	The knight's health has no upper bound.
//	Any room can contain threats or power-ups, even the first room the knight enters and the bottom-right 
//  room where the princess is imprisoned.

public class DungeonGame {
	/**
	 * 设R[i,j]表示为了能到达i,j所需要的最少的初始血量
	 * 这个题需要从后往前推导，因为如果从前往后的话会出现短期利益和长期利益相悖的情况
	 * 
	 * @param dungeon
	 * @return
	 */
    public int calculateMinimumHP(int[][] dungeon) {
    	if (dungeon == null || dungeon.length == 0) {
    		return 1;
    	}
    	
    	int rows = dungeon.length;
    	int columns = dungeon[0].length;
        int[][] R = new int[rows][columns];
        
        // 初始化R
        for (int i = 0; i < rows; ++i){
        	for (int j = 0; j < columns; ++j){
        		R[i][j] = Integer.MAX_VALUE;
        	}
        }
        
        // 初始化R的最后一个元素，也就是终点的情况，在这里我们希望我们能够刚好到达终点的时候只有一滴血，
        // 所以
        R[rows - 1][columns - 1] = 1 - dungeon[rows - 1][columns - 1];
        R[rows - 1][columns - 1] = Math.max(R[rows - 1][columns - 1], 1);
        
        // 在这个循环中我们所做的事情实际上是
        // 以[i,j]为终点，分别推断从上面和从左边到达[i,j]时，上面的点和左边的点分别至少需要多少点血。
        for (int i = rows - 1; i >= 0; --i){
        	for(int j = columns - 1; j >= 0; --j){
        		
        		if (i > 0) {
        			// 这个值表示，如果我们是从上面的点来到当前这个点，那么上面那个点所需要的初始血量是多少。
        			int up = R[i][j] - dungeon[i-1][j];
        			// 确保这个初始血量是最小的
        			R[i-1][j] = Math.min(R[i-1][j], up);
        			// 确保这个血量永远大于0
        			R[i-1][j] = Math.max(R[i-1][j], 1);
        		}
        		
        		if (j > 0) {
        			// 这个值表示，如果我们是从左边的点来到当前这个点，那么左边那个点所需要的初始血量是多少。
        			int left = R[i][j] - dungeon[i][j-1];
        			R[i][j-1] = Math.min(R[i][j-1], left);
        			R[i][j-1] = Math.max(R[i][j-1], 1);
        		}
        	}
        }
        
        return R[0][0];
    }
}
