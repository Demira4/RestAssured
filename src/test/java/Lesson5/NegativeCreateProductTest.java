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


public class NegativeCreateProductTest {

    static ProductService productService;
    Product product = null;
    Product productWithNullId = null;
    Product productWithNullTitle = null;
    Product productWithNullCategoryTitle = null;
    Product productWithNullPrice = null;

    Faker faker = new Faker();

    @BeforeAll
    static void beforeAll() {
        productService = RetrofitUtils.getRetrofit()
                .create(ProductService.class);
    }

    @BeforeEach
    void setUp() {

        productWithNullId = new Product()
                .withId(0)
                .withTitle(faker.food().ingredient())
                .withCategoryTitle("Food")
                .withPrice((int) (Math.random() * 10000));

        productWithNullTitle = new Product()
                .withTitle(null)
                .withCategoryTitle("Food")
                .withPrice((int) (Math.random() * 10000));

        productWithNullCategoryTitle = new Product()
                .withTitle(faker.food().ingredient())
                .withCategoryTitle(null)
                .withPrice((int) (Math.random() * 10000));

        productWithNullPrice = new Product()
                .withTitle(faker.food().ingredient())
                .withCategoryTitle("Food")
                .withPrice((int) 0);


    }


    @Test
    void createProductInFoodCategoryWithNullIdTest() throws IOException {
        Response<Product> response = productService.createProduct(productWithNullId)
                .execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(false));
        assertThat(response.code(), equalTo(400));
        System.out.println("Product with null 'id' don't create");
    }

    @Test
    void createProductInFoodCategoryWithNullTitleTest() throws IOException {
        Response<Product> response = productService.createProduct(productWithNullTitle)
                .execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(false));
        assertThat(response.code(), equalTo(403));
        System.out.println("Product with null 'title' don't create");
    }

    @Test
    void createProductInFoodCategoryWithNullCategoryTitleTest() throws IOException {
        Response<Product> response = productService.createProduct(productWithNullCategoryTitle)
                .execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(false));
        assertThat(response.code(), equalTo(403));
        System.out.println("Product with null 'Category title' don't create");
    }
    @Test
    void createProductInFoodCategoryWithNullPriceTest() throws IOException {
        Response<Product> response = productService.createProduct(productWithNullPrice)
                .execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(false));
        assertThat(response.code(), equalTo(403));
        System.out.println("Product with null 'Price' don't create");
    }



//    @SneakyThrows
//    @AfterEach
//    void tearDown() {
//        Response<ResponseBody> response = productService.deleteProduct(test).execute();
//        assertThat(response.isSuccessful(), CoreMatchers.is(true));
//    }


}

