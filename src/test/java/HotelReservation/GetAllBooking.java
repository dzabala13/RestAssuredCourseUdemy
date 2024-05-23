package HotelReservation;

import org.junit.jupiter.api.Test;

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
}
