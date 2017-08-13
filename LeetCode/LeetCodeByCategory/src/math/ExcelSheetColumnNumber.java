package math;

//	Related to question Excel Sheet Column Title
//	
//	Given a column title as appear in an Excel sheet, return its corresponding column number.
//	
//	For example:
//	
//	    A -> 1
//	    B -> 2
//	    C -> 3
//	    ...
//	    Z -> 26
//	    AA -> 27
//	    AB -> 28

/**
 * 27进制数到10进制数转换
 * ABC = 
 * 26^2 * 1 + 26^1 * 2 + 26^0 * 3
 * @author Dingp
 *
 */
public class ExcelSheetColumnNumber {
	
	private static final double base = 26;
	
    public int titleToNumber(String s) {
    	if (s == null || s.length() == 0) {
    		return 0;
    	}
    	
    	double result = 0;
    	for (int i = 0; i < s.length(); ++i) {
    		double weight = Math.pow(base, (double)s.length() - i - 1);
    		result += weight * (s.charAt(i) - 'A' + 1); 
    	}
    	
    	return (int)result;
    }
}
