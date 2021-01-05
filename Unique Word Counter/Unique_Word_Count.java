/*
 *  Assignment 1 - ass1
 *  Lincoln Knowles
 */

import java.io.IOException;
import java.nio.file.Paths;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Unique_Word_Count {
    private static final int[] index = new int[50000];            //Holds the index of each word
    private static final int[] sortedIndex = new int[50000];      //Holds index of sorted count words

    private static final char[] stringPool = new char[500000];     //Holds upto 500,000 Chars: 50,000 * 10 (average word length)
    private static final int[] start = new int[50000];             //Start of word
    private static final int[] end = new int[50000];               //End of word
    private static final int[] count = new int[50000];             //Count of each word

    private static final char[] word = new char[100];              //Current input word

    //Binary search tree places
    private static final int[] left = new int[50000];     //Index of left child in BST
    private static final int[] right = new int[50000];    //Index of right child in BST
    private static int nodeCount = 0;               //Counts index of nodes

    private static int len = 0;             //Holds length
    private static int charCount = 0;       //Holds place of char index

    private static int wordSEIndex = 1;     //Holds place for start and end arrays

    private static int valid = 0;           //Counts valid/unique nodes/words

    private static int bstIndex = 0;         //Index for sorted Index from BST


    public static void main(String[] args) {
        //Processes given file into char array.
        Scanner scanner = new Scanner(System.in);

        System.err.print("Enter file name: ");
        String fileName = scanner.next();

        try {
            scanner = new Scanner(Paths.get(fileName));
        } catch (IOException ex) {
            System.err.println("File failed to open: " + ex.toString());
            System.exit(0);
        }

        boolean inword = false;
        start[0] = 1; //Sets start of first word to 1

        try {
            do {
                for (char input : scanner.nextLine().toCharArray()) {
                    if (Character.isSpaceChar(input)) {
                        if (inword) {
                            insert(0);
                            len = 0;
                            inword = false;
                        }
                    } else if (Character.isLetter(input)) {
                        inword = true;
                        word[len] = Character.toLowerCase(input);
                        len++;
                    }
                }
                //Processes last word in line
                //Solves problem of combining last word in line and first word next line
                if (len > 0) {
                    insert(0);
                    len = 0; //Reset length to 0 to restart new word
                }
            }
            while (scanner.hasNextLine());

        } catch (NoSuchElementException e) {
            System.err.println("No Such Element Exception");
        }

        visit(0);                 //BST in-order traversal. Sorts alphabetically

        mergeSort(sortedIndex, valid); //Sorts by count


        System.out.println("Top 10 words");
        for (int i = valid - 1; i > valid - 11; i--) {
            System.out.print("Count: " + count[sortedIndex[i]] + " ");
            printStringPoolWord(sortedIndex[i]);
            System.out.println();
        }
        System.out.println("--------------------------------------");
        System.out.println("Bottom 10 words");
        for (int i = 0; i < 10; i++) {
            System.out.print("Count: " + count[sortedIndex[i]] + " ");
            printStringPoolWord(sortedIndex[i]);
            System.out.println();
        }
    }

    public static void processWord(char[] currentWord) {
        start[valid] = wordSEIndex - 1;

        for (int i = 0; i < len; i++) {
            stringPool[charCount] = currentWord[i];
            charCount++;
            wordSEIndex++;
        }
        end[valid] = wordSEIndex - 1;
        count[valid]++;         //Counts first word
        index[valid] = valid++; //Counts total unique words
    }

    public static void insert(int node) {
        //START AT ROOT

        /*  Alphabet compare:
                1 node goes right.
                -1 go left
                0 Same word */

        boolean leftBool;
        int next;

        if (alphabetCompare(node) == 0) {
            //Word is already found
            //Increase count by 1
            count[node]++;
            return;
        } else if (alphabetCompare(node) == -1) {
            //Next goes left
            next = left[node];
            leftBool = true;
        } else {
            //Next goes Right
            next = right[node];
            leftBool = false;
        }

        if (next != 0) {
            //Node already exists
            insert(next);
        } else {
            //Insert new node
            //Process word
            next = nodeCount++;
            processWord(word);
            if (leftBool) {
                left[node] = next;
            } else {
                right[node] = next;
            }
        }
    }

    //In order traversal:
    public static void visit(int node) {
        //Sorts right first to place z-a
        if (right[node] != 0) {
            visit(right[node]);
        }

        sortedIndex[bstIndex++] = index[node];

        if (left[node] != 0) {
            visit(left[node]);
        }

        return;
    }

    public static int alphabetCompare(int stringPoolIndex) {
        //Compares given word in StringPool array to current input in word[]
        //Returns 1 if stringPool word is higher in alphabet
        //Returns -1 if new input word is higher
        //Returns 0 if both are the same word
        int wordPlace = 3;
        int sameWordCount = 0;
        int wordIndexCount = 0;

        for (int i = start[stringPoolIndex]; i < end[stringPoolIndex]; i++) {
            if (stringPool[i] < word[wordIndexCount]) {
                //String pool word higher in alphabet
                wordPlace = 1;
                break;
            } else if (stringPool[i] > word[wordIndexCount]) {
                //New Word higher in alphabet
                wordPlace = -1;
                break;
            } else if (stringPool[i] == word[wordIndexCount]) {
                //Same word
                //Counter to check length against words[]
                sameWordCount++;
                //If counter matches length it is the same word. Break loop and return
                if (sameWordCount == len) {
                    wordPlace = 0;
                    break;
                }
            }
            wordIndexCount++;
        }
        return wordPlace;
    }

    //Merge
    public static void mergeSort(int[] sortArr, int total) {
        //Once arrays reach 1 return
        if (total < 2) {
            return;
        }
        int center = total / 2; //Finds middle. (Left + right) / 2

        //Temporary arrays: hold alphabetically sorted indexes
        int[] leftArr = new int[center];
        int[] rightArr = new int[total - center];

        for (int i = 0; i < center; i++) {
            leftArr[i] = sortArr[i];
        }

        for (int i = center; i < total; i++) {
            rightArr[i - center] = sortArr[i];
        }

        mergeSort(leftArr, center);
        mergeSort(rightArr, total - center);

        merge(sortArr, leftArr, rightArr, center, total - center);
    }

    public static void merge(int[] array, int[] leftArr, int[] rightArr, int left, int right) {
        int apos = 0, bpos = 0, cpos = 0; //Index starts at 0. Counters

        while (apos < left && bpos < right) {
            //Checks count by index
            if (count[leftArr[apos]] <= count[rightArr[bpos]]) {
                array[cpos++] = leftArr[apos++];
            } else {
                array[cpos++] = rightArr[bpos++];
            }
        }
        while (apos < left) {
            array[cpos++] = leftArr[apos++];
        }
        while (bpos < right) {
            array[cpos++] = rightArr[bpos++];
        }
    }

    public static void printStringPoolWord(int index) {
        for (int i = start[index]; i < end[index]; i++) {
            System.out.print(stringPool[i]);
        }
    }
}