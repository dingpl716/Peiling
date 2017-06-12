package divideAndConquer;

//	Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.
//	
//	For example,
//	Given [3,2,1,5,6,4] and k = 2, return 5.
//	
//	Note: 
//	You may assume k is always valid, 1 ≤ k ≤ array's length.

public class KthLargestElementInAnArray {
	
	public int findKthLargest(int[] nums, int k) {
		
		// 找到第k大的数，相当于是找到第m小的数，其中m，k满足以下等式
		int m = nums.length - k + 1;
		return findKthSmallestNumber(nums, m);
    }
	
	public int findKthSmallestNumber(int[] nums, int k){
		return findKthSmallestNumber(nums, 0, nums.length - 1, k);
	}
	
	/**
	 * 找到nums里面从left到right范围内，相对于left而言，第k个小的数
	 * @param nums
	 * @param left
	 * @param right
	 * @param k
	 * @return
	 */
	private int findKthSmallestNumber(int[] nums, int left, int right, int k){
		int indexOfPivot = partitioning(nums, left, right);
		
		// 表示pivot相对于left的位置
		int placeOfPivot = indexOfPivot - left + 1;
		if (placeOfPivot == k){
			return nums[indexOfPivot];
		}
		else if (placeOfPivot > k){
			return findKthSmallestNumber(nums, left, indexOfPivot, k);
		}
		else {
			// 丢弃的部分
			int discard = indexOfPivot - left + 1;
			k = k - discard;
			
		    // 因为partition所返回的indexOfPivot有可能等于left，
			// 所以我们需要在这里pass in indexOfPivot + 1 以确保缩小问题的规模。
			return findKthSmallestNumber(nums, indexOfPivot + 1, right, k);
		}
	}
	
	/**
	 * 对nums[left,right]的范围做partition,并且返回枢纽的index
	 * 基本步骤:
	 * 1.先从left，right已经(left+right)/2中选取中间大小的数作为pivot
	 * 2.然后swap这三个数，确保left <= middle <= right;
	 * 3.然后把pivot和right-1交换，此时left+1到right-2的范围内是需要进行处理的
	 * 4.处理这一部分，注意下面代码中是先++i/--j的
	 * 5.然后将right-1和i进行交换，一次把pivot放到正确的位置
	 * 6.返回i，也就是pivot的index
	 * @param nums
	 * @param left
	 * @param right
	 * @return 枢纽的index
	 */
	private int partitioning(int[] nums, int left, int right){
		if (left == right){
			return left;
		}
		
		if (left == right - 1){
			if (nums[left] > nums[right]){
				swap(nums, left, right);
			}
			
			return left;
		}
		
		int middle = (left + right) / 2;
		
		// 经过这一步后，right已经大于pivot，并且pivot在right-1这个位置上
		int pivot = getPivot(nums, left, right);
		
		int i = left;
		int j = right - 1;
		
		while (i < j) {
			while(nums[++i] < pivot);
			while(nums[--j] > pivot);
			
			if (i < j){
				swap(nums, i , j);
			}
		}
		
		// 讲pivot放到正确的位置上。
		swap(nums, i, right - 1);
		
		return i;
	}
	
	/**
	 * 从left, right 和（left+right）/2 中选取中位数作为pivot，并返回pivot的值
	 * 这个method只能处理left到right有大于等于3个数的情况，
	 * 所以在调用之前需要提前处理，left == right 和left == right + 1的情况
	 * @param nums
	 * @param left
	 * @param right
	 * @return pivot的值
	 */
	private int getPivot(int[] nums, int left, int right){
		int middle = (left + right) / 2;
		
		// swap left, right and middle accordingly to 
		// make sure nums[left] <= nums[middle] <= nums[right]
		if (nums[left] > nums[middle]){
			swap(nums, left, middle);
		}
		
		if (nums[left] > nums[right]) {
			swap(nums, left, right);
		}
		
		if (nums[middle] > nums[right]){
			swap(nums, middle, right);
		}
		
		//因为right已经大于pivot了，所以我们就不管right了，
		//把right-1设置为pivot，之后我们会partition 从left到right-1这一段
		swap(nums, middle, right - 1);
		
		return nums[right - 1];
	}
	
	private void swap(int[] nums, int left, int right){
		int tmp = nums[left];
		nums[left] = nums[right];
		nums[right] = tmp;
	}

	public static void main(String[] args){
		KthLargestElementInAnArray k = new KthLargestElementInAnArray();
		int[] nums = new int[] {-1, 2, 0};
		System.out.println(k.findKthLargest(nums, 3));
	}
}
