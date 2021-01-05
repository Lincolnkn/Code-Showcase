import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class TitaniumCard extends PlatinumCard implements Cloneable{

    private final static double EconomyClassDiscountRate = 0.02;
    private final static double BusinessClassDiscountRate = 0.10;
    private final static double FirstClassDiscountRate = 0.10;

    //Constructors
    public TitaniumCard(String name, Address address) {
        super(name, address);
    }

    protected TitaniumCard(String id, String name, int frequentFlyingPoint, LocalDate dateOfActivation, Address address) {
        super(id, name, frequentFlyingPoint, dateOfActivation, address);
    }


    //Returns years since Activation
    public int numYearsSinceActivation () {
        return LocalDate.now().getYear() - getDateOfActivation().getYear();
    }

    //Calculates coupon value in $ per year
    @Override
    public double getCouponAmount() {
        double couponValuePerYear = 0;
        int FFP = this.getFFP();

        if (FFP < 100000) {
            couponValuePerYear = .015 * FFP;
        } else if (FFP > 300000 || this.numYearsSinceActivation() > 5 &&
                FFP > 100000 && FFP < 300000) {
            couponValuePerYear = .02 * FFP;
        } else if (FFP >= 100000 && FFP < 300000) {
            couponValuePerYear = .03 * FFP;
        }
        return couponValuePerYear;

    }

    //String format
    @Override
    public String toString() {
        return String.format("%s", super.toString());
    }

    //Takes string array returns titanium object
    public static TitaniumCard getInstanceFromStringArray(String[] input) {
        return new TitaniumCard(input[0], input[1], Integer.parseInt(input[2]), convertStringDateToLocaleDate(input[3]),
                new Address(input[4], input[5], input[6], input[6], input[7], input[8]));
    }

    //Returns object as string split by, comma
    @Override
    public String getDataToSaveToTextFile() {
        return super.getDataToSaveToTextFile();
    }

    @Override
    public void getDiscountID() {
        System.out.println(super.getID());
    }

    //Calculates discount on specific flight
    @Override
    public double getDiscountAmount(double price, TicketType ticketType) {
        //Calculates discount on specific flight
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
        TitaniumCard clonedObj = (TitaniumCard) super.clone();
        clonedObj.setAddress(getAddress().clone());
        return clonedObj;
    }
}
