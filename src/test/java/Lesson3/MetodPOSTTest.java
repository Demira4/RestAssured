package Lesson3;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class MetodPOSTTest extends AbstractTest {

    @Test
    void postRequestTest() {

        given()
                .queryParam("apiKey", getApiKey())
                .contentType("application/x-www-form-urlencoded")
                .formParam("title", "Chicken Fajita Stuffed Bell PCoconut Peanut Macaroonsepper")
                .when()
                .post(getBaseUrl() + "/recipes/cuisine")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON);

        given()
                .queryParam("apiKey", getApiKey())
                .contentType("application/x-www-form-urlencoded")
                .formParam("title", "Mango Avocado Ice Cream")
                .when()
                .post(getBaseUrl() + "/recipes/cuisine")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON);

        given()
                .queryParam("apiKey", getApiKey())
                .contentType("application/x-www-form-urlencoded")
                .formParam("title", "Almond Joy Protein Shake")
                .when()
                .post(getBaseUrl() + "/recipes/cuisine")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON);

        given()
                .queryParam("apiKey", getApiKey())
                .contentType("application/x-www-form-urlencoded")
                .formParam("title", "Coconut Peanut Macaroons")
                .when()
                .post(getBaseUrl() + "/recipes/cuisine")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON);

        given()
                .queryParam("apiKey", getApiKey())
                .contentType("application/x-www-form-urlencoded")
                .formParam("title", "Coffee Cream Martinis")
                .when()
                .post(getBaseUrl() + "/recipes/cuisine")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON);

    }
}
