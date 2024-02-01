import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;


public class ApiTests {
    DataTest data = new DataTest();

    @Test
    @Tag("api")
    @DisplayName("Create new user")
    void apiCreateNewUserTest() {

        given()
                .baseUri(data.URI).contentType(ContentType.JSON)
                .body(data.user1)
                .when()
                .log().all()
                .post("/api/users")
                .then()
                .assertThat()
                .statusCode(201)
                .log().all()
                .body("name", is("Dmitrii"))
                .body("job", is("Engineer"));

    }

    @Test
    @Tag("api")
    @DisplayName("check code 404")
    void apiCheckErrorCodeTest() {
        given()
                .baseUri(data.URI)
                .when()
                .log().all()
                .get("/api/unknown/23")
                .then()
                .assertThat()
                .log().all()
                .statusCode(404);
    }

    @Test
    @Tag("api")
    @DisplayName("Delete User")
    void apiDeleteUserTest() {
        given()
                .baseUri(data.URI)
                .when()
                .log().all()
                .delete("/api/users/2")
                .then()
                .assertThat()
                .log().all()
                .statusCode(204);
    }

    @Test
    @Tag("api")
    @DisplayName("Update user")
    void apiUpdateUserTest() {
        given()
                .baseUri(data.URI).contentType(ContentType.JSON)
                .body(data.user1Up)
                .when()
                .log().all()
                .put("/api/users/2")
                .then()
                .assertThat()
                .statusCode(200)
                .log().all()
                .body("name", is("Dmitrii Elizarov"))
                .body("job", is("Senior Engineer"));
    }

    @Test
    @Tag("api")
    @DisplayName("Registration Test")
    void apiRegisterEmailTest() {
        given()
                .baseUri(data.URI).contentType(ContentType.JSON)
                .body(data.registr)
                .when()
                .log().all()
                .post("/api/register")
                .then()
                .assertThat()
                .statusCode(200)
                .log().all()
                .body("id", is(4))
                .body("token", is("QpwL5tke4Pnpja7X4"));

    }

}
