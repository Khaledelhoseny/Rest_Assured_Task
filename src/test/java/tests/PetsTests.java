package tests;

import EndPoints.PetsEndPonts;
import base.BaseTests;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.hamcrest.Matchers.equalTo;

public class PetsTests extends BaseTests {
    PetsEndPonts  petsEndPonts  = new PetsEndPonts();
    Map<String, Object> requestBody ;
    Map<String, Object> category ;
    Map<String, Object> tag ;
    Faker faker ;
    @BeforeClass
    public void prepareData() throws IOException {
//        FileInputStream file = new FileInputStream("D:\\Automation-Selenium-WebDriver\\data\\data.properties") ;
//        Properties p = new Properties() ;
//        p.load(file);
//        System.out.println(p.getProperty("browser"));

        faker = new Faker() ;
        requestBody = new HashMap<>();
        requestBody.put("id", faker.idNumber().hashCode());
        category = new HashMap<>();
        category.put("id", faker.idNumber().hashCode());
        category.put("name", faker.name().firstName());
        requestBody.put("category", category);
        requestBody.put("name", faker.name().firstName());
        requestBody.put("photoUrls", List.of("aaaaa","sssss"));
        tag = new HashMap<>();
        tag.put("id", faker.idNumber().hashCode());
        tag.put("name", faker.name().firstName());
        requestBody.put("tags", List.of(tag));
        requestBody.put("status", "available");
    }


    @Test(priority = 1)
    public void testCreatPet(){
        Response response = petsEndPonts.createPet(requestBody);
        response.prettyPrint();
        response.then()
                .statusCode(HttpStatus.SC_OK)
                .assertThat()
                .body( "tags[0].name", equalTo(tag.get("name")));
//        System.out.println(tag.get("name"));
//        System.out.println(response.jsonPath().getString("tags[0].name"));
    }

    @Test(priority = 2)
    public void testGetPet(){
        int pet_Id = (int) requestBody.get("id");
        Response response = petsEndPonts.getPet(pet_Id);
        response.prettyPrint();
        response.then()
                .statusCode(HttpStatus.SC_OK)
                .assertThat()
                .body( "name", equalTo(requestBody.get("name")));
    }

    @Test(priority = 3)
    public void testEditPet(){
        category.put("name", "caaaaaaaaats");
        Response response = petsEndPonts.editPet(requestBody);
        response.prettyPrint();
        response.then()
                .statusCode(HttpStatus.SC_OK)
                .assertThat()
                .body( "category.name", equalTo(category.get("name")));
//        System.out.println(category.get("name"));
//        System.out.println(response.jsonPath().getString("category.name"));
    }
    @Test(priority = 4)
    public void testDeletePet(){
//        System.out.println(requestBody.get("id"));
        int pet_Id = (int) requestBody.get("id");
        Response response = petsEndPonts.deletePet(pet_Id);
        response.prettyPrint();
        response.then()
                .statusCode(HttpStatus.SC_OK)
                .assertThat()
                .body( "message", equalTo(requestBody.get("id").toString() ));
    }
}
