//	Given n non-negative integers a1, a2, ..., an, 
//	where each represents a point at coordinate (i, ai). 
//	n vertical lines are drawn such that the two endpoints 
//	of line i is at (i, ai) and (i, 0). Find two lines, 
//	which together with x-axis forms a container, 
//	such that the container contains the most water.
//	
//	Note: You may not slant the container.

//[Thoughts]
//For any container, its volume depends on the shortest board.
//Two-pointer scan. And always move with shorter board index.
public class ContainerWithMostWater {
	
    public int maxArea(int[] height) {
    	if(height == null || height.length < 2)
    		return 0;
    	int left = 0;
    	int right = height.length-1;
    	int result = 0;
    	int tmp = 0;
    	while(left < right) {
    		if(height[left] > height[right]) {
    			tmp = (right-left) * height[right];
    			--right;
    		}else if (height[left] < height[right]) {
    			tmp = (right - left) * height[left];
    			++left;
    		}else {
    			tmp = (right - left) * height[left];
    			++left;
    			--right;
    		}
    		result = tmp > result ? tmp : result;
    	}
    	
    	return result;
    }
}
