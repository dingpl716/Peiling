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
        int maxCol = triangle.get(triangle.size() -1).size();
        ArrayList<Integer> tmp = new ArrayList<Integer>(triangle.get(triangle.size()-1));
        
        // 注意 这里不是从减一开始，而是从减2开始
        for(int i=maxCol-2; i>=0; --i) {
        	ArrayList<Integer> row = triangle.get(i);
        	for(int j=0; j<row.size(); ++j) {
        		int path = tmp.get(j) < tmp.get(j+1) ? tmp.get(j) : tmp.get(j+1);
        		tmp.set(j, path + row.get(j));
        	}
        }
        
        return tmp.get(0);
    }	
}
