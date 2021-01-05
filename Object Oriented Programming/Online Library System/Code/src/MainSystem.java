package Knowles6701620;

import javax.swing.*;

/*
 *
 *  Lincoln Knowles
 */
public class MainSystem {
    /*
    Connect a user to the Book Registration System and User Registration System.
     */

    private UserRegistrationSystem userSys;
    private BookRegistrationSystem bookSys;

    private MainPage mainPage;

    public MainSystem() {
    }

    public void openPage() {
        mainPage = new MainPage(this);
        userSys = new UserRegistrationSystem(this);
        bookSys = new BookRegistrationSystem(this);
    }

    public void openRegisterBook() {
        bookSys.openCreateBookPage();
    }

    public void openBookSearch() {
        bookSys.openQueryPage();
    }

    public void openRegisterUser() {
        userSys.openCreateUserPage();
    }

    public void openUserSearch() {
        userSys.openQueryPage();
    }

    public void backToMainMenu() {
        mainPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainPage.setVisible(true);
    }
}
