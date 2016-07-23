import java.util.ArrayList;

//	Given a string s, partition s such that every 
//	substring of the partition is a palindrome.
//	
//	Return all possible palindrome partitioning of s.
//	
//	For example, given s = "aab",
//	Return
//	
//	  [
//	    ["aa","b"],
//	    ["a","a","b"]
//	  ]
public class PalindromePartitioning {
	
    public ArrayList<ArrayList<String>> partition(String s) {
    	ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
    	ArrayList<String> tmp = new ArrayList<String>();
        if(s == null || s.length() == 0) {
        	tmp.add("");
        	result.add(tmp);
        	return result;
        }
        
        DFS(s, 0, tmp, result);
        return result;
    }
    
    private void DFS(String s, int start, ArrayList<String> tmp,
    		ArrayList<ArrayList<String>> result) {
    	if(start >= s.length()) {
    		result.add(new ArrayList<String>(tmp));
    		return;
    	}
    	for(int i=start; i<s.length(); ++i) {
    		if(isPalindrome(s, start, i)) {
    			tmp.add(s.substring(start, i+1));
    			DFS(s, i+1, tmp, result);
    			tmp.remove(tmp.size()-1);
    		}
    	}
    }
    private boolean isPalindrome(String s, int left, int right) {
    	while(left < right) {
    		if(s.charAt(left) != s.charAt(right))
    			return false;
    		++left;
    		--right;
    	}
    	
    	return true;
    }
}
