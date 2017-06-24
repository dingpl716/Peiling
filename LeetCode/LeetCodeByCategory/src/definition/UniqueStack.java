package definition;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class UniqueStack<T> {
	
	private Set<T> set;
	private Stack<T> stack;
	
	public UniqueStack(){
		set = new HashSet<T>();
		stack = new Stack<T>();
	}

	
	public void push(T t){
		if (!set.contains(t)){
			stack.push(t);
			set.add(t);
		}
	}
	
	public T pop(){
		if (!stack.isEmpty()) {
			T t = stack.pop();
			set.remove(t);
			return t;
		}
		
		return null;
	}
	
	public boolean contains(T t){
		return set.contains(t);
	}
	
	public boolean isEmpty(){
		return set.isEmpty();
	}
	
	public void clear(){
		stack.clear();
		set.clear();
	}
}
