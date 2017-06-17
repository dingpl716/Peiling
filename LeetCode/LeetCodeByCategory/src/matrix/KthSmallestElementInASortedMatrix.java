package matrix;

//	Given a n x n matrix where each of the rows and columns are sorted 
//	in ascending order, find the kth smallest element in the matrix.
//	
//	Note that it is the kth smallest element in the sorted order, not the kth distinct element.
//	
//	Example:
//	
//	matrix = [
//	   [ 1,  5,  9],
//	   [10, 11, 13],
//	   [12, 13, 15]
//	],
//	k = 8,
//	
//	return 13.
//	Note: 
//	You may assume k is always valid, 1 ≤ k ≤ n2.
public class KthSmallestElementInASortedMatrix {

	/**
	 * 核心思想： 最小堆
	 * 1. 用一个最小堆把第一行的元素全部装入。
	 * 2. 然后做k - 1次如下操作，
	 * 	  2.1 取出堆顶元素，并将其同一列的下一个元素装入堆中。
	 * 3. 最后堆顶元素即为所求。
	 * 
	 * 至于为什么要用一个堆把一整行的数据都装入这个问题，我们可以这么理解：
	 * 这是一个二维数组，他在两个方向上有序，很难处理。所以我们需要把他降低到一个维度上来。
	 * 如果把每一行都看成是一个大的单元，那么这个matrix就变成了只在纵向上有序，这么一个一维的问题了。
	 * @param matrix
	 * @param k
	 * @return
	 */
	public int kthSmallest(int[][] matrix, int k) {
		return 0;
	}
	
	/********************************************************************************************************/
	
	/**
	 * 二分查找法， 相似的问题，请参考Find the Duplicate Number
	 * @param matrix
	 * @param k
	 * @return
	 */
    public int kthSmallest_BinarySearch(int[][] matrix, int k) {
        if (matrix == null || matrix.length == 0) {
        	return 0;
        }
        
        int rows = matrix.length;
        int cols = matrix[0].length;
        
        // The smallest value and the biggest value.
        int left = matrix[0][0];
        int right = matrix[rows - 1][cols - 1];
        
        while (left < right) {
        	int middle = (left + right) / 2;
        	
        	// Count the number of elements which are smaller than or equal to M.
        	int count = countTheNumberNoLargerThanM(matrix, middle);
        	
        	// 如果有太多的数都小于middle的话，那么说明middle取大了，我们收缩右边界。
        	// 但是如果刚好前面有k个数小于等于middle，也不能说middle就是我们要找的数，
        	// 因为此时的middle可能是某个比要找的数大一点点的数，但是又不存在于这个matrix里面。
        	// 例如: ....... 11, 13, 15.......
        	// 此时13是第k小的数，我们要找的就是13，
        	// 那么此时有:
			//	countTheNumberNoLargerThanM(11) = k - 1
			//	countTheNumberNoLargerThanM(12) = k - 1
			//	countTheNumberNoLargerThanM(13) = k
			//	countTheNumberNoLargerThanM(14) = k
			//	countTheNumberNoLargerThanM(13) = k + 1
        	if (count >= k) {
        		right = middle;
        	} 
        	
        	// 如果小于middle的数不够的话，那么说明middle取小了，我们收缩左边界
        	else {
        		left = middle + 1;
        	}
        }
        
        return left;
    }
    
    /**
     * Count the number of elements which are smaller than or equal to M.
     * 从右上角开始数，对于每一行往左扫描，找到第一个小于m的数，那么这个数左边包括这个数
     * @param matrix
     * @param m
     * @return
     */
    private int countTheNumberNoLargerThanM(int[][] matrix, int m){
    	int rows = matrix.length;
        int cols = matrix[0].length;
        int result = 0;
        
        // 对于每一行从其最右边开始
        for (int i = 0; i < rows; ++i) {
        	int j = cols - 1;
        	
        	// 找到第一个小于m的数
        	while (j >= 0 && matrix[i][j] > m) {
        		-- j;
        	}
        	result += j + 1;
        }
        
        return result;
    }
    
    public static void main(String[] args) {
    	int[] row1 = new int[] {1, 5, 9};
    	int[] row2 = new int[] {10, 11, 13};
    	int[] row3 = new int[] {12, 14, 15};
    	int[][] matrix = new int[][]{row1, row2, row3};
    	
    	KthSmallestElementInASortedMatrix k = new KthSmallestElementInASortedMatrix();
    	System.out.println(k.kthSmallest_BinarySearch(matrix, 8));
    }
}
