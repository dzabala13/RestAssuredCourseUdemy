package HotelReservation;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class BaseTest {

    // this class help us to configurate our body request and reuse it in several parts of our project
    protected String bookingObject (){

        JSONObject body = new JSONObject();
        body.put("firstname","Daniel");
        body.put("lastname","Brown");
        body.put("totalprice",111);
        body.put("depositpaid",true);

        JSONObject bookingdates = new JSONObject();
        bookingdates.put("checkin","2018-01-01");
        bookingdates.put("checkout","2019-01-01");

        body.put("bookingdates",bookingdates);
        body.put("additionalneeds","Breakfast");

        return body.toString();
    }

    protected Response createBooking(){
             /*    curl -X POST \
    https://restful-booker.herokuapp.com/booking \
            -H 'Content-Type: application/json' \
            -d '{
            "firstname" : "Jim",
            "lastname" : "Brown",
            "totalprice" : 111,
            "depositpaid" : true,
            "bookingdates" : {
        "checkin" : "2018-01-01",
                "checkout" : "2019-01-01"
    },
            "additionalneeds" : "Breakfast"*/

        // Here set up the configuration like contenType and the body request
        // It is so importan validate your body request because it can be cause faults in your request
        Response response = given()
                .when()
                //this line is just necesary if you need to see how the request is send.
                //    .log().all()
                .contentType(ContentType.JSON)
                .body(bookingObject())
                .post("https://restful-booker.herokuapp.com/booking");

        response.prettyPrint();

        response
                .then()
                .statusCode(200);

        return response;
    }
}
