package dfs;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import definition.TrieNode;
import trie.Trie;
import util.Util;

//	Given a string s and a dictionary of words dict, 
//	add spaces in s to construct a sentence where 
//	each word is a valid dictionary word.
//	
//	Return all such possible sentences.
//	
//	For example, given
//	s = "catsanddog",
//	dict = ["cat", "cats", "and", "sand", "dog"].
//	
//	A solution is ["cats and dog", "cat sand dog"].

public class WordBreakII {
	
	/*******************************方法1, 纯DFS, 超时 ****************************************/
    public List<String> wordBreak1(String s, Set<String> dict) {
    	ArrayList<String> result = new ArrayList<String>();
    	ArrayList<String> buffer = new ArrayList<String>();
    	if(s == null || s.length() == 0)
    		return result;
    	if(dict == null || dict.isEmpty())
    		return result;
    	dfs1(s, 0, dict, buffer, result);
    	return result;
    }
    
//    [start, end]的部位需要被parse
    private void dfs1(String s, int start, Set<String> dict, ArrayList<String> buffer,
    						ArrayList<String> result) {
    	if(start >= s.length()) {
    		StringBuffer sb = new StringBuffer();
    		sb.append(buffer.get(0));
    		for(int i=1; i<buffer.size(); ++i)
    			sb.append(" " + buffer.get(i));
    		result.add(sb.toString());
    		return;
    	}
    	for(int i=start; i<s.length(); ++i) {
    		String word = s.substring(start, i+1);
    		if(dict.contains(word)) {
    			buffer.add(word);
    			dfs1(s, i+1, dict, buffer, result);
    			buffer.remove(buffer.size()-1);
    		}
    	}
    }
    
    /*******************************方法2, DFS + DP剪枝 ****************************************/
    /*
     * 上面的方法超时了，所以我们需要剪枝，而剪枝的方法就是用
     * dp,记录下，[i,end] 是否可分，如果不可分就不递归了
     * 如果可分，就交给dfs继续递归
     * 因为我们每次处理完前面一部分子字符串的时候，我们想知道
     * 从下个字符开始，到结束，这部分到底是能不能被break的，
     * 所以我们需要一个boolean 数组来记录[i，end]的可分情况
     */
    public ArrayList<String> wordBreak2(String s, Set<String> dict) {
    	ArrayList<String> result = new ArrayList<String>();
    	ArrayList<String> buffer = new ArrayList<String>();
    	if(s == null || s.length() == 0)
    		return result;
    	if(dict == null || dict.isEmpty())
    		return result;
    	boolean[] breakable = initBreakable(s,dict);
    	
    	dfs2(s, 0, dict, breakable, buffer, result);
    	return result;
    }
    
    private void dfs2(String s, int start, Set<String> dict, boolean[] breakable,
    		ArrayList<String> buffer, ArrayList<String> result) {
    	if(start >= s.length()) {
    		StringBuffer sb = new StringBuffer();
    		sb.append(buffer.get(0));
    		for(int i=1; i<buffer.size(); ++i)
    			sb.append(" " + buffer.get(i));
    		result.add(sb.toString());
    		return;
    	}

    	// 就在这增加一个剪枝条件，其余的都没变
    	if(!breakable[start])
    		return;
    	for(int i=start; i<s.length(); ++i) {
    		String word = s.substring(start, i+1);
    		if(dict.contains(word)) {
//    			if(i==s.length()-1 || breakable[i+1]) { 最好不要把检测是否满足调用下一层的条件放到这一层
    				buffer.add(word);
    				dfs2(s, i+1, dict, breakable, buffer, result);
    				buffer.remove(buffer.size()-1);
//    			}
    		}
    	}
    }
    
    // represents that if s[i,end] is breakable
    private boolean[] initBreakable(String s, Set<String> dict) {
    	boolean[] breakable = new boolean[s.length()];
    	for(int i=0; i<s.length(); ++i)
    		breakable[i] = false;
    	int end = s.length() -1 ;
    	for(int i=end; i>= 0; --i) {
    		if(dict.contains(s.substring(i)))
    			breakable[i] = true;
    		else {
    			for(int j=end; j>i; --j) {
    				breakable[i] = breakable[j] && dict.contains(s.subSequence(i, j));
    				if(breakable[i])
    					break;
    			}
    		}
    	}
    	
    	return breakable;
    }

    /*******************************方法3, DFS + Trie  ****************************************/
    /**
     * 1. 先对真个字典构建前缀树，
     * 2. 然后dfs，如果当前trie node包含当前index上的char，那么分两种情况
     * 		2.1 如果被包含的这个node是一个单词的结尾，那么我们下一层的递归应该跳回到root
     * 		2.2 如果被包含的这个node不是一个单词的结尾，那么下一层的递归从被包含的这个点开始
     * 3. 如果当前trie node不包含当前index上的char，那么直接返回
     * @param s
     * @param dict
     * @return
     */
    public List<String> wordBreak(String s, List<String> dict){
    	TrieNode root = buildTrie(dict);
    	ArrayList<String> results = new ArrayList<String>(dict.size());
    	ArrayList<String> buffer = new ArrayList<String>(dict.size());
    	
    	dfs(s, 0, root, root, results, buffer);
    	
    	return results;
    }
    
    /**
     * 
     * @param s
     * @param start 表示需要处理的第一个index
     * @param root val为空字符串的root
     * @param parent 之前一层的node
     * @param results
     * @param buffer
     */
    private void dfs(String s, int start, 
    		TrieNode root, TrieNode parent,
    		ArrayList<String> results, ArrayList<String> buffer) {
    	
    	if(start >= s.length()) {
    		StringBuffer sb = new StringBuffer();
    		sb.append(buffer.get(0));
    		for(int i=1; i<buffer.size(); ++i)
    			sb.append(" " + buffer.get(i));
    		results.add(sb.toString());
    		return;
    	}
    	
    	int i = start;
    	TrieNode runner = parent;
		while (i < s.length() && runner.children.containsKey(s.charAt(i))) {
			TrieNode child = runner.children.get(s.charAt(i));
			
			// 如果child表示一个完整的word的话,那么下一层递归
			// 我们实际上是要重新开始找下一个单词，那下一层递归
			// 就不应该以child为parent了，而应该以root开始
			if (child.isEndOfWord) {
				buffer.add(child.value);
				dfs(s, i + 1, root, root, results, buffer);
				buffer.remove(buffer.size() - 1);
			} 
			
			runner = child;
			++i;
		}
    }
    
    private TrieNode buildTrie(List<String> dict) {
    	Trie trie = new Trie();
    	for (String word : dict) {
    		trie.insert(word);
    	}
    	
    	return trie.root;
    }
    
	public static void main(String[] args) {
		String s = "catsanddog";
		String[] strings = {"cat","cats","and","sand","dog"};
		WordBreakII w = new WordBreakII();
		List<String> result = w.wordBreak(s, Arrays.asList(strings));
		Util.printList(result);
    }
}
