package Lesson4;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.StringContains.containsString;

public class MetodGetTest extends AbstractTest {
    @Test
    void getRecipePositiveTest() {
        given().spec(getRequestSpecificationGet())
                .when()
                .get(getBaseUrl() + "/recipes/complexSearch")
                .then()
                .spec(getResponseSpecificationGet());
    }

    @Test
    void test1(){
        given().spec(getRequestSpecificationGet())
                .when()
                .queryParam("apiKey", getApiKey())
                .queryParam("cuisine", "russian")
                .queryParam("dieta", "vegan")
                .queryParam("type", "drink")
                .get(getBaseUrl()+ "/recipes/complexSearch")
                .prettyPeek()
                .then()
                .spec(getResponseSpecificationGet())
                .body(containsString("Almond Joy Protein Shake"));

    }

    @Test
    void test2(){
        given().spec(getRequestSpecificationGet())
                .when()
                .queryParam("cuisine", "French")
                .queryParam("includeIngredients", "onion")
                .queryParam("type", "soup")
                .get(getBaseUrl()+ "/recipes/complexSearch")
                .prettyPeek()
                .then()
                .spec(getResponseSpecificationGet())
                .body(containsString("French Onion Marsala Soup"));

    }

    @Test
    void test3(){
        given().spec(getRequestSpecificationGet())
                .when()
                .queryParam("includeIngredients", "cheese")
                .queryParam("type", "souce")
                .get(getBaseUrl()+ "/recipes/complexSearch")
                .prettyPeek()
                .then()
                .spec(getResponseSpecificationGet())
                .body(containsString("Chicken Fajita Stuffed Bell Pepper"))
                .body(containsString("715495"));

    }

}
