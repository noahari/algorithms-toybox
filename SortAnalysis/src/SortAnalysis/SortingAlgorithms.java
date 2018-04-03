package SortAnalysis;
import java.util.*;
public class SortingAlgorithms {

	static void insertionSort(long[] a) {
		int length = a.length;
		for(int i = 1; i < length; i++) {
			long key = a[i];
			int j = i - 1;
			while((j >= 0) && (a[j] > key)) {
				a[j + 1] = a[j];
				j = j - 1;
			}
			a[j + 1] = key; 
		}
	}

	static void mergeSort(long[] a) {
		mergeSort(a, 0, a.length-1);
	}
	static void mergeSort(long[] a, int p, int l) {
		if(p < l) {
			int q = (int) Math.ceil((p + l - 1) / 2);
			mergeSort(a, p, q);
			mergeSort(a, q + 1, l);
			merge(a, p, q, l);
		}
	}

	static void merge(long[] a, int p, int q, int r) {
		//int p, q, and r are used to measure indices of 
		//the two sub arrays of a being merged
		int n1 = q - p + 1;
		int n2 = r - q;
		//left and right sub arrays with their respective lengths n1&n2
		long L[] = new long[n1];
		long R[] = new long[n2];
		//actually fill these sub arrays with necessary vals
		for(int i = 0; i < n1; i++) {
			L[i] = a[p + i];
		}
		for(int j = 0; j < n2; j++) {
			R[j] = a[q + j + 1];
		}
		//place a sentinel here if necessary
		int i = 0;
		int j = 0;
		int k = p;
		while(i < n1 && j < n2) {
			if(L[i] <= R[j]){
				a[k] = L[i];
				i++;
			}
			else {
				a[k] = R[j];
				j++;
			}
			k++;
		}
		while(i < n1) {
			a[k] = L[i];
			i++;
			k++;
		}
		while(j < n2) {
			a[k] = R[j];
			j++;
			k++;
		}
	}

	//I originally wrote the code based off of in class pseudo-code,
	//but according to the instructions the order must be non-decreasing,
	//therefore I needed to convert it to a min-heap based sort
	static void heapSort(long[] a) {
		buildMinHeap(a);
		for(int i = a.length - 1; i >= 0; i--) {
			long temp = a[0];
			a[0] = a[i];
			a[i] = temp;
			minHeapify(a, 0);
		}
	}
	
	static void minHeapify(long[] a, int i) {
		int smallest = i;
		int leftChild = 2*i;
		int rightChild = (2*i) + 1;
		if((leftChild < a.length) && (a[leftChild] < a[smallest])) {
			smallest = leftChild;
		}
		if((rightChild < a.length) && (a[rightChild] < a[smallest])) {
			smallest = rightChild;
		}
		if(smallest != i) {
			long temp = a[i];
			a[i] = a[smallest];
			a[smallest] = temp;
			minHeapify(a, smallest);
		}
	}
	
	static void buildMinHeap (long[] a) {
		for(int i = (int) Math.floor(a.length/2) - 1; i >= 0; i--) {
			minHeapify(a, i);
		}
	}

	static void quickSort(long[] a) {
		quickSort(a, 0, a.length - 1);
	}

	static void quickSort(long[] a, int p, int r) {
		if(p < r) {
			int q = partition(a, p, r);
			quickSort(a, p, q - 1);
			quickSort(a, q + 1, r);
		}
	}
	
	static int partition(long[] a, int p, int r) {
		long x = a[r];
		int i = p - 1;
		for(int j = p; j < r; j++) {
			if(a[j] <= x) {
				i++;
				long temp = a[i];
				a[i] = a[j];
				a[j] = temp;
			}
		}
		long temp = a[r];
		a[r] = a[i + 1];
		a[i + 1] = temp;
		return i + 1;
	}
	
	static void quickSortRandomized(long[] a) {
		shuffleArray(a);
		quickSort(a, 0, a.length - 1);
	}
	
	//When finding if there was an already implemented java.util
	//function for array randomization I learned about the Fisher-Yates
	//shuffle which they had implemented for Collections.shuffle for
	//ArrayLists so I basically just retyped that re-tooled for Arrays
	//of Longs and to remember this technique when coding later things
	static void shuffleArray(long[] a)
	{
	    int index;
	    long temp;
	    Random random = new Random();
	    for (int i = a.length - 1; i > 0; i--)
	    {
	        index = random.nextInt(i + 1);
	        temp = a[index];
	        a[index] = a[i];
	        a[i] = temp;
	    }
	}
}
