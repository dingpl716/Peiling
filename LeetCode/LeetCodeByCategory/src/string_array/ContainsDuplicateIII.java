package string_array;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

//	Given an array of Longs, find out whether there are two distinct indices i and j 
//	in the array such that the absolute difference between nums[i] and nums[j] 
//	is at most t and the absolute difference between i and j is at most k.

// 在数组中找到两个数，要求他们相距不超过k，差的绝对值不超过t

/**
 * 方法1：用BST来维护一个window。
 * 
 * 我自己实现的方法里面，用了两个heap来维护window。用heap来维护window可以让我们在
 * O(1)的时间内找到最大最小值，并且用log(n)的时间来维护这个heap。但是当我们要查找的
 * 目标不是一个特定数而是一个范围的时候，heap就很有可能需要做线性查找。此时应该用BST
 * 
 * BST能够让我们在log(n)的时间内找出，在该BST中，是否有一个数在目标范围之内，并且我们
 * 也只需要用log(N)的时间来维护这个BST
 * 
 * 
 * 方法2：Bucketing
 * 
 * 所谓Bucketing就是把nums里面的数分组，Bucket = num / (t + 1)，我们以t = 3 为例
 * Bucket 0 -> [0, 1, 2, 3]
 * Bucket 1 -> [4, 5, 6, 7]
 * Bucket 2 -> [8, 9, 10, 11]
 * 左边是bucket的号，右边是该bucket中可能存在的数的范围
 * 
 * 我们用一个Map<int, int>来代表buckets的情况，key是bucket的号码，value是在该bucket中，目前存在的数.
 * 注意我们这个地方并不需要把bucket里面所有的数都保存下来，这也是为什么该方法把时间复杂度降下来的原因。
 * 否则就还是相当于在bucket里面做线性查找。
 * 
 * 步骤：
 * 1. 对nums里面的数遍历，设i为index
 * 2. 如果此时的i > k了，求出nums[i - k]对应的bucket，然后从map里面删除这个bucket
 * 3. 求出nums[i]对应的bucket, 此时如果数组中存在满足条件的数那么只可能是下面三种情况：
 * 		3.1 map中正好有该bucket，那么直接返回true
 * 		3.2 map中没有改bucket，但是有bucket-1，那么把这个bucket-1里面放的数取出来，看是否在范围内
 * 		3.3 map中没有改bucket，但是有bucket+1，那么把这个bucket+1里面放的数取出来，看是否在范围内
 * 4. 用nums[i] 替换该bucket中的数。
 * 
 * 3.1里面中的条件，保证了我们即使是替换掉bucket中的数（而不是把该bucket中的数都保存下来），我们也
 * 不会错过满足条件的数！！！！！！
 * 
 * @author Dingp
 *
 */
public class ContainsDuplicateIII {

	// 这个方法速度不够，因为还是做了线性查找
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {

    	PriorityQueue<Long> minHeap = new PriorityQueue<Long>();
        PriorityQueue<Long> maxHeap = new PriorityQueue<Long>(new Comparator<Long>(){

			@Override
			public int compare(Long arg0, Long arg1) {
				if (arg0 < arg1) {
					return 1;
				} else if (arg0 > arg1) {
					return -1;
				} else {
					return 0;
				}
				
			}
        });
    	
        long[] newNums = Arrays.stream(nums).mapToLong(num -> num).toArray();
        
        for (int i = 0; i < newNums.length; ++i) {
        	if (i > k) {
        		minHeap.remove(newNums[i-k-1]);
        		maxHeap.remove(newNums[i-k-1]);
        	}
        	
        	if (!minHeap.isEmpty() && !maxHeap.isEmpty()) {
        		if (minHeap.peek() >= newNums[i] - t && maxHeap.peek() <= newNums[i] + t) {
        			return true;
        		} else if (maxHeap.peek() < newNums[i] - t || minHeap.peek() > newNums[i] + t) {
        		} else {
        			if (findLinearly(minHeap, maxHeap, newNums[i], t)) {
        				return true;
        			}
        		}
        	}
        	
        	minHeap.offer(newNums[i]);
        	maxHeap.offer(newNums[i]);
        }
        
        return false;
    }
    
    // 这个方法速度不够，因为还是做了线性查找
    private boolean findLinearly(PriorityQueue<Long> minHeap,
    		PriorityQueue<Long> maxHeap,
    		long target,
    		int offset) {
    	if (minHeap.peek() >= target - offset) {
    		for (Long number : minHeap) {
    			if (target - offset <= number && target + offset >= number) {
    				return true;
    			}
    		}
    		
    		return false;
    	}
    	
    	if (maxHeap.peek() <= target + offset) {
    		for (Long number : maxHeap) {
    			if (target - offset <= number && target + offset >= number) {
    				return true;
    			}
    		}
    		
    		return false;
    	}
    	
    	for (Long number : maxHeap) {
			if (target - offset <= number && target + offset >= number) {
				return true;
			}
		}
		
		return false;
    }
   
    public static void main(String[] args) {
    	ContainsDuplicateIII c = new ContainsDuplicateIII();
    	int[] nums = new int[] {0, 2147483647};
    	int k = 1;
    	int t = 2147483647;
    	System.out.println(c.containsNearbyAlmostDuplicate(nums, k, t));
    }
}
