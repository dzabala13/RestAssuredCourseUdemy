package HotelReservation;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class DeleteBooking extends BaseTest{

    @Test
    public void deleteBooking(){
        int idBooking = createBookingId();

       Response response=  given()
               .header("Cookie","token="+generateToken())
               .contentType(ContentType.JSON)
               .when()
               .log().all()
               .delete("https://restful-booker.herokuapp.com/booking/"+idBooking);

        response.prettyPrint();

        response.then().statusCode(201);

        // that it is a plus, because we need to validete that the booking registred was deleted correctly
        Response search =  given()
                .when()
                .get("https://restful-booker.herokuapp.com/booking/"+idBooking);

        search.prettyPrint();

        search.then()
                .statusCode(404);

        Assertions.assertEquals("Not Found",search.body().asPrettyString());






    }
}
