package bits;

//	Given a range [m, n] where 0 <= m <= n <= 2147483647, 
//	return the bitwise AND of all numbers in this range, inclusive.
//	
//	For example, given the range [5, 7], you should return 4.
public class BitwiseANDofNumbersRange {

	public int rangeBitwiseAnd(int m, int n) {
    
		int result = m & (m + 1);
		for (int i = m + 2; i <= n; ++i){
			result &= i;
		}
		
		return result;
    }
	
	public static void main(String[] args){
		BitwiseANDofNumbersRange b = new BitwiseANDofNumbersRange();
		System.out.println(b.rangeBitwiseAnd(5, 7));
	}
}
