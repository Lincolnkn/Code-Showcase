package Knowles6701620;

import javax.swing.*;
import java.awt.*;

/*
 *
 *  Lincoln Knowles
 */
public class BookAuthorQuery extends JFrame {
    /*
    The goal of this class is to allow a user to make a query for a book. A user can choose to
    search for a book by its title or its author name. To process this query, this GUI class
    will call the method in the system to return the query information back to the system.
    So that the system can process the information and display the appropriate result back
    to the user (see Task 2.5). A user should be able to navigate back to the main page
    (in the main system) via a button named “Back to Main Menu”.
     */

    private BookRegistrationSystem bookSys;

    private final JPanel typePanel = new JPanel();
    private final JPanel bookPanel = new JPanel();
    private final JPanel aFNamePanel = new JPanel();
    private final JPanel aLNamePanel = new JPanel();
    private final JPanel buttonsPanel = new JPanel();

    private final JRadioButton bookTitleSearch = new JRadioButton("Search by Book Title");
    private final JRadioButton authorSearch = new JRadioButton("Search by Author Name");

    private final JLabel bookTitle = new JLabel("Book Title:");
    private final JTextField bookTitleText = new JTextField(30);

    private final JLabel aFName = new JLabel("Author First Name: ");
    private final JTextField aFNameText = new JTextField(25);

    private final JLabel aLName = new JLabel("Last Name: ");
    private final JTextField aLNameText = new JTextField(30);

    private final JButton searchDisplay = new JButton("Search & Display");
    private final JButton backtoMainPage = new JButton("Back to Main Menu");
    private final JButton clear = new JButton("Clear");

    public BookAuthorQuery(BookRegistrationSystem bookSys) {
        this.bookSys = bookSys;

        setTitle("Query");
        setLayout(new GridLayout(5,2));
        setSize(400, 250);

        //Search Buttons
        ButtonGroup group = new ButtonGroup(); //Only one button can be selected at a time
        group.add(bookTitleSearch);
        group.add(authorSearch);
        bookTitleSearch.setSelected(true);
        typePanel.add(bookTitleSearch);
        typePanel.add(authorSearch);

        add(typePanel, "North");

        //Book Search bar
        bookPanel.add(bookTitle);
        bookPanel.add(bookTitleText);
        add(bookPanel);

        //Author Search
        //First Name
        aFNamePanel.add(aFName);
        aFNamePanel.add(aFNameText);
        add(aFNamePanel);

        //Last Name
        aLNamePanel.add(aLName);
        aLNamePanel.add(aLNameText);
        add(aLNamePanel);


        //Bottom buttons
        buttonsPanel.add(searchDisplay);
        searchDisplay.addActionListener(e -> openRequestForCard());

        buttonsPanel.add(clear);
        clear.addActionListener(e -> clear());
        buttonsPanel.add(backtoMainPage);
        backtoMainPage.addActionListener(e -> backToPreviousPage());

        add(buttonsPanel, "South");
    }

    public void openRequestForCard() {
        bookSys.closeQueryPage();
        bookSys.displayCardInformationToPage();

        if (bookTitleSearch.isSelected()) {
            bookSys.searchByBookTitleAndDisplay(bookTitleText.getText());
        } else {
            bookSys.searchByAuthorNameAndDisplay(aFNameText.getText(), aLNameText.getText()); //Sends name split by '.'
        }
    }


    private void clear() {
        bookTitleText.setText("");
        aFNameText.setText("");
        aLNameText.setText("");
    }

    private void backToPreviousPage() {
        bookSys.closeQueryPage();
        bookSys.backToMain();
    }
}
