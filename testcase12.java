import java.time.LocalDate;

public class testcase12 {
    public static void main(String[] args) {
        String filename = "hotel_state.ser";
        HotelBookingSystem originalSystem = new HotelBookingSystem("Book My Stay");
        originalSystem.addRoom(601, RoomType.STANDARD);

        LocalDate checkIn = LocalDate.now().plusDays(25);
        LocalDate checkOut = LocalDate.now().plusDays(27);

        // 1. Create a reservation
        BookingRequest req = originalSystem.createBookingRequest("Suresh", RoomType.STANDARD, checkIn, checkOut);
        Reservation res = originalSystem.confirmReservation(req);
        System.out.println("UC12 - Data Persistence & System Recovery");
        System.out.println("Before Save: " + res);

        // 2. Save the system state
        originalSystem.saveSystemState(filename);

        // 3. Simulate system crash/restart by loading state into a new instance
        HotelBookingSystem recoveredSystem = HotelBookingSystem.loadSystemState(filename);
        
        if (recoveredSystem != null) {
            System.out.println("System recovered successfully.");
            System.out.println("Recovered Reservations Count: " + recoveredSystem.getReservationHistory().size());
            
            // 4. Verify the reservation exists and is confirmed
            Reservation recoveredRes = recoveredSystem.getReservationHistory().get(0);
            System.out.println("Recovered Reservation: " + recoveredRes);
            
            // 5. Verify room is still occupied in the recovered system
            System.out.println("Availability in Recovered System (Expected 0): " + 
                recoveredSystem.getAvailableRoomCount(RoomType.STANDARD, checkIn, checkOut));
        } else {
            System.err.println("Failed to recover system state.");
        }

        // Cleanup
        new java.io.File(filename).delete();
    }
}
