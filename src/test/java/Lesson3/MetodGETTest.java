package Lesson3;

import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.core.StringContains.containsString;


public class MetodGETTest extends AbstractTest {

    @Test
    void getWithQueryParams() {
        given()
                // .headers("x-api-key", getApiKey())
                .queryParam("apiKey", getApiKey())
                .queryParam("cuisine", "French")
                .queryParam("includeIngredients", "onion")
                .queryParam("type", "soup")
                .when()
                .get(getBaseUrl() + "/recipes/complexSearch")
                .then()
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .statusLine(containsString("OK"))
                .header("Connection", "keep-alive")
                .header("Server", "cloudflare")
                .contentType(ContentType.JSON)
                .body(containsString("French Onion Marsala Soup"))
                .time(Matchers.lessThan(2000L));

        given()
                // .headers("x-api-key", getApiKey())
                .queryParam("apiKey", getApiKey())
                .queryParam("cuisine", "russian")
                .queryParam("dieta", "vegan")
                .queryParam("type", "drink")
                .when()
                .get(getBaseUrl()+ "/recipes/complexSearch")
                .then()
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .statusLine(containsString("OK"))
                .header("Connection", "keep-alive")
                .header("Server", "cloudflare")
                .contentType(ContentType.JSON)
                .body(containsString("Almond Joy Protein Shake"))
                .time(Matchers.lessThan(2000L));
        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("includeIngredients", "cheese")
                // .queryParam("dieta", "vegan")
                .queryParam("type", "souce")
                .when()
                .get(getBaseUrl()+ "/recipes/complexSearch")
                .then()
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .statusLine(containsString("OK"))
                .header("Connection", "keep-alive")
                .header("Server", "cloudflare")
                .contentType(ContentType.JSON)
                .body(containsString("Chicken Fajita Stuffed Bell Pepper"))
                .body(containsString("715495"))
                .time(Matchers.lessThan(2000L));
        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("includeIngredients", "mango")
                .queryParam("query", "ice cream")
                .queryParam("type", "dessert")
                .when()
                .get(getBaseUrl()+ "/recipes/complexSearch")
                .then()
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .statusLine(containsString("OK"))
                .header("Connection", "keep-alive")
                .header("Server", "cloudflare")
                .contentType(ContentType.JSON)
                .body(containsString("Mango Avocado Ice Cream"))
                .body(containsString("653145"))
                .time(Matchers.lessThan(2000L));

        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("query", "cake")
                .queryParam("includeIngredients", "egg")
                .queryParam("maxReadyTime", "60")
                .queryParam("cuisine", "italian")
                .when()
                .get(getBaseUrl()+ "/recipes/complexSearch")
                .then()
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .statusLine(containsString("OK"))
                .header("Connection", "keep-alive")
                .header("Server", "cloudflare")
                .contentType(ContentType.JSON)
                .body(containsString("Charlotte Tiramisu Cake With A Hint Of Baileys - Heaven In Mouth"))
                .body(containsString("637463"))
                .time(Matchers.lessThan(2000L));

    }

}
