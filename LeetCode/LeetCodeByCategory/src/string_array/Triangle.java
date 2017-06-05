package string_array;
import java.util.ArrayList;

//	
//	Given a triangle, find the minimum path sum from top to bottom. 
//	Each step you may move to adjacent numbers on the row below.
//	
//	For example, given the following triangle
//	[
//	     [2],
//	    [3,4],
//	   [6,5,7],
//	  [4,1,8,3]
//	]
//	The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).
//	
//	Note:
//	Bonus point if you are able to do this using only O(n) extra space, 
//	where n is the total number of rows in the triangle.

public class Triangle {
    /**
     * 从下忘上搜索，如果从上往下的话，初始值会很麻烦
     * tmp 来记录每一行的中间结果
     * 选取两个点中小的那个
     */
     
    public int minimumTotal(ArrayList<ArrayList<Integer>> triangle) {
        if(triangle == null)
            return 0;
        
        ArrayList<Integer> lastRow = triangle.get(triangle.size()-1);
        int sizeOfLastRow = lastRow.size();
        ArrayList<Integer> tmp = new ArrayList<Integer>(lastRow);
        
        // 注意 这里不是从减一开始，而是从减2开始
        for(int i=sizeOfLastRow-2; i>=0; --i) {
        	ArrayList<Integer> row = triangle.get(i);
        	for(int j=0; j<row.size(); ++j) {
        		int path = Math.min(tmp.get(j), tmp.get(j+1));
        		tmp.set(j, path + row.get(j));
        	}
        }
        
        return tmp.get(0);
    }	

    /**
     * 像树一样遍历这个
     * @param triangle
     * @return
     */
    public int minimumTotal2(ArrayList<ArrayList<Integer>> triangle) {
    	return dfs(triangle, 0, 0, 0);
    }
    
    private int dfs(ArrayList<ArrayList<Integer>> triangle, int currentRow, int currentCol, int currentSum){
    	int[] leftChild = getLeftChild(triangle, currentRow, currentCol);
    	int[] rightChild = getRightChild(triangle, currentRow, currentCol);
    	int currentValue = triangle.get(currentRow).get(currentCol);
    	currentSum += currentValue;
    	if (leftChild == null && rightChild == null){
    		return currentSum;
    	}
    	else {
    		return Math.min(dfs(triangle, leftChild[0], leftChild[1], currentSum),
    				dfs(triangle, rightChild[0], rightChild[1], currentSum));
    	}
    }
    
    private int[] getLeftChild(ArrayList<ArrayList<Integer>> triangle, int currentRow, int currentCol){
    	if (currentRow == triangle.size()){
    		return null;
    	}
    	
    	return new int[]{currentRow + 1, currentCol};
    }
    
    private int[] getRightChild(ArrayList<ArrayList<Integer>> triangle, int currentRow, int currentCol){
    	if (currentRow == triangle.size()){
    		return null;
    	}
    	
    	return new int[]{currentRow + 1, currentCol + 1};
    }
}
