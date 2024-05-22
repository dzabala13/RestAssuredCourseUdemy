package HotelReservation;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class UpdateBooking extends BaseTest{
    @Test
    public void updateBooking(){

        Response response= given()
                .contentType(ContentType.JSON)
                .header("Cookie","token="+generateToken())
                .when()
                .body( bookingObject ("DanielUpdate", "zabala", 9999, false))
                .put("https://restful-booker.herokuapp.com/booking/"+createBookingId());

        response.prettyPrint();

        response
                .then()
                .statusCode(200);

        // this way to obtain a object from Json, we just put the label of item that we want and it extract it, without the full path
        Assertions.assertEquals("DanielUpdate",response.jsonPath().getJsonObject("firstname"));
        Assertions.assertEquals("zabala",response.jsonPath().getJsonObject("lastname"));
        Assertions.assertEquals(9999,(int) response.jsonPath().getJsonObject("totalprice"));
        Assertions.assertEquals(false,response.jsonPath().getJsonObject("depositpaid"));



    }


}
