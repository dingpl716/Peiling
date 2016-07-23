//	Implement pow(x, n).

public class Pow {

    /**
     * n < 0
     * n = 0
     * n > 0
     * x == 0
     */ 
    
	/**
	 * 易犯错误
	 * 1. 没考虑 x = 1, -1, 0
	 * 2. 没考虑x=-1时n<0
	 * 3. update tmp的方式错误
	 * 4. 没考虑 n = Integer.MIN_VALUE
	 * 5. 
	 * 
	 */
    public double pow(double x, int n) {
        if(n == 0d)
            return 1d;
        if(x == 1d)
            return 1d;
        if(x == -1d) {
            if((n&1) == 1)
                return -1d;
            else
                return 1d;
        }
        if(x == 0d) {
            if(n > 0)
                return 0;
            if(n < 0)
                // throw exception
                ;
        }

       double result = 1d;
       double tmp = x;

//     利用二进制的思想：将N拆为二进制数，譬如13=(1101)2那么 A^13= A^8 * A^4 * A^1。
       int k;
       boolean minValue = false;
       if(n == Integer.MIN_VALUE) {
    	   k = Integer.MAX_VALUE;
    	   minValue = true;
       }else if (n < 0)
           k = -1 * n;
       else
           k = n;
       while(k != 0) {
    	   if((k & 1) == 1)
    		   result *= tmp;
    	   tmp *= tmp;  // tmp 就等于每一位上的数，所以要保持更新，
    	   k = k >> 1;
       }
       if(n > 0) {
    	   return result; 
       } else {
           if(minValue)
                return 1/(result * x);
           else
    	        return 1/result;
       }
       
    }
}
