package dfs;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;

import util.Util;

//	Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.
//	
//	Note: The input string may contain letters other than the parentheses ( and ).
//	
//	Examples:
//	"()())()" -> ["()()()", "(())()"]
//	"(a)())()" -> ["(a)()()", "(a())()"]
//	")(" -> [""]

public class RemoveInvalidParentheses {
	// currentMaxLength,  当前results里面string的长度，这个算法会把所有符合合法的括号对都加入到结果集里面，而不是只加入最长的，所以我们需要筛选结果集
	int currentLength = 0;
	
	public List<String> removeInvalidParentheses(String s) {
		HashSet<String> results = new HashSet<String>();
		results.add("");
		
		if (s == null || s.length() == 0) {
			return toList(results);
		}

		StringBuilder temp = new StringBuilder();
		int index = 0;
		int leftCount = 0, rightCount = 0;

		dfs(s, results, temp, index, leftCount, rightCount);

		return toList(results);
	}

	// S, 原始string
	// temp， 目前valid的string，
	// results, 结果集
	// index, 目前访问的位置，我们需要考虑能不能把s[index]放到temp里面，
	// leftCount,目前temp里面已有的左括号数，
	// rightCount, 目前temp里面已有的有括号数
	
	public void dfs(String s, HashSet<String> results, StringBuilder temp, int index, int leftCount, int rightCount) {
		// 剪枝的条件 如果把之后的substring全加上，长度都不可能超过现有的长度，那就不用往下了
		if (temp.length() + s.length() - index < currentLength){
			return;
		}
		
		if (index >= s.length()) {
			if (leftCount == rightCount) {
				if(temp.length() > currentLength){
					results.clear();
					results.add(temp.toString());
					currentLength = temp.length();
				}else if (temp.length() == currentLength){
					results.add(temp.toString());
				}else{
				}
			}
			
			return;
		}

		char current = s.charAt(index);
		if (current == '(') {
			// 永远试图加入一个左括号
			temp.append(s.charAt(index));
			dfs(s, results, temp, index + 1, leftCount + 1, rightCount);
			
			temp.delete(temp.length()-1, temp.length());
			
			// 也要试图不加这个左括号
			dfs(s, results, temp, index + 1, leftCount, rightCount);
		}
		else if (current == ')') {
			// 如果有括号的数大于等于左括号了， 那么不能再加入有括号了
			if (rightCount >= leftCount) {
				dfs(s, results, temp, index + 1, leftCount, rightCount);
			} else {
				// 左括号数大于有括号数, 还可以继续加有括号
				temp.append(s.charAt(index));
				dfs(s, results, temp, index + 1, leftCount, rightCount + 1);
				temp.delete(temp.length()-1, temp.length());

				// 同样, 也要试图跳过这个有括号
				dfs(s, results, temp, index + 1, leftCount, rightCount);
			}
		}
		else {
			temp.append(s.charAt(index));
			dfs(s, results, temp, index + 1, leftCount, rightCount);
			
			// 已知错误: 忘记在这个地方清空最后一个元素了
			temp.delete(temp.length()-1, temp.length());
		}
	}
	
	private static List<String> toList(HashSet<String> results){
		List<String> list = new ArrayList<String>();
		Object[] array = results.toArray();
		for (int i=0; i<array.length; ++i){
			list.add((String)array[i]);
		}
		
		return list;
	}
	
	public static void main(String[] args) {
		RemoveInvalidParentheses instance = new RemoveInvalidParentheses();

//		String s1 = "()())()";
		String s1 = ")(n";
		List<String> results = instance.removeInvalidParentheses(s1);
		Util.PrintListOfString(results);
	}

}
