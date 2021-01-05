public enum DiscountType{
    CardDiscount("Card Discount", 1), CouponDiscount("Coupon Discount", 2);

    private String name;
    private int code;

    DiscountType(String name, int i) {
        this.name = name;
        this.code = i;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return String.format("%s %d", this.name, this.code);
    }

    public static DiscountType getDiscountType(String discountType) {
        for (DiscountType discount : DiscountType.values()) {
            if (discount.getName().equals(discountType)) {
                return discount;
            }
        }
        return null;
    }
}

