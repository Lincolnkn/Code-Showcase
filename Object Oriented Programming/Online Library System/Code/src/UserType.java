package Knowles6701620;

/*
 *
 *  Lincoln Knowles
 */
public enum UserType {
    /*
    User Type (Enum class) (0.5 marks)
        -It contains Student, General Staff, Admin Staff, Academic Staff, Librarian.
        -The abbreviation of each record should be designed and used in the code
         instead of its full name.
        -It should have a method to obtain its full name.
        -The toString method outputs all detail.
     */
    Student("Student", 1), GeneralStaff("General Staff", 2), AdminStaff("Admin Staff", 3),
    AcademicStaff("Academic Staff", 4), Librarian("Librarian", 4);


    private String name;
    private int code;

    UserType(String name, int code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String toString() {
        return String.format("%s, %d", this.name, this.code);
    }

    public static UserType getUserType(String userType) {
        for (UserType user : UserType.values()) {
            if (user.getName().equals(userType)) {
                return user;
            }
        }
        return null;
    }
}
