package br.com.api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


public class RestAssuredExampleTests {

    @BeforeClass
    public void setUp(){

        RestAssured.baseURI = "http://jsonplaceholder.typicode.com";

    }

    //GET
    @Test
    public void getRequest()
    {

                given()
                .when()
                .get("/posts/1")
                .then()
                .statusCode(200)
                .body("userId", is(1))
                .body("id", is(1))
                .body("title", containsString("sunt aut facere"));
    }

    @Test
    public void getRequestWithExtractResponse()
    {

               given()
               .contentType(ContentType.JSON)
               .when()
               .get("/posts/")
               .then()
               .extract().response();

    }


    @Test
        public void getRequestWithParam(){

        given().
                queryParam("id", 1).
                when().
                get("/posts/").
                then().
                statusCode(200).log().all().
                body("body", hasItem(containsString("expedita")));

    }

//POST
    @Test
    public void postRequestWithConcatenation()
    {
      given()
              .contentType("application/json")
              .body("{ \"title\":\"VR Dev Summit\", \"body\":\"blabla\", \"userId\":123 }")
              .when()
              .post("/posts")
              .then()
              .statusCode(201)
              .body("title", containsString("VR Dev Summit"))
              .body("body", containsString("blabla"))
              .body("userId", is(123));
    }

    @Test
    public void postRequestWithHashMap()
    {
        Map<String, Object> register = new HashMap<>();
        register.put("title","test tittle");
        register.put("body","text body");
        register.put("userId",124);

        given()
                .contentType(ContentType.JSON).log().all()
                .body(register)
                .when()
                .post("/posts")
                .then()
                .statusCode(201)
                .body("title", containsString("test tittle"))
                .body("body", containsString("text body"))
                .body("userId", is(124));

    }

    @Test
    public void postRequestWithObjectMapping(){

    Register register = new Register("Test Title", "Test body", 150);

    given()
            .contentType(ContentType.JSON)
            .body(register)
            .when()
            .post("/posts")
            .then()
            .statusCode(201)
            .body("title", is(register.getTitle()))
            .body("body", is(register.getBody()))
            .body("userId", is(register.getUserId()));

    }


    //PUT

    @Test
    public void putRequest(){

        Register register = new Register("Test2", "Test3", 180);

      Response response =  given()
                .contentType(ContentType.JSON)
                .body(register)
                .when()
                .put("/posts/1")
                .then()
                .extract().response();

        Assert.assertEquals(200, response.statusCode());
        Assert.assertEquals("Test2", response.jsonPath().getString("title"));
        Assert.assertEquals("180", response.jsonPath().getString("userId"));


    }


}
