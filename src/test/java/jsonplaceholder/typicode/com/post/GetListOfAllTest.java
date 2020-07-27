package jsonplaceholder.typicode.com.post;

import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GetListOfAllTest extends BaseTest {

    //check total number got by id is 100

    @Test (groups = {"regression", "smoke"})
    public void checkTotalNumberOfResources() {
        JsonPath response = postSteps.getListOfAllResources()
                .then()
                .extract()
                .body()
                .jsonPath();

        assertThat(response.getList("id").size(), equalTo(100));
    }
}
