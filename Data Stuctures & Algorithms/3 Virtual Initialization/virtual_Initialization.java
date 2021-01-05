/*
    203 Lab 3
    Lincoln Knowles
 */

import java.io.*;
import java.nio.file.Paths;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ex3 {

    public static void main(String[] args) throws IOException {
        int[] data = new int[100];
        int[] forward = new int[100];
        int[] backward = new int[100];
        int validCount = 0;

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter file name: ");
        String fileName = scanner.next();

        try {
            scanner = new Scanner(Paths.get(fileName));

        } catch (IOException ex) {
            System.err.println("File failed to open: " + ex.toString());
            System.exit(0);
        }

        int what;
        int where;
        int probe;

        try {
            while (true) {
                what = scanner.nextInt();
                where = scanner.nextInt();

                System.out.println(what + " " + where);
                if (what == -1 && where == -1) break;
                data[where] = what;

                if (backward[where] < 0 || backward[where] >= validCount || forward[backward[where]] != where) {
                    validCount++;
                    forward[validCount] = where;
                    backward[where] = validCount;
                }
            }
        } catch (InputMismatchException exception) {
            scanner.next(); //Skips over second -1.
        }

        try {
            while (true) {
                probe = scanner.nextInt();
                if (probe == -1) break;

                if (backward[probe] > 0 && backward[probe] <= validCount && forward[backward[probe]] == probe) {
                    System.out.printf("\nPosition %s has been initialized to value %s.", probe, data[probe]);
                } else {
                    System.out.printf("\nPosition %s has not been initialized.", probe);
                }
            }
        } catch (InputMismatchException exception) {
        }
    }
}
