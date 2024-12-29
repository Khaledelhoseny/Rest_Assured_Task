package EndPoints;

import ApiData.PerosnBody;
import base.BaseTests;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

public class PetsEndPonts extends BaseTests {

    public Response createPet(Map<String, Object> body){
        Response response = sendRequest("POST","/pet" , body);
        return response;
    }
    public Response getPet(int petID){
        pathParams.put("petID" ,petID );
        Response response = sendRequest("GET","/pet/{petID}" , null);
        return response;
    }

    public Response editPet(Map<String, Object> body){

        RequestSpecification request ;
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
        request = RestAssured.given()
                .header("Content-Type", "application/json")
                .log()
                .all() ;
        Response response = request.body(body).when().put("pet");
        return response;
    }

    public Response deletePet(int petID){
        pathParams.put("petID" ,petID );
        Response response = sendRequest("DELETE","/pet/{petID}" , null);
        return response;
    }
}
