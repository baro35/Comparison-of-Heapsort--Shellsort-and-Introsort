import java.util.Random;

public class Test {

	public static void main(String[] args) {
		long startTime = 0;
		long estimatedTime = 0;
		int n = 10000;
		int[] arrayToSort1 = new int[n];
		int[] arrayToSort2 = new int[n];
		int[] arrayToSort3 = new int[n];
		int j = 0;

//		// equal
//		for (int i = 0; i < n; i++) {
//			arrayToSort1[i] = 5;
//			arrayToSort2[i] = 5;
//			arrayToSort3[i] = 5;
//			j++;
//		}
		
		// random
		for (int i = 0; i < n; i++) {
			Random rnd = new Random();
			int a = rnd.nextInt(1000);
			arrayToSort1[i] = a;
			arrayToSort2[i] = a;
			arrayToSort3[i] = a;
		}

//		// increasing
//		j = 0;
//		for (int i = 0; i < n; i++) {
//			arrayToSort1[i] = j;
//			arrayToSort2[i] = j;
//			arrayToSort3[i] = j;
//			j++;
//		}

		
//		// decreasing
//		j = 0;
//		for (int i = n - 1; i >= 0; i--) {
//			arrayToSort1[i] = j;
//			arrayToSort2[i] = j;
//			arrayToSort3[i] = j;
//			j++;
//		}

		SortingClass[] sortObject = new SortingClass[3];
		sortObject[0] = new SortingClass(arrayToSort1);
		sortObject[1] = new SortingClass(arrayToSort2);
		sortObject[2] = new SortingClass(arrayToSort3);

		System.out.println("array size = " + n);
		startTime = System.nanoTime();
		sortObject[0].Heapsort();
		estimatedTime = System.nanoTime() - startTime;
		System.out.print(estimatedTime);
		System.out.println(" ns for HeapSort");

		startTime = System.nanoTime();
		sortObject[1].Shellsort();
		estimatedTime = System.nanoTime() - startTime;
		System.out.print(estimatedTime);
		System.out.println(" ns for ShellSort");

		startTime = System.nanoTime();
		sortObject[2].introSort();
		estimatedTime = System.nanoTime() - startTime;
		System.out.print(estimatedTime);
		System.out.println(" ns for IntroSort");

	}

}
