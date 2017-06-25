package graph;

//	There is a new alien language which uses the latin alphabet. 
//	However, the order among letters are unknown to you. 
//	You receive a list of words from the dictionary, where words are sorted 
//	lexicographically by the rules of this new language. 
//	Derive the order of letters in this language.
//	
//	For example, Given the following words in dictionary,
//	
//	[
//	  "wrt",
//	  "wrf",
//	  "er",
//	  "ett",
//	  "rftt"
//	] 
//	The correct order is: "wertf".
//	
//	Note: You may assume all letters are in lowercase. 
//	If the order is invalid, return an empty string. 
//	There may be multiple valid order of letters, return any one of them is fine.

/**
 * 核心思想:先建图，然后用拓扑排序
 * 
 * 建图部分：
 * 1. 对words两两进行扫描, word1, word2
 * 2. 对word1， word2逐个字符进行扫描，找出第一个不同的letter的位置i
 * 3. 此时可以生成一条边 word1(i) -> word2(i)
 * 
 * 拓扑排序部分： 参见Course Schedule
 * @author Dingp
 *
 */
public class AlienDictionary {
	public String alienOrder(String[] words) {
		return "";
	}
}
