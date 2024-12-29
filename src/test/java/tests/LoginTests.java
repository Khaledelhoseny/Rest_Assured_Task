package tests;

import base.BaseTests;
import dataProvider.ExcelDataSupllier;
import EndPoints.Login;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;

public class LoginTests extends BaseTests {
    Login login = new Login();
    @Test(dataProviderClass = ExcelDataSupllier.class , dataProvider = "loginData")
    public void testSuccessfulLogin( String username , String password ){
        Response response = login.sendLogin(username, password);
        response.prettyPrint();
        response.then()
                .statusCode(HttpStatus.SC_OK)
                .assertThat()
                .body( "code", equalTo(200));
    }
}
