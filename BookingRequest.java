import java.time.LocalDate;

public class BookingRequest {
    private static int nextRequestId = 1;

    private final String requestId;
    private final String guestName;
    private final RoomType requestedRoomType;
    private final LocalDate checkInDate;
    private final LocalDate checkOutDate;
    private final boolean serviceable;

    public BookingRequest(
            String guestName,
            RoomType requestedRoomType,
            LocalDate checkInDate,
            LocalDate checkOutDate,
            boolean serviceable) {
        this.requestId = "REQ-" + nextRequestId++;
        this.guestName = guestName;
        this.requestedRoomType = requestedRoomType;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.serviceable = serviceable;
    }

    public String getRequestId() {
        return requestId;
    }

    public String getGuestName() {
        return guestName;
    }

    public RoomType getRequestedRoomType() {
        return requestedRoomType;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public boolean isServiceable() {
        return serviceable;
    }

    @Override
    public String toString() {
        return requestId
                + " | Guest: " + guestName
                + " | Room Type: " + requestedRoomType.getDisplayName()
                + " | Stay: " + checkInDate + " to " + checkOutDate
                + " | Serviceable: " + serviceable;
    }
}
