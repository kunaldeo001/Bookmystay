import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class HotelBookingSystem implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private final String hotelName;
    private final Map<RoomType, List<Room>> roomsByType;
    private final List<Reservation> reservationHistory;
    private int nextReservationId;

    public HotelBookingSystem(String hotelName) {
        this.hotelName = hotelName;
        this.roomsByType = new EnumMap<>(RoomType.class);
        this.reservationHistory = new ArrayList<>();
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
        return createBookingRequest(guestName, roomType, checkInDate, checkOutDate, new ArrayList<>());
    }

    public BookingRequest createBookingRequest(
            String guestName,
            RoomType roomType,
            LocalDate checkInDate,
            LocalDate checkOutDate,
            List<AddOnService> selectedAddOns) {
        
        if (guestName == null || guestName.trim().isEmpty()) {
            throw new IllegalArgumentException("Guest name cannot be empty");
        }
        if (checkInDate == null || checkOutDate == null) {
            throw new IllegalArgumentException("Dates cannot be null");
        }
        if (!checkOutDate.isAfter(checkInDate)) {
            throw new IllegalArgumentException("Check-out date must be after check-in date");
        }
        if (checkInDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Check-in date cannot be in the past");
        }

        boolean serviceable = getAvailableRoomCount(roomType, checkInDate, checkOutDate) > 0;
        return new BookingRequest(guestName, roomType, checkInDate, checkOutDate, serviceable, selectedAddOns);
    }

    public synchronized Reservation confirmReservation(BookingRequest bookingRequest) {
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

        Reservation reservation = new Reservation(
                "RES-" + nextReservationId++,
                bookingRequest,
                allocatedRoom,
                "CONFIRMED");

        reservationHistory.add(reservation);
        return reservation;
    }

    public boolean cancelReservation(String reservationId) {
        for (Reservation res : reservationHistory) {
            if (res.getReservationId().equals(reservationId) && "CONFIRMED".equals(res.getStatus())) {
                Room room = res.getAllocatedRoom();
                if (room != null) {
                    room.release(res.getBookingRequest().getCheckInDate(), res.getBookingRequest().getCheckOutDate());
                }
                res.setStatus("CANCELLED");
                return true;
            }
        }
        return false;
    }

    public List<Reservation> getReservationHistory() {
        return new ArrayList<>(reservationHistory);
    }

    public void generateReportingSummary() {
        System.out.println("--- Reporting Summary ---");
        System.out.println("Total Reservations: " + reservationHistory.size());
        long confirmedCount = reservationHistory.stream()
                .filter(r -> "CONFIRMED".equals(r.getStatus()))
                .count();
        System.out.println("Confirmed Reservations: " + confirmedCount);

        double totalRevenue = reservationHistory.stream()
                .filter(r -> "CONFIRMED".equals(r.getStatus()))
                .mapToDouble(r -> {
                    double roomPrice = r.getBookingRequest().getRequestedRoomType().getBasePrice();
                    long days = java.time.temporal.ChronoUnit.DAYS.between(
                            r.getBookingRequest().getCheckInDate(),
                            r.getBookingRequest().getCheckOutDate());
                    double addOnsPrice = r.getBookingRequest().getSelectedAddOns().stream()
                            .mapToDouble(AddOnService::getPrice)
                            .sum();
                    return (roomPrice * days) + addOnsPrice;
                })
                .sum();
        System.out.println("Estimated Total Revenue: ₹" + totalRevenue);
        System.out.println("-------------------------");
    }

    public void saveSystemState(String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(this);
            System.out.println("System state saved to " + filename);
        } catch (IOException e) {
            System.err.println("Error saving state: " + e.getMessage());
        }
    }

    public static HotelBookingSystem loadSystemState(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (HotelBookingSystem) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading state: " + e.getMessage());
            return null;
        }
    }
}
