package pl.javastart.restassured.test.tasks;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pl.javastart.main.pojo.user.User;

import static io.restassured.RestAssured.given;

public class UserCreationTests extends TestBase {

    @Test
    public void givenCorrectUserDataWhenCreateUserThenUserIsCreatedTest() {

        User user = new User();
        user.setId(445);
        user.setUsername("firstuser");
        user.setFirstName("Hideo");
        user.setLastName("Kojima");
        user.setEmail("videokojima@mgs.com");
        user.setPassword("lalulilelo");
        user.setPhone("+123456789");
        user.setUserStatus(123);

        given()
                .contentType("application/json")
                .body(user)
                .when().post("user")
                .then().log().all().statusCode(200);

        given()
                .contentType("application/json")
                .pathParam("username", user.getUsername())
                .when().get("user/{username}")
                .then().statusCode(200);
    }
}
