package Knowles6701620;


import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/*
 *
 *  Lincoln Knowles
 */
public class BookRegistrationSystem {
    /*
    Design Book Registration System class: The goal of this system (class) is to manage
    the books and its related information. It should have Lists or Maps to keep a record
    of book and its associate information. Its functions are to connect other systems
    and process information that a user inputs through GUI.
     */

    private ArrayList<Book> books;
    private ArrayList<BookAuthorRecords> bookAuthorRecords;
    private ArrayList<Author> authors;
    private HashMap<Integer, Integer> bookLocationRecords; //Book ID : Location ID
    private ArrayList<Location> locations;

    //GUI Pages
    private BookInformationDisplay display;
    private BookAuthorQuery query;
    private BookRegistration bookReg;

    private int bookID;
    private int locationID;
    private int authorID1;
    private int authorID2;

    private MainSystem main;

    public BookRegistrationSystem(MainSystem main) {
        this.main = main;

        books = new ArrayList<>();
        bookAuthorRecords = new ArrayList<>();
        bookLocationRecords = new HashMap<>();
        authors = new ArrayList<>();
        locations = new ArrayList<>();
    }

    //Getters
    public int getBookID() {
        return bookID;
    }

    public int getLocationID() {
        return locationID;
    }

    public int getAuthorID1() {
        return authorID1;
    }

    public int getAuthorID2() {
        return authorID2;
    }

    //Register & Process
    public void registerBook(String[] book, String[] author, String[] location) {
        /*
        When a user submits the data, it will call this method to
        pass the data back from the GUI to the system. At the last step, it will process
        the data and create a new record of book, author and location, and add them to
        the system. After registering the book and its associated records, it should
        display all information about the book on the windows (via the Book Information
        Display). (1 mark)
         */

        try {
            //Book Register
            //Book ID Starts with 9, 5 digits long
            Random random = new Random();
            bookID = 9 + 1000 + random.nextInt(9000);

            //Creates a new book and adds to array
            if (book[5].equals("A")) {
                Book newBook = new Book(bookID, Integer.parseInt(book[0]), book[1],
                        book[2], book[3], book[4], BookStatus.Active);
                books.add(newBook);
            } else {
                Book newBook = new Book(bookID, Integer.parseInt(book[0]), book[1],
                        book[2], book[3], book[4], BookStatus.Removed);
                books.add(newBook);
            }

            //Author & Record Register
            //Author ID Starts with 2, 6 digits long
            authorID1 = 2 + 10000 + random.nextInt(90000);
            authorID2 = 2 + 10000 + random.nextInt(90000);
            //Record ID Starts with 1, 5 digits long
            int randR = 1000 + random.nextInt(9000);
            int recordID = 1 + randR;
            HashMap<Integer, Integer> authorRecord = new HashMap<>();

            if (author[0].equals("1")) { //Adds 1 author to authors and records
                Author author1 = new Author(authorID1, author[1], author[2], author[3], author[4]);
                authors.add(author1);
                authorRecord.put(author1.getId(), 1);
            } else { //Adds 2 authors to authors and records
                Author author1 = new Author(authorID1, author[1], author[2], author[3], author[4]);
                Author author2 = new Author(authorID2, author[5], author[6], author[7], author[8]);
                authors.add(author1);
                authors.add(author2);
                authorRecord.put(author1.getId(), 1);
                authorRecord.put(author2.getId(), 2);
            }

            BookAuthorRecords bookAuthorRecord = new BookAuthorRecords(recordID, bookID, authorRecord);
            bookAuthorRecords.add(bookAuthorRecord);


            //Location Register
            //Location ID Starts with 2, 5 digits long
            locationID = 2 + randR;
            Location location1 = new Location(locationID, location[0], Integer.parseInt(location[1]), Integer.parseInt(location[2]));
            locations.add(location1);
            bookLocationRecords.put(bookID, locationID);
        } catch (NullPointerException ex) {
            JOptionPane.showMessageDialog(display, "Null Pointer");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(display, "Please enter correct details");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(display, "Exception");
        }
    }

