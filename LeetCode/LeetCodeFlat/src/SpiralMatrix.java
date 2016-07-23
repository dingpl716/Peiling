import java.util.ArrayList;

//	Given a matrix of m x n elements (m rows, n columns), 
//	return all elements of the matrix in spiral order.
//	
//	For example,
//	Given the following matrix:
//	
//	[
//	 [ 1, 2, 3 ],
//	 [ 4, 5, 6 ],
//	 [ 7, 8, 9 ]
//	]
//	You should return [1,2,3,6,9,8,7,4,5].

//已犯错误：
//1. 更改方向的时候就也必须要把坐标改了，不能只更改方向，不然下次会重复加入元素
//2. k值的跟新必须发生在由left变成up的时候，而不是在如果dir是up的时候
public class SpiralMatrix {
    public ArrayList<Integer> spiralOrder(int[][] matrix) {
    	ArrayList<Integer> result = new ArrayList<Integer>();
    	if(matrix == null || matrix.length ==0 || matrix[0].length==0)
    		return result;
    	//0: right, 1: down, 2: left, 3: up
    	int dir = 0;
    	//steps before reaching the edge;
    	int k = 0;
    	int m = matrix.length;
    	int n = matrix[0].length;
    	int i=0, j=0;
    	for(int s=1; s<=m*n; ++s) {
    		if(dir == 0) {
    			if(j<n-1-k)
    				result.add(matrix[i][j++]);
    			else {
    				result.add(matrix[i][j]);
    				dir = 1;
    				++i;
    			}
    		}else if(dir == 1){
    			if(i<m-1-k)
    				result.add(matrix[i++][j]);
    			else {
    				result.add(matrix[i][j]);
    				dir = 2;
    				--j;
    			}
    		}else if(dir == 2){
    			if(j>k)
    				result.add(matrix[i][j--]);
    			else{
    				result.add(matrix[i][j]);
    				dir = 3;
    				--i;
    				++k;
    			}
    		}else {
    			if(i>k)
    				result.add(matrix[i--][j]);
    			else{
    				result.add(matrix[i][j]);
    				dir = 0;
    				++j;
    			}
    		}
    	}
    	return result;
    }
}
