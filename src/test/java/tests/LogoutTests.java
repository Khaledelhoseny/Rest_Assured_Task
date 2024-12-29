package tests;

import EndPoints.LogoutEndPoint;
import base.BaseTests;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;

public class LogoutTests extends BaseTests {
    @Test
    public void testUserLogout(){
        LogoutEndPoint logoutEndPoint = new LogoutEndPoint();
        Response response = logoutEndPoint.logoutUser();
        response.prettyPrint();
        response.then()
                .statusCode(HttpStatus.SC_OK)
                .assertThat()
                .body( "code", equalTo(200));
    }
}
