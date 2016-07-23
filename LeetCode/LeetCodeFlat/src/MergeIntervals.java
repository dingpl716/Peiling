import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import definition.Interval;

//	Given a collection of intervals, merge all overlapping intervals.
//	
//	For example,
//	Given [1,3],[2,6],[8,10],[15,18],
//	return [1,6],[8,10],[15,18].

public class MergeIntervals {


	public class IntervalsCompartor<T> implements Comparator<T>{
		@Override
		public int compare(T o1, T o2) {
			Interval i1 = (Interval)o1;
			Interval i2 = (Interval)o2;
			return i1.start - i2.start;
		}
	}
//	
//	假设这个list是按start排好序了的，如果不然，那先按start排序
//	思路：
//	两个临时变量currentStart，currentEnd
//	第一个来记录目前最小的start，第二个用来记录目前最大的end
//	如果 newStart > currentEnd 用currentStart和currentEnd来合并left和right, currentStart = newStart
//	如果 newEnd > currentEnd 更新currentEnd 
    public ArrayList<Interval> merge(ArrayList<Interval> intervals) {
    	if(intervals == null || intervals.size() < 2)
    		return intervals;
    	
    	Interval[] inter = new Interval[intervals.size()];
    	for(int i=0; i<inter.length; ++i)
    		inter[i] = intervals.get(i);
    	Arrays.sort(inter, new IntervalsCompartor<Interval>());
    	
    	ArrayList<Interval> result = new ArrayList<Interval>();
    	int currentStart = inter[0].start;
    	int currentEnd = inter[0].end;
    	for(int i=1; i<inter.length; ++i) {
    		int newStart = inter[i].start;
    		int newEnd = inter[i].end;
    		if(newStart > currentEnd) {
    			Interval newInterval = new Interval(currentStart, currentEnd);
    			result.add(newInterval);
    			currentStart = newStart;
    			currentEnd = newEnd;
    		}
    		if(newEnd > currentEnd) {
    			currentEnd = newEnd;
    		}
    	}
    	
    	Interval newInterval = new Interval(currentStart, currentEnd);
		result.add(newInterval);
    	return result;
    }
}
