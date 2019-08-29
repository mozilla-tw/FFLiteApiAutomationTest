import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

@Tag("partner")
public class newsPointTest {
    private static final String NEWSPOINT_BASE_URL = "http://partnersnp.indiatimes.com/feed/fx/atp";

    @Test
    public void checkNewsPointJsonSchema() {
        RestAssured.baseURI = NEWSPOINT_BASE_URL;

        // build request spec
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.addParam("channel", "*");
        requestSpecBuilder.addParam("section", "top-news");
        requestSpecBuilder.addParam("lang", "english");
        requestSpecBuilder.addParam("curpg", "1");
        requestSpecBuilder.addParam("pp", "5");
        requestSpecBuilder.addParam("v", "v1");
        requestSpecBuilder.addParam("fromtime", "1551267146210");
        RequestSpecification requestSpec = requestSpecBuilder.build();

        // build response spec
        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder();
        responseSpecBuilder.expectStatusCode(200);
        responseSpecBuilder.expectBody(matchesJsonSchemaInClasspath("schema/NewsPointSchema.json"));
        ResponseSpecification responseSpec = responseSpecBuilder.build();

        // send newspoint request to verify response
        given().log().all().spec(requestSpec).when().get("/").then().log().all().spec(responseSpec);
    }
}
