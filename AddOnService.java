public enum AddOnService {
    BREAKFAST("Breakfast", 500.0),
    SPA("Spa Treatment", 1500.0),
    EXTRA_BED("Extra Bed", 1000.0),
    LATE_CHECKOUT("Late Checkout", 800.0);

    private final String displayName;
    private final double price;

    AddOnService(String displayName, double price) {
        this.displayName = displayName;
        this.price = price;
    }

    public String getDisplayName() {
        return displayName;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return displayName + " (₹" + price + ")";
    }
}
