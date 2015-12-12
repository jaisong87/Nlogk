package Nlogk;

import java.util.ArrayList;

public class BinaryHeap<T extends Comparable<T>> {

	private static final int DEFAULT_HEAP_CAPACITY = 128;
	// private T[] heapNodes;(Generics issue - forced to use ArrayList)
	private ArrayList<T> heapNodes;
	private int heapSize;

	public BinaryHeap() {
		this(DEFAULT_HEAP_CAPACITY);
	}

	public BinaryHeap(int heapCapacity) {
		heapNodes = new ArrayList<T>(heapCapacity);
		heapSize = 0;
	}

	public void insert(T e) {
		heapNodes.add(heapSize, e);
		heapifyUp(heapSize);
		heapSize++;
	}

	public T pop() {
		if (heapSize > 0) {
			T topElement = heapNodes.get(0);
			T lastElement = heapNodes.get(heapSize - 1);
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

	// heapify-Down
	private void heapifyDown(int pos) {
		if (pos >= heapSize) {
			return;
		}

		int leftChildIdx = 2 * pos + 1;
		int rightChildIdx = 2 * pos + 2;

		T curNode = heapNodes.get(pos);
		T leftChild = (leftChildIdx < heapSize) ? heapNodes.get(leftChildIdx) : null;
		T rightChild = (rightChildIdx < heapSize) ? heapNodes.get(rightChildIdx) : null;

		if (rightChild != null && rightChild.compareTo(leftChild) > 0 && rightChild.compareTo(curNode) > 0) {
			heapNodes.set(pos, rightChild);
			heapNodes.set(rightChildIdx, curNode);
			heapifyDown(rightChildIdx);
		} else if (leftChild != null && leftChild.compareTo(curNode) > 0) {
			heapNodes.set(leftChildIdx, curNode);
			heapNodes.set(pos, leftChild);
			heapifyDown(leftChildIdx);
		}
	}

	// heapify-up
	private void heapifyUp(int pos) {
		int parentIdx = (int) Math.floor(pos / 2.0);
		boolean heapifyComplete = false;
		while (!heapifyComplete && (parentIdx < pos)) {
			T curNode = heapNodes.get(pos);
			T parentNode = heapNodes.get(parentIdx);
			if (curNode.compareTo(parentNode) > 0) {
				heapNodes.set(pos, parentNode);
				heapNodes.set(parentIdx, curNode);
			} else {
				heapifyComplete = true;
			}
			pos = parentIdx;
			parentIdx = (int) Math.floor(pos / 2.0);
		}
	}

	public void print() {
		for (T node : heapNodes) {
			System.out.print(node + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		BinaryHeap<Integer> bh = new BinaryHeap<Integer>();

		for (int i = 10; i > 0; i--) {
			bh.insert(i);
		}

		bh.print();
		while (bh.size() > 0) {
			Integer top = bh.pop();
			System.out.println("Top element is : " + top + " , size = " + bh.size());
			bh.print();
		}
	}
}
