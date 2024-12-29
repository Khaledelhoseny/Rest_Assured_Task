package EndPoints;

import ApiData.PerosnBody;
import base.BaseTests;
import io.restassured.response.Response;

import java.util.List;

public class CreateWithArrayEndPoint extends BaseTests {
    public Response createListOfUsers(List<PerosnBody> body){
        Response response = sendRequest("POST","/user/createWithArray" , body);
        return response;
    }
}
