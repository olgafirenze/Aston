package Lesson_8;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import java.util.Map;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ApiEchoTests {

    static {
        RestAssured.baseURI = "https://postman-echo.com";
    }

    @Test
    void testGetRequest() {
        Response response = given()
                .header("cache-control", "no-cache")
                .queryParam("foo1", "bar1")
                .queryParam("foo2", "bar2")
                .when()
                .get("/get")
                .then()
                .extract().response();

        assertEquals(200, response.getStatusCode());

        Map<String, Object> jsonResponse = response.jsonPath().getMap("$");
        assertThat(jsonResponse).containsKeys("args", "headers", "url");

        Map<String, String> args = response.jsonPath().getMap("args");
        assertThat(args)
                .containsEntry("foo1", "bar1")
                .containsEntry("foo2", "bar2");

        String url = response.jsonPath().getString("url");
        assertThat(url).contains("/get?foo1=bar1&foo2=bar2");

        Map<String, String> headers = response.jsonPath().getMap("headers");
        assertThat(headers)
                .containsKeys("host", "x-forwarded-proto", "user-agent",
                        "accept", "accept-encoding", "cache-control");
        assertThat(headers)
                .containsEntry("host", "postman-echo.com")
                .containsEntry("x-forwarded-proto", "https")
                .containsEntry("accept", "*/*")
                .containsEntry("accept-encoding", "gzip, br")
                .containsEntry("cache-control", "no-cache");
    }


    @Test
    void testPostRawText() {
        String requestBody = "{\"test\": \"value\"}";
        Response response = given()
                .header("cache-control", "no-cache")
                .header("Content-Type", "text/plain")
                .body(requestBody)
                .when()
                .post("/post")
                .then()
                .extract().response();

        assertEquals(200, response.getStatusCode());

        Map<String, Object> jsonResponse = response.jsonPath().getMap("$");
        assertThat(jsonResponse).containsKeys("args", "headers", "url", "data", "json");

        String data = response.jsonPath().getString("data");
        assertThat(data).isEqualTo("{\"test\": \"value\"}");

        String url = response.jsonPath().getString("url");
        assertThat(url).isEqualTo("https://postman-echo.com/post");

        Map<String, String> headers = response.jsonPath().getMap("headers");
        assertThat(headers)
                .containsKeys("host", "x-forwarded-proto", "user-agent",
                        "accept", "accept-encoding", "cache-control", "content-type");
        assertThat(headers)
                .containsEntry("host", "postman-echo.com")
                .containsEntry("x-forwarded-proto", "https")
                .containsEntry("accept", "*/*")
                .containsEntry("accept-encoding", "gzip, br")
                .containsEntry("cache-control", "no-cache");
    }


    @Test
    void testPostFormData() {
        Response response = given()
                .header("cache-control", "no-cache")
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .formParam("foo1", "bar1")
                .formParam("foo2", "bar2")
                .when()
                .post("/post")
                .then()
                .extract().response();

        assertEquals(200, response.getStatusCode());

        Map<String, Object> jsonResponse = response.jsonPath().getMap("$");
        assertThat(jsonResponse).containsKeys("args", "data", "files", "form", "headers", "url", "json");

        Map<String, String> form = response.jsonPath().getMap("form");
        assertThat(form)
                .containsEntry("foo1", "bar1")
                .containsEntry("foo2", "bar2");

        Map<String, Object> json = response.jsonPath().getMap("json");
        assertThat(json)
                .containsEntry("foo1", "bar1")
                .containsEntry("foo2", "bar2");

        Map<String, String> headers = response.jsonPath().getMap("headers");
        assertThat(headers)
                .containsKeys("host", "x-forwarded-proto", "user-agent", "accept", "accept-encoding", "cache-control", "content-type");

        assertThat(headers)
                .containsEntry("host", "postman-echo.com")
                .containsEntry("x-forwarded-proto", "https")
                .containsEntry("accept", "*/*")
                .containsEntry("accept-encoding", "gzip, br")
                .containsEntry("cache-control", "no-cache")
                .containsEntry("content-type", "application/x-www-form-urlencoded; charset=UTF-8");

        String url = response.jsonPath().getString("url");
        assertThat(url).isEqualTo("https://postman-echo.com/post");
    }


    @Test
    void testPutRequest() {
        String textBody = "This is expected to be sent back as part of response body.";
        Response response = given()
                .header("cache-control", "no-cache")
                .header("Content-Type", "text/plain")
                .body(textBody)
                .when()
                .put("/put")
                .then()
                .extract().response();

        assertEquals(200, response.getStatusCode());

        Map<String, Object> jsonResponse = response.jsonPath().getMap("$");
        assertThat(jsonResponse)
                .containsKeys("args", "headers", "data", "files", "form", "json", "url");

        String data = response.jsonPath().getString("data");
        assertThat(data).isEqualTo("This is expected to be sent back as part of response body.");

        Object json = response.jsonPath().get("json");
        assertThat(json).isNull();

        Map<String, Object> form = response.jsonPath().getMap("form");
        assertThat(form).isEmpty();

        Map<String, Object> files = response.jsonPath().getMap("files");
        assertThat(files).isEmpty();

        String url = response.jsonPath().getString("url");
        assertThat(url).isEqualTo("https://postman-echo.com/put");

        Map<String, String> headers = response.jsonPath().getMap("headers");
        assertThat(headers)
                .containsKeys("host", "x-forwarded-proto", "user-agent", "accept", "accept-encoding", "cache-control", "content-type");
        assertThat(headers)
                .containsEntry("host", "postman-echo.com")
                .containsEntry("x-forwarded-proto", "https")
                .containsEntry("accept", "*/*")
                .containsEntry("accept-encoding", "gzip, br")
                .containsEntry("cache-control", "no-cache")
                .containsEntry("content-type", "text/plain; charset=ISO-8859-1");
    }


    @Test
    void testPatchRequest() {
        String textBody = "This is expected to be sent back as part of response body.";
        Response response = given()
                .header("cache-control", "no-cache")
                .header("Content-Type", "text/plain")
                .body(textBody)
                .when()
                .patch("/patch")
                .then()
                .extract().response();

        assertEquals(200, response.getStatusCode());

        Map<String, Object> jsonResponse = response.jsonPath().getMap("$");
        assertThat(jsonResponse)
                .containsKeys("args", "headers", "data", "files", "form", "json", "url");

        String data = response.jsonPath().getString("data");
        assertThat(data).isEqualTo("This is expected to be sent back as part of response body.");

        Object json = response.jsonPath().get("json");
        assertThat(json).isNull();

        Map<String, Object> form = response.jsonPath().getMap("form");
        assertThat(form).isEmpty();

        Map<String, Object> files = response.jsonPath().getMap("files");
        assertThat(files).isEmpty();

        String url = response.jsonPath().getString("url");
        assertThat(url).isEqualTo("https://postman-echo.com/patch");

        Map<String, String> headers = response.jsonPath().getMap("headers");
        assertThat(headers)
                .containsKeys("host", "x-forwarded-proto", "user-agent", "accept", "accept-encoding", "cache-control", "content-type");
        assertThat(headers)
                .containsEntry("host", "postman-echo.com")
                .containsEntry("x-forwarded-proto", "https")
                .containsEntry("accept", "*/*")
                .containsEntry("accept-encoding", "gzip, br")
                .containsEntry("cache-control", "no-cache")
                .containsEntry("content-type", "text/plain; charset=ISO-8859-1");
    }


    @Test
    void testDeleteRequest() {
        String textBody = "This is expected to be sent back as part of response body.";
        Response response = given()
                .header("cache-control", "no-cache")
                .header("Content-Type", "text/plain")
                .body(textBody)
                .when()
                .delete("/delete")
                .then()
                .extract().response();

        assertEquals(200, response.getStatusCode());

        Map<String, Object> jsonResponse = response.jsonPath().getMap("$");
        assertThat(jsonResponse)
                .containsKeys("args", "headers", "data", "files", "form", "json", "url");

        String data = response.jsonPath().getString("data");
        assertThat(data).isEqualTo("This is expected to be sent back as part of response body.");

        Object json = response.jsonPath().get("json");
        assertThat(json).isNull();

        Map<String, Object> form = response.jsonPath().getMap("form");
        assertThat(form).isEmpty();

        Map<String, Object> files = response.jsonPath().getMap("files");
        assertThat(files).isEmpty();

        String url = response.jsonPath().getString("url");
        assertThat(url).isEqualTo("https://postman-echo.com/delete");

        Map<String, String> headers = response.jsonPath().getMap("headers");
        assertThat(headers)
                .containsKeys("host", "x-forwarded-proto", "user-agent", "accept", "accept-encoding", "cache-control", "content-type");
        assertThat(headers)
                .containsEntry("host", "postman-echo.com")
                .containsEntry("x-forwarded-proto", "https")
                .containsEntry("accept", "*/*")
                .containsEntry("accept-encoding", "gzip, br")
                .containsEntry("cache-control", "no-cache")
                .containsEntry("content-type", "text/plain; charset=ISO-8859-1");
    }

}
