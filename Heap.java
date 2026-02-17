import java.util.*;

class Heap {
    int[] heap;
    int size;
    int capacity;
    boolean isMinHeap;

    public Heap(int capacity, boolean isMinHeap) {
        this.capacity = capacity;
        this.size = 0;
        this.isMinHeap = isMinHeap;
        heap = new int[capacity];
    }

    // Insert element
    public void insert(int value) {
        if (size == capacity) {
            System.out.println("Heap is full");
            return;
        }

        heap[size] = value;
        int current = size;
        size++;

        // Heapify Up
        while (current > 0 && compare(heap[current], heap[parent(current)])) {
            swap(current, parent(current));
            current = parent(current);
        }
    }

    // Delete root element
    public void deleteRoot() {
        if (size == 0) {
            System.out.println("Heap is empty");
            return;
        }

        heap[0] = heap[size - 1];
        size--;
        heapify(0);
    }

    // Heapify Down
    private void heapify(int i) {
        int left = leftChild(i);
        int right = rightChild(i);
        int extreme = i;

        if (left < size && compare(heap[left], heap[extreme]))
            extreme = left;

        if (right < size && compare(heap[right], heap[extreme]))
            extreme = right;

        if (extreme != i) {
            swap(i, extreme);
            heapify(extreme);
        }
    }

    // Compare for Min or Max heap
    private boolean compare(int child, int parent) {
        if (isMinHeap)
            return child < parent;   // Min Heap
        else
            return child > parent;   // Max Heap
    }

    private int parent(int i) { return (i - 1) / 2; }
    private int leftChild(int i) { return 2 * i + 1; }
    private int rightChild(int i) { return 2 * i + 2; }

    private void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    // Display heap
    public void display() {
        for (int i = 0; i < size; i++)
            System.out.print(heap[i] + " ");
        System.out.println();
    }
}

public class HeapDemo {
    public static void main(String[] args) {

        int[] elements = {20, 15, 30, 5, 10, 25, 35};

        // -------- MIN HEAP --------
        System.out.println("MIN HEAP:");
        Heap minHeap = new Heap(20, true);

        for (int e : elements)
            minHeap.insert(e);

        System.out.print("Heap Elements: ");
        minHeap.display();

        System.out.println("After Deleting Root:");
        minHeap.deleteRoot();
        minHeap.display();


        // -------- MAX HEAP --------
        System.out.println("\nMAX HEAP:");
        Heap maxHeap = new Heap(20, false);

        for (int e : elements)
            maxHeap.insert(e);

        System.out.print("Heap Elements: ");
        maxHeap.display();

        System.out.println("After Deleting Root:");
        maxHeap.deleteRoot();
        maxHeap.display();
    }
}
