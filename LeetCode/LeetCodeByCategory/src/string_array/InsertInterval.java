package string_array;
import java.util.ArrayList;

import definition.Interval;

//	Given a set of non-overlapping intervals, 
//	insert a new interval into the intervals (merge if necessary).
//	
//	You may assume that the intervals were initially sorted according to their start times.
//	
//	Example 1:
//	Given intervals [1,3],[6,9], 
//	insert and merge [2,5] in as [1,5],[6,9].
//	
//	Example 2:
//	Given [1,2],[3,5],[6,7],[8,10],[12,16], 
//	insert and merge [4,9] in as [1,2],[3,10],[12,16].
//	
//	This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10].
/*
 * 易犯错误， 
 * 只有start是排了序的，end不一定是有序的
 */
public class InsertInterval {
//	先二分查找start，再二分查找end
//	如果start落在一个区间内，那么这个区间的start将是整个overlapping的start
//	如果start不在一个区间内，那么start本身将会是一个新的start
//	如果end落在一个区间内，那么这个区间的end将是整个overlapping的end
//	如果end不在一个区间内，那么这个end将会是一个新的end
    public ArrayList<Interval> insert(ArrayList<Interval> intervals, Interval newInterval) {
        if(intervals == null)
        	return null;
        if(intervals.size() == 0) {
        	intervals.add(newInterval);
        	return intervals;
        }
        
        int startIndex = search(intervals, newInterval.start, 0, intervals.size()-1);
        int endIndex = search(intervals, newInterval.end, 0, intervals.size()-1);
        int posStart = inTheInterval(intervals.get(startIndex), newInterval.start);
        int posEnd = inTheInterval(intervals.get(endIndex), newInterval.end);
        if(startIndex == endIndex) {
        	// 整个new interval都在左边
        	if(posStart < 0 && posEnd < 0)
        		return insert(intervals, startIndex, newInterval);

        	// 
        	else if(posStart > 0 && posEnd > 0) {
        		intervals.add(newInterval);
        		return intervals;
        		
    		// 整个new interval都被包含了
        	}else if(posStart == 0 && posEnd == 0)
        		return intervals;
        	
        	// new internval 包含了另外的一个interval
        	 else if(posStart < 0 && posEnd > 0) {
        		 intervals.remove(0);
        		 intervals.add(newInterval);
        		 return intervals;
        	 }
        }
        if( posStart > 0)
        	startIndex++;
        if( posEnd < 0)
        	endIndex--;
        return merge(intervals, startIndex, endIndex, newInterval);
    }
    
    private int search(ArrayList<Interval> intervals, int target, int left, int right) {
    	if(left == right)
    		return left;
    	if(left > right){
    		if(right < 0)
    			return 0;
    		else if(left >= intervals.size())
    			return intervals.size()-1;
    		else
    			return left;
    	}
    	int middle = (left+right)/2;
    	int pos = inTheInterval(intervals.get(middle), target);
    	if(pos == 0)
    		return middle;
    	else if(pos == -1){
    		return search(intervals, target, left, middle-1);
    	}else {
    		return search(intervals, target, middle+1, right);
    	}
    }
    
    private int inTheInterval(Interval interval, int target) {
    	if(interval.start<=target && target <= interval.end)
    		return 0;
    	else if(target < interval.start)
    		return -1;
    	else
    		return 1;
    }
    
    // 把[from，to]的都删除，并且以newStart和newEnd来替换
    private ArrayList<Interval> merge(ArrayList<Interval> intervals, int from, int to, Interval newInterval) {
    	Interval newInt = newInterval;
    	intervals.set(from, newInt);
    	for(int i=0; i<to-from; ++i) {
    		intervals.remove(from+1);
    	}
    	return intervals;
    }
    
    private ArrayList<Interval> insert(ArrayList<Interval> intervals, int index, Interval newInterval) {
    	intervals.add(index, newInterval);
    	return intervals;
    }
}
