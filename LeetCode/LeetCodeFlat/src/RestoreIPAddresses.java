import java.util.ArrayList;

//	Given a string containing only digits, restore it by returning all 
//	possible valid IP address combinations.
//	
//	For example:
//	Given "25525511135",
//	
//	return ["255.255.11.135", "255.255.111.35"]. (Order does not matter)

// 还是DFS， 关键在于符合要求的子段的检查上
public class RestoreIPAddresses {
	
    public ArrayList<String> restoreIpAddresses(String s) {
    	ArrayList<String> result = new ArrayList<String>();
    	ArrayList<String> buffer = new ArrayList<String>(4);
    	if(s == null || s.length() >12 || s.length() < 4)
    		return result;
    	dfs(s, 0, buffer, result);
    	return result;
    }
    
    private void dfs(String s, int start, ArrayList<String> buffer, 
    				ArrayList<String> result) {
    	if(buffer.size() == 4 && start == s.length()) {
    			result.add(buffer.get(0)+"."+buffer.get(1)+"."+
    						buffer.get(2)+"."+buffer.get(3));
    	}else if(buffer.size() < 4 && start < s.length()) {
    		for(int i=start; i<=start+2 && i<s.length(); ++i) {
    			String tmp = s.substring(start, i+1);
    			// 这里必须对这个子段做检查
    			if(checkString(tmp) == true) {
    				buffer.add(tmp);
    				dfs(s, i+1, buffer, result);
    				buffer.remove(buffer.size()-1);
    			}else {
    				break;
    			}
    		}
    	}else
    		return;
    }

    /*
     * 已犯错误，1.00.10.10是不合法的！！不能出现连续的两个0
     */
	private boolean checkString(String tmp) {
		if(tmp.length() > 1 && tmp.charAt(0) == '0')
			return false;
		int result = 0;
		for(int i=0; i<tmp.length(); ++i) 
			result = result*10 + tmp.charAt(i) - '0';
		
		return result < 256;
	}
}
