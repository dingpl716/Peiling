package list;
import java.util.ArrayList;
import java.util.Comparator;

import definition.ListNode;

//Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
public class MergeKSortedLists {
	public static class ArrayMinHeap<T> {
		public Comparator<T> comparator;
		public ArrayMinHeap (Comparator<T> comp) {
			comparator = comp;
		}
		
		private ArrayList<T> data = new ArrayList<T>();
		
		public void add(T t) {
			data.add(t);
			bottomUpMaintain(data.size()-1);
		}
		
		public T peek() {
			if(data.size() > 0)
				return data.get(0);
			else
				return null;
		}
		
		public T poll() {
			if(data.size() > 0) {
				T t = data.get(0);
				data.set(0, data.get(data.size()-1));
				data.remove(data.size()-1);
				upBottomMaintain(0);
				return t;
			}else
				return null;
		}
		
		public int size() {
			return data.size();
		}
		
		private void bottomUpMaintain(int child) {
			if(child == 0)
				return;
			int father = (child - 1)/2;
			if(comparator.compare(data.get(father), data.get(child)) > 0 ) {
				swap(father, child);
				bottomUpMaintain(father);
			}
		}
		
		private void upBottomMaintain(int father) {
			if(father > data.size() / 2 - 1)
				return;
			int maxIndex = findMin(father);
			
			if(maxIndex != father) {
				swap(father, maxIndex);
				upBottomMaintain(maxIndex);
			}
		}
		
		private int findMin(int father) {
			int right = father * 2 + 2;
			int left = father * 2 + 1;
			int result = father;
			if(comparator.compare(data.get(left), data.get(father)) < 0)
				result = left;
			if(right > data.size()-1)
				return result;
			else{
				if(comparator.compare(data.get(right), data.get(result)) < 0 )
					return right;
				else
					return result;
			}
		}
		
		private void swap(int i1, int i2) {
			T tmp = data.get(i1);
			data.set(i1, data.get(i2));
			data.set(i2, tmp);
		}
	}
	
	public class ListNodeComparator<T> implements Comparator<T> {
		@Override
		public int compare(T o1, T o2) {
			ListNode l1 = (ListNode) o1;
			ListNode l2 = (ListNode) o2;
			if(l1.val == l2.val)
				return 0;
			else if(l1.val < l2.val)
				return -1;
			else
				return 1;
		}
	}
	
	
	public ListNode mergeKLists(ArrayList<ListNode> lists) {
		if(lists == null || lists.size() == 0)
			return null;
		ListNodeComparator<ListNode> comp = new ListNodeComparator<ListNode>();
		ArrayMinHeap<ListNode> heap = new ArrayMinHeap<ListNode>(comp);
		
		ListNode head = new ListNode(Integer.MIN_VALUE);
		ListNode current = head;
		for(ListNode list : lists) {
			if(list != null)
				heap.add(list);
		}
		while(heap.size() != 0) {
			ListNode min = heap.poll();
			if(min.next != null)
				heap.add(min.next);
			current.next = min;
			min.next = null;
			current = current.next;
		}
		
		return head.next;
    }
	
}
