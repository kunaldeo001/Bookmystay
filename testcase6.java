import java.time.LocalDate;

public class testcase6 {
    public static void main(String[] args) {
        HotelBookingSystem hotelBookingSystem = new HotelBookingSystem("Book My Stay");
        hotelBookingSystem.addRoom(301, RoomType.SUITE);
        hotelBookingSystem.addRoom(302, RoomType.SUITE);

        BookingRequest bookingRequest = hotelBookingSystem.createBookingRequest(
                "Riya",
                RoomType.SUITE,
                LocalDate.of(2026, 6, 15),
                LocalDate.of(2026, 6, 18));
        Reservation reservation = hotelBookingSystem.confirmReservation(bookingRequest);

        System.out.println("UC6 - Reservation Confirmation & Room Allocation");
        System.out.println("Booking Request: " + bookingRequest);
        System.out.println("Reservation Result: " + reservation);
    }
}
