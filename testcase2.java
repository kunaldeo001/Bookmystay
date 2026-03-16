public class testcase2 {
    public static void main(String[] args) {
        HotelBookingSystem hotelBookingSystem = new HotelBookingSystem("Book My Stay");
        hotelBookingSystem.addRoom(101, RoomType.STANDARD);
        hotelBookingSystem.addRoom(102, RoomType.STANDARD);
        hotelBookingSystem.addRoom(201, RoomType.DELUXE);
        hotelBookingSystem.addRoom(301, RoomType.SUITE);

        System.out.println("UC2 - Basic Room Types & Static Availability");
        for (RoomType roomType : hotelBookingSystem.getRoomTypes()) {
            System.out.println(
                    roomType.getDisplayName()
                            + " | Capacity: " + roomType.getCapacity()
                            + " | Base Price: " + roomType.getBasePrice()
                            + " | Total Rooms: " + hotelBookingSystem.getTotalRooms(roomType));
        }
    }
}
