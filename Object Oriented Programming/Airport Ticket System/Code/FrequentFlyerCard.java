import java.security.PrivateKey;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

//Added four abstract methods below.
public abstract class FrequentFlyerCard implements StringForFile, Discount, Cloneable, Comparable<FrequentFlyerCard> {
    private String id; // Customer 10 digit ID
    private String name; //customers full name
    private int frequentFlyingPoint = 0; //Total points made from purchases. Default is 0 for new cards
    private LocalDate dateOfActivation; //date of activation
    private Address address; //Holds object of full address.

    //Constructors
    public FrequentFlyerCard(String name, Address address) {
        this.name = name;
        this.address = address;

    }

    protected FrequentFlyerCard(String id, String name, int frequentFlyingPoint,
                                LocalDate dateOfActivation, Address address) {
        this.id = id;
        this.name = name;
        this.frequentFlyingPoint = frequentFlyingPoint;
        this.dateOfActivation = dateOfActivation;
        this.address = address;

    }

    //Getters & Setters
    public String getID() {
        return this.id;
    }

    public void setId(String id) {
        //Validates ID. 10 digits
        String tenDigitRegex = "\\d{10}";

        if (!id.matches(tenDigitRegex)) {
            this.id = "Invalid id input";
        } else {
            this.id = id;
        }
    }

    public String getName() {
        return this.name;
    }

    public int getFFP() {
        return this.frequentFlyingPoint;
    }

    public LocalDate getDateOfActivation() {
        return dateOfActivation;
    }

    public void setDateOfActivation(LocalDate dateOfActivation) {
        this.dateOfActivation = dateOfActivation;
    }

    public Address getAddress() {
        return this.address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }


    //Fills String Array split by, comma
    public static String[] convertDataFromFileToStringArray(String input) {
        return input.split(",");
    }

    public static LocalDate convertStringDateToLocaleDate(String date) {
        //convert String to LocalDate
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        if (date.contains("-")) {
            return LocalDate.parse(date);
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/uuuu");

            return LocalDate.parse(date, formatter);
        }
    }


    //Clone
    @Override
    public Object clone() throws CloneNotSupportedException {
        FrequentFlyerCard clonedObj = (FrequentFlyerCard) super.clone();
        clonedObj.setAddress(getAddress().clone());
        return clonedObj;
    }


    public int compareTo(FrequentFlyerCard card) {
        //return Integer.valueOf(this.id).compareTo(Integer.valueOf(card.id));
        //return  this.getID().compareTo(card.id);
        if (Integer.parseInt(card.getID()) == Integer.parseInt(id)) {
            return 0;
        } else if (Integer.parseInt(card.getID()) > Integer.parseInt(id)) {
            return 1;
        } else {
            return -1;
        }
    }

    //String format version
    @Override
    public String toString() {
        String formattedDate = dateOfActivation.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        return String.format("%s, %s, %d, %s. %s",
                id, name, frequentFlyingPoint, formattedDate, address);
    }


    //Abstract methods
    public abstract double getCouponAmount();

    public abstract String getDataToSaveToTextFile();
}
