package Knowles6701620;

import java.util.HashMap;

/*
 *
 *  Lincoln Knowles
 */
public class GenTestingData {
    public static void main(String[] args) {
        /*
        Write a class name “GenTestingData” with “main” method that Java can start the program.
        Create multiples objects in the Task 1 and add them to the systems. Call the method that
        write the system data to files. The purpose of this class is to generate a
        data for testing the systems. (1 marks)
         */

        MainSystem main = new MainSystem();
        BookRegistrationSystem bookSys = new BookRegistrationSystem(main);
        UserRegistrationSystem userSys = new UserRegistrationSystem(main);

        //Book 1 Information
        Book book1 = new Book(94512, 2543, "005.133/JAV-1/54 c.4", "Java For Dummies",
                "Java research book.", "Black Books", BookStatus.Active);

        Author author1 = new Author(254721, "Mr", "Link", "D", "Smith");
        Author author2 = new Author(287964, "Mrs", "Catherine", "Evelyn", "Knowles");
        HashMap<Integer, Integer> bookAuthor = new HashMap<>();
        bookAuthor.put(author1.getId(), 1);
        bookAuthor.put(author2.getId(), 2);

        BookAuthorRecords bookAuthorRecords = new BookAuthorRecords(14572, book1.getId(), bookAuthor);

        Location location1 = new Location(28739, "Wollongong Campus", 5, 217);

        //Add Object to lists:
        bookSys.addObjects(book1, author1, author2, bookAuthorRecords, location1);


        //Book 2 Information
        Book book2 = new Book(97482, 1427, "012.753/PY-1/51 e.8", "Python Nightmares",
                "The Worst Python Stories", "Phoenix: Enterprise Press", BookStatus.Removed);

        Author author3 = new Author(278435, "Mr", "Mathew", "C", "Moloney");
        HashMap<Integer, Integer> bookAuthor2 = new HashMap<>();
        bookAuthor2.put(author3.getId(), 1);

        BookAuthorRecords bookAuthorRecords2 = new BookAuthorRecords(17428, book2.getId(), bookAuthor2);

        Location location2 = new Location(21438, "Innovation Campus", 1, 902);

        //Add Objects
        bookSys.addObjects(book2, author3, bookAuthorRecords2, location2);

        System.out.println("1--------------------------------");
        bookSys.showAllObjects();


        bookSys.writeObjectToFile("BooksTest.bin", "AuthorTest.bin", "LocationTest.bin", "BookAuthorRecords.bin", "BookLocationRecords.bin");
        bookSys.readObjectFile("BooksTest.bin", "AuthorTest.bin", "LocationTest.bin", "BookAuthorRecords.bin", "BookLocationRecords.bin");
        System.out.println("2--------------------------------");
        bookSys.showAllObjects();



        //User Objects
        //Students
        Student student1 = new Student(1254934, "Chris", "Davis", "chd", "1234"
                , UserType.Student, "CSIT113", "Business");

        Student student2 = new Student(1482349, "Sal", "Franklin", "saf", "09786"
                , UserType.Student, "CSIT121", "CompSci");

        //Staff
        Staff staff1 = new Staff(1284593, "Brenda", "McClop", "bmc", "abcde"
                , UserType.AcademicStaff, "Chairman");

        Staff staff2 = new Staff(1894357, "Roger", "Yert", "rye", "lkjhg"
                , UserType.GeneralStaff, "Janitor");

        //Add to List
        userSys.addUser(student1);
        userSys.addUser(student2);
        userSys.addUser(staff1);
        userSys.addUser(staff2);

        System.out.println("3--------------------------------");
        userSys.showAllUsers();

        userSys.writeObjectToFile("StudentTest.bin", "StaffTest.bin");
        userSys.readObjectFile("StudentTest.bin", "StaffTest.bin");

        System.out.println("4--------------------------------");
        userSys.showAllUsers();
    }
}
