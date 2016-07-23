import java.util.ArrayList;

//	Given an absolute path for a file (Unix-style), simplify it.
//	
//	For example,
//	path = "/home/", => "/home"
//	path = "/a/./b/../../c/", => "/c"
//	click to show corner cases.
//	
//	Corner Cases:
//	1. Did you consider the case where path = "/../"?
//		In this case, you should return "/".
//	2. Another corner case is the path might contain multiple slashes '/' together, such as "/home//foo/".
//		In this case, you should ignore redundant slashes and return "/home/foo".
//	
//	已犯错误
//	1. 在 new了一个String[] 之后，里面的每一个元素都是null，不是“”
//	2. String.split("/") 
//	     加入String是“/1/2//3/”，这个会产生["", "1", "2", "", "3"]，第一个"/"的前面会产生一个空字符串，连续的两个/也会产生一个空字符串，但是最后一个/不会产生一个空字符串
//	3. 在这里paths应该用Arraylist而不要用String[]， paths里的元素应该是有意义的item(不是空，不是.,不是..)
//	4. result最初应该是不包含开头的"/"而是最后返回的时候根据情况加上去。不能弄成最开始有开头的"/",
//		并且在返回的时候去除这个"/".因为如果result的最终结果是"/"，那么当你用result.subString(0)的时候这个'/'被删除了
public class SimplifyPath {
	
    public String simplifyPath(String path) {
        if(path == null || path.length() == 0)
        	return "/";

        boolean startingRoot = true;
        if(path.charAt(0) != '/')
        	startingRoot = false;
        
        String[] items = path.split("/");
        ArrayList<String> paths = new ArrayList<String>();
        int i = 0;
        for(String item : items) {
        	//  home//foo
        	if(item == null || item.length() == 0)
        		continue;
        	if(item.equals("."))
        		continue;
        	if(item.equals("..")) {
        		if(paths.size() > 0)
        			paths.remove(paths.size()-1);
        		continue;
        	}
        	paths.add(item);	
        }
        
        String result = "";
        for(i=0; i<paths.size(); ++i) {
    		if(i == paths.size()-1)
    			result += paths.get(i);
    		else
    			result += (paths.get(i) + "/");
        }
        
        if(startingRoot)
        	return "/" + result;
        else
        	return result;
    }
}
