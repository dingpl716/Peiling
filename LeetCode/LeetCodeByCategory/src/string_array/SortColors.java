package string_array;
//	Given an array with n objects colored red, white or blue, 
//	sort them so that objects of the same color are adjacent, 
//	with the colors in the order red, white and blue.
//	
//	Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
//	
//	Note:
//	You are not suppose to use the library's sort function for this problem.
//	
//	click to show follow up.
//	
//	Follow up:
//	A rather straight forward solution is a two-pass algorithm using counting sort.
//	First, iterate the array counting number of 0's, 1's, and 2's, 
//	then overwrite array with total number of 0's, then 1's and followed by 2's.
//	
//	Could you come up with an one-pass algorithm using only constant space?
public class SortColors {
	
	public void sortColors(int[] A) {
		// i指向的颜色应该放在左边，即红色
		int i = 0;
		// j指向的颜色应该放在右边， 即蓝色
		int j = A.length-1;
		int cur = 0;
		
		// cur要在j之前，因为j以及j之后都已经是蓝色了
		while(cur <= j && i < j) {
			// 找到最后一个非红色的颜色
			if(A[i] == 0) {
				++i;
				continue;
			}
			// 从右往左，找到第一个非蓝色的
			if(A[j] == 2) {
				--j;
				continue;
			}
			// 找到第一个非白色的
			if(A[cur] == 1) {
				++cur;
				continue;
			}
			//cur 应该在i之后，因为i以及i之前都是哄色了
			if(cur < i) 
				cur = i;
			// 如果cur是红色，则和i交换
			if(A[cur] == 0) {
				swap(A, cur, i);
				++i;
			}
			// 如果cur是蓝色则和j交换
			if(A[cur] == 2) {
				swap(A, cur, j);
				--j;
			}
		}
    }
	
	private void swap(int[] A, int i, int j) {
		int tmp = A[i];
		A[i] = A[j];
		A[j] = tmp;
	}
}
