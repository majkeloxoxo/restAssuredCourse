package pl.javastart.restassured.test.tasks;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class UserCreationTests {

    @Test
    public void givenCorrectUserDataWhenCreateUserThenUserIsCreatedTest() {

        String user = "{\n" +
                "  \"id\": 445,\n" +
                "  \"username\": \"firstuser\",\n" +
                "  \"firstName\": \"Krzysztof\",\n" +
                "  \"lastName\": \"Kowalski\",\n" +
                "  \"email\": \"krzysztof@test.com\",\n" +
                "  \"password\": \"password\",\n" +
                "  \"phone\": \"+123456789\",\n" +
                "  \"userStatus\": 1\n" +
                "}";

        given().log().all()
                .body(user).contentType("application/json")
                .when().post("https://swaggerpetstore.przyklady.javastart.pl/v2/user")
                .then().log().all().statusCode(200);

        given().log().all()
                .pathParam("username", "firstuser")
                .contentType("application/json")
                .when().get("https://swaggerpetstore.przyklady.javastart.pl/v2/user/{username}")
                .then().log().all().statusCode(200);
    }
}
