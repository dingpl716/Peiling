package string_array;

import java.util.ArrayList;
import java.util.List;

//	Given an index k, return the kth row of the Pascal's triangle.
//	
//	For example, given k = 3,
//	Return [1,3,3,1].
//	
//	Note:
//	Could you optimize your algorithm to use only O(k) extra space?
//	[ 
//	    [1],
//	   [1,1],
//	  [1,2,1],
//	 [1,3,3,1],
//	[1,4,6,4,1]
//	]

public class PascalTriangleII {
	
	public List<Integer> getRow(int rowIndex) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		
		if (rowIndex < 0) {
			return result;
		}
		
		result.add(1);
		for (int i = 1; i <= rowIndex; ++i) {
			result = generateRow(i, result);
		}
		
		return result;
    }
	
	private ArrayList<Integer> generateRow(int rowNumber, ArrayList<Integer> previousRow) {
		ArrayList<Integer> newRow = new ArrayList<Integer>(rowNumber * 2);
		newRow.add(1);
		
		for (int i = 1; i < previousRow.size(); ++i) {
			newRow.add(previousRow.get(i - 1) + previousRow.get(i));
		}
		
		newRow.add(1);
		return newRow;
	}
}
