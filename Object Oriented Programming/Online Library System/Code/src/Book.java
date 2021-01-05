package Knowles6701620;

import java.io.Serializable;

/*
 *
 *  Lincoln Knowles
 */
public class Book implements Serializable {
    /*
    Book class. (1 mark)
    -The purpose of this class is to keep the book’s information.
    -Book class must have seven attributes which are identity number,
     ISBN number, call number (e.g., 005.133/JAV-1/54 c.4), title, abstract,
     publisher and status of a book (e.g., Active or Removed).
     */
    private int id;
    private int isbn;
    private String callNum;
    private String title;
    private String abstractB;
    private String publisher;
    private BookStatus bookStatus; //Active or removed. Enum

    public Book(int id, int isbn, String callNum, String title, String abstractB, String publisher, BookStatus bookStatus) {
        this.id = id;
        this.isbn = isbn;
        this.callNum = callNum;
        this.title = title;
        this.abstractB = abstractB;
        this.publisher = publisher;
        this.bookStatus = bookStatus;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return String.format("%d, %d, %s, %s, %s, %s, %s",
                id, isbn, callNum, title, abstractB, publisher, bookStatus.name());
    }
}
