package string_array;

public class PlusOne {
    public int[] plusOne(int[] digits) {
        if(digits == null)
            return digits;
            
        int carry = 0;
        int length = digits.length;
        
        carry = (digits[length-1] + 1)/10;
        digits[length-1] = (digits[length-1] + 1)%10;
        
        int i=length - 2;
        while(carry != 0 && i >= 0) {
        	// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        	// 这里一定要注意把中间结果保存下来，不能直接用这个和来
        	// 更改carry和位，因为无论你先跟新哪个值 都会影响另外一个
        	// 错误的做法：	carry = (digits[i] + carry)/10;
        	//				digits[i] = (digits[i] + carry)%10
        	// 把上面两句话交换位置也一样错
        	int tmp = digits[i] + carry; 
        	carry = tmp/10;
            digits[i] = tmp%10;
            --i;
        }
        
        if(carry == 0)
            return digits;
        
        int[] newDigits = new int[length+1];
        for(int j=0; j<length; ++j) {
            newDigits[j+1] = digits[j];
        }
        newDigits[0] = carry;
        return newDigits;
        
    }
}
