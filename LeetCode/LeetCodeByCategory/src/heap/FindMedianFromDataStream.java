package heap;

import java.util.Comparator;
import java.util.PriorityQueue;

//	Median is the middle value in an ordered integer list. 
//	If the size of the list is even, there is no middle value. 
//	So the median is the mean of the two middle value.
//	
//	Examples: 
//	[2,3,4] , the median is 3
//	
//	[2,3], the median is (2 + 3) / 2 = 2.5
//	
//	Design a data structure that supports the following two operations:
//	
//	void addNum(int num) - Add a integer number from the data stream to the data structure.
//	double findMedian() - Return the median of all elements so far.
//	For example:
//	
//	addNum(1)
//	addNum(2)
//	findMedian() -> 1.5
//	addNum(3) 
//	findMedian() -> 2

/**
 * 核心思想：两个heap， 有一个最小堆存储较大的那一半的数，用一个最大堆存储较小的那一半数
 * 在插入之后我们需要保证
 * 要么smallerHalf.size == biggerHalf.size
 * 要么smallerHalf.size - 1 == biggerHalf.size
 * @author peding
 *
 */
public class FindMedianFromDataStream {

	// 最大堆存储前半部分
	PriorityQueue<Integer> smallerHalf;
	
	// 最小堆存储后半部分
	PriorityQueue<Integer> biggerHalf;
	
	int size = 0;
	
    /** initialize your data structure here. */
    public FindMedianFromDataStream() {
        smallerHalf = new PriorityQueue<Integer>(new Comparator<Integer>(){
        	public int compare(Integer i1, Integer i2) {
    			return -1 * (i1 - i2);
    		}
        });
        
        biggerHalf = new PriorityQueue<Integer>();
        
        size = 0;
    }
    
    public void addNum(int num) {

    	if (size == 0) {
    		smallerHalf.offer(num);
    	}
    	else {
    		// 当两个heap刚好各自有一半数的时候，我们采取的策略是
    		// 确保在插入之后smallerHalf比biggerHalf多1个数
    		if (smallerHalf.size() == biggerHalf.size()) {
    			if (num <= biggerHalf.peek()) {
    				smallerHalf.offer(num);
    			}
    			else {
    				biggerHalf.offer(num);
    				smallerHalf.offer(biggerHalf.poll());
    			}
    		}
    		
    		// 当smallerHalf比biggerHalf多1个数的时候，我们采取的策略是
    		// 确保在插入之后两个heap的size相等
    		else {
    			if (num >= smallerHalf.peek()) {
    				biggerHalf.offer(num);
    			}else {
    				smallerHalf.offer(num);
    				biggerHalf.offer(smallerHalf.poll());
    			}
    		}
    	}
    	
    	++size;
    }
    
    public double findMedian() {
    	if (smallerHalf.size() == biggerHalf.size()) {
    		return ((double)(smallerHalf.peek() + biggerHalf.peek())) / 2;
    	}else {
    		return smallerHalf.peek();
    	}
    }
}
