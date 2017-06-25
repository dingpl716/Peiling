package graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

//	Suppose you are at a party with n people (labeled from 0 to n - 1) and among them, 
//	there may exist one celebrity. The definition of a celebrity is that ALL the other 
//	n - 1 people know him/her but he/she does not know ANY of them.
//	
//	Now you want to find out who the celebrity is or verify that there is not one. 
//	The only thing you are allowed to do is to ask questions like: "Hi, A. Do you know B?" 
//	to get information of whether A knows B. You need to find out the celebrity (or verify 
//	there is not one) by asking as few questions as possible (in the asymptotic sense).
//	
//	You are given a helper function bool knows(a, b) which tells you whether A knows B. 
//	Implement a function int findCelebrity(n), your function should minimize the number of calls to knows.
//	
//	Note: There will be exactly one celebrity if he/she is in the party. 
//	Return the celebrity's label if there is a celebrity in the party. If there is no celebrity, return -1.

/**
 * 我的想法:
 * 这道题还是一个有向图的问题，而且其实就是要找到一个出度为0，入度为n-1的点。
 * 如果没有这样的点的话，就不存在celebrity。 所以如果我们有所有边的信息，则直接
 * 进行统计就好了。 要得到所有边的信息，就需要对这些人两两之间进行问话，那么问话的上限
 * 就是n^2. 而又因为名人的要求是，所有人都要知道他，那么我们至少需要进行n-1次问话，才
 * 可能确定一个人是否是名人。所以问话的下限是n-1次
 * 
 * 当 A knows B     的时候，A一定是群众, B有可能是名人
 * 当 A not knows B 的时候，B一定是群众, A有可能是名人。 
 * 
 * 我们用两个集合来存储群众People, 和名人候选人Candidate
 * 
 * 
 * 
 * 
 * @author Dingp
 *
 */

public class FindTheCelebrity {

	public int findCelebrity(int n){
		
		Queue<Integer> people = new LinkedList<Integer>();
		Queue<Integer> candidates = new LinkedList<Integer>();
		
		if (n < 2) {
			return n;
		}
		
		if (knows(0, 1)) {
			people.add(0);
			candidates.add(1);
		}
		else {
			people.add(1);
			candidates.add(0);
		}
		
		for (int i = 2; i < n; ++i) {
			if (!candidates.isEmpty()) {
				int candidate = candidates.peek();
				if (knows(i, candidate)){
					people.add(i);
					continue;
				}
				else {
					candidates.poll();
					people.add(candidate);
					candidates.add(i);
				}
			}
			else {
				
			}
		}
		
		if (candidates.size() == 1) {
			return candidates.peek();
		}
		else {
			return -1;
		}
	}
	
	private boolean knows(int a, int b){
		return false;
	}
}
