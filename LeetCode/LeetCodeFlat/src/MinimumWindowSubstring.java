import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

//	Given a string S and a string T, find the minimum window in S 
//	which will contain all the characters in T in complexity O(n).
//	
//	For example,
//	S = "ADOBECODEBANC"
//	T = "ABC"
//	Minimum window is "BANC".
//	
//	Note:
//	If there is no such window in S that covers all 
//	characters in T, return the emtpy string "".
//	
//	If there are multiple such windows, you are guaranteed that 
//	there will always be only one unique minimum window in S.
//
//这道题的思想和LongestSubstringWithoutRepeatingCharacters 差不多，应该合起来看

//首先需要一个window，也就是一个map
//char -> (currentFreq, originalFreq)
//第二个值代表这个char在T里面本来出现过多少次，这个值用来恢复map
//第一个值代表这个char在S里面出现过多少次了，初始值是0
//然后两个个指针，start还有i, 上面的map记录的就是[start,i]中的情况
//一个符合要求的答案一定满足这个条件：
//	1.window[start]的currentFreq == originalFreq， 
//	2.window[i]的currentFreq == originalFreq，
//	3.中间的currentFreq >= originalFreq
//用一个queue来保存在S中从[start,i]并且在T里面出现过的char的坐标
//如果map[start].currentFreq > originalFreq,说明在start以后到i，还有多余的被start指向的这个char
//所以start应该收缩到下一个位置，也就是queue要remove head node
//因为start变动了，所以map[start].currentFreq 要减1，表示在[newStart, i]中这个char的出现次数减少了
//我们要不断的收缩这个start，保证start不会指向多余的元素
//当且仅当，在map中的所有entry里面，currentFreq >= originalFreq时一个覆盖了所有char的子字符串才会出现
//当这个子字符串出现了之后，我们要和现有的result作比较
//并且start要加1！，因为比较还没有结束,加1之后要跟新window

//已犯错误
//1. 更新result的条件里面应该加上result.length() == 0
//2. queue里面不应该包含start，如start = 1， 那么queue里面不能有1
//3. 跟新start的条件是queue不是empty，不能忘了！
public class MinimumWindowSubstring {
	
	public String minWindow(String S, String T) {
    	if(S == null || S.length() == 0)
    		return S;
    	if(T == null || T.length() == 0)
    		return "";
    	Queue<Integer> queue = new LinkedList<Integer>();
    	// char -> 实际出现的次数, 应该出现的次数
    	Map<Character, int[]> window = initWindow(T);
    	// 如果实际出现的次数>=应该出现的次数，那么就是一个validChar
    	int validCharNumber = 0;
    	int start;
    	String result = "";
    	for(start=0; start<S.length(); ++start) {
    		if(window.containsKey(S.charAt(start)))
    			break;
    	}
    	if(start >= S.length())
    		return result;
    	
    	for(int i=start; i<S.length(); ++i) {
    		char key = S.charAt(i);
    		if(window.containsKey(key)) {
    			// the queue is used to shrink start
    			if( i != start)
    				queue.add(i);
    			int[] values = window.get(key);
    			++values[0];
    			if(values[0] == values[1])
    				++validCharNumber;
    		}
    		
    		// repeatedly shrink [start, i] by increasing start
    		int[] startValues = window.get(S.charAt(start));
    		while(startValues[0] > startValues[1]) {
    			startValues[0]--;
    			start = queue.poll();
    			startValues = window.get(S.charAt(start));
    		}
    		// 一个潜在的答案出现了，更新result，
    		if(validCharNumber == window.size()) {
    			String tmp = S.substring(start, i+1);
    			if(result.length() == 0 || tmp.length() < result.length())
    				result = tmp;
    			
    			// not finish yet, shrink [start, i] and keep going
    			if(!queue.isEmpty()) {
    				startValues = window.get(S.charAt(start));
    				startValues[0]--;
    				start = queue.poll();
    				--validCharNumber;
    			}
    		}
    	}
    	return result;
	}
	
	private Map<Character, int[]> initWindow(String T) {
		Map<Character, int[]> window = new HashMap<Character, int[]>();
		for(int i=0; i<T.length(); ++i) {
			char key = T.charAt(i);
			if(window.containsKey(key)) {
				int[] values = window.get(key);
				++values[1];
			}else {
				int[] values = {0,1};
				window.put(key, values);
			}
		}
		
		return window;
	}
	
}
