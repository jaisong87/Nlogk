package Nlogk.algo;

import java.util.Arrays;

import java.util.Random;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import junit.framework.Assert;

public class BinarySearchTest {

	private static final int NUM_TESTS_FOR_BINARY_SEARCH = 13;
	private static final int MAX_ARRAY_SIZE_FOR_BINARY_SEARCH = 16384;
	private static final int NUM_SEARCHES_PER_ARRAY = 32;

	private static final int MAX_NUM_FOR_SQR_ROOT_TEST = 986789867;
	private static final int NUM_TESTS_FOR_SQR_ROOT = 29;

	private static final Logger LOG = Logger.getLogger(BinarySearchTest.class);

	public void testBinarySearchOnRandomArray(int arraySize) {
		Random random = new Random();
		int[] arr = new int[arraySize];
		for (int i =0; i < arraySize;i++) {
			arr[i] = random.nextInt();
		}
		Arrays.sort(arr);

		Integer[] arrayList = new Integer[arraySize];
		for (int i =0; i < arraySize;i++) {
			arrayList[i] = arr[i];
		}

		for (int s = 0; s < NUM_SEARCHES_PER_ARRAY; s++) {
			Integer key = arr[random.nextInt(arraySize)];
			int idx1 = Arrays.binarySearch(arr, key);
			int idx2 = BinarySearch.binarySearch(arrayList, key);
			Assert.assertEquals(idx1, idx2);
		}
	}
	
  @Test
  public void binarySearchTest() {
	  for (int t =0; t < NUM_TESTS_FOR_BINARY_SEARCH;t++) {
		  LOG.info("Running test#" + t);
		  Random r = new Random();
		  testBinarySearchOnRandomArray(r.nextInt(MAX_ARRAY_SIZE_FOR_BINARY_SEARCH - 1) + 1);
	  }
  }

  @Test
  public void testSqrRoot() {
	  Random r = new Random();
	  for (int i=0; i < NUM_TESTS_FOR_SQR_ROOT; i++) {
		  LOG.info("Running test#" + i);
		  int num = r.nextInt(MAX_NUM_FOR_SQR_ROOT_TEST);
		  int sqrRoot = BinarySearch.findSqrRoot(num, 0, num / 2);
		  int sqrRoot2 = (int)Math.sqrt(num);
		  Assert.assertEquals(Math.abs(sqrRoot - sqrRoot2) <= 1, true);
	  }
  }
}
