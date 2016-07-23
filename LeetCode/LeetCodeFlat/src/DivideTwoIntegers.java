//Divide two integers without using multiplication, division and mod operator.
public class DivideTwoIntegers {

	public int divide(int dividend, int divisor) {
        if(dividend ==0 || divisor == 0)
            return 0;
        if(divisor == 1)
            return dividend;
        if(divisor == -1)
            return 0 - dividend;
        
        if(dividend < 0 && divisor < 0) {
            return divide(0-dividend, 0-divisor);
        }else if(dividend < 0 && divisor > 0) {
            return 0-divide(0-dividend, divisor);
        }else if(dividend > 0 && divisor < 0) {
            return 0-divide(dividend, 0-divisor);
        }else;
        
//        如果同号，则应该dividend - divisor
//        	如果dividend>0,则终止条件是dividend < divisor
//        	如果dividend<0,则终止条件是dividend > divisor
//        如果异号，则应该dividend + divisor
//        	如果dividend>0,则终止条件是dividend + divisor < 0
//        	如果dividend<0,则终止条件是dividend + divisor > 0
        int result = 0;
        while(dividend >= divisor) {
            int tmp_divisor = divisor;
            int factor = 1;
            while(true) {
                if(dividend >= tmp_divisor) {
                    dividend -= tmp_divisor;
                    result += factor;
                    factor = factor << 1;
                    tmp_divisor = tmp_divisor << 1;
                }else
                    break;
            }
        }
        return result;
    }
	
//	private int reverseSign(int number) {
//		//return number & Integer.
//	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DivideTwoIntegers d = new DivideTwoIntegers();
		System.out.println(d.divide(15, 6));
		
//		System.out.println(Integer.toBinaryString(Integer.MAX_VALUE));
//		System.out.println(Integer.toBinaryString(Integer.MIN_VALUE));
//		System.out.println(0-Integer.MIN_VALUE);
//		int re = (int) (Long.MIN_VALUE);
//		System.out.println(re);
//		System.out.println(Integer.MIN_VALUE-1);
		System.out.println(true ^ false);
		System.out.println(false ^ true);
		System.out.println(false ^ false);
		System.out.println(true ^ true);
	}

}
