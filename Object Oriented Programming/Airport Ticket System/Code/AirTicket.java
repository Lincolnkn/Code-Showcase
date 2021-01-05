import static java.lang.Integer.*;

public class AirTicket {
    private String TicketID;
    private String Name;
    private String DepartureCity;
    private String DestinationCity;
    private String FlightNumber;
    private TicketType ticketType;
    private DiscountType discountType;
    private int Price;
    private double DiscountAmount;

    public AirTicket(String ticketID, String name, String departureCity, String destinationCity,
                     String flightNumber, TicketType ticketType, DiscountType discountType, int price) {
        //TicketType
        this.TicketID = ticketID;
        this.Name = name;
        this.DepartureCity = departureCity;
        this.DestinationCity = destinationCity;
        this.FlightNumber = flightNumber;
        this.ticketType = ticketType;
        this.discountType = discountType;
        this.Price = price;

        //Types are changed from Platinum/Titanium to card and Coupon
/*        //Changes Discount Amount by the seat and card types.
        if ((discountType.name().equals(DiscountType.Platinum.name())) & (discountType.name().equals(TicketType.First.name()) || ticketType.name().equals(TicketType.Business.name()))) {
            //Platinum, First & Business
            this.DiscountAmount = 0.05;
        } else if ((discountType.name().equals(DiscountType.Platinum.name())) & (ticketType.name().equals(TicketType.Economy.name()))) {
            //Platinum & Economy
            DiscountAmount = 0.01;
        } else if ((discountType.name().equals(DiscountType.Titanium.name()) & (ticketType.name().equals(TicketType.First.name())) || ticketType.name().equals(TicketType.Business.name()))) {
            //Titanium, First & Business
            DiscountAmount = 0.1;
        } else {
            //Titanium & Economy
            DiscountAmount = 0.02;
        }*/
    }

    //Convert to Text and Arrays.
    public String getDataToSaveToTextFile() {
        return TicketID + "," + Name + "," + DepartureCity +
                "," + DestinationCity + "," + FlightNumber + "," + ticketType.name() + "," + discountType.name() + "," + Price + ","
                + DiscountAmount;
    }

    public static AirTicket getInstanceFromStringArray(String[] input) {
        TicketType ticketType;

        if (input[5].trim().equalsIgnoreCase(TicketType.Business.name())) {
            ticketType = TicketType.Business;
        } else if (input[5].trim().equalsIgnoreCase(TicketType.First.name())) {
            ticketType = TicketType.First;
        } else {
            ticketType = TicketType.Economy;
        }

        DiscountType discountType;

        if (input[6].trim().equalsIgnoreCase(DiscountType.CardDiscount.name())) {
            discountType = DiscountType.CardDiscount;
        } else {
            discountType = DiscountType.CouponDiscount;
        }


        return new AirTicket(input[0], input[1], input[2], input[3], input[4], ticketType, discountType,
                valueOf(input[7]));
    }


    @Override
    public String toString() {
        return String.format("Ticket Details: \n%s, %s, %s, %s, %s, %s, %s, $%d, Discount: %.2f%%", TicketID, Name,
                DepartureCity, DestinationCity, FlightNumber, ticketType.name(), discountType.name(), Price, DiscountAmount);
    }


}

