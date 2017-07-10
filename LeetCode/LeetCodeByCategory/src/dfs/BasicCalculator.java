package dfs;

import java.util.LinkedList;

//	Implement a basic calculator to evaluate a simple expression string.
//	
//	The expression string may contain open ( and closing parentheses ), 
//	the plus + or minus sign -, non-negative integers and empty spaces .
//	
//	You may assume that the given expression is always valid.
//	
//	Some examples:
//	"1 + 1" = 2
//	" 2-1 + 2 " = 3
//	"(1+(4+5+2)-3)+(6+8)" = 23

/**
 * 核心思想DFS：
 * 每个DFS都只处理一个括号对
 * 一个字符一个字符的扫描,如果遇到左括号（就递归调用DFS
 * 在DFS里面，用一个int list存储扫描所得的所有数字，
 * 用一个char list存储扫描所得的所有操作符
 * 如果遇到右括号）就用所有数字和操作符进行计算
 * 如果遍历完整个字符串，也进行此计算。
 * @author Dingp
 *
 */
public class BasicCalculator {

    public int calculate(String s) {
    	if (s == null || s.length() == 0) {
    		return 0;
    	}
    	
    	return dfs(s, -1)[1];
    }
    
    /**
     * 处理一个括号对或者没有括号的整个表达式，
     * 返回两个数值：
     * 1. 最后被处理的index
     * 2. 该dfs所计算的结果。
     * @param s
     * @param startIndex 起始左括号的index
     */
    private int[] dfs(String s, int startIndex) {
    	int currentIndex = startIndex + 1;
    	
    	// 用来存储扫描所得的所有数字
    	LinkedList<Integer> numbers = new LinkedList<Integer>();
    	numbers.addLast(0);
    	
    	// 用来存储扫描所得的所有运算符
    	LinkedList<Character> operations = new LinkedList<Character>();
    	
    	while (currentIndex < s.length()) {
    		char c = s.charAt(currentIndex);
    		switch (c) {
	    		case '(':
	    			int[] values = dfs(s, currentIndex);
	    			int tmp1 = numbers.pollLast();
	    			tmp1 = tmp1 * 10 + values[1];
	    			numbers.addLast(tmp1);
	    			currentIndex = values[0];
	    			break;
	    		case ')':
	    			int result = calculate(numbers, operations);
	    			return new int[] {currentIndex, result};
	    		case '+':
	    			numbers.addLast(0);
	    			operations.addLast('+');
	    			break;
	    		case '-':
	    			numbers.addLast(0);
	    			operations.addLast('-');
	    			break;
	    		case ' ':
	    			break;
	    		default: 
	    			int tmp2 = numbers.pollLast();
	    			tmp2 = (tmp2 * 10) + (c - '0');
	    			numbers.addLast(tmp2);
    		}
    		currentIndex++;
    	}
    	
    	int result = calculate(numbers, operations);
    	return new int[] {currentIndex, result};
    }
    
    /**
     * 计算数字和相应的运算符的结果
     * @param numbers
     * @param operations
     * @return
     */
    private int calculate(LinkedList<Integer> numbers, LinkedList<Character> operations) {
    	int result = numbers.poll();
    	
    	while (!operations.isEmpty() && !numbers.isEmpty()) {
    		
    		int number = numbers.poll();
    		char operation = operations.poll();
    		
    		if (operation == '+') {
    			result = result + number;
    		}else {
    			result = result - number;
    		}
    	}
    	
    	return result;
    }
    
    public static void main(String[] args) {
    	BasicCalculator b = new BasicCalculator();
    	System.out.println(b.calculate("(1+(4+5+2)-3)+(6+8)"));
    }
}
