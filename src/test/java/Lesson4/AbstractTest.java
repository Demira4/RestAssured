package Lesson4;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AbstractTest {
    static Properties prop = new Properties();
    private static InputStream configFile;
    private static String apiKey;
    private static String baseUrl;
    private static String username;
    protected static ResponseSpecification responseSpecificationGet;
    protected static RequestSpecification requestSpecificationGet;
    protected static ResponseSpecification responseSpecificationPost;
    protected static RequestSpecification requestSpecificationPost;
    protected static RequestSpecification requestSpecificationPostShoppingList;
    protected static ResponseSpecification responseSpecificationPostShoppingList;

    @BeforeAll
    static void initTest() throws IOException {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        configFile = new FileInputStream("src/main/resources/myProperty");
        prop.load(configFile);

        apiKey =  prop.getProperty("apiKey");
        baseUrl= prop.getProperty("base_url");
        username = prop.getProperty("username");

        responseSpecificationGet = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectStatusLine("HTTP/1.1 200 OK")
                .expectContentType(ContentType.JSON)
                .expectResponseTime(Matchers.lessThan(5000L))
                .expectHeader("Connection","keep-alive")
                .expectHeader("Server", "cloudflare")
                .build();

        requestSpecificationGet = new RequestSpecBuilder()
                .addQueryParam("apiKey", apiKey)
              //  .addQueryParam("includeNutrition", "false")
                .setContentType(ContentType.JSON)
                .log(LogDetail.BODY)
                .build();

//        RestAssured.responseSpecification = responseSpecificationPost;
//        RestAssured.requestSpecification = requestSpecificationPost;
        responseSpecificationPost = new ResponseSpecBuilder()
                .expectStatusCode(200)
               // .expectStatusLine("HTTP/1.1 200 OK")
                .expectContentType(ContentType.JSON)
                .expectResponseTime(Matchers.lessThan(5000L))
                .expectHeader("Connection","keep-alive")
                .expectHeader("Server", "cloudflare")
                .build();

        requestSpecificationPost = new RequestSpecBuilder()
                .addQueryParam("apiKey", apiKey)
                .addQueryParam("language", "en")
                .setContentType(ContentType.fromContentType("application/x-www-form-urlencoded"))
                .log(LogDetail.HEADERS)
                .build();

//        RestAssured.responseSpecification = responseSpecificationPost;
//        RestAssured.requestSpecification = requestSpecificationPost;

        requestSpecificationPostShoppingList = new RequestSpecBuilder()
                .addQueryParam("apiKey", apiKey)
                .setContentType(ContentType.JSON)
                .log(LogDetail.HEADERS)
                .build();

        responseSpecificationPostShoppingList = new ResponseSpecBuilder()
                .expectStatusCode(200)
                // .expectStatusLine("HTTP/1.1 200 OK")
                .expectContentType(ContentType.JSON)
                .expectResponseTime(Matchers.lessThan(5000L))
                .expectHeader("Connection","keep-alive")
                .expectHeader("Server", "cloudflare")
                .build();
    }

    public static String getApiKey() {
        return apiKey;
    }

    public static String getBaseUrl() {
        return baseUrl;
    }
    public static String getUsername() {
        return username;
    }
    public RequestSpecification getRequestSpecificationGet(){
        return requestSpecificationGet;
    }
    public RequestSpecification getRequestSpecificationPost(){
        return requestSpecificationPost;
    }
    public ResponseSpecification getResponseSpecificationGet(){
        return responseSpecificationGet;
    }
    public ResponseSpecification getResponseSpecificationPost(){
        return responseSpecificationPost;
    }
    public RequestSpecification getRequestSpecificationPostShoppingList(){
        return requestSpecificationPostShoppingList;
    }
    public ResponseSpecification getResponseSpecificationPostShoppingList(){
        return responseSpecificationPostShoppingList;
    }
}
