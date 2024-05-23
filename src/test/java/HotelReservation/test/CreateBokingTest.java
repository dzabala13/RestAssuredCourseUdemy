package HotelReservation.test;

import HotelReservation.Models.Booking;
import HotelReservation.Models.Bookingdates;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class CreateBokingTest  extends BaseTest{

    @Test
    public void createBookingTest(){
        Response response = createBooking();

        Assertions.assertEquals("Daniel", response.jsonPath().getJsonObject("booking.firstname"));
        Assertions.assertEquals("Brown", response.jsonPath().getJsonObject("booking.lastname"));
        // with the expression (Integer), we parse the value to do the match
        Assertions.assertEquals(111, (Integer) response.jsonPath().getJsonObject("booking.totalprice"));
        Assertions.assertEquals(true, response.jsonPath().getJsonObject("booking.depositpaid"));
        Assertions.assertEquals("2018-01-01", response.jsonPath().getJsonObject("booking.bookingdates.checkin"));

    }

    @Test
    public void createBookingWithPojo(){

        // Inyection of dependency, here we need to supply the instance of Bookingdates to the instance of Booking
        // that is because Booking needs a instance of Bookingdates to work well.
        Bookingdates bookingdates = new Bookingdates("2013-02-23","2014-10-23");
        Booking booking = new Booking("Sally","Brown",111,true,bookingdates,"dancing");

        Response response= given(spec).
                contentType(ContentType.JSON)
                // here we are serializing the objet JAVA because we are converting it to a JSON
                // object to can send the post peticion.
                .body(booking)
                .when()
                .post("/booking");

        response.then().statusCode(200);






    }


}
