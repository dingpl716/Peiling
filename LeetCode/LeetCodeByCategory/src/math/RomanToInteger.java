package math;

import java.util.HashMap;
import java.util.Map;

//	Given a roman numeral, convert it to an integer.
//	
//	Input is guaranteed to be within the range from 1 to 3999.

//	在较大的罗马数字的右边记上较小的罗马数字，表示大数字加小数字。
//	在较大的罗马数字的左边记上较小的罗马数字，表示大数字减小数字。
//	左减的数字有限制，仅限于I、X、C。比如45不可以写成VL，只能是XLV
//	但是，左减时不可跨越一个位值。比如，99不可以用IC（100-1）表示，而是用XCIX（[100-10]+[10-1]）表示。（等同于阿拉伯数字每位数字分别表示。）
//	左减数字必须为一位，比如8写成VIII，而非IIX。
//	右加数字不可连续超过三位，比如14写成XIV，而非XIIII。
/**
 * 核心思想，
 * 将目前的字符和后面的一个比较，
 * 如果当前的<之后的，那么 sum-= 当前的数
 * 如果当前的>=之后的，那么 sum += 当前的数
 * 
 */
public class RomanToInteger {

	private static Map<Character, Integer> map = initialize();
	
	private static Map<Character, Integer> initialize() {
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		map.put('I', 1);
		map.put('V', 5);
		map.put('X', 10);
		map.put('L', 50);
		map.put('C', 100);
		map.put('D', 500);
		map.put('M', 1000);
		
		return map;
	}
	
    public int romanToInt(String s) {
        if (s == null || s.length() == 0) {
        	return 0;
        }
        
        int i = 0;
        int sum = 0;
        for (; i < s.length() - 1; ++i) {
        	int current = map.get(s.charAt(i));
        	int next = map.get(s.charAt(i + 1));
        	if (current < next) {
        		sum -= current;
        	} else {
        		sum += current;
        	}
        }
        
        // 最后一个字符一定是加
        sum += map.get(s.charAt(i));
        
        return sum;
    }
}
