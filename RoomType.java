public enum RoomType {
    STANDARD("Standard", 2, 2500.0),
    DELUXE("Deluxe", 3, 4000.0),
    SUITE("Suite", 4, 6500.0);

    private final String displayName;
    private final int capacity;
    private final double basePrice;

    RoomType(String displayName, int capacity, double basePrice) {
        this.displayName = displayName;
        this.capacity = capacity;
        this.basePrice = basePrice;
    }

    public String getDisplayName() {
        return displayName;
    }

    public int getCapacity() {
        return capacity;
    }

    public double getBasePrice() {
        return basePrice;
    }
}
