package HotelReservation.Models;

import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class getBookingsByIds extends BaseTest {

    // curl -i https://restful-booker.herokuapp.com/booking/1
    @Test
    public void getBookingsByIdsTest(){

        Response response =  given(spec)
                                    .when()
                                    .get("/booking/"+createBookingId());

        response.then()
                .statusCode(200);

        String firstname = response.jsonPath().getJsonObject("firstname");
        String lastname = response.jsonPath().getJsonObject("lastname");
        int totalprice = response.jsonPath().getJsonObject("totalprice");

        Assertions.assertEquals("Daniel",firstname);
        Assertions.assertEquals("Brown",lastname);
        Assertions.assertEquals(111,totalprice);






    }
}
