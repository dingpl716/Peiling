package math;

//	Given two integers representing the numerator and denominator of a fraction, 
//	return the fraction in string format.
//	
//	If the fractional part is repeating, enclose the repeating part in parentheses.
//	
//	For example,
//	
//	Given numerator = 1, denominator = 2, return "0.5".
//	Given numerator = 2, denominator = 1, return "2".
//	Given numerator = 2, denominator = 3, return "0.(6)".

/**
 * 核心思想：
 * 1. 先判断符号位
 * 2. 获取整数位： numerator / denominator
 * 3. 然后开始处理小数位：
 *    3.1 先获取余数 mod = numerator % denominator, 此时一定存在以下关系 mod < denominator < mod * 10
 *    3.2 将mod记录到一个SET里面
 *    3.3 mod = mod * 10
 *    3.4 将mod / denominator 存入result cache 里面
 *    3.5 mod = mod % denominator
 *    3.6 如果 mod 等于0则结束循环
 *    3.7 如果 mod 存在于之前的SET里面，则循环节开始出现，此时需要break出loop，加上括号之后返回结果
 *    3.8 如果 mod 不为0， 则返回至3.1 
 * @author Dingp
 *
 */
public class FractionToRecurringDecimal {

	public String fractionToDecimal(int numerator, int denominator) {
        return "";
    }
}
