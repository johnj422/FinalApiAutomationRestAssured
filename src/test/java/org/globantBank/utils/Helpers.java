package org.globantBank.utils;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.globantBank.pojo.BankTransactionPojo;

import java.net.MalformedURLException;
import java.util.List;

import static io.restassured.RestAssured.baseURI;

public class Helpers {

    static List<BankTransactionPojo> transactions;
    public static void ConvertToObject(String url) {
        baseURI = url;
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get();

        JsonPath jsonPathEvaluator = response.jsonPath();

        transactions = jsonPathEvaluator.getList("transactions", BankTransactionPojo.class);

        for(BankTransactionPojo transaction : transactions){
            System.out.println("Transaction Name: " + transaction.getName());
        }

    }
}
