package org.globantBank.tests;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;

public class BaseTest {

    @BeforeClass
    public void setupTest(){
        /*RestAssured.baseURI = "https://637e4e3dcfdbfd9a63ae0792.mockapi.io";
        RestAssured.basePath = "/api/v1/bank/transactions";*/
        /*RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());*/
    }
}
