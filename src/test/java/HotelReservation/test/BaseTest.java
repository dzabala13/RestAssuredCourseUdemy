package HotelReservation.test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;

import java.util.Arrays;

import static io.restassured.RestAssured.given;

// this class help us to configurate our body request and reuse it in several parts of our project
public class BaseTest {
    RequestSpecification spec;
    // when we specify the general configuration for all project with the method sepUp, it has the general configuration
    //however it is importan take into account that it should be give like argument into the step give(spec) in each
    // request that we send, that is the reason that we have declared spec like a global variable that it will inherit
    // to another class.
    // beacuse
    @BeforeEach
    public void setUp(){
         spec = new RequestSpecBuilder()
                .setBaseUri("https://restful-booker.herokuapp.com")
                 // with this part we dont need to use the comand to see logs in each request
                .addFilters(Arrays.asList(new RequestLoggingFilter(),new ResponseLoggingFilter()))
                 .build();
    }

    protected String bookingObject (String firstname, String lastname, int totalprice, boolean depositpaid){

        JSONObject body = new JSONObject();
        body.put("firstname",firstname);
        body.put("lastname",lastname);
        body.put("totalprice",totalprice);
        body.put("depositpaid",depositpaid);

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
        Response response = given(spec)
                .when()
                //this line is just necesary if you need to see how the request is send.
                .contentType(ContentType.JSON)
                .body(bookingObject( "Daniel",  "Brown",  111,  true))
                .post("/booking");

        response
                .then()
                .statusCode(200);

        return response;
    }

    protected int createBookingId(){
       return createBooking().jsonPath().getJsonObject("bookingid");
    }


    protected String generateToken(){

        JSONObject body = new JSONObject();
        body.put("username" , "admin");
        body.put("password" , "password123");

        Response response = given(spec).
                contentType(ContentType.JSON).
                when().
                body(body.toString()).
                post("/auth");

        response
                .then()
                .statusCode(200);

        return response.jsonPath().getJsonObject("token");

    }
}
