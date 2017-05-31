package string_array;
import java.util.ArrayList;

//	The gray code is a binary numeral system where two successive values differ in only one bit.
//	
//	Given a non-negative integer n representing the total number of bits in the code, print the sequence of gray code. A gray code sequence must begin with 0.
//	
//	For example, given n = 2, return [0,1,3,2]. Its gray code sequence is:
//	
//	00 - 0
//	01 - 1
//	11 - 3
//	10 - 2
//	Note:
//	For a given n, a gray code sequence is not uniquely defined.
//	
//	For example, [0,2,3,1] is also a valid gray code sequence according to the above definition.
/**
 * 基本思路：
 * 1. 从n=1，一点一点一点的网上组装gray codes。 比如，先是n=1，再是n=2，再是n=3.。。。
 * 2. 每次从n-1升级到n的时候做以下事情：
 * 		1) 反向复制现有的gray codes
 * 		2) 给前半部分的每一个code尾部添加0，给后半部分的每一个添加1
 * 
 * 例子:
 *  n = 1:
 *  0
 *  1
 *  
 *  n = 2:
 *  0     00
 *  1  -> 10
 *  1  -> 11
 *  0     01
 *  
 *  n = 3:
 *  00       000
 *  10       100
 *  11       110
 *  01  ->   010
 *  01  ->   011
 *  11       111
 *  10       101
 *  00       001
 * @author Dingp
 *
 */
public class GrayCode {

	public ArrayList<Integer> grayCode(int n) {
		
		if(n == 0)
			return null;
		
		StringBuffer s0 = new StringBuffer("0");
		StringBuffer s1 = new StringBuffer("1");
		
		ArrayList<StringBuffer> grayCodes = new ArrayList<StringBuffer>();
		grayCodes.add(s0);
		grayCodes.add(s1);
		
		if(n == 1) {
			return generateGrayCode(grayCodes);
		}
		
        int i = 1;
		while(i < n) {
			grayCodes = getNewGrayCodes(grayCodes);
			++i;
        }
		
		return generateGrayCode(grayCodes);
    }
	
	private Integer stringToInt(String s) {
		int result = 0;
		for(int i=0; i<s.length(); ++i){
			result = result*2 + Character.getNumericValue(s.charAt(i));
		}
		
		return result;
	}
	
	private ArrayList<Integer> generateGrayCode(ArrayList<StringBuffer> list) {
		ArrayList<Integer> codes = new ArrayList<Integer>();
		
		for(int i=0; i<list.size(); ++i) {
			codes.add(stringToInt(list.get(i).toString()));
		}
		
		return codes;
	}
	
	/**
	 * 
	 * @param grayCodes
	 * @return
	 */
	private ArrayList<StringBuffer> getNewGrayCodes(ArrayList<StringBuffer> grayCodes) {
		int size = grayCodes.size();
		for(int i=size-1; i>=0; --i) {
			StringBuffer sb = new StringBuffer(grayCodes.get(i));  //这个必须重新new，不能直接get了之后就add进去
			grayCodes.add(sb);
		}
		
		for(int i=0; i<size; ++i) {
			grayCodes.get(i).insert(0, '0');
		}
		
		for(int i=size; i<grayCodes.size(); ++i) {
			grayCodes.get(i).insert(0, '1');
		}
		
		return grayCodes;
	}
}
