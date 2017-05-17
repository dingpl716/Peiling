package graph;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import definition.Word2Word;

//	Given two words (beginWord and endWord), and a dictionary's word list, find all shortest transformation sequence(s) from beginWord to endWord, such that:
//	
//	Only one letter can be changed at a time
//	Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
//	For example,
//	
//	Given:
//	beginWord = "hit"
//	endWord = "cog"
//	wordList = ["hot","dot","dog","lot","log","cog"]
//	Return
//	  [
//	    ["hit","hot","dot","dog","cog"],
//	    ["hit","hot","lot","log","cog"]
//	  ]
//	Note:
//	Return an empty list if there is no such transformation sequence.
//	All words have the same length.
//	All words contain only lowercase alphabetic characters.
//	You may assume no duplicates in the word list.
//	You may assume beginWord and endWord are non-empty and are not the same.


//同样用BFS，在达到end时，不忙return，而是遍历完这一层。因为在这一层上的
//所有单词都是一条可能的通路。
//每个node都是一个string，两个node相连如果他们只相差一个字符。
//现在的问题就是找到所有从start到end的最短路径（路径上节点的数目。）。
//这个图是动态构建的：也就是我们访问到某个string而且这个string在dict里面，
//我们才把他加入图。我们使用BFS，当遇到一个没有见过的string，而且在dict里，
//我们把它加入一个队列，并且记录这个string是从哪些节点访问到的。
//当我们访问到了end的时候，我们采用back trace（我们已经记录了每个string的前一个string的集合），
//来找到所有通往start的路径。

/*
 * 已犯错误：
 * 1.metEnd 的位置不对，造成死循环，metEnd不应该和队列判空一起放置，而应该放在其里面
 * 2.tmp时忘了clear
 * 3.met里面不应该记录重复的点，而应该记录重复的边，因为从不同点跳到同一个点其实是不一样的。
 * 比如从start到end有很多条路径，但是这些路径都会在一个点汇聚，那么此时就不能因为之前已经
 * 访问过这个点而放弃这些路径。
 * 但是这里还有一个例外就是不论从什么点，只要又跳回了起点，那么都认为他是重复的
 * 4. 3的观点仍然是错误的，反例：从start到一个中间点middle有很多条路径，而这个middle
 * 到end只有一条没分差的路径，并且middle到end的这条路径比较长，需要很多条才能过去，那么
 * 此时按照3的方法，最终结果只会有一条路径，因为从middle之后程序会认为只有一条不重复的路径
 * 5. 建立HashMap<String, Integer> met. String 是到达某个单词，Integer表示从start到这个单词的路径长度
 * 如果在遇到同样的String的时候,比较他们的Integer, 如果这个新的路径长于已有的，那么就把这个新的排除掉。
 * 这样可以避免绕远路和环路
 */
public class WordLadderII {
	
	/*
	 * 用来记录一个词是从哪个词跳过来的，这样可以
	 * 在到达终点之后用来回溯到起点
	 * 这个类相当于是一个链表，每个节点指向前一个节点
	 */
	private static class Word2Word{
		Word2Word from;
		String to;
		int length;
		Word2Word(Word2Word f, String t, int l) {
			from = f;
			to = t;
			length = l;
		}
	}
	
    public ArrayList<ArrayList<String>> findLadders(String start, String end, HashSet<String> dict) {
        if(start.equals(end))
        	return new ArrayList<ArrayList<String>>();
        if(dict == null || dict.isEmpty())
        	return new ArrayList<ArrayList<String>>();
        
        Map<String, Integer> met = new HashMap<String, Integer>();
        Queue<Word2Word> q1 = new LinkedList<Word2Word>();
        Queue<Word2Word> q2 = new LinkedList<Word2Word>();
        ArrayList<Word2Word> endPoints = new ArrayList<Word2Word>();
        boolean metEnd = false;
        
        Word2Word startingPath = new Word2Word(null ,start,1);
        q1.add(startingPath);
        met.put(start, 0);
        while(!q1.isEmpty() || !q2.isEmpty()) {
        	if(metEnd)
        		break;
        	if(q2.isEmpty()) {
        		metEnd = jumpOneStep(q1, q2, end, met, dict, endPoints);
        	}else {
        		metEnd = jumpOneStep(q2, q1, end, met, dict, endPoints);
        	}
        }
        
    	return backTrack(endPoints);
    }
    
    /**
     * 从cq里面取出每一个元素，然后尝试着从这些词往下跳一步，如果
     * 能跳到终点,则直接加入到endpoints里面，并返回true; 如果不能
     * 而又是在dict里面且没访问过的元素，则加入到nq里面，并且在met里面
     * 记录下来
     * @param source Current queue, get every element from this queue
     * @param target Next queue, add elements to this queue
     * @param end
     * @param met 
     * @param dict
     * @param endPoints
     * @return
     */
    private boolean jumpOneStep(Queue<Word2Word> source, Queue<Word2Word> target, String end,
    		Map<String, Integer> met, HashSet<String> dict, ArrayList<Word2Word> endPoints) {
    	boolean metEnd = false;
    	
    	while(!source.isEmpty()) {
			Word2Word prev = source.poll();
			//当前到达的单词
			String current = prev.to;
			char[] chars = current.toCharArray();
			for(int i=0; i<current.length(); ++i) {
				for(char c='a'; c<='z'; ++c) {
					if(c == chars[i])
						continue;
					char tmp = chars[i];
					chars[i] = c;
					String newStr = new String(chars);
					if(newStr.equals(end)) {
						endPoints.add(new Word2Word(prev, newStr, prev.length+1));
						metEnd = true;
						break;
					}else {
						if(dict.contains(newStr)) {
							Word2Word newPath = new Word2Word(prev, newStr, prev.length+1);
							Integer jumps = met.get(newStr);
							if(jumps == null) {
								met.put(newStr, prev.length+1);
								target.add(newPath);
							}else {
								if(prev.length+1 <= jumps) {
									met.put(newStr, prev.length+1);
									target.add(newPath);
								}
							}
						}
					}
					chars[i] = tmp;
				}
			}
		}
    	
    	return metEnd;
    }
    
    /**
     * 
     * @param endPoints 是一系列从end到start的链表，通过这些词来
     * 找出之前我们是怎么跳过来的。
     * 每一个endPoint的第一个节点都是end这个单词，最后一个是start,类似于以下结构：
     * start <- string1 <- string2 <- ... <- end
     * @return
     */
    private ArrayList<ArrayList<String>> backTrack(ArrayList<Word2Word> endPoints) {
    	ArrayList<ArrayList<String>> results = new ArrayList<ArrayList<String>>();
    	int length = 0;
    	if(endPoints.isEmpty())
    		return results;
    	Word2Word onePath = endPoints.get(0);
    	while(onePath != null) {
    		++length;
    		onePath = onePath.from;
    	}
    	if(length == 0)
    		return results;
    	String[] tmp = new String[length];
    	
    	// 每一个endPoint都是一条符合要求的路径。
    	for(Iterator<Word2Word> i=endPoints.iterator(); i.hasNext();) {
    		int index = length;
    		Word2Word current = i.next();
    		while(current != null) {
    			tmp[--index] = current.to;
    			current = current.from;
    		}
    		results.add(toArrayList(tmp));
    		
    	}
    	
    	return results;
    }
    
    private ArrayList<String> toArrayList(String[] strs) {
    	ArrayList<String> tmp = new ArrayList<String>();
    	for(String s : strs) {
    		tmp.add(s);
    	}
    	return tmp;
    }
}
