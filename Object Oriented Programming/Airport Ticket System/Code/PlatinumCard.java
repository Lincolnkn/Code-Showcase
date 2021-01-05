import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class PlatinumCard extends FrequentFlyerCard implements Cloneable{

    private final static double EconomyClassDiscountRate = 0.01;
    private final static double BusinessClassDiscountRate = 0.05;
    private final static double FirstClassDiscountRate = 0.05;

    //Constructors
    public PlatinumCard(String name, Address address) {
        super(name, address);
    }

    protected PlatinumCard(String id, String name, int frequentFlyingPoint, LocalDate dateOfActivation, Address address) {
        super(id, name, frequentFlyingPoint, dateOfActivation, address);
    }

    //Calculates coupon value in $ per year
    @Override
    public double getCouponAmount() {
        double couponValuePerYear = 0;
        int FFP = this.getFFP();

        if (FFP < 10000) {
            couponValuePerYear = .005 * FFP;
        } else if (FFP > 10000) {
            couponValuePerYear = .01 * FFP;
        }

        return couponValuePerYear;
    }

    //String format
    @Override
    public String toString() {
        return String.format("%s", super.toString());
    }

    //Returns object as String separated by , comma
    @Override
    public String getDataToSaveToTextFile() {
        return this.getID() + "," + this.getName() + "," + this.getFFP() + "," + this.getDateOfActivation() + "," + this.getAddress();
    }

    //Takes string array returns platinum object
    public static PlatinumCard getInstanceFromStringArray(String[] input) {
        return new PlatinumCard(input[0], input[1], Integer.parseInt(input[2]), convertStringDateToLocaleDate(input[3]),
                new Address(input[4], input[5], input[6], input[6], input[7], input[8]));
    }

    public void getDiscountID() {
        System.out.println(super.getID());
    }

    //Calculates discount on specific flight
    @Override
    public double getDiscountAmount(double price, TicketType ticketType) {
        double discountAmount;

        if (ticketType.getName().equals("Economy")) {
            discountAmount = price * EconomyClassDiscountRate;
        } else {
            discountAmount = price * FirstClassDiscountRate;
        }

        return discountAmount;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        PlatinumCard clonedObj = (PlatinumCard) super.clone();
        clonedObj.setAddress(getAddress().clone());
        return clonedObj;
    }
}
