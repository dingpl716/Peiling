package string_array;

//	The count-and-say sequence is the sequence of integers with the first five terms as following:
//	
//		 1.     1
//		 2.     11
//		 3.     21
//		 4.     1211
//		 5.     111221 
//		 6.     312211
//		 7.     13112221
//		 8.     1113213211
//		 9.     31131211131221
//		 10.   13211311123113112211
//		 
//	1 is read off as "one 1" or 11.
//	11 is read off as "two 1s" or 21.
//	21 is read off as "one 2, then one 1" or 1211.
//	Given an integer n, generate the nth term of the count-and-say sequence.
//	From the examples you can see, the (i+1)th sequence is the "count and say" of the ith sequence!
//	Note: Each term of the sequence of integers will be represented as a string.

/**
 * 这个题的意思是这样的：
 * 1. 第一轮是1；
 * 2. 第二轮我们读第一轮的output, 我们读作1个1，记为11
 * 3. 第三轮我们读第二轮的output, 我们读作2个1，记为21
 * 4. 第四轮我们读第三轮的output, 我们读作1个2,1个1， 记为1211
 * 5. 第五轮我们读第四轮的output, 我们读作1个1,1个2，2个1， 记为 111221
 * 6. 第六轮我们读第五轮的output, 我们读作3个1,2个2,1个1， 记为312211
 * @author Dingp
 *
 */
public class CountAndSay {

    public String countAndSay(int n) {
    	String output = "1";
    	
    	for (int i = 1; i < n; ++i) {
    		output = countAndSay(output);
    	}
    	
    	return output;
    }
    
    /**
     * 数出input里面相邻的相同的数的个数，然后把个数和这个值都加到output里面
     * @param input
     * @return
     */
    private String countAndSay(String input) {
    	StringBuilder output = new StringBuilder();
    	
    	// 注意，在这里不用再做++i了，因为i的增长由next控制。
    	for (int i = 0; i < input.length();) {
    		int count = 1;
    		int next = i + 1;
    		while (next < input.length() && input.charAt(next) == input.charAt(i)){
    			++count;
    			++next;
    		}
    		
    		output.append(count);
    		output.append(input.charAt(i));
    		
    		i = next;
    	}
    	
    	return output.toString();
    }
}
