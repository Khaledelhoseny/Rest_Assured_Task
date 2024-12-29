package tests;

import base.BaseTests;
import dataProvider.ExcelDataSupllier;
import EndPoints.Login;
import org.testng.annotations.Test;

public class LoginTests extends BaseTests {
    Login login = new Login();
    @Test(dataProviderClass = ExcelDataSupllier.class , dataProvider = "loginData")
    public void testSuccessfulLogin( String username , String password ){
        login.sendRequestWithValidData(username, password);
        login.verifySuccessfulResponse();
    }
}
