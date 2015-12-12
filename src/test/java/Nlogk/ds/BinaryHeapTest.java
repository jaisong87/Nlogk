package Nlogk.ds;

import java.util.ArrayList;

import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

import org.testng.annotations.Test;

import junit.framework.Assert;
import org.apache.log4j.Logger;

public class BinaryHeapTest {

	private static final Logger LOG = Logger.getLogger(BinaryHeapTest.class);
	private static final Comparator<Integer> minHeapComparator = new Comparator<Integer>() {

		@Override
		public int compare(Integer o1, Integer o2) {
			return -1*o1.compareTo(o2);
		}
	};

	private static final int MAX_HEAP_SIZE = 16384;
	private static final int NUM_TEST_ATTEMPTS = 16;

	private ArrayList<Integer> getRandomIntegerList(int size) {
		ArrayList<Integer> arrayList1 = new ArrayList<Integer>(size);
		Random rand = new Random();
		for (int i=0; i < size; i++) {
			arrayList1.add(rand.nextInt());
		}
		return arrayList1;
	}	

	public void testMinHeap(int heapSize) {
		LOG.info("Testing binaryHeap of size " + heapSize);
		ArrayList<Integer> integerList = getRandomIntegerList(heapSize);
		BinaryHeap<Integer> minHeap = new BinaryHeap<Integer>(minHeapComparator);
		minHeap.addAll(integerList);
		minHeap.validateHeap();

		Collections.sort(integerList);
		
		for (Integer i : integerList) {
			Integer topPriorityElement = minHeap.pop();
			Assert.assertEquals(i, topPriorityElement);
		}
	}

	@Test
	public void testBinaryMinHeap() {
		Random randomForHeapSize = new Random();

		for (int i=0;i<NUM_TEST_ATTEMPTS;i++) {
			LOG.info("TestAttemp# " + i);
		    int heapSize = randomForHeapSize.nextInt(MAX_HEAP_SIZE + 1);
		    testMinHeap(heapSize);
		}
	}
}
