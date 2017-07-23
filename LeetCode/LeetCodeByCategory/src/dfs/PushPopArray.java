package dfs;
import java.util.ArrayList;
import java.util.Stack;


/**
 * 给定一个长度为n的不重复数组，求所有可能的入栈出栈顺序。
 * 也就是说把这个数组以不同的顺序入栈，再以不同的顺序出栈，求所有的出栈顺序。
 * 已犯错误：
 * 没有进行逆操作，你操作的地方有两个：
 * 1.如果向stack里面push过，那么stack应该pop一个
 * 2.如果stack向buff里面push过，那么buff应该把这个element退回给stack
 * @author Peiling
 *
 */
public class PushPopArray {
	
	private ArrayList<ArrayList<Integer>> result = null;
	
	public ArrayList<ArrayList<Integer>> getPushPopArray(int[] n) {
		result = new ArrayList<ArrayList<Integer>>();
		
		ArrayList<Integer> buff = new ArrayList<Integer>();
		Stack<Integer> stack = new Stack<Integer>();
		
		dfs(n, 0, buff, stack);
		
		return result;
	}
	
//	如果buff的size等于n的size了，buff应该被加入到result里面，
//	start小于n的size说明我们可以push， 否则只能pop
//	如果stack的size不空的话我们可以pop
//	如果stack的size等于n的size的话我们只能pop
	
	/**
	 * 核心思想:
	 * 如果数组里面还有值，那么就肯定可以入栈，再dfs，最后记得逆操作
	 * 如果stack里面还有值，那么久肯定可以出栈，再dfs，最后记得逆操作
	 * @param n
	 * @param start
	 * @param buff
	 * @param stack
	 */
	private void dfs(int[] n, int start, ArrayList<Integer> buff,
						Stack<Integer> stack) {
		if(buff.size() == n.length) {
			result.add(new ArrayList<Integer>(buff));
			return;
		}
		if(start < n.length) {
			stack.push(n[start]);
			dfs(n, start+1, buff, stack);
			stack.pop(); //逆操作
		}
		if(!stack.empty()){
			buff.add(stack.pop());
			dfs(n, start, buff, stack);
			stack.push(buff.get(buff.size()-1)); //逆操作
			buff.remove(buff.size()-1);		//逆操作
		}
	}
}
