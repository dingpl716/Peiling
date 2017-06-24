package definition;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class UniqueQueue<T> {

	private Set<T> set;
	private Queue<T> queue;
	
	public UniqueQueue(){
		set = new HashSet<T>();
		queue = new LinkedList<T>();
	}
	
	public int size() {
		return set.size();
	}

	public boolean isEmpty() {
		return set.isEmpty();
	}

	public boolean contains(Object o) {
		return set.contains(o);
	}

	public boolean add(T e) {
		if (!set.contains(e)) {
			set.add(e);
			queue.add(e);
			
			return true;
		}
		
		return false;
	}
	
	public T poll() {
		T tmp = queue.poll();
		set.remove(tmp);
		
		return tmp;
	}
	
	public T peek() {
		return queue.peek();
	}
	
	public Iterator<T> iterator() {
		return queue.iterator();
	}

	public <T> T[] toArray(T[] a) {
		return (T[]) queue.toArray();
	}

	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean addAll(Collection<? extends T> c) {
		if (c != null) {
			for (T t : c){
				if(!set.contains(t)){
					queue.add(t);
					set.add(t);
				}
			}
		}
		
		return true;
	}

	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	public void clear() {
		queue.clear();
		set.clear();
	}

	public boolean offer(T e) {
		// TODO Auto-generated method stub
		return false;
	}

	public T remove() {
		// TODO Auto-generated method stub
		return null;
	}
}
