package SortAnalysis;
import java.lang.management.ThreadMXBean;
import java.lang.management.ManagementFactory;
import java.util.*;
public class RuntimeTester extends SortingAlgorithms {

	static long[] a;
	static long[] b;
	static long[] c;
	static long[] d;
	static long[] e;
	static long elapsedTimeA = 0;
	static long elapsedTimeB = 0;
	static long elapsedTimeC = 0;
	static long elapsedTimeD = 0;
	static long elapsedTimeE = 0;
	private static final int TRIALS = 100000;
	private static final int TRIALS2 = 15;
	
	public static void main(String[] args) {
		/*
		long test[] = {1,2,5,4,3};
		insertionSort(test);
		mergeSort(test);
		heapSort(test);
		quickSort(test);
		quickSortRandomized(test);
		printArray(test);
		*/
		int counter = 1;
		for(int i = 0; i < TRIALS; i++) {
		fillArrays(counter);
		sortTimer();
		}
		System.out.println("Array Size");
		System.out.println(counter);
		printRuntimes(TRIALS);
		for(int i = 0; i < TRIALS2; i++) {
		fillArrays(counter);
		sortTimer();
		counter = counter * 2;
		}
		System.out.println("Array Size");
		System.out.println(counter);
		printRuntimes(TRIALS2 + TRIALS);
	}
	//array size n
	static void fillArrays(int n) {
		Random randy = new Random();
		long LOWER_RANGE = 0;
		long UPPER_RANGE = Long.MAX_VALUE;
		a = new long[n];
		b = new long[n];
		c = new long[n];
		d = new long[n];
		e = new long[n];
		for(int i = 0; i < n; i++) {
			a[i] = LOWER_RANGE + (long)(randy.nextDouble()*(UPPER_RANGE - LOWER_RANGE));
			b[i] = c[i] = d[i] = e[i] = a[i];
		}
	}
	
	static void sortTimer() {
		for(int i = 0; i < 5; i++) {
			ThreadMXBean bean = ManagementFactory.getThreadMXBean();
			if(i == 0) {
				long startTime = bean.getCurrentThreadCpuTime();
				insertionSort(a);
				long endTime = bean.getCurrentThreadCpuTime();
				elapsedTimeA = endTime - startTime + elapsedTimeA;			
			}
			else if(i == 1) {
				long startTime = bean.getCurrentThreadCpuTime();
				mergeSort(b);
				long endTime = bean.getCurrentThreadCpuTime();
				elapsedTimeB = endTime - startTime + elapsedTimeB;			
			}
			else if(i == 2) {
				long startTime = bean.getCurrentThreadCpuTime();
				heapSort(c);
				long endTime = bean.getCurrentThreadCpuTime();
				elapsedTimeC = endTime - startTime + elapsedTimeC;			
			}
			else if(i == 3) {
				long startTime = bean.getCurrentThreadCpuTime();
				quickSort(d);
				long endTime = bean.getCurrentThreadCpuTime();
				elapsedTimeD = endTime - startTime + elapsedTimeD;			
			}
			/* forgot windows OS may affect results, tried this to fix an issue
			else if(i == 4) {
				shuffleArray(e);
				long startTime = bean.getCurrentThreadCpuTime();
				quickSort(e);
				long endTime = bean.getCurrentThreadCpuTime();
				elapsedTimeE = endTime - startTime + elapsedTimeE;			
			}
			*/
			//original implementation, the shuffling counted in the sort time
			else if(i == 4) {
				long startTime = bean.getCurrentThreadCpuTime();
				quickSortRandomized(e);
				long endTime = bean.getCurrentThreadCpuTime();
				elapsedTimeE = endTime - startTime + elapsedTimeE;			
			}
			//*/
			else {
				System.out.println("error catch, i too large in test");
			}
		}
	}
	
	static void printRuntimes(int x) {
		//prints runtimes divided by x where x is number of trials
		//to print average runtime
		System.out.println("Insertion Sort avg runtime");
		System.out.println(elapsedTimeA/x);
		System.out.println("Merge Sort avg runtime");
		System.out.println(elapsedTimeB/x);
		System.out.println("Heap Sort avg runtime");
		System.out.println(elapsedTimeC/x);
		System.out.println("Quick Sort avg runtime");
		System.out.println(elapsedTimeD/x);
		System.out.println("Randomzied Quick Sort avg runtime");
		System.out.println(elapsedTimeE/x);
		System.out.println("");
		/*
		//zeros elapsedTime vars to prep for a new trial run
		//since this should only be run at the end of a trial run
		elapsedTimeA = 0;
		elapsedTimeB = 0;
		elapsedTimeC = 0;
		elapsedTimeD = 0;
		elapsedTimeE = 0;
		*/
	}
	
	static void printArray(long a[]) {
		int length = a.length;
		for(int i = 0; i < length; i++) {
			System.out.print(a[i] + " ");
		}
		System.out.println();
	}

	/*Given Hint on CPU Time
	 * ThreadMXBean bean = ManagementFactory.getThreadMXBean ();
		long starTime = bean.getCurrentThreadCpuTime();
		// a simple mindless loop
		long acc = 0;
		for(long i = 0; i < 10000000L ; i ++)
		acc += acc;
		long endTime = bean.getCurrentThreadCpuTime();
		long elapsedTime = endTime - starTime;
		System.out.printf("Elapsed time: %d nanosecs \n", + elapsedTime );
	 */
}
