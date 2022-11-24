package org.globantBank.tests;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.globantBank.pojo.BankTransactionPojo;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.util.List;

import static io.restassured.RestAssured.baseURI;

public class BaseTest {

    @Parameters({"url"})
    @Test
    public void JsonPathUsages(String url) throws MalformedURLException
    {
        baseURI = url;
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get();

        JsonPath jsonPathEvaluator = response.jsonPath();

        List<BankTransactionPojo> transactions = jsonPathEvaluator.getList("transactions", BankTransactionPojo.class);

        for(BankTransactionPojo transaction : transactions){
            System.out.println("Transaction Name: " + transaction.getName());
        }
    }
}
