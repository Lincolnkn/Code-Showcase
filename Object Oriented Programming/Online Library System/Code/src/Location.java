package Knowles6701620;

import java.io.Serializable;

/*
 *
 *  Lincoln Knowles
 */
public class Location implements Serializable {
    /*
    Location class. (0.5 mark)
    -The purpose of this class is to keep the location’s information.
    - Location class has four attributes which are identity number,
      campus name, floor and shelf number.
     */
    private int id;
    private String campusName;
    private int floor;
    private int shelf; //Unsure if shelf may have letters in it.

    public Location(int id, String campusName, int floor, int shelfNum) {
        this.id = id;
        this.campusName = campusName;
        this.floor = floor;
        this.shelf = shelfNum;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return String.format("%d, %s, %d, %d",
                id, campusName, floor, shelf);
    }
}
