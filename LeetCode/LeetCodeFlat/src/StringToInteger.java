//	Implement atoi to convert a string to an integer.
//	
//	Hint: Carefully consider all possible input cases. 
//	If you want a challenge, please do not see below 
//	and ask yourself what are the possible input cases.
//	
//	Notes: It is intended for this problem to be specified vaguely 
//	(ie, no given input specs). You are responsible 
//	to gather all the input requirements up front.

public class StringToInteger {
//	1. trim掉空格
//	2. 第一个字符只能是+， - 或数字
//	3. 数字完了之后的其他字符被忽略。
//	4. 不能被正确parse的，就返回0
//	5. 越界的返回INT.MAX或INT.MIN
    public int atoi(String str) {
        if(str == null)
        	return 0;
        str = str.trim();
        if(str.length() == 0)
        	return 0;
        
        int cur = 0;
        int result = 0;
        boolean negative = false;
        if(str.charAt(cur) == '-' || str.charAt(cur) == '+') {
        	if(str.charAt(cur) == '-')
        		negative = true;
        	++cur;
        }
        
        // now we should only have number
//        -2147483648
//           214748364
        while(cur < str.length()) {
        	if(!isNumber(str.charAt(cur)))
        		break;
        	// 这个检测是为了防止乘以10溢出
        	if(result < -214748364){
        		result = Integer.MIN_VALUE;
        		break;
        	}
        	// 这个检测是为了防止加上个位溢出
        	// 并且我们用负数来存储中间结果，因为正数的最大值比负数的最小值的绝对值小1，会溢出
        	int tmp = result * 10 - (str.charAt(cur) - '0');
        	if(result < 0 && tmp >= 0) {
        		result = Integer.MIN_VALUE;
        		break;
        	}
        	result = tmp;
        	++cur;
        }
        
        if(negative)
        	return result;
        else {
        	if(result == Integer.MIN_VALUE)
        		return Integer.MAX_VALUE;
        	else 
        		return 0 - result;
        }
    }
    
    private boolean isNumber(char c) {
    	return '0' <= c && c <= '9';
    }
}
