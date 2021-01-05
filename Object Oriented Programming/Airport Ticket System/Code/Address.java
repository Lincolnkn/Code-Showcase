import java.util.*;

public class Address implements Cloneable, Comparable{
    private String StreetNumber;
    private String StreetName;
    private String Suburb;
    private String City;
    private String State;
    private String Postcode;

    //Constructor
    public Address(String streetNumber, String streetName, String suburb, String city, String state, String postcode) {
        StreetNumber = streetNumber;
        StreetName = streetName;
        Suburb = suburb;
        City = city;
        State = state;
        Postcode = postcode;
    }

    public void setStreetName(String streetName) {
        StreetName = streetName;
    }

    //Returns object as string split by , comma
    public String getAddressToSaveToTextFile() {
        return StreetName + "," + StreetNumber + "," + Suburb + "," + City + "," + State + "," + Postcode;
    }

    @Override
    public String toString() {
        return String.format("Address: %s, %s, %s, %s, %s, %s ", this.StreetNumber, this.StreetName, this.Suburb
                , this.City, this.State, this.Postcode);
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }

    @Override
    public Address clone() throws CloneNotSupportedException {
        return (Address) super.clone();
    }
}
