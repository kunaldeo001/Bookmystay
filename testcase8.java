import java.time.LocalDate;
import java.util.Arrays;

public class testcase8 {
    public static void main(String[] args) {
        HotelBookingSystem hotelBookingSystem = new HotelBookingSystem("Book My Stay");
        hotelBookingSystem.addRoom(101, RoomType.STANDARD);
        hotelBookingSystem.addRoom(201, RoomType.DELUXE);

        // Booking 1
        BookingRequest req1 = hotelBookingSystem.createBookingRequest(
                "Alice", RoomType.STANDARD, LocalDate.now().plusDays(1), LocalDate.now().plusDays(3));
        hotelBookingSystem.confirmReservation(req1);

        // Booking 2 with Add-Ons
        BookingRequest req2 = hotelBookingSystem.createBookingRequest(
                "Bob", RoomType.DELUXE, LocalDate.now().plusDays(5), LocalDate.now().plusDays(7),
                Arrays.asList(AddOnService.BREAKFAST, AddOnService.SPA));
        hotelBookingSystem.confirmReservation(req2);

        System.out.println("UC8 - Booking History & Reporting");
        hotelBookingSystem.generateReportingSummary();

        System.out.println("Reservation History:");
        hotelBookingSystem.getReservationHistory().forEach(System.out::println);
    }
}
