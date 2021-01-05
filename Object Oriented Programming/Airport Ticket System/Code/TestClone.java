import java.lang.reflect.Array;
import java.util.*;
import java.time.LocalDate;

public class TestClone {
    public static void main(String[] args) throws CloneNotSupportedException {
        //Assignment 2 Test Code: Clones

        MainSystem mainSystem = new MainSystem();
        CardAndUserRegisterSystem cardAndUserRegisterSystem = new CardAndUserRegisterSystem(mainSystem);
        CardRegisterView cardRegisterView = new CardRegisterView();

        Address address1 = new Address("99", "Murray", "Sherman", "Sydney", "NSW", "4853");
        Address address2 = new Address("561", "Cobble Lane", "Carterton", "Christchurch", "QLD", "5748");
        Address address3 = new Address("874", "Center", "Masterton", "Auckland", "NSW", "2059");

        PlatinumCard platinumCard1 = new PlatinumCard("1000009999", "Lincoln Knowles", 50000, LocalDate.of(2020, 6, 20), address1);
        PlatinumCard platinumCard2 = new PlatinumCard("1000000999", "Catherine Smith", 1200, LocalDate.of(2015, 3, 2), address1);

        TitaniumCard titaniumCard1 = new TitaniumCard("2000000009", "Alexander Walker", 75000, LocalDate.of(1998, 7, 15), address2);
        TitaniumCard titaniumCard2 = new TitaniumCard("2000000099", "Maria Campbell", 8955, LocalDate.of(2008, 1, 5), address3);

        Coupon coupon1 = new Coupon("1000152785", 8052);

        System.out.println("Clones. Changing Street Name");
        System.out.println("\nPlatinum Card Clone: ");
        System.out.println(platinumCard1.toString());
        PlatinumCard platinumCardClone1 = (PlatinumCard) platinumCard1.clone();
        platinumCardClone1.getAddress().setStreetName("Glass st");
        System.out.println(platinumCard1.toString());
        System.out.println(platinumCardClone1.toString());

        System.out.println("\nSecond Platinum Clone: ");
        System.out.println(platinumCard2.toString());
        PlatinumCard platinumCardClone2 = (PlatinumCard) platinumCard2.clone();
        platinumCardClone2.getAddress().setStreetName("Fire st");
        System.out.println(platinumCard2.toString());
        System.out.println(platinumCardClone2.toString());


        System.out.println("\nTitanium Card Clone: ");
        System.out.println(titaniumCard1.toString());
        TitaniumCard titaniumCardClone1 = (TitaniumCard) titaniumCard1.clone();
        titaniumCardClone1.getAddress().setStreetName("Wood st");
        System.out.println(titaniumCard1.toString());
        System.out.println(titaniumCardClone1.toString());

        System.out.println("\nSecond Titanium Clone: ");
        System.out.println(titaniumCard2.toString());
        TitaniumCard titaniumCardClone2 = (TitaniumCard) titaniumCard2.clone();
        titaniumCardClone2.getAddress().setStreetName("Water st");
        System.out.println(titaniumCard2.toString());
        System.out.println(titaniumCardClone2.toString());

        System.out.println("\nCoupon Clone: ");
        System.out.println(coupon1.toString());
        Coupon couponClone1 = (Coupon) coupon1.clone();
        couponClone1.setValue(79624);
        System.out.println(coupon1.toString());
        System.out.println(couponClone1.toString());


        System.out.println("\nSort ArrayList of Clones");
        System.out.println("----------------------\n");

        ArrayList<FrequentFlyerCard> flyerCards = new ArrayList<>();

        flyerCards.add(platinumCardClone1);
        flyerCards.add(platinumCardClone2);
        flyerCards.add(titaniumCardClone1);
        flyerCards.add(titaniumCardClone2);
        //flyerCardsCoupons.add(couponClone1);

        System.out.println("Unsorted: ");
        for (FrequentFlyerCard cards: flyerCards) {
            System.out.println(cards);
        }
        System.out.println("----------------------\n");

        Collections.sort(flyerCards, Collections.reverseOrder()); // Sorts smallest to largest by ID

        System.out.println("Sorted smallest to largest by ID: ");
        for (FrequentFlyerCard cards: flyerCards) {
            System.out.println(cards);
        }

        //Cannot sort as coupon is disconnected to Frequent Flyer
        /*ArrayList<Discount> discountCCs = new ArrayList<>();
        System.out.println("Unsorted");
        for (FrequentFlyerCard cards: flyerCards) {
            System.out.println(cards);
        }
        discountCCs.add( platinumCardClone1);
        discountCCs.add(platinumCardClone2);
        discountCCs.add(titaniumCardClone1);
        discountCCs.add(titaniumCardClone2);
        discountCCs.add(couponClone1);

        Collections.sort(discountCCs, Collections.reverseOrder());
        System.out.println("Sorted");
        for (FrequentFlyerCard cards: flyerCards) {
            System.out.println(cards);
        }*/

    }

}
