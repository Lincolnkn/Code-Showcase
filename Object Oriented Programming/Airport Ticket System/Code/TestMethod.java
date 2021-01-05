import java.util.*;
import java.time.LocalDate;

public class TestMethod {
    public static void main(String[] args) {
        //Assignment 1 Test Code

        //Creates System/View instance
        CardRegisterView cardRegisterView = new CardRegisterView();
        //Creates new airTicket
        //Adds Discount amount based on seat & card type
        AirTicket airTicket = new AirTicket("21651", "Lincoln Knowles", "Sydney",
                "Wellington", "2167", TicketType.Business, DiscountType.CardDiscount, 1200);
        //Creates a new address & coupon cards
        Address address1 = new Address("2", "Woodside", "Greytown", "Wellington", "NSW", "5794");
        TitaniumCard titaniumCard = new TitaniumCard("111111111", "David Knowles", 40000, LocalDate.of(2008,4,15), address1);
        PlatinumCard platinumCard = new PlatinumCard("111111112", "Mary Marolis", 500000, LocalDate.of(2001,6,22), address1);



        System.out.println("----------------------------------------------");
        System.out.println("Titanium Coupon\n");

        System.out.println(titaniumCard.getID());
        System.out.println(titaniumCard.getCouponAmount());
        String titaniumText = titaniumCard.getDataToSaveToTextFile();
        System.out.println(titaniumText);
        TitaniumCard titanium1 = TitaniumCard.getInstanceFromStringArray(FrequentFlyerCard.convertDataFromFileToStringArray(titaniumText));
        System.out.println(titanium1);

        cardRegisterView.displayInformationToConsole(titanium1.toString());
        System.out.println();


        System.out.println("----------------------------------------------");
        System.out.println("Platinum Coupon\n");

        System.out.println(platinumCard.getID());
        System.out.println(platinumCard.getCouponAmount());
        String platinumText = platinumCard.getDataToSaveToTextFile();
        System.out.println(platinumText);
        PlatinumCard platinum1 = PlatinumCard.getInstanceFromStringArray(FrequentFlyerCard.convertDataFromFileToStringArray(platinumText));
        System.out.println(platinum1);

        cardRegisterView.displayInformationToConsole(platinum1.toString());
        System.out.println();

        System.out.println("----------------------------------------------");
        System.out.println("Address/ID/Name For Titanium Card Holder\n");

        //Get Address
        System.out.println(titanium1.getAddress());
        //Showcase address set method
        titanium1.setAddress(new Address("13", "McCall", "Camden South"
                , "Sydney", "NSW", "2700"));
        //Change and get ID
        titanium1.setId("0123456789");
        System.out.println("ID: " + titanium1.getID());
        //Change and get Name
        System.out.println("Name: " + titanium1.getName());
        //Print Address
        System.out.println(titanium1.getAddress().toString());
        //Print card information
        System.out.println(titanium1.toString());

        System.out.println();

        System.out.println("----------------------------------------------");
        System.out.println("Air Ticket\n");

        //Converts ticket to text file format & print
        String airTicText = airTicket.getDataToSaveToTextFile();
        System.out.println(airTicText);
        //Converts to array and back to object. Print
        AirTicket airTic2 = AirTicket.getInstanceFromStringArray(FrequentFlyerCard.convertDataFromFileToStringArray(airTicText));
        System.out.println(airTic2);
        System.out.println("----------------------------------------------");
        System.out.println("Enums\n");

        System.out.println(DiscountType.getDiscountType("Card Discount"));
        System.out.println(TicketType.getTicketType("First"));


    }
}
