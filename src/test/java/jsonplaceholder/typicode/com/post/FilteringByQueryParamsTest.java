package jsonplaceholder.typicode.com.post;

import io.restassured.path.json.JsonPath;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;

public class FilteringByQueryParamsTest extends BaseTest {

    //check every can be filtered by userId
    //check total number for filtered by id is 10 for userId from 1 to 10
    //check that can be filtered by title (TODO from test file)
    //check that can be filtered by id
    //check that can not be filtered by non-existant param (TODO bug or feature)
    //check that can not be filtered by existant param, non-existant value

    @Test (groups = {"regression", "smoke"})
    public void filterByUserIdTest() {
        for (Integer i = 1; i <= 10; i++) {
            JsonPath response = postSteps.filterByParameter("userId", i.toString())
                    .then()
                    .extract()
                    .body()
                    .jsonPath();

            assertThat(response.getList("userId").size(), equalTo(10));
        }
    }

    @Test (groups = {"regression", "smoke"})
    public void filterByFewParamsTest() {
        HashMap testData = new HashMap();
        testData.put("id", "10");
        testData.put("userId", "1");
        JsonPath response = postSteps.filterByFewParameter(testData)
                .then()
                .extract()
                .body()
                .jsonPath();
        System.out.println(response);
        assertThat(response.getList("userId").size(), equalTo(1));
        assertThat(response.getString("userId"), equalTo("[" + testData.get("userId") + "]"));
        assertThat(response.getString("id"), equalTo("[" + testData.get("id") + "]"));
    }

    @Test (groups = {"smoke"})
    public void filterByRandomId() {
        for (Integer i = 1; i <= ATTEMPTS; i++) {
            Integer randomId = (int)(Math.random() * 100 + 1);
            String id = postSteps.filterByParameter("id", randomId.toString())
                    .then()
                    .spec(postSteps.checkResponseStructure())
                    .extract()
                    .body()
                    .jsonPath()
                    .getString("id");

            assertThat(id, equalTo("[" + randomId.toString() + "]"));
        }
    }

    @Test (groups = {"regression"})
    public void filterByIdTest() {
        for (Integer i = 1; i <= 100; i++) {
            String id = postSteps.filterByParameter("id", i.toString())
                    .then()
                    .spec(postSteps.checkResponseStructure())
                    .extract()
                    .body()
                    .jsonPath()
                    .getString("id");

            assertThat(id, equalTo("[" + i.toString() + "]"));
        }
    }

    //TODO bug found : Access to all data if filtering by not existent parameter
    @Test
    public void filterByNotExistantParams() {
        System.out.println(postSteps.filterByParameter("12ddded", "1").then().extract().body().asString());
    }

    @Test (groups = {"regression", "smoke"}, dataProvider = "testDataTitle")
    public void filterByTitle(String firstTitle, String secondTitle) {
        String id = postSteps.filterByParameter("title", firstTitle)
                .then()
                .spec(postSteps.checkResponseStructure())
                .extract()
                .body()
                .jsonPath()
                .getString("id");

        assertThat(id, equalTo("[97]"));

        id = postSteps.filterByParameter("title", secondTitle)
                .then()
                .spec(postSteps.checkResponseStructure())
                .extract()
                .body()
                .jsonPath()
                .getString("id");

        assertThat(id, equalTo("[3]"));
    }

    @Test (groups = {"regression", "smoke"})
    public void filterByNotExistantValue() {
        String title = postSteps.filterByParameter("title", "some1232222")
                .then()
                .spec(postSteps.checkResponseStructure())
                .extract()
                .body()
                .jsonPath()
                .getString("title");

        assertThat(title, equalTo("[" + "]"));

        String userId = postSteps.filterByParameter("userId", "123")
                .then()
                .spec(postSteps.checkResponseStructure())
                .extract()
                .body()
                .jsonPath()
                .getString("userId");

        assertThat(userId, equalTo("[" + "]"));
    }

    @DataProvider(name = "testDataTitle")
    public Object[][] dataProviderTitle(){
        return new Object[][]{{"quas fugiat ut perspiciatis vero provident", "ea molestias quasi exercitationem repellat qui ipsa sit aut"}};
    }

}
