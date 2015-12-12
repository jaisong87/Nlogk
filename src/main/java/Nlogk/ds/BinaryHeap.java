package Nlogk.ds;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;

import org.apache.log4j.Logger;

public class BinaryHeap<E extends Comparable<E>> implements PriorityQueue<E> {

	private static final Logger LOG = Logger.getLogger(BinaryHeap.class);
	private static final int DEFAULT_HEAP_CAPACITY = 128;
	// private T[] heapNodes;(Generics issue - forced to use ArrayList)
	private ArrayList<E> heapNodes;
	private int heapSize;
	private Comparator<? super E> priorityComparator;

	/*
	 * Constructors
	 */
	public BinaryHeap() {
		this(DEFAULT_HEAP_CAPACITY, null);
	}

	public BinaryHeap(int heapCapacity) {
		this(heapCapacity, null);
	}

	public BinaryHeap(Comparator<? super E> c) {
		this(DEFAULT_HEAP_CAPACITY, c);
	}

	public BinaryHeap(int heapCapacity, Comparator<? super E> c) {
		this.heapNodes = new ArrayList<E>(heapCapacity);
		this.heapSize = 0;
		this.priorityComparator = c;
	}

	/*
	 * PriorityQueue Methods
	 */
	public void addAll(Collection<? extends E> c) {
		for (E e : c) {
			this.insert(e);
		}
	}

	public void insert(E e) {
		heapNodes.add(heapSize, e);
		heapSize++;
		heapifyUp(heapSize - 1);
//		validateHeap();
	}

	public E pop() {
		if (heapSize > 0) {
			E topElement = heapNodes.get(0);
			E lastElement = heapNodes.get(heapSize - 1);
			heapNodes.set(0, lastElement);
			heapNodes.remove(heapSize - 1);
			heapSize--;
			heapifyDown(0);
			return topElement;
		} else {
			return null;
		}
	}

	public int size() {
		return heapSize;
	}

	/*
	 * Helper methods
	 */
	// heapify-Down
	private void heapifyDown(int pos) {
		if (pos >= heapSize) {
			return;
		}

		int leftChildIdx = 2 * pos + 1;
		int rightChildIdx = 2 * pos + 2;

		E curNode = heapNodes.get(pos);
		E leftChild = (leftChildIdx < heapSize) ? heapNodes.get(leftChildIdx) : null;
		E rightChild = (rightChildIdx < heapSize) ? heapNodes.get(rightChildIdx) : null;

		boolean isRightChildTopPriority = false;
		boolean isLeftChildTopPriority = false;

		if (priorityComparator != null) {
			if (rightChild != null && priorityComparator.compare(rightChild, leftChild) > 0
					&& priorityComparator.compare(rightChild, curNode) > 0) {
				isRightChildTopPriority = true;
			} else if (leftChild != null && priorityComparator.compare(leftChild, curNode) > 0) {
				isLeftChildTopPriority = true;
			}
		} else {
			if (rightChild != null && rightChild.compareTo(leftChild) > 0 && rightChild.compareTo(curNode) > 0) {
				isRightChildTopPriority = true;
			} else if (leftChild != null && leftChild.compareTo(curNode) > 0) {
				isLeftChildTopPriority = true;
			}
		}

		if (isRightChildTopPriority) {
			heapNodes.set(pos, rightChild);
			heapNodes.set(rightChildIdx, curNode);
			heapifyDown(rightChildIdx);
		} else if (isLeftChildTopPriority) {
			heapNodes.set(leftChildIdx, curNode);
			heapNodes.set(pos, leftChild);
			heapifyDown(leftChildIdx);
		}
	}

	// heapify-up
	private void heapifyUp(int pos) {
		int parentIdx = (int) Math.floor( (pos - 1) / 2.0);
		boolean heapifyComplete = false;
		while (!heapifyComplete && (pos > 0)) {
			E curNode = heapNodes.get(pos);
			E parentNode = heapNodes.get(parentIdx);
			if ((priorityComparator != null && priorityComparator.compare(curNode, parentNode) > 0)
					|| (priorityComparator == null && curNode.compareTo(parentNode) > 0)) {
				heapNodes.set(pos, parentNode);
				heapNodes.set(parentIdx, curNode);
			} else {
				heapifyComplete = true;
			}
			pos = parentIdx;
			parentIdx = (int) Math.floor( (pos - 1) / 2.0);
		}
	}

	public String toString() {
		return Arrays.toString(heapNodes.toArray());
	}

	/*
	 * A utility function used for debugging
	 */
	public boolean validateHeap() {
		for (int pos = 0; pos < heapSize; pos++) {
			E curNode = heapNodes.get(pos);
			int leftChildIdx = 2 * pos + 1;
			int rightChildIdx = 2 * pos + 2;

			if (leftChildIdx < heapSize) {
				E leftChild = heapNodes.get(leftChildIdx);
				if ((priorityComparator != null && priorityComparator.compare(leftChild, curNode) > 0)
						|| (priorityComparator == null && leftChild.compareTo(curNode) > 0)) {
					LOG.error("Priority broken for idx=" + pos + " and leftChildIdx=" + leftChildIdx
							+ " with values " + curNode + " and " + leftChild);
					return false;
				}
			}

			if (rightChildIdx < heapSize) {
				E rightChild = heapNodes.get(rightChildIdx);
				if ((priorityComparator != null && priorityComparator.compare(rightChild, curNode) > 0)
						|| (priorityComparator == null && rightChild.compareTo(curNode) > 0)) {
					LOG.error("Priority broken for idx=" + pos + " and rightChildIdx=" + rightChildIdx
							+ " with values " + curNode + " and " + rightChild);
					return false;
				}
			}
		}
		return true;
	}
}
