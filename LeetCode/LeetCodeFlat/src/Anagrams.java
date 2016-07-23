import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

//	Given an array of strings, return all groups of strings that are anagrams.
//	
//	Note: All inputs will be in lower-case.
// anagrams : 易位构词 如 heart = earth;
public class Anagrams {
//    							 a,b,c,d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z 
	public static int table[] = {2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59,61,67,71,73,79,83,89,87,101};
	
	public ArrayList<String> anagrams(String[] strs) {
		Map<Long, ArrayList<String>> map = new HashMap<Long, ArrayList<String>>();
		for(int i=0; i<strs.length; ++i) {
			String str = strs[i];
			Long hashCode = getHashCode(str);
			if(map.containsKey(hashCode)) {
				ArrayList<String> group = map.get(hashCode);
				group.add(str);
			}else{
				ArrayList<String> group = new ArrayList<String>();
				group.add(str);
				map.put(hashCode, group);
			}
		}
		
		ArrayList<String> result = new ArrayList<String>();
		for(Iterator<Map.Entry<Long, ArrayList<String>>> i=map.entrySet().iterator(); i.hasNext();) {
			Map.Entry<Long, ArrayList<String>> entry = i.next();
			ArrayList<String> group = entry.getValue();
			if(group.size() > 1) {
				for(int j=0; j<group.size(); ++j) 
					result.add(group.get(j));
			}
		}
		
		return result;
    }
	
	private long getHashCode(String str) {
		long result = 1l;
		for(int i=0; i<str.length(); ++i)
			result *= table[str.charAt(i) - 'a'];
		
		return result;
	}
}
