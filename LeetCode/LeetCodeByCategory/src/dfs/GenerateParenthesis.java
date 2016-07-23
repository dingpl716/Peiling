package dfs;
import java.util.ArrayList;
//	Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
//	
//	For example, given n = 3, a solution set is:
//	
//	"((()))", "(()())", "(())()", "()(())", "()()()"

/**
 * 假设在位置k我们还剩余left个左括号和right个右括号，
 * 如果left>0，则我们可以直接打印左括号，而不违背规则。
 * 能否打印右括号，我们还必须验证left和right的值是否满足规则，
 * 如果left>=right，则我们不能打印右括号，
 * 因为打印会违背合法排列的规则，否则可以打印右括号。
 * 如果left和right均为零，则说明我们已经完成一个合法排列，可以将其打印出来。
 * @author Peiling
 *
 */

/**
 * 把这个题和出栈入栈题一起看，PushPopArray
 * @author Peiling
 *
 */
public class GenerateParenthesis {

    public ArrayList<String> generateParenthesis(int n) {
        ArrayList<String> result = new ArrayList<String>();
        String s = "";
        generateParenthesis(n, n, s, result);
        return result;
    }
    
    /**
     * 
     * @param left : the # of ( we still can print
     * @param right : the # of ) we can print
     * @param s : the string of parenthesis that we are going to print out
     * @param result
     */
    private void generateParenthesis(int left, int right, String s, ArrayList<String> result) {
        if(left == 0 && right == 0) {
        	result.add(s);
        	return;
        }
        if(left > 0) {
            generateParenthesis(left-1, right, s+"(", result);
        }
        
        if(right > 0 && right > left) {
            generateParenthesis(left, right-1, s+")", result);
        }
    }
}
