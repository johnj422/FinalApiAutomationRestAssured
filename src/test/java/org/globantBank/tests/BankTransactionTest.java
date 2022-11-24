package org.globantBank.tests;

import org.globantBank.pojo.BankTransactionPojo;
import org.globantBank.utils.Helpers;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.tinylog.Logger;

import java.net.MalformedURLException;
import java.util.List;
import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.*;

public class BankTransactionTest {

    @Parameters({"url"})

   /* @Test
    public void testInfo(String url){

       Response response =
               given().
                       contentType(ContentType.JSON).
               body("{\"email\":\"test@mail.com\"}").
               post(url);

               response.prettyPrint();

    }*/
/*    @Test
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

    }*/
    @Test
    public void initialTest(String url){
        Helpers.ConvertToObject(url);
    }

}
