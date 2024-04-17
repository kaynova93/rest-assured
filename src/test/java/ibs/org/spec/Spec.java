package ibs.org.spec;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Spec {
    private static String url = "http://localhost:8080/";

    public static RequestSpecification requestSpecification(){
        return new RequestSpecBuilder().setBaseUri(url).build();
    }

    public static ResponseSpecification responseSpecification(int statusCode){
        return new ResponseSpecBuilder()
                .expectStatusCode(statusCode)
                .build();
    }

    public static void installSpec(RequestSpecification rqSpec, ResponseSpecification rsSpec){
        RestAssured.requestSpecification = rqSpec;
        RestAssured.responseSpecification = rsSpec;
    }
}
