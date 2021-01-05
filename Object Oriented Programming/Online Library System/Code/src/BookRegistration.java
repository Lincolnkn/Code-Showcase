package Knowles6701620;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/*
 *
 *  Lincoln Knowles
 */
public class BookRegistration extends JFrame {
    /*
    The goal of this class is to allow a user to input the information about a new book
    and its related information for registering a book to the system. When a user
    clicked at the “Create Book Record” button, it calls the method in the system
    (see Task 5.4) to pass the information back to the system (pass them as a List
    object or an array of String or a Map object). A user should be able to navigate
    back to the main page (in the main system) via a button named “Back to Main Menu”.
     */

    //Book
    private final JLabel bookStatus = new JLabel("Book Status");
    private final JRadioButton active = new JRadioButton("Active");
    private final JRadioButton removed = new JRadioButton("Removed");
    private final JPanel bookStatusPanel = new JPanel();

    private final JLabel isbn = new JLabel("ISBN: ");
    private final JTextArea isbnText = new JTextArea();

    private final JLabel callNum = new JLabel("Call Number: ");
    private final JTextArea callNumText = new JTextArea();

    private final JLabel title = new JLabel("Title: ");
    private final JTextArea titleText = new JTextArea();

    private final JLabel abstractB = new JLabel("Abstract: ");
    private final JTextArea abstractBText = new JTextArea();

    private final JLabel publisher = new JLabel("Publisher: ");
    private final JTextArea publisherText = new JTextArea();

    //Location
    private final JLabel campusN = new JLabel("Campus Name: ");
    private final JTextArea campusNText = new JTextArea();

    private final JLabel floorNum = new JLabel("Floor Number: ");
    private final JTextArea floorNumText = new JTextArea();

    private final JLabel shelfNum = new JLabel("Shelf Number: ");
    private final JTextArea shelfNumText = new JTextArea();

    private final JButton register = new JButton("Register");
    private final JButton clear = new JButton(("Clear"));
    private final JButton back = new JButton("Back");

    //Authors
    //Unsure how to streamline varying numbers of authors
    private final JLabel numAuthorL = new JLabel("Number of Authors: ");
    private final String[] numOfAuthors = new String[]{"1", "2"};
    private final JComboBox<String> numAuthorsList = new JComboBox<>(numOfAuthors);
    private String selectedNumber;

    private final JLabel authorTitle = new JLabel("1. Author Title: ");
    private final JTextArea authorTitleText = new JTextArea();
    private final JLabel aFName = new JLabel("1. Author First Name: ");
    private final JTextArea aFNameText = new JTextArea();
    private final JLabel aMName = new JLabel("1. Middle Name: ");
    private final JTextArea aMNameText = new JTextArea();
    private final JLabel aLName = new JLabel("1. Last Name: ");
    private final JTextArea aLNameText = new JTextArea();

    private final JLabel authorTitle2 = new JLabel("2. Author Title: ");
    private final JTextArea authorTitleText2 = new JTextArea();
    private final JLabel aFName2 = new JLabel("2. Author First Name: ");
    private final JTextArea aFNameText2 = new JTextArea();
    private final JLabel aMName2 = new JLabel("2. Middle Name: ");
    private final JTextArea aMNameText2 = new JTextArea();
    private final JLabel aLName2 = new JLabel("2. Last Name: ");
    private final JTextArea aLNameText2 = new JTextArea();

    BookRegistrationSystem bookSys;

    private final String[] bookInfo = new String[7];
    private final String[] locationInfo = new String[3];
    private final String[] authorInfo1 = new String[5];
    private final String[] authorInfo2 = new String[9];

    private GridLayout gridLayout = new GridLayout(13, 3);


    public BookRegistration(BookRegistrationSystem bookSys) {
        this.bookSys = bookSys;

        GridLayout gridLayout = new GridLayout(21, 3);
        setTitle("Register");
        //Sets gaps between areas
        gridLayout.setVgap(10);
        gridLayout.setHgap(10);
        setLayout(gridLayout);
        //setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 750);

        //Book
        //Adds book type checkboxes
        bookStatus();
        //Adds labels and text areas
        setLabelUI(isbn, isbnText);
        setLabelUI(callNum, callNumText);
        setLabelUI(title, titleText);
        setLabelUI(abstractB, abstractBText);
        setLabelUI(publisher, publisherText);

        //Location
        setLabelUI(campusN, campusNText);
        setLabelUI(floorNum, floorNumText);
        setLabelUI(shelfNum, shelfNumText);

        //Authors
        add(numAuthorL);
        add(numAuthorsList);

        numAuthorsList.addItemListener(e -> numAuthorAction());

        setLabelUI(authorTitle, authorTitleText);
        setLabelUI(aFName, aFNameText);
        setLabelUI(aMName, aMNameText);
        setLabelUI(aLName, aLNameText);

        setLabelUI(authorTitle2, authorTitleText2);
        setLabelUI(aFName2, aFNameText2);
        setLabelUI(aMName2, aMNameText2);
        setLabelUI(aLName2, aLNameText2);

        setAllAuthor2VisibleFalse();

        //Adds buttons
        add(register);
        register.addActionListener(e -> openBookRegister());
        add(clear);
        clear.addActionListener(e -> clear());
        add(back);
        back.addActionListener(e -> backToPreviousPage());


    }

