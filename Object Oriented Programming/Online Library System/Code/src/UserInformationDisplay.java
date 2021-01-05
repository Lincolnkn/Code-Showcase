package Knowles6701620;

import javax.swing.*;
import java.awt.*;

/*
 *
 *  Lincoln Knowles
 */
public class UserInformationDisplay extends JFrame{
    /*
    Design User Information Display class (extend from JFrame or JPanel). The goal is to allow
    the system to use this class to display user’s information in the text area. A user should
    be able to navigate back to the main page (in the main system) via a button named
    “Back to Main Menu”.
     */

    private UserRegistrationSystem userSys;

    private final JButton backtoMainPage = new JButton("Back to Main Menu");
    private final JButton clearText = new JButton("Clear");
    private JTextArea textArea = new JTextArea();
    private JScrollPane scrollPane = new JScrollPane();

    public UserInformationDisplay(UserRegistrationSystem userSys) {
        this.userSys = userSys;



        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(550, 250);
        setLayout(new FlowLayout());
        setTitle("User Display");
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

        add(textArea);
        textArea.setEditable(false); //Unable to change info
        textArea.setColumns(45);
    }

    public void displayInformationToTextArea(String[] info) {
        String userInfo = "";

        for (int i = 0; i < info.length - 1; i++) {
            userInfo += info[i] + ", ";
        }

        //Display area for card info
        textArea.setText(userInfo);
    }

    public void displayInformationToTextArea(User user) {
        //Display area for card info
        textArea.setText(user.toString());
    }

    private void clear() {
        textArea.setText("");
    }

    private void backToPreviousPage() {
        userSys.closeUserDisplay();
        userSys.backToMain();
    }
}
