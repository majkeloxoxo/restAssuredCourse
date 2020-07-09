package pl.javastart.restassured.test.parameters;

import org.testng.annotations.Test;
import pl.javastart.main.pojo.Category;
import pl.javastart.main.pojo.Pet;
import pl.javastart.main.pojo.Tag;

import java.util.Arrays;
import java.util.Collections;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class QueryParamsTests {

    @Test
    public void givenExistingPetWithStatusSoldWhenGetPetWithSoldStatusThenPetWithStatusIsReturnedTest() {
        Category category = new Category();
        category.setId(777);
        category.setName("dogs");

        Tag tag = new Tag();
        tag.setId(1);
        tag.setName("dogs-category");

        Pet pet = new Pet();
        pet.setId(123);
        pet.setName("Rex");
        pet.setPhotoUrls(Collections.singletonList("http://photos.com/dog1.jpg"));
        pet.setTags(Collections.singletonList(tag));
        pet.setStatus("sold");

        given().log().all()
                .contentType("application/json")
                .body(pet)
                .when().post("https://swaggerpetstore.przyklady.javastart.pl/v2/pet")
                .then().log().all()
                .statusCode(200);

        Pet[] pets = given().log().all()
                .contentType("application/json")
                .body(pet)
                .queryParam("status", "sold")
                .when().get("https://swaggerpetstore.przyklady.javastart.pl/v2/pet/findByStatus")
                .then().log().all()
                .statusCode(200)
                .extract().as(Pet[].class);

        assertTrue(Arrays.asList(pets).size() > 0, "List of pets");
//        assertFalse(Arrays.asList(pets).isEmpty());






    }
}
