import java.time.LocalDate;

public class testcase9 {
    public static void main(String[] args) {
        HotelBookingSystem hotelBookingSystem = new HotelBookingSystem("Book My Stay");

        System.out.println("UC9 - Error Handling & Validation");

        // Case 1: Empty Guest Name
        try {
            hotelBookingSystem.createBookingRequest("", RoomType.STANDARD, LocalDate.now().plusDays(1), LocalDate.now().plusDays(2));
        } catch (IllegalArgumentException e) {
            System.out.println("Caught Expected Error (Empty Name): " + e.getMessage());
        }

        // Case 2: Invalid Date Range (Check-out before Check-in)
        try {
            hotelBookingSystem.createBookingRequest("John", RoomType.STANDARD, LocalDate.now().plusDays(5), LocalDate.now().plusDays(3));
        } catch (IllegalArgumentException e) {
            System.out.println("Caught Expected Error (Invalid Dates): " + e.getMessage());
        }

        // Case 3: Past Date
        try {
            hotelBookingSystem.createBookingRequest("John", RoomType.STANDARD, LocalDate.now().minusDays(1), LocalDate.now().plusDays(1));
        } catch (IllegalArgumentException e) {
            System.out.println("Caught Expected Error (Past Date): " + e.getMessage());
        }
    }
}
