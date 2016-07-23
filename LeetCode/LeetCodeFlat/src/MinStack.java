import java.util.Stack;

//	Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
//	
//	push(x) -- Push element x onto stack.
//	pop() -- Removes the element on top of the stack.
//	top() -- Get the top element.
//	getMin() -- Retrieve the minimum element in the stack.

public class MinStack {

	private Stack<Integer> dataStack = new Stack<Integer>();
	private Stack<Integer> minStack = new Stack<Integer>();
	
    public void push(int x) {
    	if (dataStack.isEmpty()){
    		dataStack.push(x);
    		minStack.push(x);
    	}else{
    		dataStack.push(x);
    		if (x <= minStack.peek()){
    			minStack.push(x);
    		}
    	}
    }

    public void pop() {
    	if (dataStack.isEmpty()){
    		return;
    	}
    	
    	int top = dataStack.pop();
    	if (top == minStack.peek()){
    		minStack.pop();
    	}
    	
    	if (dataStack.isEmpty()){
    		minStack.clear();
    	}
    }

    public int top() {
        return dataStack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
    
	public static void main(String[] args) {
		
	}

}
