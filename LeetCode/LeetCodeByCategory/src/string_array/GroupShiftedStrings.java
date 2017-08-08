package string_array;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

//	Given a string, we can "shift" each of its letter to its successive letter, 
//	for example: "abc" -> "bcd". We can keep "shifting" which forms the sequence:
//	
//	"abc" -> "bcd" -> ... -> "xyz"
//	Given a list of strings which contains only lowercase alphabets, group all 
//	strings that belong to the same shifting sequence.
//	
//	For example, given: ["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"], 
//	A solution is:
//	
//	[
//	  ["abc","bcd","xyz"],
//	  ["az","ba"],
//	  ["acef"],
//	  ["a","z"]
//	]

/**
 * 核心思想：
 * 1. 长度为1的string一定能被group到一起
 * 2. 两个string可以被group到一起的条件为:
 * 	2.1 他们的长度一样
 * 	2.2 character之间的距离一一对应。比如 abc字符间的距离是 1(b - a), 1(c - b), 
 *                                    而bcd字符间的距离是 1(c - b), 1(d - c)
 *                                       
 *                                       az字符间的距离是 25(z - a)或者(-1)
 *                                       ba字符间的距离是 -1(b - a)
 *                                       
 *                                       ay字符间的距离是24(y - a)或者(-2)
 *                                       bz字符间的距离是24(z - b)或者(-2)
 *                                       ca字符间的距离是(-2)
 *                                       
 * 所以，如果两个字符间距值distance1和disctance2, 只要满足以下任意条件，我们就认为他们相等：
 * 1. distance1 = distance2
 * 2. Abs(distance) + Abs(distance) = 26
 *  
 * @author Dingp
 *
 */
public class GroupShiftedStrings {
	
	private static class Distances{
		
		private int[] distances;
		
		private Distances(String str) {
			if (str.length() == 1) {
				distances = new int[0];
			} else {
				distances = new int[str.length() - 1];
				for (int i = 1; i < str.length(); ++i) {
					distances[i-1] = str.charAt(i) - str.charAt(i - 1);
				}
			}
		}
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			for (int i = 0; i < distances.length; ++i) {
				if (distances[i] < 0) {
					result = (prime * result) + (distances[i] + 26);  
				} else {
					result = prime * result + distances[i];
				}
			}
			
			result = prime * result + distances.length;
			
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			
			if (obj == null) {
				return false;
			}
			
			if (obj.getClass() != this.getClass()){
				return false;
			}
			
			Distances other = (Distances)obj;
			
			if (other.distances == null || other.distances.length != this.distances.length) {
				return false;
			}
			
			for (int i = 0; i < this.distances.length; ++i) {
				if (other.distances[i] != this.distances[i] &&
					Math.abs(other.distances[i]) + Math.abs(this.distances[i]) != 26) {
					return false;
				}
			}
			
			return true;
		}
	}
	
	
    public List<List<String>> groupStrings(String[] strings) {
        List<List<String>> results = new LinkedList<List<String>>();
        if (strings == null) {
        	return results;
        }
        
        Map<Distances, List<String>> map = new HashMap<Distances, List<String>>();
        
        for (String str : strings) {
        	Distances distances = new Distances(str);
        	if (map.containsKey(distances)) {
        		List<String> list = map.get(distances);
        		list.add(str);
        	} else {
        		List<String> list = new LinkedList<String>();
        		list.add(str);
        		map.put(distances, list);
        	}
        }
        
        for(List<String> list : map.values()) {
        	results.add(list);
        }
        
        return results;
    }
}