    /*
    The system may get back the data via another method in this class and process it to
    search for the book with the given input information. After obtaining the book, it
    should display the all information about the book including its author’s names and
    its location on the window (via the Book Information Display). (1 mark)
     */
    public void searchByBookTitleAndDisplay(String bookTitle) {
        int locationID = 0;
        int bookIdTemp = 0;
        ArrayList<Author> authorsList = new ArrayList<>();

        if (books.isEmpty()) {
            JOptionPane.showMessageDialog(display, "No Books Registered");
        }

        for (Book book : books) {
            if (book.getTitle().equals(bookTitle)) {
                bookIdTemp = book.getId();
                locationID = searchBookLocationByBookID(bookIdTemp);
                for (Location l : locations) {
                    if (l.getId() == locationID) {
                        for (BookAuthorRecords bookAuthorRecord : bookAuthorRecords) {
                            if (bookAuthorRecord.getBookID() == bookIdTemp) {
                                if (bookAuthorRecord.getAuthorID().size() == 1) {
                                    for (int a : bookAuthorRecord.getAuthorID()) { //Holds both authors IDs
                                        for (Author author : authors) {
                                            if (author.getId() == a) {
                                                display.displayInformationToTextArea(book, bookAuthorRecord, l, author);
                                            }
                                        }
                                    }
                                } else {
                                    for (int a : bookAuthorRecord.getAuthorID()) {
                                        for (Author author : authors) {
                                            if (author.getId() == a) {
                                                authorsList.add(author);
                                            }
                                        }
                                    }
                                    display.displayInformationToTextArea(book, bookAuthorRecord, l, authorsList);
                                }
                            }
                        }
                    }
                }
            }
        }


    }

