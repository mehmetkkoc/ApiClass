package pojos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BookingResponseBodyPojo {

    //    1- Tum keyler icin private variable lar olusturuyoruz
    private Integer bookingId;
    private BookingPojo booking;

    //    2- Tum parametrelerle ve parametresiz constructor larimizi olusturuyoruz

    public BookingResponseBodyPojo(Integer bookingId, BookingPojo booking) {
        this.bookingId = bookingId;
        this.booking = booking;
    }

    public BookingResponseBodyPojo() {
    }

    //    3- Getter ve setter olusturuyoruz

    public Integer getBookingId() {
        return bookingId;
    }

    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }

    public BookingPojo getBooking() {
        return booking;
    }

    public void setBooking(BookingPojo booking) {
        this.booking = booking;
    }

    //    4- toString methodunu olusturuyoruz

    @Override
    public String toString() {
        return "BookingResponseBodyPojo{" +
                "bookingId=" + bookingId +
                ", booking=" + booking +
                '}';
    }
}
