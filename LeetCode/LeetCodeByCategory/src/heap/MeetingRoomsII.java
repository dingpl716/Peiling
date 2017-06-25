package heap;

//	Given an array of meeting time intervals consisting of 
//	start and end times [[s1,e1],[s2,e2],...] (si < ei), 
//	find the minimum number of conference rooms required.
//	
//	For example,
//	Given [[0, 30],[5, 10],[15, 20]],
//	return 2.

/**
 * 解法1：heap
 * 1. 先把intervals按start time 排序
 * 2. 用一个最小堆来存储interval，这个最小堆就代表会议进行的情况，
 *    堆顶元素代表最早结束的会议，也就是最早可以被重复使用的会议室
 * 3. 扫描每一个interval，如果这个interval的start time大于堆顶元素的end time,
 *    那么用这个interval取代堆顶元素,即重复使用会议室，不另开新室
 * 4. 如果这个interval的start time小于堆顶元素的end time，则加入堆中
 *    即另开新室
 * 5. 最后返回堆   
 * 
 * 解法2：线性解法
 * 大体思路：
 * 1. 先把interval按start time 排序
 * 2. 用i来代表时间轴，比如以上面例子为例，i需要从0到30进行扫描
 * 3. 如果i代表的会议开始时间，则会议室+1，否则会议室-1
 * 4. 在进行加减的时候，记下所需最大会议室
 * 
 * i	currentMeetingRooms	MaxMeetingRooms
 * 0	1					1
 * 5	2					2
 * 10	1					2
 * 15	2					2
 * 20	1					2
 * 30	0					2
 * 
 * @author Dingp
 *
 */
public class MeetingRoomsII {

}
