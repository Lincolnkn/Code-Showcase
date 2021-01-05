/*
 *  Lincoln knowles
 *  ex6
 */

import java.io.IOException;
import java.nio.file.Paths;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ex6 {
    static char[] target = new char[5000];
    static char[] search = new char[10];
    static int prime = 5;
    static int mod = 5;
    static long searchHash;
    static long targetHash;


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


        try {
            do {
                target = scanner.next().toCharArray();
                search = scanner.next().toCharArray();
            } while (scanner.hasNextLine());
        } catch (NoSuchElementException e) {
            System.err.print("");
        }

        int searchLength = search.length;

        searchHash = hash(search, searchLength);
        targetHash = hash(target, searchLength);

        long maxPower = power(searchLength);

        for (int i = 0; i < target.length - searchLength; i++) {
            if (searchHash == targetHash) {
                //Brute force compare
                int lengthCnt = 0;
                int tCnt = i;
                for (char c : search) {
                    if (target[tCnt++] == c) lengthCnt++;

                    if (lengthCnt == searchLength) System.out.print(i + " ");
                }
            }
            targetHash = roll(targetHash, target[i], target[i + searchLength], maxPower);
        }

    }

    public static long power(int length) {
        long num = 1;
        for (int i = 0; i < length - 1; i++) {
            num *= prime;
        }
        return num;
    }

    public static long roll(long hash, char firstChar, char newChar, long maxPower) {
        return prime * (hash - (maxPower * (firstChar % mod))) + newChar % mod;
    }

    public static long hash(char[] string, int length) {
        long hash = (int) string[0] % mod;

        for (int i = 1; i < length; i++) {
            hash = (int) string[i] % mod + hash * prime;
        }

        return hash;
    }

}
