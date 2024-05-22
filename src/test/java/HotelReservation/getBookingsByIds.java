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

        Response response =  given()
                                    .when()
                                    .get("https://restful-booker.herokuapp.com/booking/"+createBookingId());

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
