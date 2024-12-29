package tests;

import EndPoints.UploadPetsImage;
import base.BaseTests;
import org.testng.annotations.Test;

public class UploadPetsImageTests extends BaseTests {
    UploadPetsImage uploadPetsImage = new UploadPetsImage();
    @Test
    public void testSuccessfulImageUpload(){
        uploadPetsImage.sendRequestWithValidImageFile();
        uploadPetsImage.verifySuccessfulResponse();
    }
}
