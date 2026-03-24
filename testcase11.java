import java.time.LocalDate;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class testcase11 {
    public static void main(String[] args) throws InterruptedException {
        HotelBookingSystem hotelBookingSystem = new HotelBookingSystem("Book My Stay");
        // Only ONE room available
        hotelBookingSystem.addRoom(501, RoomType.SUITE);

        LocalDate checkIn = LocalDate.now().plusDays(20);
        LocalDate checkOut = LocalDate.now().plusDays(22);

        System.out.println("UC11 - Concurrent Booking Simulation");
        System.out.println("Initial Room Availability: " + hotelBookingSystem.getAvailableRoomCount(RoomType.SUITE, checkIn, checkOut));

        ExecutorService executor = Executors.newFixedThreadPool(5);
        AtomicInteger successCount = new AtomicInteger(0);
        AtomicInteger failureCount = new AtomicInteger(0);

        for (int i = 0; i < 5; i++) {
            final int guestId = i + 1;
            executor.submit(() -> {
                try {
                    BookingRequest req = hotelBookingSystem.createBookingRequest("Guest-" + guestId, RoomType.SUITE, checkIn, checkOut);
                    Reservation res = hotelBookingSystem.confirmReservation(req);
                    if ("CONFIRMED".equals(res.getStatus())) {
                        successCount.incrementAndGet();
                        System.out.println("Guest-" + guestId + " SUCCESS: " + res.getReservationId());
                    } else {
                        failureCount.incrementAndGet();
                        System.out.println("Guest-" + guestId + " FAILED: Declined");
                    }
                } catch (Exception e) {
                    System.err.println("Error for Guest-" + guestId + ": " + e.getMessage());
                }
            });
        }

        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.SECONDS);

        System.out.println("--- Summary ---");
        System.out.println("Successful Bookings: " + successCount.get());
        System.out.println("Failed Bookings (Over-booking prevented): " + failureCount.get());
    }
}
