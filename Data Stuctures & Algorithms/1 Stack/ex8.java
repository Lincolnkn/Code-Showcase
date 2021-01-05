import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Paths;
import java.util.Scanner;
/*
 *  Lab 8 - Ex8
 *  Lincoln Knowles
 */

public class ex8 {
    static int[] numbers = new int[1]; //Stack
    static int index = -1;  //Index for top word.
    static int stackSize = 0;
    static int entries = 0;

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

        stackSize = scanner.nextInt();
        numbers = new int[stackSize];
        String pushPop;
        int value;

        do {
            pushPop = scanner.next();
            if (pushPop.equals("push")) {
                value = scanner.nextInt();
                push(value);
            } else {
                pop(numbers);
            }
        } while (scanner.hasNext());
        scanner.close();

        System.out.println("Stack contains " + entries + " entries.");
    }

    public static void push(int number) {
        if (index + 1 < stackSize) {
            entries++;
            numbers[++index] = number; //Places int in array. Increase index by 1
        } else {
            growStack();
            push(number);
        }
    }

    public static int pop(int[] numbers) {
        if (index > -1) {
            entries--;
            return numbers[index--]; //Return int, takes index down
        }
        return -1;
    }

        public static void growStack() {
            int[] tempStack = new int[stackSize];
            int tempSize = stackSize;

            for (int i = 0; i < stackSize; i++) {
                tempStack[i] = numbers[i];
            }
            stackSize *= 2;
            numbers = new int[stackSize];

            for (int i = 0; i < tempSize; i++) {
                numbers[i] = tempStack[i];
            }

            System.out.println("Stack doubled from " + tempSize + " to " + stackSize + ".");
        }

}