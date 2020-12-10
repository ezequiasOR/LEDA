package sorting.divideAndConquer;

import java.util.Arrays;

import sorting.AbstractSorting;

/**
 * Merge sort is based on the divide-and-conquer paradigm. The algorithm
 * consists of recursively dividing the unsorted list in the middle, sorting
 * each sublist, and then merging them into one single sorted list. Notice that
 * if the list has length == 1, it is already sorted.
 */
public class MergeSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (leftIndex < rightIndex) {
			int meio = (leftIndex+rightIndex) / 2;
			
			this.sort(array, leftIndex, meio);
			this.sort(array, meio + 1, rightIndex);
			
			merge(array, leftIndex, meio, rightIndex);
		}
	}
	
	public void merge(T[] array, int leftIndex, int meio, int rightIndex) {
		T[] helper = Arrays.copyOf(array, array.length);
		
		int i = leftIndex;
		int j = meio + 1;
		int k = leftIndex;
		
		while (i <= meio && j <= rightIndex) {
			if (helper[i].compareTo(helper[j]) < 0) {
				array[k] = helper[i];
				i++;
			} else {
				array[k] = helper[j];
				j++;
			}
			k++;
		}
		
		while (i <= meio) {
			array[k] = helper[i];
			i++;
			k++;
		}
		
		while (j <= rightIndex) {
			array[k] = helper[j];
			j++;
			k++;
		}
	}
}
