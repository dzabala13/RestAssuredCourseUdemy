package HotelReservation;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
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


}
