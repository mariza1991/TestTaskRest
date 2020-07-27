package jsonplaceholder.typicode.com.steps;

import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

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

    //filtering by more parameters
    public Response filterByFewParameter(HashMap<String, String> params) {
        String request = "";
        for (Map.Entry entry : params.entrySet()) {
            request = request + entry.getKey() + "=" + entry.getValue() + "&";
        }

        return given()
                .when()
                .get(API_URL + POSTS_URL + "?" + request.replaceAll(".$", ""));
    }
}
