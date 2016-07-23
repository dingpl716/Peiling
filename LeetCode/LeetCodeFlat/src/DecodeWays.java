//	A message containing letters from A-Z is 
//	being encoded to numbers using the following mapping:
//	
//	'A' -> 1
//	'B' -> 2
//	...
//	'Z' -> 26
//	Given an encoded message containing digits, determine the total number of ways to decode it.
//	
//	For example,
//	Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).
//	
//	The number of ways decoding "12" is 2.

//	dfs problem
//	we need a pointer pointing current index
//	try to read one digit and two digits
//	if the two digits number is smaller than 27, then it is valid
//	if the one digt number is 1 <= number <=9, then it is valid

public class DecodeWays {
	
//	超时
    public int numDecodings1(String s) {
        if(s == null )
        	return 0;
        return dfs(s, 0, 0);
    }
    
    private int dfs(String s, int start, int currentNum) {
    	if(start == s.length()) {
    		return currentNum + 1;
    	}
    	
    	int number = 0;
    	number = s.charAt(start)-'0';
    	if(number != 0)
    		currentNum = dfs(s, start+1, currentNum);
    	
    	if(start < s.length() -1)
    	number = number*10 + s.charAt(start+1) - '0';
    	if(number < 27)
    		currentNum = dfs(s, start+2, currentNum);
    	
    	return currentNum;
    }
    
//    设len(x) 为到x有多少种走法，number(y,x)为第y位和第x位组合的数字，那么
//    len(0) = 0; 空字符串
//    len(1) = 1 if x != 0
//    len(2) = 2 if number(1,2) < 27
//    len(3) = len(2) if number(2,3) > 26
//    	   = len(1) + len(2) if number(2,3) < 27 && number(3) > 0
//    len(4) = len(3) if number(3,4) > 26
//    		 len(3) + len(2) if number(3,4) < 27 && number(4) > 0
//    		 
//    len(x) = len(x-1) if number(x-1, x) >26
//    		 len(x-2) + len(x-1) if number(x-1, x) < 27 && number(x) > 0
    public int numDecodings(String s) {
        if(s == null || s.length() == 0)
        	return 0;
        int[] len = new int[s.length()];
        
        if(s.charAt(0) != '0') {
        	len[0] = 1;
        }else{
        	return 0;
        }
        if(s.length() < 2)
        	return len[0];
        
        if(!isValidDouble(s, 1) && !isValidSingle(s, 1))
        	return len[s.length()-1];
        else if(isValidDouble(s, 1) && isValidSingle(s, 1)) 
        	len[1] = 2;
        else 
        	len[1] = 1;
        
        for(int i=2; i<s.length(); ++i) {
        	if(!isValidDouble(s, i) && !isValidSingle(s, i))
        		break;
        	else if(isValidDouble(s, i) && !isValidSingle(s, i))
        		len[i] = len[i-2];
        	else if(!isValidDouble(s, i) && isValidSingle(s, i))
        		len[i] = len[i-1];
        	else
        		len[i] = len[i-2] + len[i-1];
        }
        
        return len[s.length()-1];
    }
    
//    检测number(x-1, x)是不是合法的数字
    private boolean isValidDouble(String s, int x) {
//    前面一个数字为0的话不是一个valid
    	if(s.charAt(x-1) == '0')
    		return false;
    	int number = (s.charAt(x-1)-'0') * 10 + (s.charAt(x)-'0');
    	return number < 27;
    }
    
//    检测number(x)是不是合法的数字
    private boolean isValidSingle(String s, int x) {
    	return s.charAt(x) != '0';
    }
}
