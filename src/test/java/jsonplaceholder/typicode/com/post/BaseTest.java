package jsonplaceholder.typicode.com.post;

import io.restassured.RestAssured;
import jsonplaceholder.typicode.com.steps.PostSteps;
import org.testng.annotations.BeforeTest;

public class BaseTest {

    //check headers ??
    //As a result 2 sets of tests - smoke and regression

    int ATTEMPTS = 5;
    PostSteps postSteps = new PostSteps();

    @BeforeTest
    public void before() {
        RestAssured.useRelaxedHTTPSValidation(); //SSL
    }
}
