//	Given a sorted array of integers, find the starting and ending position of a given target value.
//	
//	Your algorithm's runtime complexity must be in the order of O(log n).
//	
//	If the target is not found in the array, return [-1, -1].
//	
//	For example,
//	Given [5, 7, 7, 8, 8, 10] and target value 8,
//	return [3, 4].

public class SearchForARange {
	
//	思路一：
//	先坐二分查找，然后向左再向右探测，找到边界即可
//	但是这个方法的最差时间复杂度是O(n)
	
//	思路二：
//	上面的思路之所以会出现O(n)，就是因为在找到一个target
//	之后，我们做的是线性查找，其实我们可以继续二分查找。
//	也就是找到一个target如果他两边都是target的话，那么
//	继续二分，知道找到他的左边，或是右边不为target。
//	所以先找到一个左边的边界，再找到一个右边的边界。
	
}
