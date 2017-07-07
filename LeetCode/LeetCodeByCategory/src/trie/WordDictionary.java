package trie;

//	Design a data structure that supports the following two operations:
//	
//	void addWord(word)
//	bool search(word)
//	search(word) can search a literal word or a regular expression string 
//	containing only letters a-z or .. A . means it can represent any one letter.
//	
//	For example:
//	
//	addWord("bad")
//	addWord("dad")
//	addWord("mad")
//	search("pad") -> false
//	search("bad") -> true
//	search(".ad") -> true
//	search("b..") -> true
//	Note:
//	You may assume that all words are consist of lowercase letters a-z.

/**
 * 核心思想：前缀树
 * 
 * 1. addword的时候构建前缀树
 * 2. 搜索普通单词的时候，需要做以下事情：
 * 	2.1 遍历这个单词的每一个char
 * 	3.3 如果当前char不为'.'，则找出这一层中子树为char的那一个，然后递归
 * 	2.2 如果当前char为'.'，则遍历这一层中所有子树并且在便利当中递归
 * @author peding
 *
 */
public class WordDictionary {
	
    /** Initialize your data structure here. */
    public WordDictionary() {
        
    }
    
    /** Adds a word into the data structure. */
    public void addWord(String word) {
        
    }
    
    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return false;
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */