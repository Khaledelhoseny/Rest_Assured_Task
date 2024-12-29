package tests;

import EndPoints.UploadPetsImage;
import base.BaseTests;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;

public class UploadPetsImageTests extends BaseTests {
    UploadPetsImage uploadPetsImage = new UploadPetsImage();
    @Test
    public void testSuccessfulImageUpload(){
        Response response = uploadPetsImage.sendRequestWithValidImageFile();
        response.prettyPrint();
        response.then()
                .statusCode(HttpStatus.SC_OK)
                .assertThat()
                .body( "code", equalTo(200));
    }
}
