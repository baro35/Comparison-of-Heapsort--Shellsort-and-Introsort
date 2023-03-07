
public class SortingClass {
	public int[] arrayToSort;

	public SortingClass(int[] arrayToSort) {
		this.arrayToSort = arrayToSort;
	}

	// HeapSort
	public void Heapsort() {
		int n = arrayToSort.length;
		heapSort(0, n - 1);
	}

	// main function to do heapsort
	private void heapSort(int begin, int end) {
		int heapN = end - begin;

		// Build heap (rearrange array)
		this.buildHeap(begin, end, heapN);

		// One by one extract an element from heap
		for (int i = heapN; i >= 1; i--) {

			// Move current root to end
			swap(begin, begin + i);

			// call maxHeap() on the reduced heap
			heapify(i, begin);
		}
	}

	// Function to build the heap (rearranging the array)
	private void buildHeap(int begin, int end, int heapN) {
		for (int i = (heapN + 1) / 2 - 1; i >= 0; i--)
			heapify(heapN + 1, i);
	}

	// To heapify a subtree rooted with node i which is
	// an index in arr[]. n is size of heap
	void heapify(int n, int i) {
		int largest = i; // Initialize largest as root
		int l = 2 * i + 1; // left = 2*i + 1
		int r = 2 * i + 2; // right = 2*i + 2

		// If left child is larger than root
		if (l < n && arrayToSort[l] > arrayToSort[largest])
			largest = l;

		// If right child is larger than largest so far
		if (r < n && arrayToSort[r] > arrayToSort[largest])
			largest = r;

		// If largest is not root
		if (largest != i) {
			int swap = arrayToSort[i];
			arrayToSort[i] = arrayToSort[largest];
			arrayToSort[largest] = swap;

			// Recursively heapify the affected sub-tree
			heapify(n, largest);
		}
	}

	// ShellSort
	/* function to sort arr using shellSort */
	public int Shellsort() {
		int n = arrayToSort.length;

		// Start with a big gap, then reduce the gap
		for (int gap = n / 2; gap > 0; gap /= 2) {
			// Do a gapped insertion sort for this gap size.
			// The first gap elements a[0..gap-1] are already
			// in gapped order keep adding one more element
			// until the entire array is gap sorted
			for (int i = gap; i < n; i += 1) {
				// add a[i] to the elements that have been gap
				// sorted save a[i] in temp and make a hole at
				// position i
				int temp = arrayToSort[i];

				// shift earlier gap-sorted elements up until
				// the correct location for a[i] is found
				int j;
				for (j = i; j >= gap && arrayToSort[j - gap] > temp; j -= gap)
					arrayToSort[j] = arrayToSort[j - gap];

				// put temp (the original a[i]) in its correct
				// location
				arrayToSort[j] = temp;
			}
		}
		return 0;
	}

	// The utility function to swap two elements
	private void swap(int i, int j) {
		int temp = arrayToSort[i];
		arrayToSort[i] = arrayToSort[j];
		arrayToSort[j] = temp;
	}

	// Function for finding the median of the three elements
	private int findPivot(int a1, int b1, int c1) {
		int max = Math.max(Math.max(arrayToSort[a1], arrayToSort[b1]), arrayToSort[c1]);
		int min = Math.min(Math.min(arrayToSort[a1], arrayToSort[b1]), arrayToSort[c1]);
		int median = max ^ min ^ arrayToSort[a1] ^ arrayToSort[b1] ^ arrayToSort[c1];
		if (median == arrayToSort[a1])
			return a1;
		if (median == arrayToSort[b1])
			return b1;
		return c1;
	}

	// This function takes the last element as pivot, places
	// the pivot element at its correct position in sorted
	// array, and places all smaller (smaller than pivot)
	// to the left of the pivot
	// and greater elements to the right of the pivot
	private int partition(int low, int high) {

		// pivot
		int pivot = arrayToSort[high];

		// Index of smaller element
		int i = (low - 1);
		for (int j = low; j <= high - 1; j++) {

			// If the current element is smaller
			// than or equal to the pivot
			if (arrayToSort[j] <= pivot) {

				// increment index of smaller element
				i++;
				swap(i, j);
			}
		}
		swap(i + 1, high);
		return (i + 1);
	}

	// The main function that implements Introsort
	// low --> Starting index,
	// high --> Ending index,
	// depthLimit --> recursion level
	private void sortDataUtil(int begin, int end, int depthLimit) {
		if (end - begin > 0) { //n > 1
			if (depthLimit == 0) {

				// if the recursion limit is
				// occurred call heap sort
				this.heapSort(begin, end);
				return;
			} else {
				depthLimit = depthLimit - 1;
				int pivot = findPivot(begin, begin + ((end - begin) / 2) + 1, end);
				swap(pivot, end);

				// p is partitioning index,
				// arr[p] is now at right place
				int p = partition(begin, end);

				// Separately sort elements before
				// partition and after partition
				sortDataUtil(begin, p - 1, depthLimit);
				sortDataUtil(p + 1, end, depthLimit);
			}
		} 
		else { // base case -->  n <= 1
			return;
		}
	}

	// A utility function to begin the
	// Introsort module
	public void introSort() {
		// Initialise the depthLimit
		// as 2*log(length(data))
		int depthLimit = (int) (2 * Math.floor(Math.log(arrayToSort.length) / Math.log(2)));

		this.sortDataUtil(0, arrayToSort.length - 1, depthLimit);
	}

	/* A utility function to print array of size n */
	public void printArray() {
		int n = arrayToSort.length;
		for (int i = 0; i < n; ++i)
			System.out.print(arrayToSort[i] + " ");
		System.out.println();
	}

}
