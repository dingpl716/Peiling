import java.util.HashMap;
import java.util.Map;

import definition.RandomListNode;


//	A linked list is given such that each node contains an additional 
//	random pointer which could point to any node in the list or null.
//	
//	Return a deep copy of the list
//	
//	这道题和clone graph 是一样的
public class CopyListWithRandomPointer {
	
    public RandomListNode copyRandomList(RandomListNode head) {
    	if(head == null)
    		return head;
    	Map<RandomListNode, RandomListNode> copied = 
    			new HashMap<RandomListNode, RandomListNode>();
    	RandomListNode newHead = new RandomListNode(head.label);
    	copied.put(head, newHead);
    	
    	RandomListNode oldNode = head;
    	while(oldNode != null) {
    		RandomListNode newNode = copied.get(oldNode);
    		if(oldNode.next != null) {
    			if(copied.containsKey(oldNode.next))
    				newNode.next = copied.get(oldNode.next);
    			else{
    				RandomListNode newNext = new RandomListNode(oldNode.next.label);
    				newNode.next = newNext;
    				copied.put(oldNode.next, newNext);
    			}
    		}
    		if(oldNode.random != null) {
    			if(copied.containsKey(oldNode.random))
    				newNode.random = copied.get(oldNode.random);
    			else {
    				RandomListNode newRandom = new RandomListNode(oldNode.random.label);
    				newNode.random = newRandom;
    				copied.put(oldNode.random, newRandom);
    			}
    		}
    		
    		oldNode = oldNode.next;
    	}
    	return newHead;
    }
    
}
