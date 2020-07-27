package jsonplaceholder.typicode.com.steps;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.IsNull.nullValue;

public class BaseSteps {

    public static final String API_URL = "https://jsonplaceholder.typicode.com";

    public ResponseSpecification checkResponseStructure() {
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType("application/json; charset=utf-8")
                //       .expectHeader("Content-Encoding", "gzip")
                .expectBody("id", notNullValue())
                .expectBody("userId", notNullValue())
                .expectBody("title", notNullValue())
                .expectBody("body", notNullValue())
                .build();
    }

    public ResponseSpecification checkInvalidResponseStructure() {
        return new ResponseSpecBuilder()
                .expectStatusCode(404)
                .expectBody(equalTo("{}"))
                .build();
    }
}
