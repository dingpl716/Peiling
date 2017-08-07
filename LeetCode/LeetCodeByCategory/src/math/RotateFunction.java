package math;

//	Given an array of integers A and let n to be its length.
//	
//	Assume Bk to be an array obtained by rotating the array A k positions
//	clock-wise, we define a "rotation function" F on A as follow:
//	
//	F(k) = 0 * Bk[0] + 1 * Bk[1] + ... + (n-1) * Bk[n-1].
//	
//	Calculate the maximum value of F(0), F(1), ..., F(n-1).
//	
//	Note:
//	n is guaranteed to be less than 105.
//	
//	Example:
//	
//	A = [4, 3, 2, 6]
//	
//	F(0) = (0 * 4) + (1 * 3) + (2 * 2) + (3 * 6) = 0 + 3 + 4 + 18 = 25
//	F(1) = (0 * 6) + (1 * 4) + (2 * 3) + (3 * 2) = 0 + 4 + 6 + 6 = 16
//	F(2) = (0 * 2) + (1 * 6) + (2 * 4) + (3 * 3) = 0 + 6 + 8 + 9 = 23
//	F(3) = (0 * 3) + (1 * 2) + (2 * 6) + (3 * 4) = 0 + 2 + 12 + 12 = 26
//	
//	So the maximum value of F(0), F(1), F(2), F(3) is F(3) = 26.

/**
 * 初始函数记为F(k), 右移一位记为F(k+1)
 * F(k)  = 0 * Bk[0] + 1 * Bk[1] + ... + (n-1) * Bk[n-1].
 * F(k+1)= 0 * Bk[n-1] + 1 * Bk[0] + 2 * Bk[1] + ... + (n-1) * Bk[n-2]
 * 所以
 * F(k+1) - F(k) = 0 * Bk[n-1] + 1 * Bk[0] + 1 * Bk[1] + ... + 1 * Bk[n-2] - (n-1) * Bk[n-1]
 *               = 1 * Bk[0] + 1 * Bk[1] + ... + 1 * Bk[n-2] + 1 * Bk[n-1] - n * Bk[n-1]
 *               = sum - nBk[n-1]
 *        F(k+1) = F(k) + sum - n * Bk[n-1]       
 * What is Bk[n-1]?
 * when F(k+1), Bk[n-1] = A[n-1]
 * when F(k+2), Bk[n-1] = A[n-2]
 * when F(k+3), Bk[n-1] = A[n-3]
 * @author Dingp
 *
 */
public class RotateFunction {
    
	public int maxRotateFunction(int[] A) {
        if (A == null || A.length <= 1 ) {
        	return 0;
        }
        
        int sum = 0;
        int f0 = 0;
        for (int i = 0; i < A.length; ++i) {
        	f0 += (i * A[i]);
        	sum += A[i];
        }
        
        int max = f0;
        int fk = f0;
        for (int i = 1; i < A.length; ++i) {
        	// f(k+1)
        	int fk1 = fk + sum - (A.length * A[A.length - i]);
        	max = Math.max(fk1, max);
        	fk = fk1;
        }
        
        return max;
    }
	
	public static void main(String[] args) {
		RotateFunction r = new RotateFunction();
		int[] A = {4,3,2,6};
		System.out.println(r.maxRotateFunction(A));
	}
}
