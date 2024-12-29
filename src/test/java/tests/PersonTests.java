package tests;

import ApiData.PerosnBody;
import EndPoints.PersonEndPoints;
import base.BaseTests;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;

public class PersonTests extends BaseTests {
    String body ;
    PerosnBody perosnBody;
    PersonEndPoints personEndPoints = new PersonEndPoints();
    Faker faker ;

    @BeforeClass
    public void prepareBodyData(){
        faker = new Faker() ;
        perosnBody = new PerosnBody() ;
        perosnBody.setId(faker.idNumber().hashCode());
        perosnBody.setUsername(faker.name().username());
        perosnBody.setFirstName(faker.name().firstName());
        perosnBody.setLastName(faker.name().lastName());
        perosnBody.setEmail(faker.internet().emailAddress());
        perosnBody.setPassword(faker.internet().password());
        perosnBody.setPhone(String.valueOf(faker.number().randomNumber()));
        }

    @Test (priority = 1)
    public void testCreateNewUser() {
        Response response = personEndPoints.createUser(perosnBody);
        response.prettyPrint();
        response.then()
                .statusCode(HttpStatus.SC_OK)
                .assertThat()
                .body( "code", equalTo(200));
    }
    @Test(priority = 2)
    public void testGetUser(){
        Response response = personEndPoints.getUser(perosnBody.getUsername()) ;
        response.prettyPrint();
        response.then()
                .statusCode(HttpStatus.SC_OK)
                .assertThat()
                .body( "username", equalTo(perosnBody.getUsername()));
    }

    @Test(priority = 3)
    public void testEditUser(){
        perosnBody.setEmail(faker.internet().emailAddress());
        perosnBody.setFirstName(faker.name().firstName());
        Response response =  personEndPoints.editUser(perosnBody.getUsername() , perosnBody );
        response.prettyPrint();
        response.then()
                .statusCode(HttpStatus.SC_OK)
                .assertThat()
                .body( "code", equalTo(200));
    }
    @Test(priority = 4)
    public void testDeleteUser(){
        Response response = personEndPoints.deleteUser(perosnBody.getUsername()) ;
        response.prettyPrint();
        response.then()
                .statusCode(HttpStatus.SC_OK)
                .assertThat()
                .body( "message", equalTo(perosnBody.getUsername()));
    }


}
