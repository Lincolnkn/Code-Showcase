public class MainSystem {
    //TicketPurchaseSystem ticketSys
    CardAndUserRegisterSystem cardSys;

    public MainSystem() {

    }

    public void generateCoupon() {
        cardSys.createCouponFromCard();
    }

    public void createCard() {
        cardSys.createNewCard();
    }

    protected DiscountType getDiscountType(String discountID) {
        return cardSys.getDiscountType(discountID);
    }

    protected double getDiscountAmount(String discountID, double price, TicketType ticketType) {
        return cardSys.getDiscountAmount(discountID, price, ticketType);
    }
}
