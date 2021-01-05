/*
    Lincoln Knowles
    203 Lab 4. ex4
 */

import java.io.IOException;
import java.nio.file.Paths;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ex4 {

    private static int[] data = new int[1000];
    private static int[] left = new int[1000];
    private static int[] right = new int[1000];

    private static int next = 0;
    private static int numberCountLine = 0;

    public static void main(String[] args) {
        //Processes given file into array.
        Scanner scanner = new Scanner(System.in);

        System.err.print("Enter file name: ");
        String fileName = scanner.next();

        try {
            scanner = new Scanner(Paths.get(fileName));
        } catch (IOException ex) {
            System.err.println("File failed to open: " + ex.toString());
            System.exit(0);
        }

        int root = 0;
        try {
            do {
                root = insert(root, scanner.nextInt());
            } while (scanner.hasNextLine());
        } catch (NoSuchElementException e) {
            System.err.println("");
        }

        visit(root);
    }

    public static int insert(int node, int value) {
        if (node == 0) {
            node = next++;
            data[node] = value;
            left[node] = 0;
            right[node] = 0;
            return node;
        }
        if (value <= data[node]) {
            left[node] = insert(left[node], value);
            return node;
        }
        if (value > data[node]) {
            right[node] = insert(right[node], value);
            return node;
        }

        return node;
    }

    public static void visit(int node) {
        //Sorts right first to place z-a
        if (node == 0) return;

        visit(left[node]);

        //Formating:
        System.out.printf("%5d", data[node]);
        numberCountLine++;
        if (numberCountLine == 10) {
            System.out.println();
            numberCountLine = 0;
        }

        visit(right[node]);
        return;
    }
}