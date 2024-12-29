package EndPoints;

import base.BaseTests;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;

import java.io.File;

import static org.hamcrest.Matchers.equalTo;

public class UploadPetsImage extends BaseTests {

    Response response ;
    public Response sendRequestWithValidImageFile(){
        File imageFile = new File("D:\\RestAssuredTask\\data\\images.jpeg");
        RequestSpecification request ;
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
        request = RestAssured.given()
                .multiPart("file", imageFile)
                .multiPart("additionalMetadata", "Sample Image")
                .pathParams("petID" , 1)
                .log()
                .all() ;
        return response = request.when().post("/pet/{petID}/uploadImage");
    }

}