    public void searchByAuthorNameAndDisplay(String aFirstN, String aLastN) {
        if (books.isEmpty()) {
            JOptionPane.showMessageDialog(display, "No Books Registered");
        }


        int authorIDTemp = 0;
        int bookIdTemp = 0;
        int locationIdTemp = 0;

        for (Author a : authors) {
            if (aFirstN.equals(a.getFirstName()) || aLastN.equals(a.getLastName())) {
                authorIDTemp = a.getId();
            } else {
                JOptionPane.showMessageDialog(display, "No Authors Found");
            }
        }

        //Use Author ID to match bookID in records. With The record find book id then search for the correct match
        for (BookAuthorRecords bookAuthorRecord : bookAuthorRecords) {
            for (int a : bookAuthorRecord.getAuthorID()) {
                if (authorIDTemp == a) { //Finds all author IDs
                    bookIdTemp = bookAuthorRecord.getBookID();
                    if (bookAuthorRecord.getAuthorID().size() == 1) {
                        for (Book book : books) { //Search book
                            if (bookIdTemp == book.getId()) { //Check ID
                                locationIdTemp = searchBookLocationByBookID(book.getId()); //Returns matching location ID
                                for (Location l : locations) { //Search Location
                                    if (l.getId() == locationIdTemp) { //Matches location
                                        for (Author author : authors) {
                                            if (authorIDTemp == author.getId()) {
                                                display.displayInformationToTextArea(book, bookAuthorRecord, l, author); //Displays
                                            } else {
                                                JOptionPane.showMessageDialog(display, "No Authors Found");
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        for (Book book : books) { //Search book
                            if (bookIdTemp == book.getId()) { //Check ID
                                locationIdTemp = searchBookLocationByBookID(book.getId()); //Returns matching location ID
                                for (Location l : locations) { //Search Location
                                    if (l.getId() == locationIdTemp) { //Matches location
                                        display.displayInformationToTextArea(book, bookAuthorRecord, l, authorsFromBookRecord(bookAuthorRecord)); //Displays
                                    } else {
                                        JOptionPane.showMessageDialog(display, "No Authors Found");
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public ArrayList<Author> authorsFromBookRecord(BookAuthorRecords bookAuthorRecords) {
        ArrayList<Author> authorsList = new ArrayList<>();

        for (int authorsID : bookAuthorRecords.getAuthorID()) {
            for (Author author : authors) {
                if (author.getId() == authorsID) {
                    authorsList.add(author);
                }
            }
        }

        return authorsList;
    }

    public BookAuthorRecords getWithBookID(int bookIdT) {
        //Returns record from search by book ID
        for (BookAuthorRecords bookRecord : bookAuthorRecords) {
            if (bookIdT == bookRecord.getBookID()) {
                return bookRecord;
            }
        }
        return null;
    }

    public int searchBookLocationByBookID(int bookID) {
        //Searches hash map using bookID. Returns matching Location ID
        for (Map.Entry<Integer, Integer> entry : bookLocationRecords.entrySet()) {
            if (entry.getKey() == bookID) {
                return entry.getValue();
            }
        }
        return 0;
    }

    //GUI Methods
    public void displayCardInformationToPage() {
        display = new BookInformationDisplay(this);
        display.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        display.setVisible(true);
    }

    protected void displayCardInformationToPage(int authorNum, String[] bookInfo, String[] authorInfo, String[] locationInfo) {
        display = new BookInformationDisplay(this);
        display.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        if (authorNum == 1) {
            display.displayInformationToTextArea(1, bookInfo, authorInfo, locationInfo);
        } else {
            display.displayInformationToTextArea(2, bookInfo, authorInfo, locationInfo);
        }
        display.setVisible(true);
    }

    public void openCreateBookPage() {
        /*
        The system should provide a method that opens a creating book page for a user to
        input the information of the book. It is used to create a book, a book-authors,
        and a book-location records.
         */
        bookReg = new BookRegistration(this);
        bookReg.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        bookReg.setVisible(true);
    }

    public void openQueryPage() {
        /*
        The system should provide a method that opens a page to query the information to
        search for a book from a user. The information may be the book title or author name.
         */
        query = new BookAuthorQuery(this);
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
        bookReg.setVisible(false);
        bookReg = null;
    }

    //Read & Write objects
    public void readObjectFile(String bookFile, String authorFile, String locationFile,
                               String bookAuthorRecord, String bookLocationRecord) {
        /*
        The system should provide a method that allows the MainSystem (calling this method
        with the file name(s) as input) to save data to files (e.g., books, authors and locations).
        (0.9 marks)
         */
        ObjectInputStream bInputStream = null;
        ObjectInputStream aInputStream = null;
        ObjectInputStream lInputStream = null;
        ObjectInputStream rInputStream = null;
        ObjectInputStream blInputStream = null;

        try {
            bInputStream = new ObjectInputStream(Files.newInputStream(Paths.get(bookFile)));
            aInputStream = new ObjectInputStream(Files.newInputStream(Paths.get(authorFile)));
            lInputStream = new ObjectInputStream(Files.newInputStream(Paths.get(locationFile)));
            rInputStream = new ObjectInputStream(Files.newInputStream(Paths.get(bookAuthorRecord)));

            blInputStream = new ObjectInputStream(Files.newInputStream(Paths.get(bookLocationRecord)));

            while (true) {
                Book b = (Book) bInputStream.readObject();
                books.add(b);
                Author a = (Author) aInputStream.readObject();
                authors.add(a);
                Location l = (Location) lInputStream.readObject();
                locations.add(l);
                BookAuthorRecords r = (BookAuthorRecords) rInputStream.readObject();
                bookAuthorRecords.add(r);

                bookLocationRecords = (HashMap<Integer, Integer>) blInputStream.readObject();
            }
        } catch (EOFException ex) {
            System.err.println("1r: No more records!");
        } catch (ClassNotFoundException ex) {
            System.err.println("2r: Error in wrong lass in the file.");
        } catch (IOException ex) {
            System.err.println("3r: Error in create/open the file");
            System.exit(1);
        } catch (Exception e) {
            System.out.println("Hashmap error");
        } finally {
            if (bInputStream != null && aInputStream != null && lInputStream != null && rInputStream != null
                    && blInputStream != null) {
                try {
                    bInputStream.close();
                    aInputStream.close();
                    lInputStream.close();
                    bInputStream.close();
                    blInputStream.close();
                } catch (IOException ex) {
                    System.err.println("Error in close the file.");
                }
            }
        }
    }


    public void writeObjectToFile(String bookFile, String authorFile, String locationFile,
                                  String bookAuthorRecord, String bookLocationRecord) {
        /*
        The system should provide a method that allows the MainSystem
        (calling this method with the file name(s) as input) to save data to files
        (e.g., books, authors and locations). (0.9 marks)
         */
        ObjectOutputStream bOutputStream = null;
        ObjectOutputStream aOutputStream = null;
        ObjectOutputStream lOutputStream = null;
        ObjectOutputStream rOutputStream = null;
        ObjectOutputStream blOutputStream = null;

        try {
            bOutputStream = new ObjectOutputStream(Files.newOutputStream(Paths.get(bookFile)));
            aOutputStream = new ObjectOutputStream(Files.newOutputStream(Paths.get(authorFile)));
            lOutputStream = new ObjectOutputStream(Files.newOutputStream(Paths.get(locationFile)));
            rOutputStream = new ObjectOutputStream(Files.newOutputStream(Paths.get(bookAuthorRecord)));
            blOutputStream = new ObjectOutputStream(Files.newOutputStream(Paths.get(bookLocationRecord)));

            //Creates new files under string name
            for (Book b : books) {
                bOutputStream.writeObject(b);
            }
            for (Author a : authors) {
                aOutputStream.writeObject(a);
            }
            for (Location l : locations) {
                lOutputStream.writeObject(l);
            }
            for (BookAuthorRecords bA : bookAuthorRecords) {
                rOutputStream.writeObject(bA);
            }

            blOutputStream.writeObject(bookLocationRecords);
        } catch (IOException ex) {
            System.err.println("1w: Error in create/open the file.");
            System.exit(1);
        } finally {
            if (bOutputStream != null && aOutputStream != null && lOutputStream != null
                    && rOutputStream != null && blOutputStream != null) {
                try {
                    bOutputStream.close();
                    aOutputStream.close();
                    lOutputStream.close();
                    rOutputStream.close();
                    blOutputStream.close();
                } catch (IOException ex) {
                    System.err.println("2w: Error in close the file.");
                }
            }
        }
    }

    public void showAllObjects() { //Test Method to quickly check what is in each list
        System.out.println("Books:");
        for (int i = 0; i < books.size(); i++) {
            System.out.println(books.get(i));
        }
        System.out.println("Authors:");
        for (int i = 0; i < authors.size(); i++) {
            System.out.println(authors.get(i));
        }
        System.out.println("Book Author Records:");
        for (int i = 0; i < bookAuthorRecords.size(); i++) {
            System.out.println(bookAuthorRecords.get(i));
        }
        System.out.println("Book Location Records:");
        System.out.println(bookLocationRecords.toString());

        System.out.println("Locations:");
        for (int i = 0; i < locations.size(); i++) {
            System.out.println(locations.get(i));
        }
    }

    public void addObjects(Book book, Author author, Author author2, BookAuthorRecords bookAuthorRecord, Location location) {
        books.add(book);
        authors.add(author);
        authors.add(author2);
        locations.add(location);
        bookLocationRecords.put(book.getId(), location.getId());
        bookAuthorRecords.add(bookAuthorRecord);
    }

    public void addObjects(Book book, Author author, BookAuthorRecords bookAuthorRecord, Location location) {
        books.add(book);
        authors.add(author);
        locations.add(location);
        bookLocationRecords.put(book.getId(), location.getId());
        bookAuthorRecords.add(bookAuthorRecord);
    }
}
