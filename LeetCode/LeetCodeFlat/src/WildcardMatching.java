//	Implement wildcard pattern matching with support for '?' and '*'.
//	
//	'?' Matches any single character.
//	'*' Matches any sequence of characters (including the empty sequence).
//	
//	The matching should cover the entire input string (not partial).
//	
//	The function prototype should be:
//	bool isMatch(const char *s, const char *p)
//	
//	Some examples:
//	isMatch("aa","a") → false
//	isMatch("aa","aa") → true
//	isMatch("aaa","aa") → false
//	isMatch("aa", "*") → true
//	isMatch("aa", "a*") → true
//	isMatch("ab", "?*") → true
//	isMatch("aab", "c*a*b") → false

//	两个指针sp,pp, 一个boolean变量表示目前遇到*了没
//	如果pp是一个？，进行下一轮
//	如果没有遇到*，且sp不等于pp那么return false
//	如果遇到*的情况下，sp!=pp 那么sp往前走
//	最后如果s走完了，p没走完，则要根据p是不是以*结尾来返回
public class WildcardMatching {
	
    public boolean isMatch(String s, String p) {
    	if(s == p || s.equals(p))
    		return true;
    	char[] sc = s.toCharArray();
    	char[] pc = p.toCharArray();
    	boolean metSter = false;
    	int ibk = 0;
    	int pp = 0;
    	int i, j;
    	for(i=0, j=0; i<s.length(); ++i, ++j) {
    		if(pc[j] == '?') {
    			continue;
    		}
    		if(pc[j] == '*') {
    			metSter = true;
    			ibk = i;
    			pp = j;
    			// 找到下一个不为*的字符
    			while(pp<pc.length && pc[pp] == '*')
    				++pp;
    			// 这个说明p后面是以*结尾的了，直接返回true
    			if(pp >= pc.length)  
    				return true;
    			i = ibk -1;
    			j = pp - 1;
    			continue;
    		}
    		if(pc[j] != sc[i]) {
    			if(!metSter)
    				return false;
    			++ibk;
    			i = ibk - 1;
    			j = pp - 1;
    		}
    	}
    	
    	while(j<pc.length && pc[j] == '*')
    		++j;
    	if(j>=pc.length)
    		return true;
    	else 
    		return false;
    }
}
