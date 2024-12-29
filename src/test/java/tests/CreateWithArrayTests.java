package tests;

import ApiData.PerosnBody;
import EndPoints.CreateWithArrayEndPoint;
import EndPoints.PersonEndPoints;
import base.BaseTests;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;

public class CreateWithArrayTests extends BaseTests {
    String body ;
    PerosnBody perosnBody1;
    PerosnBody perosnBody2;
    PersonEndPoints personEndPoints = new PersonEndPoints();
    List<PerosnBody> users = new ArrayList<>() ;
    Faker faker1 ;
    Faker faker2 ;
    @BeforeClass
    public void prepareBodyData(){
        faker1 = new Faker() ;
        perosnBody1 = new PerosnBody() ;
        perosnBody1.setId(faker1.idNumber().hashCode());
        perosnBody1.setUsername(faker1.name().username());
        perosnBody1.setFirstName(faker1.name().firstName());
        perosnBody1.setLastName(faker1.name().lastName());
        perosnBody1.setEmail(faker1.internet().emailAddress());
        perosnBody1.setPassword(faker1.internet().password());
        perosnBody1.setPhone(String.valueOf(faker1.number().randomNumber()));

        faker2 = new Faker() ;
        perosnBody2 = new PerosnBody() ;
        perosnBody2.setId(faker2.idNumber().hashCode());
        perosnBody2.setUsername(faker2.name().username());
        perosnBody2.setFirstName(faker2.name().firstName());
        perosnBody2.setLastName(faker2.name().lastName());
        perosnBody2.setEmail(faker2.internet().emailAddress());
        perosnBody2.setPassword(faker2.internet().password());
        perosnBody2.setPhone(String.valueOf(faker2.number().randomNumber()));

        users.add(perosnBody1);
        users.add(perosnBody2);
    }

    @Test
    public void testCreateWithArrayList (){
        CreateWithArrayEndPoint createWithArrayEndPoint = new CreateWithArrayEndPoint();
        Response response = createWithArrayEndPoint.createListOfUsers(users);
        response.prettyPrint();
        response.then()
                .statusCode(HttpStatus.SC_OK)
                .assertThat()
                .body( "code", equalTo(200));
    }
}
