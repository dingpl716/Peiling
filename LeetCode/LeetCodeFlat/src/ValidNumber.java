//	Validate if a given string is numeric.
//	
//	Some examples:
//	"0" => true
//	" 0.1 " => true
//	"abc" => false
//	"1 a" => false
//	"2e10" => true
//	Note: It is intended for the problem statement to be ambiguous. 
//	You should gather all requirements up front before implementing one.
public class ValidNumber {
//	isNumber的要求
//	第一位可以是 +, -, 0-9
//	如果第一位是0，或者第一位是+, -而第二位是0，那么0后面只能跟小数点.
//	如果不是这样的0，那么后面可以跟0-9和小数点
//	1-9的后面可以跟0-9，和小数点，但是只能有一个小数点。
//	小数点后面永远只能跟数字
    public boolean isNumber(String s) {
        if(s == null || s.length() == 0)
        	return false;
        s = s.trim();
        if(s.length() == 1) {
        	if(s.charAt(0)<'0' || s.charAt(0) > '9')
        		return false;
        	else
        		return true;
        }
        
        //检测是否有除了数字，小数点，正负号以外的字符
        for(int i=0; i<s.length(); ++i){
        	char c = s.charAt(i);
        	if(!isSign(c) && !isPoint(c) && !is0_9(c))
        		return false;
        }
        
        boolean metPoint = false;
     // 第一个应该出现数字的地方
        int start = 0; 
        if(isSign(s.charAt(start))) 
        	++start;
        if(isPoint(s.charAt(start))) {
        	metPoint = true;
        	++start;
        }
        if(start>=s.length() || !is0_9(s.charAt(start)))
        	return false;
//        // 以0为开头的数字,后面必跟小数点，或者到此结束
//        if(is0(s.charAt(start))){
//        	if(start+1 >= s.length())
//        		return true;
//        	if(!isPoint(s.charAt(start+1)))
//        		return false;
//        	start += 2;
//        	metPoint = true;
//        }
        // from now we should only have number or point
        for(int i=start; i<s.length(); ++i) {
        	char c = s.charAt(i);
        	if(metPoint){
        		if(isPoint(c))
        			return false;
        	}else{
        		if(isPoint(c)) {
        			metPoint = true;
        			continue;
        		}
        	}
        	if(!is0_9(c))
        		return false;
        }
        
        return true;
    }
    
    private boolean is0_9(char c) {
    	return ('0' <= c && c <= '9');
    }
    private boolean is0(char c) {
    	return '0' == c;
    }
    private boolean isPoint(char c) {
    	return '.' == c;
    }
    private boolean isSign(char c) {
    	return '+' == c || '-' == c;
    }
    
    public static void main(String[] args) {
//    	System.out.println(Double.parseDouble("+001.2"));
    	ValidNumber vn = new ValidNumber();
    	System.out.println(vn.isNumber("+.01"));
    }
}
