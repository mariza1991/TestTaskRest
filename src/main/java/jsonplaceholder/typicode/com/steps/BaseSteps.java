package jsonplaceholder.typicode.com.steps;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;

import static org.hamcrest.core.IsNull.notNullValue;

public class BaseSteps {

    public static final String API_URL = "https://jsonplaceholder.typicode.com";

    public ResponseSpecification checkResponseStructure() {
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectBody("id", notNullValue())
                .expectBody("userId", notNullValue())
                .expectBody("title", notNullValue())
                .expectBody("body", notNullValue())
                .build();
    }
}
