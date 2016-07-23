package string_array;
//	Given two numbers represented as strings, 
//	return multiplication of the numbers as a string.
//	
//	Note: The numbers can be arbitrarily large and are non-negative.

public class MultiplyStrings {

    public String multiply(String num1, String num2) {
    	if(num1 == null || num1.length() == 0 || num2 == null || num2.length() == 0)
    		return "";
    	if((num1.length() == 1 && num1.charAt(0) == '0') || (num2.length() == 1 && num2.charAt(0) == '0'))
    		return "0";
    	num1 = reverse(num1);
    	num2 = reverse(num2);
    	String result = "0";
//    	num2的第i位乘以num1的第j位
    	for(int i=0; i<num2.length(); ++i) {
    		StringBuffer current = new StringBuffer();
    		//往前面shift i个0
    		for(int k=0; k<i; ++k)
    			current.append('0');
    		int carrier = 0;
    		int n2 = num2.charAt(i) - '0';
    		for(int j=0; j<num1.length(); ++j) {
    			int n1 = num1.charAt(j) - '0';
    			int value = (n1*n2 + carrier)%10;
    			carrier = (n1*n2 + carrier)/10;
    			current.append(value+"");
    		}
    		if(carrier!=0)
    			current.append(carrier+"");
    		
//    		乘完之后要加上之前的结果
    		result = plus(current.toString(), result);
    	}
    	
    	return reverse(result);
    }
    
    private String plus(String num1, String num2) {
    	if(num1.length() < num2.length())
    		return plus(num2, num1);
    	int carrier = 0;
    	StringBuffer sb = new StringBuffer();
    	for(int i=0; i<num1.length(); ++i) {
    		int n1 = num1.charAt(i) - '0';
    		int n2 = i>num2.length()-1 ? 0 : num2.charAt(i) - '0';
    		int value = (carrier + n1 + n2)%10;
    		carrier = (carrier + n1 + n2)/10;
    		sb.append(value+"");
    	}
    	if(carrier != 0)
    		sb.append(carrier+"");
    	
    	return sb.toString();
    }
    
    private String reverse(String str) {
    	if(str == null || str.length() == 0)
    		return str;
    	
    	char[] c = str.toCharArray();
    	int i = 0; 
    	int j = str.length()-1;
    	while(i<j) {
    		char tmp = c[i];
    		c[i] = c[j];
    		c[j] = tmp;
    		++i;
    		--j;
    	}
    	
    	return new String(c);
    }
    
}
