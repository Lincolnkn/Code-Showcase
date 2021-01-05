package Knowles6701620;

import javax.swing.*;
import java.awt.*;

/*
 *
 *  Lincoln Knowles
 */
public class MainPage extends JFrame {
    /*
    The goal is to allow the system to use this class to present the menu to a user.
    A user can choose to create a new book or user, search for a book or user,
    or exit the application.
     */

    private MainSystem main;

    JButton bookReg = new JButton("Register New Book");
    JButton bookSearch = new JButton("Search Book");

    JButton userReg = new JButton("Register New User");
    JButton userSearch = new JButton("Search User");

    JButton exit = new JButton("Exit");

    public MainPage(MainSystem mainSystem) {
        main = mainSystem;

        //Sets gaps between areas

        setLayout(new FlowLayout());
        setSize(300, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Main Page");
        setVisible(true);

        add(bookReg);
        bookReg.addActionListener(e -> bookRegButtonAction());
        add(bookSearch);
        bookSearch.addActionListener(e -> bookSearchButtonAction());

        add(userReg);
        userReg.addActionListener(e -> userRegButtonAction());
        add(userSearch);
        userSearch.addActionListener(e -> userSearchButtonAction());

        add(exit);
        exit.addActionListener(e -> exitButtonAction());
    }

    //Listeners
    public void bookRegButtonAction() {
        this.setVisible(false);
        main.openRegisterBook();
    }

    public void bookSearchButtonAction() {
        this.setVisible(false);
        main.openBookSearch();
    }

    public void userRegButtonAction() {
        this.setVisible(false);
        main.openRegisterUser();
    }

    public void userSearchButtonAction() {
        this.setVisible(false);
        main.openUserSearch();
    }

    private void exitButtonAction() {
        System.exit(0);
    }
}
