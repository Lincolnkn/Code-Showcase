package Knowles6701620;

import javax.swing.*;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/*
 *
 *  Lincoln Knowles
 */
public class UserRegistrationSystem {
    /*
     The goal of this system (class) is to manage the books and its related information.
     It should have Lists or Maps to keep the user information. Its functions are to
     connect other systems and process information that a user inputs through GUI.
     */
/*    private ArrayList<Book> books;
    private ArrayList<BookAuthorRecords> bookAuthorRecords;
    HashMap<Integer, Integer> bookLocationRecords;*/
    private ArrayList<User> users;

    //GUI Pages
    private UserInformationDisplay display;
    private UserQuery query;
    private UserRegistration userReg;

    private MainSystem main;


    public UserRegistrationSystem(MainSystem main) {
        this.main = main;

/*        books = new ArrayList<>();
        bookAuthorRecords = new ArrayList<>();
        bookLocationRecords = new HashMap<>();*/
        users = new ArrayList<>();
    }


    public void registerUser(String[] userInfo) {
        /*
        The system should provide a method that opens a user registration page for a user to input
        information. This information is need to create the user record in the system. You will need
        to write another method to get this data back. When a user submitted the data, it will call
        this method to pass the data back from the GUI to the system. At the last step, it will
        process the data and create a new user and add them to the system. After registering the
        new user, it should display the user’s information on the windows
        (via the Book Information Display). (0.9 mark)
         */
        Random random = new Random();
        int rand = 100000 + random.nextInt(900000);
        int userID = 1 + rand;
        try {
            if (userInfo[4].equals("Student")) {
                Student student = new Student(userID, userInfo[0], userInfo[1],
                        userInfo[2], userInfo[3], UserType.Student, userInfo[5], userInfo[6]);
                users.add(student);
            } else {
                Staff staff = new Staff(userID, userInfo[0], userInfo[1],
                        userInfo[2], userInfo[3], UserType.getUserType(userInfo[4]), userInfo[5]);
                users.add(staff);
            }
        } catch (NullPointerException ex) {
            JOptionPane.showMessageDialog(display, "Null Pointer");
        } catch (IndexOutOfBoundsException ex) {
            JOptionPane.showMessageDialog(display, "Out of bounds");
        }

    }

    public void searchByFirstNameAndDisplay(String firstName) {
        if (users.isEmpty()) {
            JOptionPane.showMessageDialog(display, "No Users Registered");
        }

        for (User user : users) {
            if (user.getFirstName().equals(firstName)) {
                display.displayInformationToTextArea(user);
            } else {
                JOptionPane.showMessageDialog(display, "No User Found");
            }
        }
    }

    public void searchByLastNameAndDisplay(String lastName) {
        if (users.isEmpty()) {
            JOptionPane.showMessageDialog(display, "No Users Registered");
        }

        for (User user : users) {
            if (user.getLastName().equals(lastName)) {
                display.displayInformationToTextArea(user);
            } else {
                JOptionPane.showMessageDialog(display, "No User Found");
            }
        }
    }

    //GUI methods
    public void closeUserDisplay() {
        display.setVisible(false);
        display = null;
    }

    public void displayCardInformationToPage() {
        display = new UserInformationDisplay(this);
        display.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        display.setVisible(true);
    }

    protected void displayUserInformationToPage(String[] userInfo) {
        display = new UserInformationDisplay(this);
        display.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        display.displayInformationToTextArea(userInfo);
        display.setVisible(true);
    }

    public void openCreateUserPage() {
        userReg = new UserRegistration(this);
        userReg.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        userReg.setVisible(true);
    }

    public void openQueryPage() {
        query = new UserQuery(this);
        query.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        query.setVisible(true);
    }

    public void backToMain() {
        /*
        The system should provide a method that allows the user to navigate back to
        the main page in the MainSystem. (0.2 marks)
         */
        main.backToMainMenu();
    }

    public void closeBookDisplay() {
        display.setVisible(false);
        display = null;
    }

    public void closeQueryPage() {
        query.setVisible(false);
        query = null;
    }

    public void closeBookRegisterPage() {
        userReg.setVisible(false);
        userReg = null;
    }


    //Read & Write objects
    public void readObjectFile(String studentFileName, String staffFileName) {
        /*
        The system should provide a method that allows the MainSystem (calling
        this method with the file name(s) as input) to load the user’s
        information from a file. (0.5 marks)
         */
        ObjectInputStream stInputStream = null; //Students
        ObjectInputStream sfInputStream = null; //Staff
        try {
            stInputStream = new ObjectInputStream(Files.newInputStream(Paths.get(studentFileName)));
            sfInputStream = new ObjectInputStream(Files.newInputStream(Paths.get(staffFileName)));
            while (true) {
                Student s = (Student) stInputStream.readObject();
                users.add(s);
                Staff sf = (Staff) sfInputStream.readObject();
                users.add(sf);
            }
        } catch (EOFException ex) {
            System.err.println("1r: No more records!");
        } catch (ClassNotFoundException ex) {
            System.err.println("2r: Error in wrong lass in the file.");
        } catch (IOException ex) {
            System.err.println("3r: Error in create/open the file");
            System.exit(1);
        } finally {
            if (stInputStream != null && sfInputStream != null) {
                try {
                    stInputStream.close();
                    sfInputStream.close();
                } catch (IOException ex) {
                    System.err.println("Error in close the file.");
                }
            }
        }
    }

    public void writeObjectToFile(String studentFileName, String staffFileName) {
        /*
        The system should provide a method that allows the MainSystem (calling this method
        with the file name(s) as input) to save the user’s information to a file. (0.5 marks)
         */
        ObjectOutputStream stOutputStream = null; //Student
        ObjectOutputStream sfOutputStream = null;  //Staff
        try {
            stOutputStream = new ObjectOutputStream(Files.newOutputStream(Paths.get(studentFileName)));
            sfOutputStream = new ObjectOutputStream(Files.newOutputStream(Paths.get(staffFileName)));

            //Creates new files under string name
            for (User u : users) {
                if (u.getUserType() == UserType.Student) {
                    stOutputStream.writeObject(u);
                } else {
                    sfOutputStream.writeObject(u);
                }
            }
        } catch (IOException ex) {
            System.err.println("1w: Error in create/open the file.");
            System.exit(1);
        } finally {
            if (stOutputStream != null && sfOutputStream != null) {
                try {
                    stOutputStream.close();
                    sfOutputStream.close();
                } catch (IOException ex) {
                    System.err.println("2w: Error in close the file.");
                }
            }
        }
    }

    //Methods aiding Read And Write
    public void addUser(User user) {
        users.add(user);
    }

    public void showAllUsers(){
        System.out.println("Users:");
        for (User u : users) {
            System.out.println(u);
            System.out.println(u.getUserType());
        }
    }
}

