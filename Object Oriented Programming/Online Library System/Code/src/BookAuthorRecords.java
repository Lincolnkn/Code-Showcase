package Knowles6701620;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;

/*
 *
 *  Lincoln Knowles
 */
public class BookAuthorRecords implements Serializable {
    /*
    Book-Author records Class. (0.5 marks)
    - The purpose of this class is to keep the book’s authors information.
      Since a book can have many authors, we will keep each author id,
      its order (e.g., 2 as the second author) and the book id in this record.
    - Each record should have its own identity number for indexing purpose.
     */

    private int recordID;
    private int bookID;

    private HashMap<Integer, Integer> bookAuthorOrders; //ID : Order

    public BookAuthorRecords(int recordID, int bookID, HashMap<Integer, Integer> bookAuthorOrders) {
        this.recordID = recordID;
        this.bookID = bookID;
        this.bookAuthorOrders = bookAuthorOrders;
    }

    public int getRecordID() {
        return recordID;
    }

    public int getBookID() {
        return bookID;
    }

    public Collection<Integer> getAuthorID() {
        return bookAuthorOrders.keySet();
    }

    @Override
    public String toString() {
        String authorsAndOrder = "";
        for (HashMap.Entry<Integer, Integer> entry : bookAuthorOrders.entrySet()) {
            authorsAndOrder += " Author: " + entry.getValue() + ", " + entry.getKey() + ". ";

        }

        return String.format("Record ID %d, Book ID %d.%s", recordID, bookID, authorsAndOrder);
    }
}
