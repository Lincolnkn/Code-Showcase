/*
 *  Lab 1 - Ex1
 *  Lincoln Knowles
 *  ldk939
 */

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class minHeap {

    private static int arraySize = 10;

    private static final int[] num = new int[arraySize];

    private static int numHolder; //Holder swapping numbers


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        //Scans file
        try {
            scanner = new Scanner(Paths.get("t.txt"));
        } catch (IOException ex) {
            System.out.println("File failed to open: " + ex.toString());
            System.exit(0);
        }
        //Fills array
        int count = 0;
        do {
            num[count++] = scanner.nextInt();
        } while (scanner.hasNext());

        scanner.close();

        System.out.println("BEFORE");
        for (int i = 0; i < 10; i++) {
            System.out.println(i + " " + num[i]);
        }

        //makeHeap(num.length);
        //siftUp(num, num.length);
        siftDown(9);

        //Prints top 10 numbers
        System.out.println("AFTER");
        for (int i = 0; i < 10; i++) {
            System.out.println(i + " " + num[i]);
        }
    }

    private static void makeHeap(int heapSize) {
        for (int i = heapSize / 2 - 1; i >= 0; i--) {
            //System.out.println(i);
            //siftDown(num, i);
            siftUp(num, i);
        }
    }

    public static void siftUp(int[] heap, int i) {
        if (i == 0) return; //0 = root of heap.

        int p = i / 2;

        if (heap[p] < heap[i]) return;
        else {
            numHolder = heap[i];
            heap[i] = heap[p];
            heap[p] = numHolder;
            siftUp(heap, p);
        }
    }

    private static void siftDown(int i) {
        // Inserts a new root element into the correct position
        int c = i * 2 + 1; //Index starts at 0
        if (c >= num.length - 1 || c + 1 >= num.length - 1) return;
        if (num[c] < num[c + 1]) c = c + 1;
        if (num[i] < num[c]) {
            //Swaps numbers
            numHolder = num[i];
            num[i] = num[c];
            num[c] = numHolder;

            siftDown(c);
        }
    }
}