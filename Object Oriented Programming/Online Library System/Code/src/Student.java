package Knowles6701620;

/*
 *
 *  Lincoln Knowles
 */
public class Student extends User {
    /*
    Student class. (0.5 mark)
    The purpose of this class is to keep the student’s information.
    - Student class has two attributes more than user class which are course, degree.
     */
    private String course;
    private String degree;

    public Student(int id, String firstName, String lastName, String userName, String pass, UserType userType, String course, String degree) {
        super(id, firstName, lastName, userName, pass, userType);
        this.course = course;
        this.degree = degree;
    }

    public String getCourse() {
        return course;
    }

    public String getDegree() {
        return degree;
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %s", super.toString(), course, degree);
    }
}

