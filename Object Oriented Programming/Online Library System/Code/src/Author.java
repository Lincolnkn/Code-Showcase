package Knowles6701620;

import java.io.Serializable;

/*
 *
 *  Lincoln Knowles
 */
public class Author implements Serializable {
    /*
    Author class. (1 mark)
    -The purpose of this class is to keep the author’s information.
    -Author class has five attributes which are identity number,
      title, first name, middle name and last name.
     */
    private int id;
    private String title;
    private String firstName;
    private String middleName;
    private String lastName;

    public Author(int id, String title, String firstName, String middleName, String lastName) {
        this.id = id;
        this.title = title;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
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

    @Override
    public String toString() {
        return String.format("%d, %s, %s, %s, %s",
                id, title, firstName, middleName, lastName);
    }
}
