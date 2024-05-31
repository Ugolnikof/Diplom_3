package user;

import configure.EnvConfig;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class UserProperties {
    @Step("delete User")
    public ValidatableResponse deleteExistingUser(String token) {
        return given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .baseUri(EnvConfig.BASE_URL)
                .when()
                .delete(EnvConfig.USER_PATH)
                .then().log().all();
    }
}
