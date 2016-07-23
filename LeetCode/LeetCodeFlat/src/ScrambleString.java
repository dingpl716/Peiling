//	Given a string s1, we may represent it as a binary tree 
//	by partitioning it to two non-empty substrings recursively.
//	
//	Below is one possible representation of s1 = "great":
//	
//	    great
//	   /    \
//	  gr    eat
//	 / \    /  \
//	g   r  e   at
//	           / \
//	          a   t
//	To scramble the string, we may choose any 
//	non-leaf node and swap its two children.
//	
//	For example, if we choose the node "gr" and swap 
//	its two children, it produces a scrambled string "rgeat".
//	
//	    rgeat
//	   /    \
//	  rg    eat
//	 / \    /  \
//	r   g  e   at
//	           / \
//	          a   t
//	We say that "rgeat" is a scrambled string of "great".
//	
//	Similarly, if we continue to swap the children of nodes 
//	"eat" and "at", it produces a scrambled string "rgtae".
//	
//	    rgtae
//	   /    \
//	  rg    tae
//	 / \    /  \
//	r   g  ta  e
//	       / \
//	      t   a
//	We say that "rgtae" is a scrambled string of "great".
//	
//	Given two strings s1 and s2 of the same length, 
//	determine if s2 is a scrambled string of s1.

public class ScrambleString {
//	 a,b,c,d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z 
	private static int table[] = {2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59,61,67,71,73,79,83,89,87,101};
	
    public boolean isScramble(String s1, String s2) {
        if(s1 == null && s2 == null)
        	return true;
        else if(s1 != null && s2 == null)
        	return false;
        else if(s1 ==null && s2 != null)
        	return false;
        else if(s1.length() != s2.length())
        	return false;
        // 剪枝条件，他们是scramble的前提条件是他们是anagrams，
		// 如果不是那么就没有必要往下递归了。 不加这个剪枝条件会超时。
        else if(!isAnagrams(s1, s2))
        	return false;
        else if(s1.equals(s2))
        	return true;
        else {
        	for(int i=1; i<s1.length(); ++i) {
        		 //seporate s1 as [0,i - 1],[i, s1.size() - 1]
        		String s11 = s1.substring(0,i);
        		String s12 = s1.substring(i);
        		
        		String s21 = s2.substring(0, i);
        		String s22 = s2.substring(i);
    			if(isScramble(s11, s21) && isScramble(s12, s22))
    				return true;
        		
        		s21 = s2.substring(s2.length() - i);
        		s22 = s2.substring(0, s2.length() - i);
    			if(isScramble(s11, s21) && isScramble(s12, s22))
    				return true;
        	}
        	return false;
        }
    }
    
    private boolean isAnagrams(String s1, String s2) {
    	long result1 = 1l;
		for(int i=0; i<s1.length(); ++i)
			result1 *= table[s1.charAt(i) - 'a'];
		
    	long result2 = 1l;
		for(int i=0; i<s2.length(); ++i)
			result2 *= table[s2.charAt(i) - 'a'];
		
		return result1 == result2;
    }
}
