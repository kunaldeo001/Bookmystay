import java.util.Map;

public class testcase3 {
    public static void main(String[] args) {
        HotelBookingSystem hotelBookingSystem = new HotelBookingSystem("Book My Stay");
        hotelBookingSystem.addRoom(101, RoomType.STANDARD);
        hotelBookingSystem.addRoom(102, RoomType.STANDARD);
        hotelBookingSystem.addRoom(201, RoomType.DELUXE);
        hotelBookingSystem.addRoom(202, RoomType.DELUXE);
        hotelBookingSystem.addRoom(301, RoomType.SUITE);

        System.out.println("UC3 - Centralized Room Inventory Management");
        Map<RoomType, Integer> inventorySummary = hotelBookingSystem.getInventorySummary();
        for (Map.Entry<RoomType, Integer> entry : inventorySummary.entrySet()) {
            System.out.println(entry.getKey().getDisplayName() + " Rooms Managed: " + entry.getValue());
        }
    }
}
