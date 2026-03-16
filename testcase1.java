public class testcase1 {
    public static void main(String[] args) {
        HotelBookingSystem hotelBookingSystem = new HotelBookingSystem("Book My Stay");

        System.out.println("UC1 - Application Entry & Welcome Message");
        System.out.println(hotelBookingSystem.getWelcomeMessage());
    }
}
