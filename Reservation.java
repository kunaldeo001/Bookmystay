import java.io.Serializable;
import java.util.List;

public class Reservation implements Serializable {
    private static final long serialVersionUID = 1L;
    private final String reservationId;
    private final BookingRequest bookingRequest;
    private final Room allocatedRoom;
    private String status;
    private final List<AddOnService> selectedAddOns;

    public Reservation(String reservationId, BookingRequest bookingRequest, Room allocatedRoom, String status) {
        this.reservationId = reservationId;
        this.bookingRequest = bookingRequest;
        this.allocatedRoom = allocatedRoom;
        this.status = status;
        this.selectedAddOns = bookingRequest.getSelectedAddOns();
    }

    public String getReservationId() {
        return reservationId;
    }

    public BookingRequest getBookingRequest() {
        return bookingRequest;
    }

    public Room getAllocatedRoom() {
        return allocatedRoom;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        String roomDetails = allocatedRoom == null ? "No room allocated" : allocatedRoom.toString();
        return reservationId
                + " | Guest: " + bookingRequest.getGuestName()
                + " | Status: " + status
                + " | " + roomDetails
                + " | Add-Ons: " + selectedAddOns;
    }
}
