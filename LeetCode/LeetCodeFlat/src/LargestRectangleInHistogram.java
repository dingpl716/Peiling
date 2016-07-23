import java.util.Stack;

//	Given n non-negative integers representing the histogram's 
//	bar height where the width of each bar is 1, find the area 
//	of largest rectangle in the histogram.

//	基本思想是，以一个长方形为中心，往左数，数出比他大的长方形的个数left，
//	往右数，数出比他长的长方形个数right，那么这个长方形为中心，最大面积就是
//	height*（left+right+1)
//	这个解法的时间复杂度是On^2
//	
//	下面的这个解法的巧妙就在于，用一个堆栈记录下了之前的比我小的长方形，
//	比如，栈顶元素是x，那么栈里面剩下的元素都小于x，如果此时遇到了y，x>y，
//	那么这个时候就是应该要计算以x为中心，能获取到的最大面积是多少的时候了。
//	x,下面的那个元素不一定紧挨着x，但是肯定是在左边且第一个小于x的，姑且
//	记为z，这个z就是要纳入计算范围的左边界，而此时的y也不一定紧挨着x，但是
//	一定是在右边第一个小于x的，也就是右边界。最后的就是右边界 - 左边界再
//	乘以x了

public class LargestRectangleInHistogram {
	
//	维护一个递增的栈，每次比较栈顶与当前元素。
//	如果当前元素大于栈顶元素，则入站，
//	否则合并现有栈，直至栈顶元素小于当前元素。
//	结尾入站元素0，重复合并一次。
    public int largestRectangleArea(int[] height) {
      int[] newHeight = new int[height.length+1];
      for(int i=0; i<height.length; ++i){
    	  newHeight[i] = height[i];
      }
      // 在最后附加一个0，以免整个height都是一个完全递增的数组
      newHeight[newHeight.length-1] = 0;
      // stack里面压入的是坐标
      Stack<Integer> stack = new Stack<Integer>();
      int sum = 0;
      for(int i=0; i<newHeight.length; ++i) {
    	  if(stack.isEmpty() || newHeight[stack.peek()] < newHeight[i]) {
    		  stack.push(i);
    	  }
    	  else {
    		  int tmp = stack.pop();
    		  sum = Math.max(sum, newHeight[tmp] * (stack.isEmpty() ? i : i-stack.peek()-1));
    		  --i;
    	  }
      }
      
      return sum;
    }
}