    public void openBookRegister() {
        returnBookRegisterInfo();

        setVisible(false);
        if (selectedNumber.equals("1")){
            bookSys.displayCardInformationToPage(1, bookInfo, authorInfo1,locationInfo);
        } else {
            bookSys.displayCardInformationToPage(2, bookInfo, authorInfo2,locationInfo);
        }

    }

    public void returnBookRegisterInfo() {
        //Book
        bookInfo[0] = isbnText.getText();
        bookInfo[1] = callNumText.getText();
        bookInfo[2] = titleText.getText();
        bookInfo[3] = abstractBText.getText();
        bookInfo[4] = publisherText.getText();

        if (active.isSelected()) {
            bookInfo[5] = "A"; //Active
        } else {
            bookInfo[5] = "R"; //Removed
        }

        //Location
        locationInfo[0] = campusNText.getText();
        locationInfo[1] = floorNumText.getText();
        locationInfo[2] = shelfNumText.getText();

        //Authors & Register
        selectedNumber = numAuthorsList.getSelectedItem().toString();
        //Checks how many authors selected. Adds either 1 or 2 author details
        if (selectedNumber.equals("1")) {
            authorInfo1[0] = "1";
            authorInfo1[1] = authorTitleText.getText();
            authorInfo1[2] = aFNameText.getText();
            authorInfo1[3] = aMNameText.getText();
            authorInfo1[4] = aLNameText.getText();

            bookSys.registerBook(bookInfo, authorInfo1, locationInfo);
        } else {
            authorInfo2[0] = "2";
            authorInfo2[1] = authorTitleText.getText();
            authorInfo2[2] = aFNameText.getText();
            authorInfo2[3] = aMNameText.getText();
            authorInfo2[4] = aLNameText.getText();
            authorInfo2[5] = authorTitleText2.getText();
            authorInfo2[6] = aFNameText2.getText();
            authorInfo2[7] = aMNameText2.getText();
            authorInfo2[8] = aLNameText2.getText();

            bookSys.registerBook(bookInfo, authorInfo2, locationInfo);
        }
    }

    //Shows/hides author details
    public void numAuthorAction() {
        selectedNumber = numAuthorsList.getSelectedItem().toString();

        if (selectedNumber.equals("1")) {
            setLabelTextVisibleTrue(authorTitle, authorTitleText);
            setLabelTextVisibleTrue(aFName, aFNameText);
            setLabelTextVisibleTrue(aMName, aMNameText);
            setLabelTextVisibleTrue(aLName, aLNameText);

            setLabelTextVisibleFalse(authorTitle2, authorTitleText2);
            setLabelTextVisibleFalse(aFName2, aFNameText2);
            setLabelTextVisibleFalse(aMName2, aMNameText2);
            setLabelTextVisibleFalse(aLName2, aLNameText2);
        } else if (selectedNumber.equals("2")) {
            //2
            setLabelTextVisibleTrue(authorTitle, authorTitleText);
            setLabelTextVisibleTrue(aFName, aFNameText);
            setLabelTextVisibleTrue(aMName, aMNameText);
            setLabelTextVisibleTrue(aLName, aLNameText);

            setLabelTextVisibleTrue(authorTitle2, authorTitleText2);
            setLabelTextVisibleTrue(aFName2, aFNameText2);
            setLabelTextVisibleTrue(aMName2, aMNameText2);
            setLabelTextVisibleTrue(aLName2, aLNameText2);
        }
    }

    //Hides given label and text
    public void setLabelTextVisibleFalse(JLabel label, JTextArea textArea) {
        label.setVisible(false);
        textArea.setVisible(false);
    }

    //Shows given label and text
    public void setLabelTextVisibleTrue(JLabel label, JTextArea textArea) {
        label.setVisible(true);
        textArea.setVisible(true);
    }

    //Hide author 2 details
    public void setAllAuthor2VisibleFalse() {
        setLabelTextVisibleFalse(authorTitle2, authorTitleText2);
        setLabelTextVisibleFalse(aFName2, aFNameText2);
        setLabelTextVisibleFalse(aMName2, aMNameText2);
        setLabelTextVisibleFalse(aLName2, aLNameText2);
    }

    //Clears all text
    private void clear() {
        isbnText.setText("");
        callNumText.setText("");
        titleText.setText("");
        abstractBText.setText("");
        publisherText.setText("");

        authorTitleText.setText("");
        aFNameText.setText("");
        aMNameText.setText("");
        aLNameText.setText("");
    }

    //Returns to main menu
    private void backToPreviousPage() {
        bookSys.closeBookRegisterPage();
        bookSys.backToMain();
    }

    //Creates group and adds the panel
    private void bookStatus() {
        ButtonGroup group = new ButtonGroup();
        group.add(active);
        group.add(removed);
        active.setSelected(true);
        add(bookStatus);
        bookStatusPanel.add(active);
        bookStatusPanel.add(removed);
        add(bookStatusPanel);
    }

    //Adds label & text area.
    private void setLabelUI(JLabel label, JTextArea textArea) {
        add(label);
        textArea.setColumns(15);
        add(textArea);
    }
}
