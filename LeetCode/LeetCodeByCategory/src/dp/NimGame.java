package dp;

//	You are playing the following Nim Game with your friend: 
//	There is a heap of stones on the table, each time one of 
//	you take turns to remove 1 to 3 stones. The one who removes 
//	the last stone will be the winner. You will take the first 
//	turn to remove the stones.
//	
//	Both of you are very clever and have optimal strategies for 
//	the game. Write a function to determine whether you can win 
//	the game given the number of stones in the heap.
//	
//	For example, if there are 4 stones in the heap, then you will 
//	never win the game: no matter 1, 2, or 3 stones you remove, 
//	the last stone will always be removed by your friend.

/**
 * 赢的条件是，在我pick up完之后，只剩下1,2,3,4号石头，此时他必须pickup一个石头
 * 赢 = 最后留4个石头给他
 *   = 我必须要能pick up 第5号 -> 这个条件有三种情况，pick up 5， 或者5，6 或者5，6， 7
 *   = 他必须要 pick up第8号 -> 这种情况对应如果他pick up 8，那么我可以直接pickup 567， 如果他pickup 78，那么我可以pickup 6 5
 *   = 我必须要能pick up 第9号 -> 同样三种情况 [9] [9，10] [9,10,11]
 *   = 他必须要pick up第 12号 ->这种情况对应如果他pick up 12，那么我可以直接pickup 9,10,11， 如果他pickup 11,12，那么我可以pickup 9 10
 *   = 我必须要能pick up 第 13 号 等等一次类推
 *   
 *   所以最后赢的条件就是我必须要能pick up 第1,5,9,13,17,21....号
 *   这些点就是critical point，而对于每一个critical point 我其实都有三种情况能取到他
 *   设critical point 是 c, 那么我只要能pickup c, c到c+1, 或者c到c+2 我就一定能赢
 *   那么换言之，我输的critical point 就是 4,8,12,16,20
 *   所以最后答案很清楚了，只要n不是4的倍数我们就能赢。
 *  
 * @author Dingp
 *
 */
public class NimGame {
	
    public boolean canWinNim(int n) {
        return n%4 != 0;
    }
}
