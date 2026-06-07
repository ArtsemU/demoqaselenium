package webtest.demoqa.com.tasks.elements.cucumber;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;

import static io.restassured.RestAssured.given;

public class ApiSteps {

    private Response response;

    @Given("I send a GET request to {string}")
    public void sendGetRequest(String url) {
        response = given()
                .relaxedHTTPSValidation()  // игнорируем SSL сертификат
                .when()
                .get(url);
    }

    @Then("the response status code should be {int}")
    public void verifyStatusCode(int expectedCode) {
        Assertions.assertEquals(
                expectedCode,
                response.getStatusCode(),
                "Status code mismatch"
        );
    }
}
