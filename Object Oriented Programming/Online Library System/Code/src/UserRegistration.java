package Knowles6701620;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/*
 *
 *  Lincoln Knowles
 */
public class UserRegistration extends JFrame {
    /*
    The goal of this class is to allow a user to input the information of the new user
    for registering this user to the system. When a user clicked at the “Create New User”
    button, it calls the method in the system (see Task 6.4) to pass the information
    back to the system (pass them as a List object or an array of String or a Map object).
    A user should be able to navigate back to the main page (in the main system) via a
    button named “Back to Main Menu”.
     */
    private final JLabel userType = new JLabel("UserType");
    private final String[] userTypes = new String[]{"Student", "General Staff", "Admin Staff", "Academic Staff", "Librarian"};
    private final JComboBox<String> userTypeList = new JComboBox<>(userTypes);
    private String selectedUserType;

    private final JLabel firstName = new JLabel("First Name: ");
    private final JTextArea firstNameText = new JTextArea();

    private final JLabel lastName = new JLabel("Last Name: ");
    private final JTextArea lastNameText = new JTextArea();

    private final JLabel userName = new JLabel("User Name: ");
    private final JTextArea userNameText = new JTextArea();

    private final JLabel pass = new JLabel("Password: "); //Could change this to *****??
    private final JTextArea passText = new JTextArea();

    //Will grey out upon combo box selection
    private final JLabel course = new JLabel("Student Course: ");
    private final JTextArea courseText = new JTextArea();

    private final JLabel degree = new JLabel("Student Degree: ");
    private final JTextArea degreeText = new JTextArea();

    private final JLabel position = new JLabel("Staff Position: ");
    private final JTextArea positionText = new JTextArea();

    private final JButton register = new JButton("Register");
    private final JButton clear = new JButton(("Clear"));
    private final JButton back = new JButton("Back");

    private UserRegistrationSystem userSys;

    private final String[] userInfo = new String[8];

    public UserRegistration(UserRegistrationSystem userSys) {
        this.userSys = userSys;



        GridLayout gridLayout = new GridLayout(12, 3);
        setTitle("Register");
        //Sets gaps between areas
        gridLayout.setVgap(10);
        gridLayout.setHgap(10);
        setLayout(gridLayout);
        //setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 450);

        //Adds user combox box label & types
        add(userType);
        add(userTypeList);
        positionText.setEditable(false);
        positionText.setBackground(Color.black);

        degreeText.setEditable(true);
        degreeText.setBackground(Color.white);

        courseText.setEditable(true);
        courseText.setBackground(Color.white);
        userTypeList.addItemListener(e -> userTypeAction());

        //Adds labels and text areas
        setLabelUI(firstName, firstNameText);
        setLabelUI(lastName, lastNameText);
        setLabelUI(userName, userNameText);
        setLabelUI(pass, passText);
        setLabelUI(course, courseText);
        setLabelUI(degree, degreeText);
        setLabelUI(position, positionText);

        //Adds buttons
        add(register);
        register.addActionListener(e -> openUserRegister());
        add(clear);
        clear.addActionListener(e -> clear());
        add(back);
        back.addActionListener(e -> backToPreviousPage());

    }

    public void openUserRegister() {
        returnBookRegisterInfo();

        setVisible(false);
        userSys.displayUserInformationToPage(userInfo);
    }

    public void returnBookRegisterInfo() {
        userInfo[0] = firstNameText.getText();
        userInfo[1] = lastNameText.getText();
        userInfo[2] = userNameText.getText();
        userInfo[3] = passText.getText();
        userInfo[4] = userTypeList.getSelectedItem().toString();

        if (userInfo[4].equals("Student")) {
            userInfo[5] = courseText.getText();
            userInfo[6] = degreeText.getText();
        } else {
            userInfo[5] = positionText.getText();
            userInfo[6] = "";
        }

        userSys.registerUser(userInfo);
    }

    private void userTypeAction() {
        //Greys out the other user attributes.
        //If student is the selected the user can only select student stuff.
        selectedUserType = userTypeList.getSelectedItem().toString();

        if (selectedUserType.equals("Student")) {
            positionText.setEditable(false);
            positionText.setBackground(Color.black);

            degreeText.setEditable(true);
            degreeText.setBackground(Color.white);

            courseText.setEditable(true);
            courseText.setBackground(Color.white);
        } else {
            degreeText.setEditable(false);
            degreeText.setBackground(Color.black);

            courseText.setEditable(false);
            courseText.setBackground(Color.black);

            positionText.setEditable(true);
            positionText.setBackground(Color.white);

        }
    }

    //Clears all text
    private void clear() {
        firstNameText.setText("");
        lastNameText.setText("");
        userNameText.setText("");
        passText.setText("");
    }

    //Returns to main menu
    private void backToPreviousPage() {
        userSys.closeBookRegisterPage();
        userSys.backToMain();
    }

    //Adds label & text area.
    private void setLabelUI(JLabel label, JTextArea textArea) {
        add(label);
        textArea.setColumns(15);
        add(textArea);
    }
}
