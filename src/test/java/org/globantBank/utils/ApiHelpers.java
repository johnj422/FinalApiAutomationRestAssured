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
     * A Constant to use the Url from API endpoint previously set in ApiUrl class.
     */
    static String url = ApiUrl.getUrl2();

    /**
     * A Constant to store the information retrieved from the API.
     */
    static List<BankTransactionPojo> transactions;

    /**
     *
     * Receives a Json Sting from API server and converts it into a List of Java Objects
     */
    public static void convertToObject() {
        baseURI = url;
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get();
        JsonPath jsonPathEvaluator = response.jsonPath();
        transactions = jsonPathEvaluator.getList("transactions", BankTransactionPojo.class);

    }

    /**
     * @return The size from the transactions list.
     */
    public static int getListSize() {
        return transactions.size();
    }

    /**
     * Retrieves data from API, if there is any transaction, it wipes everything to leaves it blank.
     * @return The number of transactions.
     */
    public static int wipeTransactions() {
        if(transactions.size() > 0){
            for(BankTransactionPojo transaction : transactions){
                given().
                        delete(url + transaction.getId()).
                then().
                        assertThat().
                        statusCode(HttpStatus.SC_OK);
            }   given().
                        get(url);
                convertToObject();

        }return transactions.size();
    }

    /**
     * Creates the number of transactions specified by the given number.
     * @return the number of created transactions.
     */
    public static int initializeData(){
        int transactionsToBeCreated = 10;

        while(transactionsToBeCreated > 0){
            given().
                    contentType(ContentType.JSON).
                    body("").
                    post(url);
            transactionsToBeCreated--;
        }
            convertToObject();
            return transactions.size();

    }

    /**
     * Validates if the email included in the transaction is already on a registered transaction.
     * @return Boolean indicating email presence.
     */
    public static boolean isEmailCreated(BankTransactionPojo transaction){
        String emailToValidate = transaction.getEmail();
        long countOfEmailMatches = transactions.stream().filter(t -> t.getEmail().contains(emailToValidate)).count();
        return countOfEmailMatches != 0;

    }

    /**
     * Sends a transaction post if the email hasn't been already registered.
     * @return A String with the response.
     */
    public static String sendPost(){

        BankTransactionPojo transactionToSend = new BankTransactionPojo("test@gmail.com");
        String response;

        if (!isEmailCreated(transactionToSend)){
            given().
                    contentType(ContentType.JSON).
                    body(transactionToSend).
                    post(url);
            convertToObject();
            response = "Post successfully sent";
        }else{

            response = "Email already exists";
        }
        return response;
    }

    /**
     * Receives the information from API, extracts all emails from transactions
     * @return An int number of unique emails.
     */
    public static int validateEmailDuplication(){
        convertToObject();
        return (int) transactions.stream().map(BankTransactionPojo::getEmail).distinct().count();
    }

    /**
     * Updates the account number from a transaction ID.
     * @return The status code of the put request.
     */
    public static int updateAccountNumber(){
        int idToUpdate = 1;
        String accountNumberToSend = "12345678";

        Response code = given().contentType(ContentType.JSON).
                                body("{" + "\"accountNumber\":\"" + accountNumberToSend + "\"}").
                                put(url + idToUpdate);
        return code.getStatusCode();
    }
}
