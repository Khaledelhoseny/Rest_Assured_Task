package EndPoints;

import ApiData.PerosnBody;
import base.BaseTests;
import io.restassured.response.Response;

public class LogoutEndPoint extends BaseTests {
    public Response logoutUser(){
        Response response = sendRequest("GET","/user/logout" , null);
        return response;
    }
}
