public class Reservation {
    private final String reservationId;
    private final BookingRequest bookingRequest;
    private final Room allocatedRoom;
    private final String status;

    public Reservation(String reservationId, BookingRequest bookingRequest, Room allocatedRoom, String status) {
        this.reservationId = reservationId;
        this.bookingRequest = bookingRequest;
        this.allocatedRoom = allocatedRoom;
        this.status = status;
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

    @Override
    public String toString() {
        String roomDetails = allocatedRoom == null ? "No room allocated" : allocatedRoom.toString();
        return reservationId
                + " | Guest: " + bookingRequest.getGuestName()
                + " | Status: " + status
                + " | " + roomDetails;
    }
}
