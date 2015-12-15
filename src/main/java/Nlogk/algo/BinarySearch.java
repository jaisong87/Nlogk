package Nlogk.algo;

public class BinarySearch {

	public static final int INVALID_INDEX = -1;
	public static final int COMPLEX_INT = -1;

	/*
	 * Binary Search on sorted Array
	 */
	public static final <T extends Comparable<T>> int findFirstInSortedArray(T[] arr, T key, int low, int high) {
		if (low > high || low < 0 || high < 0) {
			return INVALID_INDEX;
		}

		int mid = (low + high) / 2;		
		if (arr[mid].compareTo(key) == 0) {
			if ( mid > low && arr[mid].equals(arr[mid-1])) {
				return findFirstInSortedArray(arr, key, low, mid);
			} else {
				return mid;
			}
		} else if (low == high ) {
			return INVALID_INDEX;
		} else if (arr[mid].compareTo(key) > 0) {
			return findFirstInSortedArray(arr, key, low, mid);
		} else {
			return findFirstInSortedArray(arr, key, mid + 1, high);
		}
	}	

	public static final <T extends Comparable<T>> int binarySearch(T[] arr, T key) {
		return findFirstInSortedArray(arr, key, 0, arr.length -1);
	}

	/*
	 * In general you can find Nth root using this
	 */
	public static final int findSqrRoot(int num, int low, int high) {
		int mid = (low + high) / 2;
		if ( num < 0) {
			return COMPLEX_INT;
		}

		long midSqr = (long) mid * (long) mid;

		if (num <= midSqr) {
			if (mid > low) {
				return findSqrRoot(num, low, mid);
			} else {
				return mid;
			}
		} else {
			if (high > mid) {
				return findSqrRoot(num, mid + 1, high);
			} else {
				return mid;
			}
		}		
	}
}
