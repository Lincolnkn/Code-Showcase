package Knowles6701620;

/*
 *
 *  Lincoln Knowles
 */
public class Staff extends User {
    /*
    Staff class. (0.5 mark)
    - The purpose of this class is to keep the staff’s information.
    - Staff class has one attribute more than user class which is position.
     */private String position;

    public Staff(int id, String firstName, String lastName, String userName, String pass, UserType userType, String position) {
        super(id, firstName, lastName, userName, pass, userType);
        this.position = position;
    }

    public String getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return String.format("%s, %s", super.toString(), position);
    }
}
