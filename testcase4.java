import java.time.LocalDate;
import java.util.List;

public class testcase4 {
    public static void main(String[] args) {
        HotelBookingSystem hotelBookingSystem = new HotelBookingSystem("Book My Stay");
        hotelBookingSystem.addRoom(101, RoomType.STANDARD);
        hotelBookingSystem.addRoom(102, RoomType.STANDARD);
        hotelBookingSystem.addRoom(201, RoomType.DELUXE);

        BookingRequest existingRequest = hotelBookingSystem.createBookingRequest(
                "Aarav",
                RoomType.STANDARD,
                LocalDate.of(2026, 4, 10),
                LocalDate.of(2026, 4, 12));
        hotelBookingSystem.confirmReservation(existingRequest);

        System.out.println("UC4 - Room Search & Availability Check");
        List<Room> availableStandardRooms = hotelBookingSystem.searchAvailableRooms(
                RoomType.STANDARD,
                LocalDate.of(2026, 4, 10),
                LocalDate.of(2026, 4, 12));

        System.out.println("Available STANDARD rooms for 2026-04-10 to 2026-04-12:");
        for (Room room : availableStandardRooms) {
            System.out.println(room);
        }
        System.out.println("Available count: " + availableStandardRooms.size());
    }
}
