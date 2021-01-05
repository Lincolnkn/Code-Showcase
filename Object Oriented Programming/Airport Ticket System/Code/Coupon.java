import java.util.*;


public class Coupon implements StringForFile, Discount, Cloneable, Comparable<Coupon> {
    private String id;
    private double value;

    public Coupon(String id, double value) {
        this.id = id;
        this.value = value;
    }

    public Coupon(double value) {
        this.value = value;
    }

    //Getters & Setters
    public void setValue(double value) {
        this.value = value;
    }

    public double getCouponAmount() {
        //how to calculate coupon amount?
        return value;
    }

    public String getID() {
        return id;
    }

    public double getValue() {
        return value;
    }

    //Convert to Text and Arrays.
    public String getDataToSaveToTextFile() {
        return id + "," + value;
    }
    //Takes string array returns platinum object
    public static Coupon getInstanceFromStringArray(String[] input) {
        return new Coupon(input[0], Double.parseDouble(input[1]));
    }

    @Override
    public void getDiscountID() {
        getID();
    }

    @Override
    public double getDiscountAmount(double price, TicketType ticketType) {
        return value;
    }

    @Override
    public String toString() {
        return String.format("ID: %s, Coupon Amount: $%.2f", this.id, this.value);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return (Coupon) super.clone();
    }

    @Override
    public int compareTo(Coupon coupon) {
        //return Integer.valueOf(this.id).compareTo(Integer.valueOf(card.id));
        //return  this.getID().compareTo(card.id);
        if (Integer.parseInt(coupon.getID()) == Integer.parseInt(id)) {
            return 0;
        } else if (Integer.parseInt(coupon.getID()) > Integer.parseInt(id)) {
            return 1;
        } else {
            return -1;
        }
    }
}
