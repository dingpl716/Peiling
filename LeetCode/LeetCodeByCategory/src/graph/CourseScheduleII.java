package graph;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import definition.UniqueQueue;
import definition.UniqueStack;
import util.Util;

//	There are a total of n courses you have to take, labeled from 0 to n - 1.
//	
//	Some courses may have prerequisites, for example to take course 0 you have to first take course 1, 
//	which is expressed as a pair: [0,1]
//	
//	Given the total number of courses and a list of prerequisite pairs, 
//	return the ordering of courses you should take to finish all courses.
//	
//	There may be multiple correct orders, you just need to return one of them. 
//	If it is impossible to finish all courses, return an empty array.
//	
//	For example:
//	
//	2, [[1,0]]
//	There are a total of 2 courses to take. To take course 1 you should have finished course 0. 
//	So the correct course order is [0,1]
//	
//	4, [[1,0],[2,0],[3,1],[3,2]]
//	There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. 
//	Both courses 1 and 2 should be taken after you finished course 0. So one correct course order is [0,1,2,3].
//	Another correct ordering is[0,2,1,3].
//	
//	Note:
//	The input prerequisites is a graph represented by a list of edges, not adjacency matrices. 
//	Read more about how a graph is represented.
//	You may assume that there are no duplicate edges in the input prerequisites.

public class CourseScheduleII {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
    	Map<Integer, LinkedList<Integer>> table = initHashTable(prerequisites);
    	
    	// 两个结构来存储待完成的课程，但是queue里面的课程是stack里面的前置课程
    	UniqueQueue<Integer> coursesToFinishQueue = new UniqueQueue<Integer>();
    	UniqueStack<Integer> coursesToFinishStack = new UniqueStack<Integer>();
    	
    	// 用来做最后判断，亦可以用来做剪纸
    	UniqueQueue<Integer> coursesHaveBeenFinished = new UniqueQueue<Integer>();
    	
    	// 指向当前要处理的课程
    	int i = -1;
    	
    	while(coursesHaveBeenFinished.size() < numCourses) {
    		++i;
    		
    		coursesToFinishQueue.add(i);
    		
    		// 当还有前置课程要完成的时候
    		while (!coursesToFinishQueue.isEmpty()) {
    			
    			// 拿出这一轮需要处理的课程
    			int courseToFinish = coursesToFinishQueue.poll();
    			
    			// 剪枝
    			if (coursesHaveBeenFinished.contains(courseToFinish)){
    				continue;
    			}
    			
    			// 立即将这个课程加入到set里面
    			coursesToFinishStack.push(courseToFinish);
    			
    			// 取出它的前置课程
    			LinkedList<Integer> prereqs = table.get(courseToFinish);
    			
    			// 如果有前置课程的话
    			if (prereqs != null) {
    				for (Integer prereq : prereqs) {
    					if (coursesHaveBeenFinished.contains(prereq)){
    						continue;
    					}
    					
    					// 破坏了单向的前置关系，说明遇到环了
    					if (coursesToFinishStack.contains(prereq)) {
    						return new int[0];
    					}
    					coursesToFinishQueue.add(prereq);
    				}
    			}
    		}
    		
    		finishCourses(coursesHaveBeenFinished, coursesToFinishStack);
    		coursesToFinishStack.clear(); //注意这个地方一定要clear，不然程序会比较慢
    	}
    	
    	return toArray(coursesHaveBeenFinished);
    }
    
    private void finishCourses(UniqueQueue<Integer> coursesHaveBeenFinished, UniqueStack coursesToFinishStack){
    	if (coursesToFinishStack != null) {
    		while(!coursesToFinishStack.isEmpty()){
    			coursesHaveBeenFinished.add((int)coursesToFinishStack.pop());
    		}
    	}
    }
    
    private Map<Integer, LinkedList<Integer>> initHashTable(int[][] prerequisites) {
    	Map<Integer, LinkedList<Integer>> result = new HashMap<Integer, LinkedList<Integer>>(prerequisites.length);
    	
    	if (prerequisites != null){
    		for (int[] coursesPair : prerequisites){
    			int target = coursesPair[0];
    			int prereq = coursesPair[1];
    			
    			if (result.containsKey(target)){
    				LinkedList<Integer> prereqs = result.get(target);
    				prereqs.add(prereq);
    			}else {
    				LinkedList<Integer> prereqs = new LinkedList<Integer>();
    				prereqs.add(prereq);
    				result.put(target, prereqs);
    			}
    		}
    	}
    	
    	return result;
    }
    
    private int[] toArray(UniqueQueue<Integer> queue) {
    	if (queue == null){
    		return new int[0];
    	}
    	
    	int[] result = new int[queue.size()];
    	
    	for(int i = 0; i < result.length; ++i){
    		result[i] = queue.poll();
    	}
    	
    	return result;
    }
    
    public static void main(String[] args){
    	int[] prereq1 = new int[]{0, 1};
    	int[] prereq2 = new int[]{0, 2};
    	int[] prereq3 = new int[]{1, 2};
    	int[][] prerequisites = new int[][]{prereq1, prereq2, prereq3};
    	
//    	int[][] prerequisites = new int[][]{{1, 0}};
    	CourseScheduleII c = new CourseScheduleII();
    	int[] order = c.findOrder(2, prerequisites);
//    	Util.timmer((object) -> System.out.println(c.findOrder(3, prerequisites)));
    }
}
