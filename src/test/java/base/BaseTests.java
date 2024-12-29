package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utils.ScreenRecorderUtil;
import utils.UtilsTests;

import java.io.FileNotFoundException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class BaseTests {
    protected WebDriver driver ;
    public UtilsTests utilsTests ;
    ChromeOptions chromeOptions ;
    FirefoxOptions firefoxOptions ;


    @BeforeMethod (groups = {"regression2","smoke"})
    public void goHome( Method testMethod ) throws Exception {
        //        Create Test case in report
        utilsTests = new UtilsTests(driver) ;
        utilsTests.createTestCaseInReport(testMethod);
    }
    @AfterMethod (groups = {"regression2", "smoke"})
    public void takeScrenShotAfterMethod (Method testMethod , ITestResult result) throws Exception {
//                Set status in extent report
        utilsTests.setStatus(testMethod,result);
    }


    @BeforeSuite  (groups = {"regression2","smoke"})
    public void beforeSuite(){
        utilsTests = new UtilsTests(driver) ;
        utilsTests.createReport();
    }
    @AfterSuite (groups = {"regression2","smoke"})
    public void afterSuite(){
        utilsTests = new UtilsTests(driver) ;
        utilsTests.flushReport();
    }

    public static String access_token;

    public Map<String,Object> pathParams = new HashMap<String, Object>();
    public Map<String,Object> queryParam = new HashMap<>();

    public Response sendRequest(String method, String endpoint,Object requestBody ) {
        RequestSpecification request ;
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
        request = RestAssured.given()
                .header("Content-Type", "application/json")
                .log()
                .all() ;
        if (pathParams != null && !pathParams.isEmpty()) {
            request.pathParams(pathParams);
        }

        if (queryParam != null && !queryParam.isEmpty()) {
            request.queryParams(queryParam);
        }
        switch (method.toUpperCase()) {
            case "POST":
                if (requestBody != null ) {
                    return request.body(requestBody).when().post(endpoint);
                } else {
                    return request.when().post(endpoint);
                }
            case "GET":
                return request.when().get(endpoint);
            case "PUT":
                if (requestBody != null ) {
                    return request.body(requestBody).when().put(endpoint);
                } else {
                    return request.when().put(endpoint);
                }
            case "DELETE":
                return request.when().delete(endpoint);
            case "PATCH":
                if (requestBody != null ) {
                    return request.body(requestBody).when().patch(endpoint);
                } else {
                    return request.when().patch(endpoint);
                }
            default:
                throw new IllegalArgumentException("Unsupported HTTP method: " + method);
        }
    }

    public void Validate_Error_Messages (Response response,Integer statusCode , String error_message ,Integer error_id ) {
        response.prettyPrint();
        response.then()
                .statusCode(statusCode)
                .assertThat()
                .body("error_message" ,containsString(error_message) ,"error_id" ,equalTo(error_id) );
    }



}
