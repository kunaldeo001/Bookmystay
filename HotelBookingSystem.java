import java.time.LocalDate;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class HotelBookingSystem {
    private final String hotelName;
    private final Map<RoomType, List<Room>> roomsByType;
    private int nextReservationId;

    public HotelBookingSystem(String hotelName) {
        this.hotelName = hotelName;
        this.roomsByType = new EnumMap<>(RoomType.class);
        this.nextReservationId = 1;
        for (RoomType roomType : RoomType.values()) {
            roomsByType.put(roomType, new ArrayList<>());
        }
    }

    public String getWelcomeMessage() {
        return "Welcome to " + hotelName + " Hotel Booking Management System";
    }

    public void addRoom(int roomNumber, RoomType roomType) {
        roomsByType.get(roomType).add(new Room(roomNumber, roomType));
    }

    public List<RoomType> getRoomTypes() {
        return new ArrayList<>(roomsByType.keySet());
    }

    public int getTotalRooms(RoomType roomType) {
        return roomsByType.get(roomType).size();
    }

    public int getAvailableRoomCount(RoomType roomType, LocalDate checkInDate, LocalDate checkOutDate) {
        return searchAvailableRooms(roomType, checkInDate, checkOutDate).size();
    }

    public List<Room> searchAvailableRooms(RoomType roomType, LocalDate checkInDate, LocalDate checkOutDate) {
        List<Room> availableRooms = new ArrayList<>();
        for (Room room : roomsByType.get(roomType)) {
            if (room.isAvailable(checkInDate, checkOutDate)) {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }

    public Map<RoomType, Integer> getInventorySummary() {
        Map<RoomType, Integer> summary = new EnumMap<>(RoomType.class);
        for (RoomType roomType : RoomType.values()) {
            summary.put(roomType, roomsByType.get(roomType).size());
        }
        return summary;
    }

    public BookingRequest createBookingRequest(
            String guestName,
            RoomType roomType,
            LocalDate checkInDate,
            LocalDate checkOutDate) {
        boolean serviceable = getAvailableRoomCount(roomType, checkInDate, checkOutDate) > 0;
        return new BookingRequest(guestName, roomType, checkInDate, checkOutDate, serviceable);
    }

    public Reservation confirmReservation(BookingRequest bookingRequest) {
        List<Room> availableRooms = searchAvailableRooms(
                bookingRequest.getRequestedRoomType(),
                bookingRequest.getCheckInDate(),
                bookingRequest.getCheckOutDate());

        if (availableRooms.isEmpty()) {
            return new Reservation(
                    "RES-" + nextReservationId++,
                    bookingRequest,
                    null,
                    "DECLINED");
        }

        Room allocatedRoom = availableRooms.get(0);
        allocatedRoom.reserve(bookingRequest.getCheckInDate(), bookingRequest.getCheckOutDate());

        return new Reservation(
                "RES-" + nextReservationId++,
                bookingRequest,
                allocatedRoom,
                "CONFIRMED");
    }
}
