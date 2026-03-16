import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Room {
    private final int roomNumber;
    private final RoomType roomType;
    private final List<StayPeriod> reservations;

    public Room(int roomNumber, RoomType roomType) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.reservations = new ArrayList<>();
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public boolean isAvailable(LocalDate checkInDate, LocalDate checkOutDate) {
        StayPeriod requestedPeriod = new StayPeriod(checkInDate, checkOutDate);
        for (StayPeriod existingPeriod : reservations) {
            if (existingPeriod.overlaps(requestedPeriod)) {
                return false;
            }
        }
        return true;
    }

    public boolean reserve(LocalDate checkInDate, LocalDate checkOutDate) {
        if (!isAvailable(checkInDate, checkOutDate)) {
            return false;
        }
        reservations.add(new StayPeriod(checkInDate, checkOutDate));
        return true;
    }

    @Override
    public String toString() {
        return "Room " + roomNumber + " (" + roomType.getDisplayName() + ")";
    }
}
