package EndPoints;

import ApiData.PerosnBody;
import base.BaseTests;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasToString;

public class Login extends BaseTests {

    Response response;
    public Response sendLogin(String username , String password){
        queryParam.put("username " , username);
        queryParam.put("password", password);
        Response response  = sendRequest("GET" ,"/user/login" , null ) ;
        return response;
    }



}
