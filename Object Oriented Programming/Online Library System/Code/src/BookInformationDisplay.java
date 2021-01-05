package Knowles6701620;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

/*
 *
 *  Lincoln Knowles
 */
public class BookInformationDisplay extends JFrame {
    /*
    The goal is to allow the system to use this class to display information about
    the book in the text area. A user should be able to navigate back to the
    main page (in the main system) via a button named “Back to Main Menu”.
     */
    private BookRegistrationSystem bookSys;

    private final JButton backtoMainPage = new JButton("Back to Main Menu");
    private final JButton clearText = new JButton("Clear");

    private JTextArea bookTextArea = new JTextArea();

    private JTextArea authorTextArea1 = new JTextArea();
    private JTextArea authorTextArea2 = new JTextArea();

    private JTextArea authorRecords = new JTextArea();

    private JTextArea locationTextArea = new JTextArea();

    private final int columnSize = 45;


    public BookInformationDisplay(BookRegistrationSystem bookSys) {
        this.bookSys = bookSys;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(550, 250);
        setLayout(new FlowLayout());
        setTitle("Book Display");
        setVisible(true);

        //Creates back button,
        add(backtoMainPage);
        backtoMainPage.setToolTipText("Return to Main Page");
        backtoMainPage.setVerticalAlignment(SwingConstants.BOTTOM);
        backtoMainPage.setHorizontalAlignment(SwingConstants.LEFT);
        backtoMainPage.addActionListener(e -> backToPreviousPage());

        //Remove Text
        add(clearText);
        clearText.addActionListener(e -> clear());

        add(bookTextArea);
        bookTextArea.setEditable(false); //Unable to change info
        bookTextArea.setColumns(columnSize);

        add(locationTextArea);
        locationTextArea.setEditable(false); //Unable to change info
        locationTextArea.setColumns(columnSize);

        add(authorTextArea1);
        authorTextArea1.setEditable(false); //Unable to change info
        authorTextArea1.setColumns(columnSize);

    }

    public void displayInformationToTextArea(int authorNum, String[] bookInfo, String[] authorInfo, String[] locationInfo) {
        String bookInfoDisplay = "Book Info: \n" + bookSys.getBookID() + ", ";
        String locationInfoDisplay = "Located: \n" + bookSys.getLocationID() + ", ";

        //Book
        for (int i = 0; i < bookInfo.length - 1; i++) {
            bookInfoDisplay += bookInfo[i] + ", ";
        }

        if (bookInfo[5].equals("A")) {
            bookInfoDisplay += "Active.";
        } else {
            bookInfoDisplay += "Removed.";
        }
        //Display area for the bookInfo
        bookTextArea.setText(bookInfoDisplay);

        //Location
        for (int i = 0; i < locationInfo.length; i++) {
            locationInfoDisplay += locationInfo[i] + ". ";
        }
        //Display location
        locationTextArea.setText(locationInfoDisplay);

        //Authors
        if (authorNum == 1) {
            String authorInfoDisplay1 = "Author Info 1: \n" + bookSys.getAuthorID1() + ", ";
            for (int i = 1; i < authorInfo.length; i++) {
                authorInfoDisplay1 += authorInfo[i] + ", ";
            }
            authorTextArea1.setText(authorInfoDisplay1);
        } else {
            String authorInfoDisplay1 = "Author Info 1: \n" + bookSys.getAuthorID1() + ", ";
            for (int i = 1; i < authorInfo.length - 4; i++) {
                authorInfoDisplay1 += authorInfo[i] + ", ";
            }
            authorTextArea1.setText(authorInfoDisplay1);

            String authorInfoDisplay2 = "Author Info 2: \n" + bookSys.getAuthorID2() + ", ";
            for (int i = 5; i < authorInfo.length; i++) {
                authorInfoDisplay2 += authorInfo[i] + ", ";
            }
            authorTextArea2.setText(authorInfoDisplay2);
            add(authorTextArea2);
            authorTextArea2.setEditable(false); //Unable to change info
            authorTextArea2.setColumns(columnSize);
        }
    }

    public void displayInformationToTextArea(Book book, BookAuthorRecords authorRecord, Location location, Author author) {
        //Display area for card info
        bookTextArea.setText(book.toString());
        add(authorRecords);
        authorRecords.setColumns(columnSize);
        authorRecords.setText(authorRecord.toString());
        locationTextArea.setText(location.toString());
        authorTextArea1.setText(author.toString());
    }
    public void displayInformationToTextArea(Book book, BookAuthorRecords authorRecord, Location location, ArrayList<Author> authorArrayList) {
        //Display area for card info
        add(authorTextArea2);
        authorTextArea2.setColumns(columnSize);

        bookTextArea.setText(book.toString());

        add(authorRecords);
        authorRecords.setColumns(columnSize);

        authorRecords.setText(authorRecord.toString());
        locationTextArea.setText(location.toString());

        for (int i = 0; i < authorArrayList.size(); i++) {
            authorTextArea1.setText(authorArrayList.get(0).toString());
            authorTextArea2.setText(authorArrayList.get(1).toString());
        }
    }

    private void clear() {
        bookTextArea.setText("");
        authorTextArea1.setText("");
        authorTextArea2.setText("");
        locationTextArea.setText("");
        authorRecords.setText("");
    }

    private void backToPreviousPage() {
        bookSys.closeBookDisplay();
        bookSys.backToMain();
    }

}
