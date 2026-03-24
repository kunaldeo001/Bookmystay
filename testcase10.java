import java.time.LocalDate;

public class testcase10 {
    public static void main(String[] args) {
        HotelBookingSystem hotelBookingSystem = new HotelBookingSystem("Book My Stay");
        hotelBookingSystem.addRoom(101, RoomType.STANDARD);

        LocalDate checkIn = LocalDate.now().plusDays(10);
        LocalDate checkOut = LocalDate.now().plusDays(12);

        // 1. Create and confirm booking
        BookingRequest req = hotelBookingSystem.createBookingRequest("Vikram", RoomType.STANDARD, checkIn, checkOut);
        Reservation res = hotelBookingSystem.confirmReservation(req);
        System.out.println("UC10 - Booking Cancellation & Inventory Rollback");
        System.out.println("Initially Reserved: " + res);
        System.out.println("Available Count: " + hotelBookingSystem.getAvailableRoomCount(RoomType.STANDARD, checkIn, checkOut));

        // 2. Cancel booking
        boolean cancelled = hotelBookingSystem.cancelReservation(res.getReservationId());
        System.out.println("Cancellation Success: " + cancelled);
        System.out.println("Updated Reservation Status: " + res);
        
        // 3. Check inventory rollback
        System.out.println("Available Count after Cancellation: " + hotelBookingSystem.getAvailableRoomCount(RoomType.STANDARD, checkIn, checkOut));
    }
}
