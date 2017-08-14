package design;

import java.util.Iterator;
import java.util.List;

//	Implement an iterator to flatten a 2d vector.
//	
//	For example,
//	Given 2d vector =
//	
//	[
//	  [1,2],
//	  [3],
//	  [4,5,6]
//	]
//	By calling next repeatedly until hasNext returns false, 
//	the order of elements returned by next should be: [1,2,3,4,5,6].

public class Vector2D implements Iterator<Integer> {

	private Iterator<List<Integer>> outter = null;
	private Iterator<Integer> inner = null;
	private Integer next = null;
	
    public Vector2D(List<List<Integer>> vec2d) {
    	outter = vec2d.iterator();
    	next = getNext();
    }

    @Override
    public Integer next() {
        Integer tmp = next;
        next = getNext();
        return tmp;
    }
    
    private Integer getNext() {
    	if (inner == null || !inner.hasNext()) {
    		if (!outter.hasNext()) {
    			return null;
    		} else {
    			inner = outter.next().iterator();
    			
    			// 和NestedIterator一样，这个地方依然要递归。
    			return getNext();
    		}
    	} else {
    		return inner.next();
    	}
    }

    @Override
    public boolean hasNext() {
        return next != null;
    }
}
