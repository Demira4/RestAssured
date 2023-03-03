package Lesson4;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

public class MealPlannerTest extends AbstractTest{

    @Test
    void methodPostTest() {
        given().spec(getRequestSpecificationPostShoppingList())
                .when()
                .post(getBaseUrl() + "/mealplanner/"+ getUsername()+"/shopping-list/items")
                .prettyPeek()
                .then()
                .spec(getResponseSpecificationPostShoppingList())
                .body(containsString("aisles"))
        ;
    }
}
