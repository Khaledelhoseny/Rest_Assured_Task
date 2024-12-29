package EndPoints;

import ApiData.PerosnBody;
import base.BaseTests;
import io.restassured.response.Response;

public class PersonEndPoints extends BaseTests {


    public Response createUser(PerosnBody body){
        Response response = sendRequest("POST","/user" , body);
        return response;
    }

    public Response getUser(String username){
        pathParams.put("username" ,username );
        Response response = sendRequest("GET","/user/{username}" , null);
        return response;
    }

    public Response editUser(String username , PerosnBody body){
        pathParams.put("username" ,username );
        Response response = sendRequest("PUT","/user/{username}" , body);
        return response;
    }
    public Response deleteUser(String username){
        pathParams.put("username" ,username );
        Response response = sendRequest("DELETE","/user/{username}" , null);
        return response;
    }

}
