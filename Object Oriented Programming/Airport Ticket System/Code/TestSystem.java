import java.time.LocalDate;

public class TestSystem {
    public static void main(String[] args) {
        //Assignment 2 Test Code

        MainSystem mainSystem = new MainSystem();
        CardAndUserRegisterSystem cardAndUserRegisterSystem = new CardAndUserRegisterSystem(mainSystem);
        CardRegisterView cardRegisterView = new CardRegisterView();

        Address address1 = new Address("2", "Crown", "Grafton", "Wellington", "NSW", "5794");

        PlatinumCard platinumCard1 = new PlatinumCard("1000213215", "Lincoln Knowles", 50000, LocalDate.of(2020, 6, 20), address1);
        TitaniumCard titaniumCard1 = new TitaniumCard("2000214123", "Alexander Knowles", 75000, LocalDate.of(1998, 7, 15), address1);

        Coupon coupon1 = new Coupon("3000213215", 500);

        System.out.println("Platinum Card");
        System.out.println("Coupon amount: " + platinumCard1.getCouponAmount());
        System.out.println("Discount amount: " + platinumCard1.getDiscountAmount(2000, TicketType.First));
        System.out.println(platinumCard1.toString());

        System.out.println("Titanium Card");

        System.out.println("Coupon amount: " + titaniumCard1.getCouponAmount());
        System.out.println("Discount amount: " + titaniumCard1.getDiscountAmount(2000, TicketType.First));
        System.out.println(titaniumCard1.toString());


        System.out.println("Coupon");
        System.out.println(coupon1.getDiscountAmount(7500, TicketType.Business));
        System.out.println(coupon1.getValue());
        System.out.println(coupon1.getCouponAmount());
        cardAndUserRegisterSystem.createNewCard();
        System.out.println("1_____________________________1");


        cardAndUserRegisterSystem.createCouponFromCard();
        System.out.println("2_____________________________2");

        System.out.println(cardAndUserRegisterSystem.getDiscountType(cardRegisterView.getFlyerCardIdFromConsole()));
        System.out.println("3_____________________________3");

        cardAndUserRegisterSystem.displayCouponInfo(cardRegisterView.getFlyerCardIdFromConsole());
        System.out.println("\n4_____________________________4");

        System.out.println(cardAndUserRegisterSystem.getDiscountAmount(cardRegisterView.getFlyerCardIdFromConsole(), 4500, TicketType.First));
        System.out.println("5_____________________________5");


        cardAndUserRegisterSystem.displayCardInfo(cardRegisterView.getFlyerCardIdFromConsole());
        System.out.println("\n6_____________________________6");

        System.out.println(cardAndUserRegisterSystem.searchByDiscountID(cardRegisterView.getFlyerCardIdFromConsole()));
        System.out.println("7_____________________________7");

        //Reads created file which holds:
        //2148727693,Lincoln Knowles,4751,2011-11-17, 290, Crown st, Greytown, Wellington, NSW, 5794
        cardAndUserRegisterSystem.readFile("TestWriteMethod.txt");
        cardAndUserRegisterSystem.displayCardInfo(cardRegisterView.getFlyerCardIdFromConsole());

        cardAndUserRegisterSystem.writeFile("test.txt");


    }
}
