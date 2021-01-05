/*
 *  Lincoln knowles
 *  ex7
 */


import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class ex10 {
    static int iNumberMatrices;
    static int[] rows = new int[500];
    static int[] columns = new int[500];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.err.print("Enter file name: ");
        String fileName = scanner.next();
        try {
            scanner = new Scanner(Paths.get(fileName));
        } catch (IOException ex) {
            System.err.println("File failed to open: " + ex.toString());
            System.exit(0);
        }
        //Read Data
        iNumberMatrices = scanner.nextInt();
        for (int i = 0; i < iNumberMatrices; i++) {
            rows[i] = scanner.nextInt();
            columns[i] = scanner.nextInt();
        }

        //Call
        System.out.println(best(0, iNumberMatrices - 1));
    }

    static int best(int i, int j) {
        if (i == j) return 0;
        int b = Integer.MAX_VALUE; //Infinite
        for (int k = i + 1; k < j + 1; k++) {
            b = min(b, cost(i, j, k) + best(i, k - 1) + best(k, j));
        }
        return b;
    }

    static int cost(int i, int j, int k) {
        return rows[i] * rows[k] * columns[j];
    }

    static int min(int a, int b) {
        if (a < b) return a;
        else return b;
    }
}
