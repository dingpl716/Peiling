package string_array;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

//	A frog is crossing a river. The river is divided into x units and at each unit there may or may not exist a stone. 
//  The frog can jump on a stone, but it must not jump into the water.
//	
//	Given a list of stones' positions (in units) in sorted ascending order, determine if the frog 
//  is able to cross the river by landing on the last stone. Initially, the frog is on the first 
//  stone and assume the first jump must be 1 unit.
//	
//	If the frog's last jump was k units, then its next jump must be either k - 1, k, or k + 1 units. Note that the frog can only jump in the forward direction.
//	
//	Note:
//	
//	The number of stones is ≥ 2 and is < 1,100.
//	Each stone's position will be a non-negative integer < 2^31.
//	The first stone's position is always 0.
//	Example 1:
//	
//	[0,1,3,5,6,8,12,17]
//	
//	There are a total of 8 stones.
//	The first stone at the 0th unit, second stone at the 1st unit,
//	third stone at the 3rd unit, and so on...
//	The last stone at the 17th unit.
//	
//	Return true. The frog can jump to the last stone by jumping 
//	1 unit to the 2nd stone, then 2 units to the 3rd stone, then 
//	2 units to the 4th stone, then 3 units to the 6th stone, 
//	4 units to the 7th stone, and 5 units to the 8th stone.
//	Example 2:
//	
//	[0,1,2,3,4,8,9,11]
//	
//	Return false. There is no way to jump to the last stone as 
//	the gap between the 5th and 6th stone is too large.

/**
 * 两点需要考虑：
 * 1. 能跳到哪些石头，不能只记录最远能到达的那个。
 * 2. 当跳到某一个石头上的时候，跳了几步。这个步数是一个集合，因为可以从不同的地方跳过来。
 * 在例子2里面，跳到石头4的方法就有两种，分别是从2，和3上跳过来去
 * 从2上跳过去，就跳了2步，那么之后从4跳走的时候可以跳，2-1， 2 或者 2+1 步
 * 从3上跳过去，就跳了1步，那么之后从4跳走的时候可以跳，1-1， 1 或者1+1 步
 * 
 * 所以我需要一个HashSet[]来记录以上情况，每一个hashset对应跳到该石头上的可能跳数。
 * 然后还需要一个队列来存储，目前我可以跳到的石头。每次从这个队列中取出一个石头来，
 * 然后试图跳跃，然后把这些能跳到的点加到队列里面去。 但其实这里我们并不真正需要一个
 * Queue，因为1)并不需要严格的先进先出; 2)需要平凡的更改着队列中的元素情况.所以用
 * 一个HashSet来取代。
 * @author Dingp
 *
 */
public class JumpForg {

	private static class Jump{
		// 能跳到的石头，这个不是stones的index，是stones里面的值。
		int stone;
		// 跳到这个石头时用了多少步
		int lastStep;
		
		Jump(int stone, int lastStep){
			this.stone = stone;
			this.lastStep = lastStep;
		}
	}
	
    public boolean canCross(int[] stones) {
    	
    	// 因为题目要求第一次跳，只能跳一步。
    	if (stones[1] > 1){
    		return false;
    	}
    	
    	// 为了达到更快的查询速度，我们用一个set来储存stones的值
    	HashSet<Integer> stonesSet = new HashSet<Integer>();
    	for(int stone : stones){
    		stonesSet.add(stone);
    	}
    	
    	// 我们现在需要处理的石头，这个的值是stones的index
		Stack<Jump> queue = new Stack<Jump>();
		
		// 初始化
		// 表示现在需要处理stone1,并且只能从0跳1步到stone1
		queue.add(new Jump(1, 1));
		
		while(!queue.isEmpty()){
			Jump start = queue.pop();
			
			if (start.stone == stones[stones.length - 1]){
				return true;
			}
			
			for (int i = -1; i <= 1; ++i){
				if (start.lastStep + i != 0){
					Jump destination = new Jump(start.stone + start.lastStep + i, start.lastStep + i);
					
					if (stonesSet.contains(destination.stone)){
						queue.push(destination);
					}
				}
			}
		}
		
		return false;
    }
}
