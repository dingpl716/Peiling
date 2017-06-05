package bits;

//	The Hamming distance between two integers is the number of positions at which the corresponding bits are different.
//	
//	Now your job is to find the total Hamming distance between all pairs of the given numbers.
//	
//	Example:
//	Input: 4, 14, 2
//	
//	Output: 6
//	
//	Explanation: In binary representation, the 4 is 0100, 14 is 1110, and 2 is 0010 (just
//	showing the four bits relevant in this case). So the answer will be:
//	HammingDistance(4, 14) + HammingDistance(4, 2) + HammingDistance(14, 2) = 2 + 2 + 2 = 6.

//	Note:
//	Elements of the given array are in the range of 0 to 10^9
//	Length of the array will not exceed 10^4.
public class TotalHammingDistance {
	
	public int totalHammingDistance(int[] nums) {

		int result = 0;
		for (int i = 0; i < nums.length - 1; ++i){
			for (int j = i + 1; j < nums.length; ++j){
				result += hammingDistance(nums[i], nums[j]);
			}
		}
        
		return result;
    }
	
	
	public int hammingDistance(int num1, int num2){
		int result = 0;
		if (num1 == num2){
			return result;
		}
		
		for (int i = 0, pattern = 1; i < 31; ++i, pattern = pattern << 1){
			if ((num1 & pattern) != (num2 & pattern)){
				++ result;
			}
		}
		
		return result;
	}
	
	public static void main(String[] args){
		TotalHammingDistance t = new TotalHammingDistance();
		System.out.println(t.hammingDistance(14, 2));
	}
}
