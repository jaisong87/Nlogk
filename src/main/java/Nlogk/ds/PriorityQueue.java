package Nlogk.ds;

import java.util.Collection;

public interface PriorityQueue<E extends Comparable<E>> {

	public void addAll(Collection<? extends E> c);
	public void insert(E e);
	public E pop();
	public int size();
}
