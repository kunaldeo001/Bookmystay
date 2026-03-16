import java.time.LocalDate;

public class testcase5 {
    public static void main(String[] args) {
        HotelBookingSystem hotelBookingSystem = new HotelBookingSystem("Book My Stay");
        hotelBookingSystem.addRoom(201, RoomType.DELUXE);
        hotelBookingSystem.addRoom(202, RoomType.DELUXE);

        BookingRequest bookingRequest = hotelBookingSystem.createBookingRequest(
                "Meera",
                RoomType.DELUXE,
                LocalDate.of(2026, 5, 1),
                LocalDate.of(2026, 5, 4));

        System.out.println("UC5 - Booking Request");
        System.out.println(bookingRequest);
    }
}
