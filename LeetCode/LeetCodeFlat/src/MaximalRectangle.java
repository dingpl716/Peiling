import java.util.Stack;

//	Given a 2D binary matrix filled with 0's and 1's, 
//	find the largest rectangle containing all ones and return its area.

//	思路，
//	一行一行的来，把每一行到第一行中连续的1想成是LargestRectangleInHistogram
//	中的Histogram，求出每一个行对应的largest rectangle，然后和result比较
//	总时间复杂度：O(n^2)
public class MaximalRectangle {
    public int maximalRectangle(char[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0)
        	return 0;
        int[] currentHistogram = new int[matrix[0].length];
        int result = 0;
        for(int i=0; i<matrix.length; ++i) {
        	currentHistogram = getNewHistogram(i, matrix, currentHistogram);
        	result = Math.max(result, getLargestRectangle(currentHistogram));
        }
        return result;
    }
    
    private int[] getNewHistogram(int i, char[][] matrix, int[]currentHistogram) {
    	for(int j=0; j<matrix[0].length; ++j) {
    		if(matrix[i][j] == '1')
    			++currentHistogram[j];
    		else
    			currentHistogram[j] = 0;
    	}
    	
    	return currentHistogram;
    }
    
    private int getLargestRectangle(int[] histogram) {
    	int[] h = new int[histogram.length+1];
    	for(int i=0; i<h.length-1; ++i) {
    		h[i] = histogram[i];
    	}
    	h[h.length-1] = 0;
    	
    	Stack<Integer> stack = new Stack<Integer>();
    	int result = 0;
    	for(int i=0; i<h.length; ++i) {
    		if(stack.isEmpty() || histogram[stack.peek()] <= h[i]) {
    			stack.push(i);
    		}else {
    			int tmp = stack.pop();
    			result = Math.max(result, h[tmp] * (stack.isEmpty()? i : i-stack.peek()-1));
    			--i;
    		}
    	}
    	return result;
    }
}
