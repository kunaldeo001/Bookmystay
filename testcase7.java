import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class testcase7 {
    public static void main(String[] args) {
        HotelBookingSystem hotelBookingSystem = new HotelBookingSystem("Book My Stay");
        hotelBookingSystem.addRoom(401, RoomType.DELUXE);

        List<AddOnService> selectedAddOns = Arrays.asList(
                AddOnService.BREAKFAST,
                AddOnService.SPA
        );

        BookingRequest bookingRequest = hotelBookingSystem.createBookingRequest(
                "Karan",
                RoomType.DELUXE,
                LocalDate.of(2026, 7, 10),
                LocalDate.of(2026, 7, 15),
                selectedAddOns);

        Reservation reservation = hotelBookingSystem.confirmReservation(bookingRequest);

        System.out.println("UC7 - Add-On Service Selection");
        System.out.println("Booking Request: " + bookingRequest);
        System.out.println("Reservation Result: " + reservation);
    }
}
