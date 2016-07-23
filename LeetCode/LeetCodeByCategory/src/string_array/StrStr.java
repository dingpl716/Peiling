package string_array;
//	 Implement strStr().
//	
//	Returns a pointer to the first occurrence of needle in haystack,
//	or null if needle is not part of haystack. 

public class StrStr {
	
    public String strStr(String haystack, String needle) {
    	if(haystack == null || needle == null)
    		return null;
    	if(haystack.length() < needle.length())
    		return null;
    	if(needle.length() == 0)
    		return haystack;
    	if(haystack == needle)
    		return haystack;
    	
    	int i, j;
    	for(i=0; i<=haystack.length()-needle.length(); ++i) {
    		for(j=0; j<needle.length(); ++j) {
    			if(haystack.charAt(i+j) != needle.charAt(j)) 
    				break;
    		}
    		if(j == needle.length())
    			return haystack.substring(i, i+j);
    	}
    	
    	return null;
    }
}
