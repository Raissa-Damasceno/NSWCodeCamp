package test;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import static org.hamcrest.core.IsEqual.equalTo;


public class ApiTest {

    private static String url = "https://digitalapi.auspost.com.au/postcode/search.json";

    @Test
    public void testPostCodeApi() {
         given().header("auth-key", "d04b2517-a155-47b6-baea-864017ea0f93").
             and().param("q", "Sydney").and().param("state", "NSW").when().
              get(url).then()
              .assertThat().statusCode(is(200));
    }

    @Test
    public void verifyCorrectPostcode() {
        given().
                header("auth-key", "d04b2517-a155-47b6-baea-864017ea0f93")
                .param("q", "Melbourne")
                .param("state", "VIC")
                .when()
                .get(url)
                .then().assertThat().body("localities.locality[0].postcode",equalTo(3002));
    }
}

