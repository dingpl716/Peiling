package string_array;
import java.util.ArrayList;

//	Given numRows, generate the first numRows of Pascal's triangle.
//	
//	For example, given numRows = 5,
//	Return
//	
//	[
//	     [1],
//	    [1,1],
//	   [1,2,1],
//	  [1,3,3,1],
//	 [1,4,6,4,1]
//	]


public class PascalTriangle {

	/*
     * the nth row has n elements;
     * x1 and xn = 1 for every row
     * Xnk  -- the kth element of nth row
     * Xnk = X(n-1)(k-1) + X(n-1)k for n > 2, 1<k<n
     */
    
    public ArrayList<ArrayList<Integer>> generate(int numRows) {
    	ArrayList<ArrayList<Integer>> triangle = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> one = new ArrayList<Integer>();
        one.add(1);
        ArrayList<Integer> two = new ArrayList<Integer>();
        two.add(1);
        two.add(1);
        
        if(numRows == 0)
        	return triangle;
        if(numRows == 1){
            triangle.add(one);
            return triangle;
        }
        triangle.add(one);
        triangle.add(two);
        for(int i=3; i<=numRows; ++i) {
            triangle.add(generateNthRow(i, triangle.get(i-2)));
        }
        return triangle;
    }
    
    /*
     * n will be always bigger than 2
     */
    private ArrayList<Integer> generateNthRow(int n, ArrayList<Integer> preRow) {
        ArrayList<Integer> row = new ArrayList<Integer>();
        row.add(1); //first element
        for(int k=2; k<n; ++k) {
            row.add(preRow.get(k-2) + preRow.get(k-1));
        }
        row.add(1); //last element
        
        return row;
    }
    
    public void print(ArrayList<ArrayList<Integer>> triangle) {
    	for(int i=0; i<triangle.size(); ++i) {
    		ArrayList<Integer> row = triangle.get(i);
    		for(int j=0; j<row.size(); ++j){
    			System.out.print(row.get(j) + " ");
    		}
    		System.out.println();
    	}
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PascalTriangle t = new PascalTriangle();
		ArrayList<ArrayList<Integer>> triangle = t.generate(5);
		t.print(triangle);
	}

}
