package HotelReservation.test;

import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class GetAllBooking extends BaseTest{
    //curl -i https://restful-booker.herokuapp.com/booking

    @Test
    public void getAllBookingTest(){
        given(spec)
                .when()
                .get("/booking")
                .then()
                .statusCode(200);


    }

    @Test
    public void getBookingFiltered(){

        int idBooking= createBookingId();

        // if we want to declarate params into our query, we should rewrite the object spec
        // with the params
        spec.param("firstname","Daniel");
        spec.param("lastname","Brown");

        Response response =given(spec)
                .when()
                . get("/booking");

        response
                .then()
                .statusCode(200);

        // get a list of ids
        List<Integer> listId = response.jsonPath().getList("bookingid");
        // validate if a id is into a list
        Assertions.assertTrue(listId.contains(idBooking));
    }
}
