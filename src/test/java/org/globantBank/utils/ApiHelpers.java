package org.globantBank.utils;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.globantBank.pojo.BankTransactionPojo;

import java.util.List;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;

public class ApiHelpers {

    /**
     *
     * Then receives a Json Sting from API server and converts it into a List of Java Objects
     * @author John Arango
     * @Parameters Url received from test params.
     *
     */
    static List<BankTransactionPojo> transactions;
    public static void ConvertToObject(String url) {
        baseURI = url;
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get();

        JsonPath jsonPathEvaluator = response.jsonPath();

        transactions = jsonPathEvaluator.getList("transactions", BankTransactionPojo.class);
    }

    public static int getListSize() {
        return transactions.size();
    }

    public static void wipeTransactions(String url) {
        if(transactions.size() > 0){
            for(BankTransactionPojo transaction : transactions){
                given().
                        delete(url + transaction.getId()).
                then().
                        assertThat().
                        statusCode(HttpStatus.SC_OK);
            } given().
                    get(url);
        }
    }
    public static void printTransactionsNames(){
        for(BankTransactionPojo transaction : transactions){
            System.out.println("Transaction Name: " + transaction.getName());
        }
    }

}
