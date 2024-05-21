package HotelReservation;

import io.restassured.internal.common.assertion.Assertion;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class getBookingsByIds extends BaseTest {

    // curl -i https://restful-booker.herokuapp.com/booking/1
    @Test
    public void getBookingsByIdsTest(){

        // First we create a new booking and after that we take the id's booking.
        // all this reusing the process of create a booking already declarated in our BaseTest Class, which is inherited in this class
        Response newBooking = createBooking();
        int idNewBooking = newBooking.jsonPath().getJsonObject("bookingid");

        Response response =  given()
                .when().
                    get("https://restful-booker.herokuapp.com/booking/"+idNewBooking);
        response.then()
                .statusCode(200);

        // this is a way fancy to print the response body of the response
        response.prettyPrint();

        String firstname = response.jsonPath().getJsonObject("firstname");
        String lastname = response.jsonPath().getJsonObject("lastname");
        int totalprice = response.jsonPath().getJsonObject("totalprice");

        Assertions.assertEquals("Daniel",firstname);
        Assertions.assertEquals("Brown",lastname);
        Assertions.assertEquals(111,totalprice);






    }
}
