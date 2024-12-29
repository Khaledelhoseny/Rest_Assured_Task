package EndPoints;

import base.BaseTests;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasToString;

public class Login extends BaseTests {

    Response response;
    public void sendRequestWithValidData( String username , String password ){
        queryParam.put("username " , username);
        queryParam.put("password", password);
        response  = sendRequest("GET" ,"/user/login" , null ) ;
    }

    public void verifySuccessfulResponse(){
            response.prettyPrint();
            response.then()
                    .statusCode(HttpStatus.SC_OK)
                    .assertThat()
                    .body( "code", equalTo(200));
    }

}
