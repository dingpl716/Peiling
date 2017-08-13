package binarySearch;
//	There are two sorted arrays A and B of size m and n respectively.
//	Find the median of the two sorted arrays. 
//	The overall run time complexity should be O(log (m+n)).

//	如果m+n是偶数，那么需要求平均值
//思路 http://fisherlei.blogspot.com/2012/12/leetcode-median-of-two-sorted-arrays.html
public class MedianOfTwoSortedArrays {

    public double findMedianSortedArrays(int A[], int B[]) {
    	if(((A.length+B.length) & 1) == 1)
    		return findKthNumberTwoSortedArrays2(A, 0, A.length-1, B, 0, B.length - 1, (A.length+B.length) / 2 + 1);
    	else
    		return (findKthNumberTwoSortedArrays2(A, 0, A.length-1, B, 0, B.length - 1, (A.length+B.length) / 2) +
    				findKthNumberTwoSortedArrays2(A, 0, A.length-1, B, 0, B.length - 1, (A.length+B.length) / 2 + 1))/2;
    }
    
    
    /*
     * 在A[start1, end1] 和 B[start2, end2]中找到第k小的数
     * 	If (m/2+n/2+1) > k && am/2 > bn/2 , drop Section 2
		If (m/2+n/2+1) > k && am/2 < bn/2 , drop Section 4
		If (m/2+n/2+1) < k && am/2 > bn/2 ,  drop Section 3
		If (m/2+n/2+1) < k && am/2 < bn/2 ,  drop Section 1
		section 1, 3 小于待求的中位数，所以drop他们的时候要update k
		section 2， 4大于这个中位数，所以drop他们的时候不用update k
		
		k is NOT 0 based, it is 1 based
     */
    private double findKthNumberTwoSortedArrays2(int A[], int start1, int end1, 
    		int[] B, int start2, int end2, int k) {
    	if(start1 > end1)
    		return B[start2 + k - 1];
    	if(start2 > end2)
    		return A[start1 + k - 1];
    	if(k <= 1)
    		return Math.min(A[start1], B[start2]);
    	
    	// median of current A and B
    	int ma = (end1+start1)/2 - start1 + 1;
    	int mb = (end2+start2)/2 - start2 + 1;
    	
    	if(B[start2 + mb - 1] >= A[start1 + ma - 1]) {
    		if (ma + mb >= k) {
    			//drop section 4
    			return findKthNumberTwoSortedArrays2(A, start1, end1, B, start2, start2 + mb - 2, k);
    		} else {
    			int discard = ma;
    			//drop section 1
    			return findKthNumberTwoSortedArrays2(A, start1+ma, end1, B, start2, end2, k-discard);
    		} 
    	} else {
    		if (ma + mb >= k) {
    			//drop section 2
    			return findKthNumberTwoSortedArrays2(A, start1, start1+ma-2, B, start2, end2, k);
    		} else {
    			// the number of numbers that we are going to discard
    			int discard = mb;
    			//drop section 3
    			return findKthNumberTwoSortedArrays2(A, start1, end1, B, start2+mb, end2, k-discard);
    		}
    	}
    }
    
    /**
     * 从A的start到end里面，找到第k个元素。
     * @param A
     * @param start
     * @param end
     * @param B
     * @param k
     * @return
     */
    // search k th element in [start, end] of A
    private double findKthNumberTwoSortedArrays(int A[], int start, int end, 
    		int[] B, int k) {
    	if(start > end)
    		return findKthNumberTwoSortedArrays(B, 0, B.length-1, A, k);
    	
    	int i = (start+end)/2;
    	int j = k-i-2;
//    	if(j < 0 && (j+1 >= B.length || (j+1>=0 && A[i] <= B[j+1]))) 
//    		return A[i];
//    	if(j >=n)
    	if(0<= j && j+1 < B.length) {
    		if(B[j] <= A[i] && A[i] <= B[j+1])
    			return A[i];
    		else if(A[i] > B[j+1])
    			return findKthNumberTwoSortedArrays(A, start, i-1, B, k); // lower part
    		else
    			return findKthNumberTwoSortedArrays(A, i+1, end, B, k);
		// j < 0说明在B中[0,j]中没有元素
    	}else if (j < 0) {
    		if(j+1 < 0)
    			return findKthNumberTwoSortedArrays(A, start, i-1, B, k);
    		// 如果此时还满足j+1 >= B.length,说明B整个数组的长度为0
    		else if(j+1 >= B.length)
    			return A[i];
    		else if(A[i] <= B[j+1])
    			return A[i];
    		else 
    			return findKthNumberTwoSortedArrays(A, start, i-1, B, k);
    	}else { // j+1 >= B.length
    		if(j < 0)
    			return A[i];
    		else if(j >= B.length)
    			return findKthNumberTwoSortedArrays(A, i+1, end, B, k);
    		else if(B[j] <= A[i])
    			return A[i];
    		else
    			return findKthNumberTwoSortedArrays(A, i+1, end, B, k);
    	}	
    }

    public static void main(String[] args) {
    	MedianOfTwoSortedArrays m = new MedianOfTwoSortedArrays();
    	int[] A = {1,2,3};
    	int[] B = {4,5,6};
    	
    	System.out.println(m.findMedianSortedArrays(A, B));
    }
}
