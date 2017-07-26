package definition;

import java.util.HashMap;
import java.util.Map;

public class TrieNode{
	
	public String value;
	
	public Map<Character, TrieNode> children;
	
	// 用来标示这个节点是不是某个单词的终点
	// 因为我们不能单纯的通过children是否为空，来判断是否是一个单词的终点
	public boolean isEndOfWord = false;
	
	public TrieNode(String value){
		this.value = value;
		this.children = new HashMap<Character, TrieNode>();
	}
}
