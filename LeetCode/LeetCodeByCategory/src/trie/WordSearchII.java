package trie;

import java.util.LinkedList;
import java.util.List;

import definition.TrieNode;

//	Given a 2D board and a list of words from the dictionary, find all words in the board.
//	
//	Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" 
//	cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.
//	
//	For example,
//	Given words = ["oath","pea","eat","rain"] and board =
//	
//	[
//	  ['o','a','a','n'],
//	  ['e','t','a','e'],
//	  ['i','h','k','r'],
//	  ['i','f','l','v']
//	]
//	Return ["eat","oath"].

/**
 * 核心思想：前缀树+dfs
 * 1. 首先对整个dictionary里面的单词构建前缀树
 * 2. 然后以DFS的方式遍历board和这个前缀树：
 * 	2.1 如果当前点还没有访问过，那么访问之，并且试图访问四周的点
 * 	2.2 访问四周的点的同时要看前缀树当前节点的子节点里是否有那个char，如果没有就不必往这条DFS路径上走了
 * 	2.2 如果当前点已经被访问过了，那么直接返回。
 *  
 * @author peding
 *
 */
public class WordSearchII {

    public List<String> findWords(char[][] board, String[] words) {
        TrieNode root = buildTrie(words);
        List<String> results = new LinkedList<String>();
        
        if (board == null || board.length == 0) {
        	return results;
        }
        
        for (int i = 0; i < board.length; ++i){
        	for (int j = 0; j < board[0].length; ++j) {
        		dfs(board, i, j, root, results);
        	}
        }
        
        return results;
    }
    
    /**
     * 
     * @param board
     * @param x 纵坐标
     * @param y 横坐标
     * @param previousNode 上一层的前缀树节点，这个节点的子节点才是和x,y对应的字符.
     * @param results
     */
    private void dfs(char[][] board,int x, int y, TrieNode previousNode, List<String> results) {
    	char currentChar = board[x][y]; 
    	if (currentChar == '.') {
    		return;
    	}
    	
    	if (!previousNode.children.containsKey(currentChar)) {
    		return;
    	}
    	
    	TrieNode currentNode = previousNode.children.get(currentChar);
    	if (currentNode.isEndOfWord) {
    		results.add(currentNode.value);
    	}
    	board[x][y] = '.';
    	
    	if (x > 0) {
    		dfs(board, x - 1, y, currentNode, results);
    	}
    	
    	if (x < board.length - 1) {
    		dfs(board, x + 1, y, currentNode, results);
    	}
    	
    	if (y > 0) {
    		dfs(board, x, y - 1, currentNode, results);
    	}
    	
    	if (y < board[0].length - 1) {
    		dfs(board, x, y + 1, currentNode, results);
    	}
    	
    	board[x][y] = currentChar;
    }
    
    private TrieNode buildTrie(String[] words) {
    	Trie trie = new Trie();
    	for (String word : words) {
    		trie.insert(word);
    	}
    	
    	return trie.root;
    }
}
