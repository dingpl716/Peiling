package dfs;

//	Given a positive integer n, find the least number of perfect square numbers 
//	(for example, 1, 4, 9, 16, ...) which sum to n.
//	
//	For example, given n = 12, return 3 because 12 = 4 + 4 + 4; 
//	given n = 13, return 2 because 13 = 4 + 9.

public class PerfectSquares {

	int result = Integer.MAX_VALUE;
	
    public int numSquares(int n) {
    	int squareRoot = (int)Math.sqrt(n);
    	dfs(n, squareRoot, 0);
    	
    	return result;
    }
    
    /**
     * 以dfs的方式把所有的可能性都找出来
     * 如 
     * 12 = 4 + 4 + 4 或 9 + 1 + 1 + 1
     * 13 = 9 + 4 或 9 + 1 + 1 + 1 + 1 或 4 + 4 + 4 + 1 等
     * @param n
     * @param initialSquareRoot
     * @param numberOfSquareRoots
     */
    private void dfs(int n, int initialSquareRoot, int numberOfSquareRoots) {
    	// 注意这个地方要剪枝
    	if (numberOfSquareRoots > result) {
    		return;
    	}
    	
    	if (n == 0){
    		result = Math.min(result, numberOfSquareRoots);
    		return ;
    	}
    	for (int i = initialSquareRoot; i >= 1; --i){
    		int square = (i * i);
    		
    		/*
    		 * 在这里n是我们这次递归需要处理的剩余部分, 
    		 * 而initialSquareRoot只是最开始的，那个最大的square root
    		 * 当n < square的时候我们不能直接就退出这一次循环了,
    		 * 而是要继续往下试到1为止
    		 */
    		if (n < square) {
    			continue;
    		}else {
    			dfs(n - square, i, numberOfSquareRoots + 1);
    		}
    	}
    }
    

    public static void main(String[] args){
    	PerfectSquares p = new PerfectSquares();
    	
    	System.out.println(p.numSquares(12));
    }
}
