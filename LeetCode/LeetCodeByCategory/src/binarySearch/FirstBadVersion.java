package binarySearch;

import util.Util;

//	You are a product manager and currently leading a team to develop a new product. 
//	Unfortunately, the latest version of your product fails the quality check. 
//	Since each version is developed based on the previous version, 
//	all the versions after a bad version are also bad.
//	
//	Suppose you have n versions [1, 2, ..., n] and you want to find out 
//	the first bad one, which causes all the following ones to be bad.
//	
//	You are given an API bool isBadVersion(version) which will return 
//	hether version is bad. Implement a function to find the first bad version. 
//	You should minimize the number of calls to the API.
		
public class FirstBadVersion {
	
    public int firstBadVersion(int n) {
        int left = 1;
        int right = n;
        
        while (left < right) {
        	int middle = left + ((right - left) / 2);
        	if (isBadVersion(middle)) {
        		right = middle;
        	} else {
        		left = middle + 1;
        	}
        }

        return right;
    }
      
    /* The isBadVersion API is defined in the parent class VersionControl.*/
    boolean isBadVersion(int version) {
    	return version >= 1702166719;
    }
    
    public static void main(String[] args) {
    	FirstBadVersion f = new FirstBadVersion();
    	int n = 2126753390;
    	Util.timmer((object) -> {System.out.println(f.firstBadVersion(n));});
    }
}
