import java.util.*;

public class QuickMergeSortAnalysis {

    // ----------- QUICK SORT ------------
    public static void quickSort(int arr[], int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    public static int partition(int arr[], int low, int high) {
        int pivot = arr[high];   // choosing last element as pivot
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    // ----------- MERGE SORT ------------
    public static void mergeSort(int arr[], int l, int r) {
        if (l < r) {
            int m = (l + r) / 2;

            mergeSort(arr, l, m);
            mergeSort(arr, m + 1, r);

            merge(arr, l, m, r);
        }
    }

    public static void merge(int arr[], int l, int m, int r) {

        int n1 = m - l + 1;
        int n2 = r - m;

        int L[] = new int[n1];
        int R[] = new int[n2];

        for (int i = 0; i < n1; i++)
            L[i] = arr[l + i];

        for (int j = 0; j < n2; j++)
            R[j] = arr[m + 1 + j];

        int i = 0, j = 0;
        int k = l;

        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    // ----------- MAIN METHOD ------------
    public static void main(String args[]) {

        int n = 1000;   // change size for observation
        int arr1[] = new int[n];
        int arr2[];

        Random rand = new Random();

        // -------- AVERAGE CASE (Random Array) --------
        for (int i = 0; i < n; i++)
            arr1[i] = rand.nextInt(10000);

        arr2 = arr1.clone();

        long start = System.nanoTime();
        quickSort(arr1, 0, n - 1);
        long end = System.nanoTime();
        System.out.println("Quick Sort Average Case Time: " + (end - start) + " ns");

        start = System.nanoTime();
        mergeSort(arr2, 0, n - 1);
        end = System.nanoTime();
        System.out.println("Merge Sort Average Case Time: " + (end - start) + " ns");


        // -------- BEST CASE (Already Sorted Array) --------
        Arrays.sort(arr1);
        arr2 = arr1.clone();

        start = System.nanoTime();
        quickSort(arr1, 0, n - 1);
        end = System.nanoTime();
        System.out.println("Quick Sort Best Case Time: " + (end - start) + " ns");

        start = System.nanoTime();
        mergeSort(arr2, 0, n - 1);
        end = System.nanoTime();
        System.out.println("Merge Sort Best Case Time: " + (end - start) + " ns");


        // -------- WORST CASE (Reverse Sorted Array) --------
        for (int i = 0; i < n; i++)
            arr1[i] = n - i;

        arr2 = arr1.clone();

        start = System.nanoTime();
        quickSort(arr1, 0, n - 1);
        end = System.nanoTime();
        System.out.println("Quick Sort Worst Case Time: " + (end - start) + " ns");

        start = System.nanoTime();
        mergeSort(arr2, 0, n - 1);
        end = System.nanoTime();
        System.out.println("Merge Sort Worst Case Time: " + (end - start) + " ns");
    }
}