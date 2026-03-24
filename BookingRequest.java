import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BookingRequest implements Serializable {
    private static final long serialVersionUID = 1L;
    private static int nextRequestId = 1;

    private final String requestId;
    private final String guestName;
    private final RoomType requestedRoomType;
    private final LocalDate checkInDate;
    private final LocalDate checkOutDate;
    private final boolean serviceable;
    private final List<AddOnService> selectedAddOns;

    public BookingRequest(
            String guestName,
            RoomType requestedRoomType,
            LocalDate checkInDate,
            LocalDate checkOutDate,
            boolean serviceable,
            List<AddOnService> selectedAddOns) {
        this.requestId = "REQ-" + nextRequestId++;
        this.guestName = guestName;
        this.requestedRoomType = requestedRoomType;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.serviceable = serviceable;
        this.selectedAddOns = selectedAddOns != null ? new ArrayList<>(selectedAddOns) : new ArrayList<>();
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

    public List<AddOnService> getSelectedAddOns() {
        return new ArrayList<>(selectedAddOns);
    }

    @Override
    public String toString() {
        return requestId
                + " | Guest: " + guestName
                + " | Room Type: " + requestedRoomType.getDisplayName()
                + " | Stay: " + checkInDate + " to " + checkOutDate
                + " | Serviceable: " + serviceable
                + " | Add-Ons: " + selectedAddOns;
    }
}
