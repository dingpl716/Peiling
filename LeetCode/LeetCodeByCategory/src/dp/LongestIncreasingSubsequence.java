package dp;

public class LongestIncreasingSubsequence {

	public int lis(int[] A) {
		
		// 最小末尾值, 意义:
		// 下标：递增子序列的长度
		// 值: 最后一个元素的最小值
		// minLastValue[i] = x 表示长度为i的递增子序列的最后一个元素的最小取值是x
		int[] minLastValue = new int[A.length + 1];
		minLastValue[1] = A[0];
		minLastValue[0] = min(A);
		
		// lis 的意义： 下标表示A中的前i个元素
		//			     值表示这前i个元素中最长递增子序列的长度
		int[] lis = new int[A.length];
		for(int i=0; i<lis.length; ++i)
			lis[i] = 1;
		
		int result = 1;
		
		for(int i=1; i<A.length; ++i) {
			int j;
			for(j = result; j>=0; --j) {
				if(A[i] > minLastValue[j]) {
					lis[i] = j + 1;
					break;
				}
			}
			
			if(lis[i] > result) {
				result = lis[i];
				minLastValue[lis[i]] = A[i];
			}else if (minLastValue[j] < A[i] && A[i] < minLastValue[j + 1])
				minLastValue[j+1] = A[i];
		}
		
		return result;
	}
	
	private int min(int[] A) {
		int result = A[0];
		for(int i=1; i<A.length; ++i) {
			if(A[i] < result)
				result = A[i];
		}
		
		return result;
	}
	
	
	public static void main(String[] args) {
		LongestIncreasingSubsequence lis = new LongestIncreasingSubsequence();
		int[] A = {1, -1, 2, -3, 4, -5, 6, -7};
		System.out.println(lis.lis(A));
	}
}
