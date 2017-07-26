package trie;

import java.util.HashMap;
import java.util.Map;

import definition.TrieNode;

//	Implement a trie with insert, search, and startsWith methods.
//	
//	Note:
//	You may assume that all inputs are consist of lowercase letters a-z.

public class Trie {

//	private static class TrieNode{
//	
//		public String value;
//		
//		public Map<Character, TrieNode> children;
//		
//		// 用来标示这个节点是不是某个单词的终点
//		// 因为我们不能单纯的通过children是否为空，来判断是否是一个单词的终点
//		public boolean isEndOfWord = false;
//		
//		public TrieNode(String value){
//			this.value = value;
//			this.children = new HashMap<Character, TrieNode>();
//		}
//	}
    
	TrieNode root = null;
	
	/** Initialize your data structure here. */
    public Trie() {
        root = new TrieNode("");
        root.isEndOfWord = true;
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        if (word == null || word.length() == 0){
        	return;
        }
        
        insert(root, word, 0);
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        return search(root, word, 0);
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        return startsWith(root, prefix, 0);
    }
    
    private void insert(TrieNode root, String word, int index){
    	if (index == word.length()) {
    		root.isEndOfWord = true;
    		return;
    	}
    	
    	char c = word.charAt(index);
    	if (root.children.containsKey(c)) {
    		insert(root.children.get(c), word, index + 1);
    	}
    	else {
    		TrieNode child = new TrieNode(root.value + c);
    		root.children.put(c, child);
    		insert(child, word, index + 1);
    	}
    }

    /**
     * 我们每一轮都search这个root有没有哪个child是index所指向的字符
     * @param root
     * @param word
     * @param index 指向这一轮要检查的字符
     * @return
     */
	private boolean search(TrieNode root, String word, int index) {
		
		// 说明此时word已经扫描完了
		if (index == word.length()) {
			// 此时如果root是正好也是一个终点的话，就说明这条路径刚好是一个单词，否则只是一个前缀
			return root.isEndOfWord;
		}
		
		char c = word.charAt(index);
		if (!root.children.containsKey(c)) {
			return false;
		} else {
			return search(root.children.get(c), word, index + 1);
		}
	}
	
    /**
     * 我们每一轮都search这个root有没有哪个child是index所指向的字符
     * @param root
     * @param word
     * @param index 指向这一轮要检查的字符
     * @return
     */
	private boolean startsWith(TrieNode root, String word, int index) {
		
		// 说明此时word已经扫描完了
		if (index == word.length()) {
			// 因为是starts with，所以只要扫描完word就行，不需要root是一定时一个叶子节点
			return true;
		}
		
		char c = word.charAt(index);
		if (!root.children.containsKey(c)) {
			return false;
		} else {
			return startsWith(root.children.get(c), word, index + 1);
		}
	}
	
	public static void main(String[] args){
		Trie t = new Trie();
		
		t.insert("Trie");
		t.insert("search");
		
		System.out.println(t.search(""));
		System.out.println(t.startsWith("a"));
	}
}