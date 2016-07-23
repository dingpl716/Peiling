import java.util.Stack;

//Given a string containing just the characters '(' and ')', 
//find the length of the longest valid (well-formed) parentheses substring.
//
//For "(()", the longest valid parentheses substring is "()", which has length = 2.
//
//Another example is ")()())", where the longest valid 
//parentheses substring is "()()", which has length = 4.

//	思路：
//	1.左括号不会导致invalid
//	2.多余的右括号会导致invalid
//	3.每一个有效的结果都必然是以左括号开头，又括号结尾
//	4.所以每次遇到多余的右括号，我们比较tmp和result
//	5.否则我们update tmp

public class LongestValidParentheses {
//	错误的：
//	反例"()(()"
//	expected 是2
//	output是4
//	这个想法不能搞定的case是，如果扫描完整个s
//	都不能找到多余的),那么输出应该是 <= 2*right
//	而不一定等于2*right
    public int longestValidParentheses1(String s) {
    	if(s == null || s.length() < 2)
    		return 0;
    	// 目前的左右括号数
    	int left = 0;
    	int right = 0;
    	// 最大长度
    	int result = 0;
    	// 目前的起始位置
    	int start = 0;
    	for(int i=0; i<s.length(); ++i) {
    		if(s.charAt(i) == '(')
    			++left;
    		if(s.charAt(i) == ')')
    			++right;
    		if(left >= right)
    			continue;
    		else {
    			int tmp = i - start;
    			result = tmp > result ? tmp : result;
    			start = i + 1;
    			left = 0;
    			right = 0;
    		}
    	}
    	if(left >= right)
    		result = 2*right > result ? 2*right : result;
    	return result;
    }
    
//    这道题和larget rectangle in histogram 道理是一样的
//    我们有一个stack来维护括号。遇到（ 压栈，遇到 ） 出栈, 并且
//    计算此时的i与栈顶元素的距离之差，就是一个valid的tmp，应跟新之。
//    如果遇到）且此时栈为空的话，那么说明这个）是多余的，那么应该
//    比较tmp和result，栈里面存放的是（的坐标
//    注意：
//    1.多余的）将整个s分成了几段valid的group，所以需要用一个变量来记录
//    上一次的多余的）的坐标，初始值为-1；
//    2.如果没有多余的），也有可能有多余的（
//    所以每次计算完tmp之后就要和result比较
    public int longestValidParentheses(String s) {
    	if(s == null || s.length() < 2)
    		return 0;
    	Stack<Integer> stack = new Stack<Integer>();
    	int tmp = 0;
    	int result = 0;
    	int breaking = -1;
    	for(int i=0; i<s.length(); ++i) {
    		// meet a (
    		if(s.charAt(i) == '(')
    			stack.push(i);
    		else { // meet a )
    			// redundent )
    			if(stack.empty()) {
    				result = tmp > result ? tmp : result;
    				tmp = 0;
    				breaking = i;
    			}else {
    				stack.pop();
    				tmp = i - (stack.isEmpty()? breaking : stack.peek());
    				result = tmp > result ? tmp : result;
    			}
    		}
    	}
    	
    	result = tmp > result ? tmp : result;
    	return result;
    }
}
