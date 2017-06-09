package design;

import java.util.Iterator;
import java.util.List;
import java.util.Stack;

import definition.NestedInteger;

/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
public class NestedIterator implements Iterator<Integer> {

	Stack<List<NestedInteger>> listStack = null;
	Stack<Integer> indexStack = null;
	Integer nextInteger = null;
	
    public NestedIterator(List<NestedInteger> nestedList) {
    	listStack = new Stack<List<NestedInteger>>();
    	indexStack = new Stack<Integer>();
    	
    	if (nestedList != null && !nestedList.isEmpty()){
    		listStack.push(nestedList);
    		indexStack.push(-1);
    		nextInteger = getNext();
    	}
    }

    @Override
    public Integer next() {
    	Integer tmp = nextInteger;
    	nextInteger = getNext();
    	return tmp;
    }
    
    @Override
    public boolean hasNext() {
        return nextInteger != null;
    }
    
    private Integer getNext() {
    	if (listStack.isEmpty()){
    		return null;
    	}
    	
    	List<NestedInteger> currentList = listStack.pop();
    	int nextIndex = indexStack.pop() + 1;
    	NestedInteger nextNestedInteger = null;
    	for(; nextIndex < currentList.size(); ++nextIndex){
    		nextNestedInteger = currentList.get(nextIndex);
    		if (!isValidNestedInteger(nextNestedInteger)){
    			continue;
    		}
    		
    		break;
    	}
    	
    	if (nextIndex > currentList.size() - 1) {
    		return getNext();
    	}
    	else if (nextIndex == currentList.size() - 1){
    		if (nextNestedInteger.isInteger()){
    			return nextNestedInteger.getInteger();
    		}
    		else {
    			listStack.push(nextNestedInteger.getList());
    			indexStack.push(-1);
    			return getNext();
    		}
    	}else {
    		if (nextNestedInteger.isInteger()){
    			listStack.push(currentList);
    			indexStack.push(nextIndex);
    			return nextNestedInteger.getInteger();
    		}else {
    			listStack.push(currentList);
    			indexStack.push(nextIndex);
    			listStack.push(nextNestedInteger.getList());
    			indexStack.push(-1);
    			return getNext();
    		}
    	}
    }

    private boolean isValidNestedInteger(NestedInteger nestedInteger){
    	if (nestedInteger == null) {
    		return false;
    	}
    	else if (nestedInteger.isInteger()) {
    		return nestedInteger.getInteger() != null;
    	}
    	else {
    		if (nestedInteger.getList() == null || nestedInteger.getList().isEmpty()){
    			return false;
    		}
    		else {
    			for (NestedInteger n : nestedInteger.getList()){
    				if (isValidNestedInteger(n)){
    					return true;
    				}
    			}
    			
    			return false;
    		}
    	}
    }
    
	@Override
	public void remove() {
		// TODO Auto-generated method stub
		
	}

	
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */
