package Lesson5;

import com.github.javafaker.Faker;
import lombok.SneakyThrows;
import okhttp3.ResponseBody;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ModifyProductTest {

    static ProductService productService;
    Faker faker = new Faker();
    Product productModify = null;
    Product product = null;
    int id;

    @BeforeAll
    static void beforeAll() {
        productService = RetrofitUtils.getRetrofit()
                .create(ProductService.class);
    }

    @BeforeEach
    void setUp() {
        product = new Product()
                .withTitle(faker.food().ingredient())
                .withCategoryTitle("Food")
                .withPrice((int) (Math.random() * 10000));

        productModify = new Product()
                .withId(9)
                .withTitle(faker.food().ingredient())
                .withCategoryTitle("Food")
                .withPrice(product.getPrice());
    }

    @Test
    void modifyProductInFoodCategoryTest() throws IOException {

        Response<Product> response = productService.modifyProduct(productModify)
                .execute();
        id = response.body().getId();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
        assertThat(response.body().getTitle(), equalTo(productModify.getTitle()));
        assertThat(response.body().getCategoryTitle(), equalTo(productModify.getCategoryTitle()));
        assertThat(response.body().getPrice(), equalTo(productModify.getPrice()));
        assertThat(response.code(), equalTo(200));

    }
    @Test
    void ProductByIdInFoodCategoryTest() throws IOException {

        Response<Product> response = productService.getProductById(id)
                .execute();
        assertThat(response.isSuccessful(),CoreMatchers.is(true));
        //assertThat(equalTo(product.getId()), response.body().getId();
    }

    @Test
    void returnProductsTest() throws IOException {
        Response<ResponseBody> response = productService.getProducts()
                .execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
    }

    @SneakyThrows
    @AfterEach
    void tearDown() {
        Response<ResponseBody> response = productService.deleteProduct(id).execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
    }
}
