package subSequence;

// 找出一组字符串中，他们共同的最长前缀

public class LongestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        if(strs == null || strs.length == 0)
            return "";
        if(strs.length == 1)
            return strs[0];
        
        String result = longestCommonPrefix(strs[0], strs[1]);
        for(int i=2; i<strs.length; ++i) {
            result = longestCommonPrefix(result, strs[i]);
        }
        
        return result;
    }
    
    
    private String longestCommonPrefix(String s1, String s2) {
        if(s1 == null || s2 == null)
            return "";
        StringBuffer sb = new StringBuffer();
        int i=0;
        while(i<s1.length() && i<s2.length()) {
            if(s1.charAt(i) == s2.charAt(i)) {
                sb.append(s1.charAt(i));
                ++i;
            }else
                break;
        }
        
        return sb.toString();
    }
}
