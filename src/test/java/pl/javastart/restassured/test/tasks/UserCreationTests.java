package pl.javastart.restassured.test.tasks;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import org.apache.http.RequestLine;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pl.javastart.main.pojo.user.User;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;


public class UserCreationTests extends TestBase {

    @Test
    public void givenCorrectUserDataWhenCreateUserThenUserIsCreatedTest() {

        User user = new User();
        user.setId(445);
        user.setUsername("firstuser");
        user.setFirstName("Hideo");
        user.setLastName("Kojima");
        user.setEmail("videokojima@mgs.com");
        user.setPassword("lalilulelo");
        user.setPhone("+123456789");
        user.setUserStatus(123);

        given().contentType("application/json")
                .body(user)
                .when().post("user")
                .then()
                .assertThat().body("code", equalTo(200))
                .assertThat().body("type", equalTo("unknown"))
                .assertThat().body("message", equalTo("445"))
                .assertThat().statusCode(200);

        given().contentType("application/json")
                .pathParam("username", user.getUsername())
                .when().get("user/{username}")
                .then()
                .assertThat().body("id", equalTo(445))
                .assertThat().body("username", equalTo("firstuser"))
                .assertThat().body("firstName", equalTo("Hideo"))
                .assertThat().body("lastName", equalTo("Kojima"))
                .assertThat().body("email", equalTo("videokojima@mgs.com"))
                .assertThat().body("password", equalTo("lalilulelo"))
                .assertThat().body("phone", equalTo("+123456789"))
                .assertThat().body("userStatus", equalTo(123))
                .assertThat().statusCode(200);


    }
}
