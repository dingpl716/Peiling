//	Given an array of integers, every element 
//	appears twice except for one. Find that single one.
//	
//	Note:
//	Your algorithm should have a linear runtime complexity. 
//	Could you implement it without using extra memory?
public class SingleNumber {
//	异或， 因为x^x = 0
//	所以那些重复的（出现两次的）数字彼此异或之后就全为0了
//	只剩下那个出现一次的数字
    public int singleNumber(int[] A) {
        return 0;
    }
    
    public static void main(String args[]) {
    	System.out.println(1^1);
    	System.out.println(6^6);
    }
}
