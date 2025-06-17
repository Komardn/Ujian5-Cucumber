package com.juaracoding.apitest;

import org.hamcrest.CoreMatchers;
import org.json.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class AppTest {
    RequestSpecification requestSpecification;
    Response response;
    ValidatableResponse validatableResponse;
    private int id;

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "http://localhost:8000";
    }

    @Test
    public void getListGroup() {
        RestAssured.given().header(
                "Authorization",
                "Token cc5f27d68260c334ebeaa3c48c5a042142f63bea")
                .get("/catalogs/groups/")
                .then()
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .body("count", CoreMatchers.instanceOf(Integer.class))
                .body("next", CoreMatchers.anyOf(CoreMatchers.nullValue(),
                        CoreMatchers.instanceOf(Integer.class)));
    }

    @Test(dependsOnMethods = "getListGroup")
    public void createGroup() {
        JSONObject payload = new JSONObject();
        payload.put("title", "Mobil Dinas Java");
        payload.put("origin", "Pemkot DKI Javarta");

        Response response = RestAssured
                .given()
                .header(
                        "Authorization",
                        "Token cc5f27d68260c334ebeaa3c48c5a042142f63bea")
                .contentType(ContentType.JSON)

                .when()
                .post("/catalogs/groups/");

        id = response.jsonPath().getInt("id");

        response.then()
                .statusCode(201)
                .statusLine("HTTP/1.1 201 Created")
                .body("id", CoreMatchers.instanceOf(Integer.class))
                .body("title", CoreMatchers.instanceOf(String.class))
                .body("origin", CoreMatchers.instanceOf(String.class))
                .body("created_at", CoreMatchers.instanceOf(String.class))
                .body("updated_at", CoreMatchers.instanceOf(String.class))
                .body("owner", CoreMatchers.instanceOf(Integer.class));
    }

    @Test(dependsOnMethods = "createGroup")
    public void updateGroup() {
        JSONObject payload = new JSONObject();
        payload.put("title", "Truk Java");
        payload.put("origin", "Pemkot DKI Javarta");

        RestAssured
                .given()
                .header(
                        "Authorization",
                        "Token cc5f27d68260c334ebeaa3c48c5a042142f63bea")
                .contentType(ContentType.JSON)
                .body(payload.toString())
                .when()
                .put("/catalogs/groups/" + id + "/")
                .then()
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .body("id", CoreMatchers.instanceOf(Integer.class))
                .body("title", CoreMatchers.instanceOf(String.class))
                .body("origin", CoreMatchers.instanceOf(String.class))
                .body("created_at", CoreMatchers.instanceOf(String.class))
                .body("updated_at", CoreMatchers.instanceOf(String.class))
                .body("owner", CoreMatchers.instanceOf(Integer.class));
    }

    @Test(dependsOnMethods = "updateGroup", enabled = false)
    public void deleteGroup() {
        JSONObject payload = new JSONObject();
        payload.put("title", "Truk Java");
        payload.put("origin", "Pemkot DKI Javarta");

        RestAssured
                .given()
                .header(
                        "Authorization",
                        "Token cc5f27d68260c334ebeaa3c48c5a042142f63bea")
                .contentType(ContentType.JSON)
                .when()
                .delete("/catalogs/groups/" + id + "/")
                .then()
                .statusCode(204)
                .statusLine("HTTP/1.1 204 No Content");
    }
}
