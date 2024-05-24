package HotelReservation.Models;

public class ResponseBooking {

    private int bookingid;
    private Booking booking;

    /* here it is not necessary to declarate a constructor because in the process of deserialization
    for two things the first is that when we dont put a constructor JAVA create a constructor by default
    it allows to create instances of the class without need to initializate all the attributes
    that is useful because the library Jackson can use the methos set to deserializate the json to a objetc java.
    */
    public int getBookingid() {
        return bookingid;
    }

    public void setBookingid(int bookingid) {
        this.bookingid = bookingid;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }




}
