package org.globantBank.utils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
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

    /**
     *
     * Retrieves data from API, if there is any transaction, it wipes everything to leaves it blank.
     * @param url From API.
     */
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

    /**
     * Creates the number of transactions specified by the given number.
     * @param url From API.
     */
    public static void initializeData(String url){
        int transactionsToBeCreated = 10;
        while(transactionsToBeCreated > 0){
            given().
                    contentType(ContentType.JSON).
                    body("").
                    post(url);
            transactionsToBeCreated--;
        }

    }

    /**
     * Validates if the email included in the transaction is already on a registered transaction.
     * @param transaction
     * @return Boolean indicating email presence.
     */
    public static boolean isEmailCreated(BankTransactionPojo transaction){
        String emailToValidate = transaction.getEmail();
        long countOfEmailMatches = transactions.stream().filter(t -> t.getEmail().contains(emailToValidate)).count();
        return countOfEmailMatches != 0;

    }

    /**
     * Sends a transaction post if the email hasn't already been registered.
     * @param url From API.
     */
    public static void sendPost(String url){

        BankTransactionPojo transactionToSend = new BankTransactionPojo("test@gmail.com");

        if (!isEmailCreated(transactionToSend)){
            given().
                    contentType(ContentType.JSON).
                    body(transactionToSend).
                    post(url);
        }else{

            System.out.println("Email already exists");
        }
    }

    public static List<BankTransactionPojo> getTransactions() {
        return transactions;
    }

    public static String testFunction() {
        String response = "testFunction";
        return response;
    }
}
