/*
 *  Lincoln knowles
 *  ex5
 */

import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

class Node {
    int key;
    int valid = 0;
    Node next;

    public Node(int key, int valid, Node next) {
        this.key = key;
        this.valid = valid;
        this.next = next;
    }
}

public class ex5 {
    public static Node[] hashTable = new Node[100];
    private static int initializedCount = 0;
    private static int maxCount = 0;

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
                hashInsert(scanner.nextInt());
            } while (scanner.hasNextLine());
        } catch (NoSuchElementException e) {
            System.err.print("");
        }

        System.out.printf("Number of empty spots in Hash Table: %d\n", 100 - initializedCount);
        System.out.printf("The longest chain holds %d values", maxCount);
    }

    public static void hashInsert(int nextInt) {
        int value = Math.abs(nextInt % 100);
        if (hashTable[value] == null) {
            initializedCount++;
            hashTable[value] = new Node(nextInt, 1, null);
            return;
        }
        hashTable[value].next = chainInsert(value, hashTable[value].next, 1);
    }

    public static Node chainInsert(int value, Node where, int depth) {
        if (where == null) {
            where = new Node(value, 1, null);
            if (depth > maxCount) maxCount = depth + 1;
            return where;
        }
        where.next = chainInsert(value, where.next, depth + 1);
        return where;
    }
}