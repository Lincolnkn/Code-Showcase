package Knowles6701620;

import java.io.Serializable;

/*
 *
 *  Lincoln Knowles
 */
public abstract class User implements Serializable {
    /*
    User class. (1 mark)
    - The purpose of this class is to maintain a general user information
      and methods that will be used by the user registration system.
    - User class must have 6 attributes: identity number, first name,
      last name, username, password, and user type.
     */
    private int id;
    private String firstName;
    private String lastName;
    private String userName;
    private String pass;
    private UserType userType;

    public User(int id, String firstName, String lastName, String userName, String pass, UserType userType) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.pass = pass;
        this.userType = userType;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public UserType getUserType() {
        return userType;
    }

    @Override
    public String toString() {
        return String.format("%d, %s, %s, %s, %s, %s",
                id, firstName, lastName, userName, pass, userType.getName());
    }
}
