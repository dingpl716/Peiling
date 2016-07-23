package dfs;
import java.util.ArrayList;
import java.util.Stack;


/**
 * 给定一个长度为n的不重复数组，求所有可能的入栈出栈顺序。
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
		
		getPushPopArray(n, 0, buff, stack);
		
		return result;
	}
	
//	如果buff的size等于n的size了，buff应该被加入到result里面，
//	start小于n的size说明我们可以push， 否则只能pop
//	如果stack的size不空的话我们可以pop
//	如果stack的size等于n的size的话我们只能pop
	private void getPushPopArray(int[] n, int start, ArrayList<Integer> buff,
						Stack<Integer> stack) {
		if(buff.size() == n.length) {
			result.add(new ArrayList<Integer>(buff));
			return;
		}
		if(start < n.length) {
			stack.push(n[start]);
			getPushPopArray(n, start+1, buff, stack);
			stack.pop(); //你操作
		}
		if(!stack.empty()){
			buff.add(stack.pop());
			getPushPopArray(n, start, buff, stack);
			stack.push(buff.get(buff.size()-1)); //逆操作
			buff.remove(buff.size()-1);		//逆操作
		}
	}
}
