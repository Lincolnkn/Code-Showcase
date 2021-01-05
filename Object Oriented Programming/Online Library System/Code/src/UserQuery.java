package Knowles6701620;

import javax.swing.*;
import java.awt.*;

/*
 *
 *  Lincoln Knowles
 */
public class UserQuery extends JFrame {
    /*
    The goal of this class is to allow a user to make a query for a book.
    A user can choose to search for a user by the first name and/or last name.
    To process this query, this GUI class will call the method in the
    system to return the query information back to the system. Hence,
    the system can process the information and display the appropriate result
    back to the user (see Task 6.5). A user should be able to navigate
    back to the main page (in the main system) via a button named “Back to Main Menu”.
     */
    UserRegistrationSystem userSys;

    private final JPanel typePanel = new JPanel();
    private final JPanel searchByPanel = new JPanel();
    private final JPanel buttonsPanel = new JPanel();

    private final JRadioButton userFirstName = new JRadioButton("Search by First Name");
    private final JRadioButton userLastName = new JRadioButton("Search by Last Name");
    private final JRadioButton firstAndLast = new JRadioButton("Search by Both");

    private final JLabel searchLabel = new JLabel("Enter details here:");
    private final JTextField searchText = new JTextField(15);

    private final JButton searchDisplay = new JButton("Search & Display");
    private final JButton backtoMainPage = new JButton("Back to Main Menu");
    private final JButton clear = new JButton("Clear");

    public UserQuery(UserRegistrationSystem userSys) {
        this.userSys = userSys;

        setTitle("Query");
        setLayout(new BorderLayout());
        setSize(400, 250);

        //Search Buttons
        ButtonGroup group = new ButtonGroup(); //Only one button can be selected at a time
        group.add(userFirstName);
        group.add(userLastName);
        group.add(firstAndLast);
        userFirstName.setSelected(true);
        typePanel.add(userFirstName);
        typePanel.add(userLastName);
        typePanel.add(firstAndLast);

        add(typePanel, "North");


        //Search bar
        searchByPanel.add(searchLabel);
        searchByPanel.add(searchText);
        add(searchByPanel, "West");


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
        userSys.closeQueryPage();
        userSys.displayCardInformationToPage();

        if (userFirstName.isSelected()) {
            userSys.searchByFirstNameAndDisplay(searchText.getText());
        } else if (userLastName.isSelected()) {
            userSys.searchByLastNameAndDisplay(searchText.getText());
        } //Add first and last search

    }

    private void clear() {
        searchText.setText("");
    }

    private void backToPreviousPage() {
        userSys.closeQueryPage();
        userSys.backToMain();
    }
}
