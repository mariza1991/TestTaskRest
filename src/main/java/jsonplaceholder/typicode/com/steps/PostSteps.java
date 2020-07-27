package jsonplaceholder.typicode.com.steps;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class PostSteps extends BaseSteps {

    public static final String POSTS_URL = "/posts";

    //Getting a resource by id
    public Response getResourceById(String id) {
        return given()
                        .when()
                        .get(API_URL + POSTS_URL + "/" + id);
    }

    //Getting list of all resources
    public Response getListOfAllResources() {
        return given()
                .when()
                .get(API_URL + POSTS_URL);
    }

    //Filtering by query parameters (like /posts?userId=1)
    public Response filterByParameter(String parameter, String value) {
        return given()
                .when()
                .get(API_URL + POSTS_URL + "?" + parameter + "=" + value);
    }

    //TODO filtering by more parameters
}
