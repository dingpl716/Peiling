//	There are two sorted arrays A and B of size m and n respectively.
//	Find the median of the two sorted arrays. 
//	The overall run time complexity should be O(log (m+n)).

//	如果m+n是偶数，那么需要求平均值
//思路 http://fisherlei.blogspot.com/2012/12/leetcode-median-of-two-sorted-arrays.html
public class MedianOfTwoSortedArrays {

    public double findMedianSortedArrays(int A[], int B[]) {
    	if((A.length+B.length)%2 == 1)
    		return findKthNumberTwoSortedArrays(A, 0, A.length-1, B, (A.length+B.length)/2);
    	else
    		return (findKthNumberTwoSortedArrays(A, 0, A.length-1, B, (A.length+B.length)/2-1) +
    				findKthNumberTwoSortedArrays(A, 0, A.length-1, B, (A.length+B.length)/2))/2;
    }
    
    
    /*
     * 在A[start1, end1] 和 B[start2, end2]中找到第k小的数
     * 	If (m/2+n/2+1) > k && am/2 > bn/2 , drop Section 2
		If (m/2+n/2+1) > k && am/2 < bn/2 , drop Section 4
		If (m/2+n/2+1) < k && am/2 > bn/2 ,  drop Section 3
		If (m/2+n/2+1) < k && am/2 < bn/2 ,  drop Section 1
		section 1, 3 小于待求的中位数，所以drop他们的时候要update k
		section 2， 4大于这个中位数，所以drop他们的时候不用update k
     */
    private double findKthNumberTwoSortedArrays2(int A[], int start1, int end1, 
    		int[] B, int start2, int end2, int k) {
    	if(start1 > end1)
    		return B[start2+k];
    	if(start2 > end2)
    		return A[start1+k];
    	if(k <= 0)
    		return Math.min(A[start1], B[start2]);
    	
    	// median of current A and B
    	int ma = (end1+start1)/2;
    	int mb = (end2+start2)/2;
    	if(ma + mb > k) { 
    		if(A[ma] >= B[mb]) 
    			//drop section 2
    			return findKthNumberTwoSortedArrays2(A, start1, ma-1, B, start2, end2, k);
    		else
    			//drop section 4
    			return findKthNumberTwoSortedArrays2(A, start1, end1, B, start2, mb-1, k);
    	}else {
    		if(A[ma] > B[mb]) {
    			// the number of numbers that we are going to discard
    			int discard = mb-start2+1;
    			//drop section 3
    			return findKthNumberTwoSortedArrays2(A, start1, end1, B, mb+1, end2, k-discard);
    		}
    		else{
    			int discard = mb-start1+1;
    			//drop section 1
    			return findKthNumberTwoSortedArrays2(A, ma+1, end1, B, start2, end2, k-discard);
    		}
    	}
    }
    
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
}
