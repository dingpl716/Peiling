package design;

//	Implement the following operations of a stack using queues.
//	
//	push(x) -- Push element x onto stack.
//	pop() -- Removes the element on top of the stack.
//	top() -- Get the top element.
//	empty() -- Return whether the stack is empty.
//	Notes:
//	You must use only standard operations of a queue -- which means only 
//	push to back, peek/pop from front, size, and is empty operations are valid.
//	Depending on your language, queue may not be supported natively. 
//	You may simulate a queue by using a list or deque (double-ended queue), 
//	as long as you use only standard operations of a queue.
//	You may assume that all operations are valid (for example, 
//	no pop or top operations will be called on an empty stack).

/**
 * 核心思想：
 * 每次push的时候都做以下操作：
 * 1. 把x加到queue的尾部
 * 2. 然后把queue之前的元素取出来，加到队尾，相当于是循坏移位
 * 3. 重复2的过程直到x到达队列头部了。
 * 4. 这样push的时间复杂度是O(n)
 * 
 * pop的时候，直接从队头取出元素即可。
 * @author peding
 *
 */
public class ImplementStackUsingQueues {

}
