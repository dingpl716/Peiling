package string_array;

//	Given two strings S and T, determine if they are both one edit distance apart.

/**
 * 这道题实际上可以最终划归为两种情况
 * 1.要么长度相等，那么能做的edition就只有更改letter了
 * 2.要么长度不想，那么能做的edition就只有删除或者添加letter，这两种操作其实是一回事。
 * Edit 的定义应该是 增加一个，删除一个或者更改一个letter
 * 
 * 核心思想：双指针
 * 首相两个string的length相差不能超过1，不然肯定不是one edit distance
 * 
 * 然后假设s的length <= t的length
 * 那么两个个指针ps和pt
 * 如果s[ps] == t[pt] 那么两个指针都++
 * 如果s[ps] != t[pt] 那么分两种情况
 * 		如果s.length< t.length 那么 ++pt. 
 * 		如果s.length == t.length 那么我们需要记录这种不同的情况到底有多少次，然后ps和pt都++
 * 循环的时候随时检查ps he pt的差，如果大于1那么肯定不行了
 * 最后检查ps的情况，要是ps没有走完整个s，那么肯定是不行的了
 * 
 * 
 * @author Dingp
 *
 */
public class OneEditDistance {

    public boolean isOneEditDistance(String s, String t) {
        if (s == null || t == null) {
        	return false;
        }
        
        if (s.length() > t.length()) {
        	return isOneEditDistance(t, s);
        }
        
        if (t.length() - s.length() > 1) {
        	return false;
        }
        
        // 当两个string长度相等的时候，用来记录两者之间不同char的数量
        int difference = 0;
        int ps = 0;
        int pt = 0;
        while(ps < s.length() && pt <t.length()) {
        	if (pt - ps > 1) {
        		return false;
        	}
        	
        	if (s.charAt(ps) == t.charAt(pt)) {
        		++ps;
        		++pt;
        	} else {
        		if (s.length() == t.length()) {
        			if (difference == 1) {
        				return false;
        			} else {
        				++difference;
        			}
        			++ps;
        			++pt;
        		} else {
        			++pt;
        		}
        	}
        }
        
        if(s.length() == t.length() && difference == 0) {
        	return false;
        }
        
        return ps >= s.length();
    }
}
