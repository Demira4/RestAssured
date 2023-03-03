package Lesson4;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

public class MetodPostTest extends AbstractTest {


    @Test
    void methodPostTest() {
        given().spec(getRequestSpecificationPost())
                //  Response response = given().spec(getRequestSpecificationGet())
                .when()
                .formParam("title", "Coconut Peanut Macaroons")
                .post(getBaseUrl() + "/recipes/cuisine")
                .prettyPeek()
                .then()
                .spec(getResponseSpecificationPost())
                .body(containsString("Mediterranean"))
        ;
    }

    @Test
    void test() {
        given().spec(getRequestSpecificationPost())
                .when()
                .formParam("title", "Burger")
                //  .formParam("language", "en")
                .post(getBaseUrl() + "/recipes/cuisine").prettyPeek()
                .then()
                .spec(getResponseSpecificationPost())
                .statusCode(200)
                .body(containsString("American"));
    }


}
