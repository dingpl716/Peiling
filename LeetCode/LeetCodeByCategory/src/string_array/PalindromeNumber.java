package string_array;

//	Determine whether an integer is a palindrome. Do this without extra space.
//	
//	
//	Some hints:
//	Could negative integers be palindromes? (ie, -1)
//	
//	If you are thinking of converting the integer to string, note the restriction of using extra space.
//	
//	You could also try reversing an integer. However, if you have solved the problem "Reverse Integer", 
//  you know that the reversed integer might overflow. How would you handle such case?
//	
//	There is a more generic way of solving this problem.

public class PalindromeNumber {
    public boolean isPalindrome(int x) {
    	if(x == Integer.MIN_VALUE)  // Integer.MIN_VALUE * -1 = Integer.MIN_VALUE 
    		return false;
        if(x < 0)
            x = -1 * x;
        int digits = numOfDigits(x);
        
        if(digits == 1)
            return true;
            
        int left = digits - 1;
        int right = 1;
        int leftX = x;
        int rightX = x;
        int leftDivisor = tenPower(left);
        while(left >= right) {
            int leftDigit = leftX / leftDivisor;
            leftX = leftX % leftDivisor;
            leftDivisor /= 10;
            
            int rightDigit = rightX % 10;
            rightX = rightX / 10;
            
            if(leftDigit != rightDigit)
                return false;
            left --;
            right ++;
        }
        
        return true;
    }
    
    private int numOfDigits(int x) {
        int result = 1;
        while(x >= 10) { //!!!!!!!!!! be careful with x = 10 !!
            x = x/10;
            result ++;
        }
        return result;
    }
    
    private int tenPower(int power) {
    	if(power == 0)
    		return 1;
    	int result = 1;
    	for(int i=0; i<power; ++i){
    		result = result * 10;
    	}
    	return result;
    }
}
