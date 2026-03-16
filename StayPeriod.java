import java.time.LocalDate;

public class StayPeriod {
    private final LocalDate checkInDate;
    private final LocalDate checkOutDate;

    public StayPeriod(LocalDate checkInDate, LocalDate checkOutDate) {
        if (checkInDate == null || checkOutDate == null) {
            throw new IllegalArgumentException("Check-in and check-out dates are required.");
        }
        if (!checkOutDate.isAfter(checkInDate)) {
            throw new IllegalArgumentException("Check-out date must be after check-in date.");
        }
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public boolean overlaps(StayPeriod other) {
        return checkInDate.isBefore(other.checkOutDate)
                && other.checkInDate.isBefore(checkOutDate);
    }

    @Override
    public String toString() {
        return checkInDate + " to " + checkOutDate;
    }
}
