package graph;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

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
 * 这道题实际上就是判断一个有向图里面是否存在环的问题，可以用拓扑排序来做
 * 拓扑排序可以用来找出有向无环图的顺序
 * 
 * 假设我们有3条边：A->C, B->C, C->D，先将每个节点的计数器初始化为0。
 * 然后我们对遍历边时，每遇到一个边，把目的节点的计数器都加1。
 * 然后，我们再遍历一遍，找出所有计数器值还是0的节点，这些节点就是有向无环图的“根”。
 * 然后我们从根开始广度优先搜索。具体来说，搜索到某个节点时，将该节点加入结果中，
 * 然后所有被该节点指向的节点的计数器减1，在减1之后，如果某个被指向节点的计数器变成0了，
 * 那这个被指向的节点就是该节点下轮搜索的子节点。
 * 在实现的角度来看，我们可以用一个队列，这样每次从队列头拿出来一个加入结果中，
 * 同时把被这个节点指向的节点中计数器值减到0的节点也都加入队列尾中。
 * 需要注意的是，如果图是有环的，则计数器会产生断层，即某个节点的计数器永远无法清零（
 * 有环意味着有的节点被多加了1，然而遍历的时候一次只减一个1，所以导致无法归零），
 * 这样该节点也无法加入到结果中。所以我们只要判断这个结果的节点数和实际图中节点数相等，
 * 就代表无环，不相等，则代表有环。
 *  
 */

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
	
	private static class Letter{
		Character val;
		int inDegree = 0; // 表示这个节点的入度;
		Letter[] bigger; // 比它大的字母，也就是这个点指向的点
		
		private Letter(char val) {
			this.val = val;
			bigger = new Letter[26];
		}
		
		private void addLetter(Letter other) {
			// 这个地方一定要注意
			// 我们必须通过检查是否为null来进行去重
			// 比如现在的letter 是 t，而t 已经指向过f了，
			// 那么当f在来的时候，就不能再增加f的入度了
			if (bigger[other.val - 'a'] == null) {
				bigger[other.val - 'a'] = other;
				other.inDegree += 1;
			} 
		}
	}
	
	public String alienOrder(String[] words) {
		if (words == null || words.length == 0) {
			return "";
		}
		
		if (words.length == 1) {
			return words[0];
		}
		
		// 记录字母的个数
		int numberOfLetters = 0;
		Letter[] graph = buildGraph(words);
		
		// 初始入度为0的点，即为这个图的根
		Queue<Letter> roots = new LinkedList<Letter>();
		for (Letter letter : graph) {
			
			if (letter != null) {
				
				// 记录字母的个数
				++numberOfLetters;
				
				if (letter.inDegree == 0) {
					roots.add(letter);
				}
			}
		}
		
		String result = bfs(roots);
		
		// 如果返回的string的长度比字母个数小，说明有环
		// 因我环里面的字母是不会再bfs循环中被访问到的。
		if (result.length() < numberOfLetters) {
			return "";
		} else {
			return result;
		}
	}
	
	private String bfs(Queue<Letter> queue) {
		StringBuilder results = new StringBuilder();
		
		while (!queue.isEmpty()) {
			Letter root = queue.poll();
			results.append(root.val);
			
			for (Letter bigger : root.bigger) {
				if (bigger != null) {
					--bigger.inDegree;
					
					// 如果被指向的点的入度为0了，那么也把它加入到队列里面
					// 在环里面的点不会被加入到queue里面
					if (bigger.inDegree == 0) {
						queue.add(bigger);
					}
				}
			}
		}
		
		return results.toString();
	}
	
	/**
	 * 对所有字母建图, 返回所有的点组成的集合
	 * @param words
	 * @return
	 */
	private Letter[] buildGraph(String[] words) {
		Letter[] graph = new Letter[26];
		
		// 首先对所有单词的所有字母创建对应的点
		for (int i = 0; i < words.length; ++i) {
			String word = words[i];
			
			for (int j = 0; j < word.length(); ++j) {
				char c = word.charAt(j);
				if (graph[c - 'a'] == null) {
					graph[c - 'a'] = new Letter(c);
				}
			}
			
		}
		
		// 然后再根据字母间的排序来创建边
		for (int i = 1; i < words.length; ++i) {
			String word1 = words[i - 1];
			String word2 = words[i];
			
			for (int j = 0; j < word1.length() && j < word2.length(); ++j) {
				char c1 = word1.charAt(j);
				char c2 = word2.charAt(j);
				
				if (c1 != c2) {
					graph[c1-'a'].addLetter(graph[c2 - 'a']);
					break; // 出现一次不同就必须要break了
				}
			}
		}
		
		return graph;
	}
}
