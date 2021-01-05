import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class CardAndUserRegisterSystem {
    private ArrayList<FrequentFlyerCard> flyerCards;
    private ArrayList<Coupon> coupons;
    private CardRegisterView view;
    private MainSystem main = new MainSystem();
    private String tempID;

    public CardAndUserRegisterSystem(MainSystem main) {
        this.main = main;
        flyerCards = new ArrayList<FrequentFlyerCard>();
        coupons = new ArrayList<Coupon>();
        view = new CardRegisterView();
    }

    //Get card ID from User via View
    public void displayCardInfo() {
        view.getFlyerCardIdFromConsole();
    }

    //Searches for ID. Displays card information
    public void displayCardInfo(String cardID) {
        for (FrequentFlyerCard cards : flyerCards) {
            if (cards.getID().equals(cardID)) {
                System.out.printf("Card information for the ID: %s\n%s", cardID, cards.toString());
            }
        }
    }

    //Get Card ID from user via View
    public void displayCouponInfo() {
        view.getFlyerCardIdFromConsole();
    }

    //Searches for ID. Displays coupon info
    public void displayCouponInfo(String cardID) {
        for (Coupon coupon : coupons) {
            if (coupon.getID().equals(cardID)) {
                System.out.printf("Coupon card information for the ID: %s\n%s", cardID, coupon.toString());
            }
        }
    }

    //Uses view to get card info from user. Stores new card in flyerCards ArrayList.
    public void createNewCard() {
        //Interact with user

        String[] newCard = view.getCardRegisterInfoFromUser();

        registerCard(newCard);

        //Prints ID, name, dateOfActivation
        System.out.printf("ID: %s, %s has created a new card on %s\n", tempID, newCard[0], newCard[2]);

    }

    //Checks what type new card is. Creates a new card, adds the card to ArrayList
    protected void registerCard(String[] card) {
        String cardType = view.getCardTypeFromConsole();

        Address address = new Address(card[3], card[4], card[5], card[6], card[7], card[8]);

        //Creates ID. 10 digits. Random
        //Platinum Cards start with 1
        //Titanium Cards end with 1
        Random random = new Random();
        int rand = 100000000 + random.nextInt(900000000);

        //convert String to LocalDate
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String date = card[2];
        LocalDate localDate = LocalDate.parse(date, formatter);


        if (cardType.equals("P")) {
            String id = "1" + rand;
            tempID = id;

            PlatinumCard platinumCard = new PlatinumCard(id, card[0], Integer.parseInt(card[1]), localDate, address);
            flyerCards.add(platinumCard);

        } else {
            String id = "2" + rand;
            tempID = id;

            TitaniumCard titaniumCard = new TitaniumCard(id, card[0], Integer.parseInt(card[1]), localDate, address);
            flyerCards.add(titaniumCard);
        }
    }

    //Asks user for ID. Uses genCoupon to generate a new coupon based off ID
    public void createCouponFromCard() {
        String id = view.getFlyerCardIdFromConsole();

        genCoupon(id);

        System.out.printf("ID: %s, has created a new coupon\n", tempID);
    }

    //Creates a new coupon using the users ID and flyer card
    protected void genCoupon(String discountID) {
        for (FrequentFlyerCard flyercard : flyerCards) {
            if (flyercard.getID().equals(discountID)) {
                Coupon c = new Coupon(flyercard.getID(), flyercard.getCouponAmount());
                coupons.add(c);

                tempID = flyercard.getID();
            }
        }

    }

    //Searches lists for ID match. Returns type.
    protected DiscountType getDiscountType(String discountID) {
        for (FrequentFlyerCard storedCard : flyerCards) {
            if (discountID.equals(storedCard.getID())) {
                return DiscountType.CardDiscount;
            }
        }

        for (Coupon storedCoupon : coupons) {
            if (discountID.equals((storedCoupon.getID()))) {
                return DiscountType.CouponDiscount;
            }
        }

        return null;
    }


    //Added return type double.
    //Searches for the object in the system and returns discount amount.
    protected double getDiscountAmount(String discountID, double price, TicketType ticketType) {
        //String cardType = view.getCardTypeFromConsole();
        double discountAmount;

        for (FrequentFlyerCard frequentFlyerCards : flyerCards) {
            if (frequentFlyerCards.getID().equals(discountID)) {
                discountAmount = frequentFlyerCards.getDiscountAmount(price, ticketType);
                return discountAmount;
            }
        }

        for (Coupon storedCoupon : coupons) {
            if (storedCoupon.getID().equals(discountID)) {
                discountAmount = storedCoupon.getDiscountAmount(price, ticketType);
                return discountAmount;
            }
        }

        return 0.0;
    }

    //Discount id as input, searches for object in system and return the Discount object.
    protected Discount searchByDiscountID(String discountID) {
        for (FrequentFlyerCard storedCard : flyerCards) {
            if (discountID.equals(storedCard.getID())) {
                return storedCard;
            }
        }

        for (Coupon storedCoupon : coupons) {
            if (discountID.equals(storedCoupon.getID())) {
                return storedCoupon;
            }
        }

        return null;
    }


    //Reads given file and adds object in the file to ArrayList
    public void readFile(String fileName) {
        Scanner input = null;
        boolean error = false;
        try {
            input = new Scanner(Paths.get(fileName));
        } catch (IOException ex) {
            error = true;
        }
        if (!error) {
            while (input.hasNext()) {
                String scannedCard = input.nextLine();
                String[] cardArray = FrequentFlyerCard.convertDataFromFileToStringArray(scannedCard);
                String idFirstNum = cardArray[0].substring(0, 1);
                try {
                    if (cardArray.length == 2) { //Checks object is a coupon
                        Coupon couponTemp = Coupon.getInstanceFromStringArray(cardArray);
                        coupons.add(couponTemp);
                    } else { //If object holds more than 2 values (ID, value) it is a card
                        //Platinum cards will start with 1, while Titanium cards start with 2.
                        if (idFirstNum.equals("1")) {
                            PlatinumCard platTemp = PlatinumCard.getInstanceFromStringArray(cardArray);
                            flyerCards.add(platTemp);

                        } else if (idFirstNum.equals("2")) {
                            TitaniumCard titanTemp = TitaniumCard.getInstanceFromStringArray(cardArray);
                            flyerCards.add(titanTemp);
                        }
                    }
                } catch (NoSuchElementException ex) {
                    System.out.println("File improperly formed. Terminating.");
                }
            }
            input.close();
        }
    }

    public void writeFile(String fileName) {
      /*Should take file name (String) as input to generate a file for writing
        the data. It then gets objects from the system and writes them
        on the file. Note that other necessary methods needed to build
        this method are allow to add in the system. Hints: you should use
        getDataToSaveToTextFile method to pack the data of the object and
        supply it to the Formatter object to write the object to file.*/

        File file = new File(Paths.get(fileName).toUri());
        Formatter out = null;
        boolean error = false;
        try {
            out = new Formatter(file);
        } catch (FileNotFoundException ex) {
            error = true;
        }
        if (!error) {
            for (FrequentFlyerCard card : flyerCards) {
                out.format(card.getDataToSaveToTextFile()); // output the card object to cvs format
            }
            out.close();
        }
    }


}
