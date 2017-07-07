package design;

//	Compare two version numbers version1 and version2.
//	If version1 > version2 return 1, if version1 < version2 return -1, otherwise return 0.
//	
//	You may assume that the version strings are non-empty and contain only digits and the . character.
//	The . character does not represent a decimal point and is used to separate number sequences.
//	For instance, 2.5 is not "two and a half" or "half way to version three", 
//	it is the fifth second-level revision of the second first-level revision.
//	
//	Here is an example of version numbers ordering:
//	
//	0.1 < 1.1 < 1.2 < 13.37

public class CompareVersionNumbers {
	
    public int compareVersion(String version1, String version2) {
    	if (version1 == null && version2 == null){
    		return 0;
    	}
    	
    	if (version1.isEmpty() && version2.isEmpty()) {
    		return 0;
    	}
    	
    	if (version1 == null || version1.isEmpty()) {
    		return -1;
    	}
    	
    	if (version2 == null || version2.isEmpty()) {
    		return 1;
    	}
    	
    	int[] versions1 = getVersions(version1);
    	int[] versions2 = getVersions(version2);
    	
    	int i = 0;
    	for (; i < versions1.length && i < versions2.length; ++i) {
    		if (versions1[i] < versions2[i]) {
    			return -1;
    		} 
    		
    		if (versions1[i] > versions2[i]) {
    			return 1;
    		}
    	}
    	
    	if (i < versions1.length) {
    		for (; i < versions1.length; ++i) {
    			if (versions1[i] != 0) {
    				return 1;
    			}
    		}
    	}
    	
    	if (i < versions2.length) {
    		for (; i < versions2.length; ++i) {
    			if (versions2[i] != 0) {
    				return -1;
    			}
    		}
    	}
    	
    	return 0;
    }
    
    private int[] getVersions(String version) {
    	
    	String[] versions = version.split("\\.");
    	
    	int[] result = new int[versions.length];
    	for (int i = 0; i < versions.length; ++i) {
    		if (versions[i] == null || versions[i].isEmpty()) {
    			result[i] = 0;
    		} else {
    			result[i] = Integer.parseInt(versions[i]);
    		}
    	}
    	
    	return result;
    }
    
    public static void main(String[] args) {
    	CompareVersionNumbers c = new CompareVersionNumbers();
    	System.out.println(c.compareVersion("1.0", "1"));
//    	System.out.println(c.compareVersion("0.9.9.9.9.9.9.9.9.9.9.9.9", "1.0"));
//    	System.out.println(c.compareVersion("1", "0"));
//    	System.out.println(c.compareVersion("0", "1"));
//    	
//    	System.out.println(c.compareVersion("1.0", "0."));
//    	System.out.println(c.compareVersion("0.", "1."));
//    	System.out.println(c.compareVersion(".1", "1."));
    	
    	
    }
}
