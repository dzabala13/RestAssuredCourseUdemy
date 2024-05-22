package HotelReservation;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class PartialUpgradeBooking extends  BaseTest {

    @Test
    public void partialUpgradeBooking (){
        JSONObject body = new JSONObject();
        body.put("firstname","danielPartialUpdate");

        Response response = given()
                            .contentType(ContentType.JSON)
                            .header("cookie","token="+generateToken())
                            .when()
                            .body(body.toString())
                            .patch("https://restful-booker.herokuapp.com/booking/"+createBookingId());

        response.prettyPrint();
        response.then()
                .statusCode(200);

        Assertions.assertEquals("danielPartialUpdate",response.jsonPath().getJsonObject("firstname"));

    }
}
