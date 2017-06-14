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

	// 记录我们需要遍历的list
	Stack<List<NestedInteger>> listStack = null;
	
	// 记录我们需要遍历的list的index情况
	Stack<Integer> indexStack = null;
	Integer nextInteger = null;
	
    public NestedIterator(List<NestedInteger> nestedList) {
    	listStack = new Stack<List<NestedInteger>>();
    	indexStack = new Stack<Integer>();
    	
    	// 初始化时，将最外层的list压栈，并压入-1作为起始index
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
    
    /**
     * 核心思想:
     * 1. 永远都试图取出一个list来遍历，如果取出不到任何list，那么说明遍历完了，返回null.
     * 	     这也是为什么我们一开始要把最外层的list也压栈
     * 2. 取出一个list和与之对应的index后，找到这个list里面下一个valid的NestedInteger，即一个非空的NestedInteger。
     *    在这里需要注意nested空list
     * 3. 如果我们在这个list中找不到一个valid NestedInteger了，那么这个list就被遍历完了，不再将它压入栈内，而是递归的调用getNext
     * 4. 如果找到一个valid NestedInteger了，那么要根据这个元素它是否是这个list的最后一个元素来分别进行操作。
     *    如果是最后一个元素，那么不在将这个list压栈，如果不是那么就需要压栈
     * 5. 如果找到的这个元素是一个Integer，那我们就进行输出，但是如果是一个list，那么我们此时不输出任何东西，只单纯的把这个新
     *    list压栈，并压入-1作为起始index，再递归的调用getNext
     * @return
     */
    private Integer getNext() {
    	if (listStack.isEmpty()){
    		return null;
    	}
    	
    	List<NestedInteger> currentList = listStack.pop();
    	
    	// 指向在这一轮，我们需要输出的元素的index
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
    	
    	// 如果到达该list的最后一个位置了，那么我们不在将这个list压栈
    	else if (nextIndex == currentList.size() - 1){
    		if (nextNestedInteger.isInteger()){
    			return nextNestedInteger.getInteger();
    		}
    		else {
    			listStack.push(nextNestedInteger.getList());
    			indexStack.push(-1);
    			return getNext();
    		}
    		
    	// 还没有到达这个list的最后一个元素，之后还需要遍历它，所以需要将这个list压栈
    	}else {
    		// 如果这个元素只是一个简单的Integer，那么压栈后输出
    		if (nextNestedInteger.isInteger()){
    			
    			// 将这个list和访问到的index压栈
    			listStack.push(currentList);
    			indexStack.push(nextIndex);
    			return nextNestedInteger.getInteger();
    			
    		// 如果这个元素代表一个新的list，那么把老的和新的list分别压栈，然后递归的调用getNext
    		}else {
    			// 将这个list和访问到的index压栈
    			listStack.push(currentList);
    			indexStack.push(nextIndex);
    			
    			// 将这个新的list压栈
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
