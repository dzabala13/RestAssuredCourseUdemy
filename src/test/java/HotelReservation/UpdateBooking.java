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
        String bearerToken= generateToken();

        String bookindID = createBooking().jsonPath().getJsonObject("bookingid").toString();
        String bodyForUpdating =  bookingObject ("DanielUpdate", "zabala", 9999, false);

/*        curl -X PUT \
        https://restful-booker.herokuapp.com/booking/1 \
        -H 'Content-Type: application/json' \
        -H 'Accept: application/json' \
        -H 'Cookie: token=abc123' \
        -d '{
        "firstname" : "James",
                "lastname" : "Brown",
                "totalprice" : 111,
                "depositpaid" : true,
                "bookingdates" : {
            "checkin" : "2018-01-01",
                    "checkout" : "2019-01-01"
        },
        "additionalneeds" : "Breakfast"*/

        Response response= given()
                .contentType(ContentType.JSON)
                .header("Cookie","token="+bearerToken)
                .when()
                .body(bodyForUpdating)
                .put("https://restful-booker.herokuapp.com/booking/"+bookindID);

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
