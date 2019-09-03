
import io.restassured.RestAssured;

import static io.restassured.RestAssured.given;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import services.userService;

@Tag("ffLite")
public class userServiceTest {

    @Value("${spring.application.name}")
    static String name;

    private userService userService;
    private static String LOGIN_ENDPOINT = "/api/v1/signIn";

    /**
     * 1. load user profile
     * 2. check server connection
     * 3. set baseUrl
     */
    @BeforeAll
    public static void beforeAll() {
        System.out.println("spring.application.name "+name);
//        envConfigService envConfigService = new envConfigService();
//        envConfigService.loadEnvProperties();
//        if (envConfigService.checkConnection() != 200) {
//            fail("server host connection failed ...");
//        }
//        RestAssured.baseURI = protocol + "://" + host;
    }

    /**
     * 1. send user login request
     * 2. verify response 200
     */
    @Test
    public void check_login_success() {
        // load user profile from env
        userService = new userService();
        userService.loadUserProfile();

        // build login request spec
        RequestSpecification loginRequest = userService.login();
        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder();

        // build login response spec
        responseSpecBuilder.expectStatusCode(200);
        ResponseSpecification responseSpec = responseSpecBuilder.build();

        // send login request to verify response
        given().log().all().spec(loginRequest).when().get(LOGIN_ENDPOINT).then().log().all().spec(responseSpec);
    }
    /*
       @Test
       public void logoutTest(){}
     */
    /*
       @Test
       public void delete(){}
     */

}
