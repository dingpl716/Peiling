package math;
//	Implement int sqrt(int x).
//	
//	Compute and return the square root of x.

//思路：二分法

public class Sqrt {
	
	private static final int MAX_SQRT = (int)Math.sqrt(Integer.MAX_VALUE);
	
    public int sqrt(int x) {
    	if(x <= 0)
    		return 0;
    	int start = 0;
    	int end = (x/2 < MAX_SQRT ? x/2+1 : MAX_SQRT);
    	while(start <= end) {
    		int mid = (start + end)/2;
    		int sqr = mid * mid;
    		if(sqr == x) {
    			return mid;
    		}else if (sqr < x)
    			start = mid + 1;
    		else
    			end = mid - 1;
    	}
    	
    	return (start + end) / 2;
    }
    
    public static void main(String[] args) {
    	Sqrt s = new Sqrt();
    	System.out.println(s.sqrt(1000));
    }
}
