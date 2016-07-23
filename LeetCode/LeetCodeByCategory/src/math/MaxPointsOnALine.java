package math;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import definition.Point;

//Given n points on a 2D plane, find the maximum number 
//of points that lie on the same straight line.

//要做n^2次计算和比较
//对每一个点，计算其和右边所有点的斜率，找出斜率相同的且数目最多的点，记为k
//对这些所有k，找出最大值

// 注意：
//1.点点和点重复的情况，对于这种情况，记录下重复的个数，
//并且在找到最多的在一条直线上的点时，要加上这个重复的个数
//2.斜率为无穷大的情况

//已犯错误：
//1. 对于重复的点的情况，如果一个点和其他的点全部重复，那么应该直接返回这个重复的个数，而不应该在加上dup
//2. 如果有六个点重复（这个六里面包含了本轮的起始点），然后另外一个点不重复，
//那么返回的数应该是2（两个点再一条直线上）加上5（5个点和另外一个点重复，总共六个点）等于7
//3. 在2里面的情况下，dup不应该再参与取最大值的查询了
//4. 在计算double类型时，是有可能算出来-0.0 和 0.0的，所以为了避免错误，应该在当y值相等时，直接返回0
public class MaxPointsOnALine {

    public int maxPoints(Point[] points) {
        if(points == null || points.length == 0)
        	return 0;
        if(points.length == 1)
        	return 1;
        
        int result = 2;
        int[] ks = new int[points.length];
        for(int i=0; i<ks.length; ++i) {
        	ks[i] = maxNumberOnthisPoint(points, i);
        	result = result > ks[i] ? result : ks[i];
        }
        
        return result;
    }
    
	/*
	 * 对一个点，计算其和右边所有点的斜率，找出斜率相同的且数目最多的点，记为k
	 */
    private int maxNumberOnthisPoint(Point[] points, int i) {
    	// ks是 斜率->点的数目
    	Map<String, Integer> ks = new HashMap<String, Integer>();
    	for(int j=i+1; j<points.length; ++j) {
    		String k = calcK(points[i], points[j]);
    		Integer num = ks.get(k);
    		if(num == null)
    			ks.put(k, 2);
    		else
    			ks.put(k, num + 1);
    	}
    	
    	// 如果这个点和其他的所有点都重复，那么就直接返回这些重复的点的个数就行了
    	if(ks.size() == 1 && ks.keySet().contains("dup"))
    		return ks.get("dup");
    	
    	int result = 0;
    	for(Iterator<Map.Entry<String, Integer>> itr=ks.entrySet().iterator(); itr.hasNext();) {
    		Map.Entry<String, Integer> e = itr.next();
    		if(!e.getKey().equals("dup") && e.getValue() > result)
    			result = e.getValue();
    	}
    	
    	Integer dup = ks.get("dup");
    	return dup == null ? result : result + dup - 1;
    }
    
    private String calcK(Point p1, Point p2) {
    	if(p1.x == p2.x) {
    		if(p1.y == p2.y)
    			return "dup";
    		else
    			return "infi";
    	}
    	
    	if(p1.y == p2.y)
    		return "0";
    		
    	Double k = (double)(p1.y - p2.y)/(p1.x - p2.x);
    	return k.toString();
    }
}
