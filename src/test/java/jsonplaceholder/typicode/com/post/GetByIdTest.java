package jsonplaceholder.typicode.com.post;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GetByIdTest extends BaseTest {

    //check each can be got by id + check structure for each is userId, id, title, body + check answer is 200 for all
    //check random from 1 to 100 can be got by id + check structure for each is userId, id, title, body + check answer is 200 for all
    //check non-existant ids = 0, -7, 103

    @Test (groups = {"regression"})
    public void getSourceById() {
        for (Integer i = 1; i <= 100; i++) {
            String id = postSteps.getResourceById(i.toString())
                    .then()
                    .spec(postSteps.checkResponseStructure())
                    .extract()
                    .body()
                    .jsonPath()
                    .getString("id");

            assertThat(id, equalTo(i.toString()));
        }
    }

    @Test (groups = {"smoke"})
    public void getRandomSourcesById() {
        for (Integer i = 1; i <= ATTEMPTS; i++) {
            Integer randomId = (int)(Math.random() * 100 + 1);
            String id = postSteps.getResourceById(randomId.toString())
                    .then()
                    .spec(postSteps.checkResponseStructure())
                    .extract()
                    .body()
                    .jsonPath()
                    .getString("id");

            assertThat(id, equalTo(randomId.toString()));
        }
    }

    @Test (groups = {"regression", "smoke"}, dataProvider = "testDataId")
    public void getSourceByNotExistantId(String id, String value) {
        String idValue = postSteps.filterByParameter(id, value)
                .then()
                .spec(postSteps.checkResponseStructure())
                .extract()
                .body()
                .jsonPath()
                .getString("id");
        assertThat(idValue, equalTo("[" + "]"));
    }

    @DataProvider
    public Object[][] testDataId() {
        return new Object[][]{
                {"id", null},
                {"id", "-20"},
                {"id", "22222"},
        };
    }
}
