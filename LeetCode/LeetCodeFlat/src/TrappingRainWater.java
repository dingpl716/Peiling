//	
//	Given n non-negative integers representing an elevation map where 
//	the width of each bar is 1, compute how much 
//	water it is able to trap after raining.
//	
//	For example, 
//	Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.
//	 picture : http://oj.leetcode.com/problems/trapping-rain-water/
//	
//	The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. 
//	In this case, 6 units of rain water (blue section) are being trapped. 

public class TrappingRainWater {
    public int trap1(int[] A) {
        if(A == null || A.length < 3)
            return 0; 
        
        int result = 0;
        int leftWallIndex = 0;
        
        while(leftWallIndex <= A.length-3) {
            int rightWallIndex = findRightWall(A, leftWallIndex);
            result += calWater(A, leftWallIndex, rightWallIndex);
            leftWallIndex = rightWallIndex;
        }
        
        return result;
    }
    
    
    // find the first bar which is >= leftWall, if no bar is
    // higher than it, then find the highest wall on the right
    // return the rightWallIndex;
    private int findRightWall(int[] A, int leftWallIndex) {
        int rightWallIndex = leftWallIndex+1;
        
        for(int i=leftWallIndex+1; i<A.length; ++i) {
            if(A[i] >= A[leftWallIndex]){
                return i;
            }
            if(A[i] >= A[rightWallIndex]) {
                rightWallIndex = i;
            }
        }
        
        return rightWallIndex;
    }
    
    private int calWater(int[] A, int leftWallIndex, int rightWallIndex) {
        int result = 0;
        
        if(A[leftWallIndex] <= A[rightWallIndex]) {
            for(int i=leftWallIndex + 1; i<rightWallIndex; ++i) {
                result += A[leftWallIndex] - A[i];
            }
        } else {
            for(int i=rightWallIndex-1; i>leftWallIndex; --i) {
                result += A[rightWallIndex] - A[i];
            }
        }
        
        return result;
    }
    
//    public int trap(int[] A) {
//    	
//    }
}
