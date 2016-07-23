package string_array;
import java.util.Stack;

//	Evaluate the value of an arithmetic expression in Reverse Polish Notation.
//	
//	Valid operators are +, -, *, /. Each operand may be an integer or another expression.
//	
//	Some examples:
//	  ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
//	  ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6

public class EvaluateReversePolishNotation {
//	思路：
//	遇到数字就压栈，遇到操作符就弹出两个数来运算，并且把运算结果压栈
    public int evalRPN(String[] tokens) {
    	Stack<Integer> stack = new Stack<Integer>();
    	for(int i=0; i<tokens.length; ++i) {
    		if(!isOperator(tokens[i]))
    			stack.push(Integer.parseInt(tokens[i]));
    		else {
    			Integer operand2 = stack.pop();
    			Integer operand1 = stack.pop();
    			stack.push(calculate(operand1, operand2, tokens[i]));
    		}
    	}
    	
    	return stack.peek();
    }
    
    private boolean isOperator(String str) {
    	return ("+".equals(str)) || ("-".equals(str)) ||
    			("*".equals(str)) || ("/".equals(str));
    }
    
    private int calculate(int o1, int o2, String operator) {
    	switch (operator) {
	    	case "+" : return o1 + o2;
	    	case "-" : return o1 - o2;
	    	case "*" : return o1 * o2;
	    	default : return o1 / o2;
    	}
    }
}
