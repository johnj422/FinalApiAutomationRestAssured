package org.globantBank.tests;

import org.globantBank.utils.ApiHelpers;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import org.tinylog.Logger;

public class BankTransactionTest {

    SoftAssert softAssert = new SoftAssert();

    /**
     * Verify the Endpoint is empty(If it has any data use the DELETE request to clean and leave empty)
     *
     * @param url From API
     */
    @Test(testName = "Test One", priority = 1)

    public void apiSizeTest() {
        Logger.info("Retrieving data from API...");
        ApiHelpers.convertToObject();
        Logger.info("Actual number of transactions = " + ApiHelpers.getListSize());
        Logger.info("Deleting transactions if necessary");
        softAssert.assertEquals(ApiHelpers.wipeTransactions(), 0, "Total transactions should be 0");
        Logger.info("Transactions after cleaning = " + ApiHelpers.getListSize());
        Logger.info("*** Test one completed *** \n");
        softAssert.assertAll();
    }

    /**
     * Initialize the POJO with 10 random data (Use the minimal requirements). Also make a code verification
     * for avoiding duplicate email accounts. Then perform the POST request.
     *
     * @param url From API
     */
    @Test(testName = "Test Two", priority = 2)

    public void dataInitializerTest() {
        Logger.info("Creating transactions");
        softAssert.assertEquals(ApiHelpers.initializeData(), 10, "Should create 10 transactions");
        Logger.info("Transactions created = " + ApiHelpers.getListSize());
        Logger.info("Posting transaction");
        softAssert.assertEquals(ApiHelpers.sendPost(), "Post successfully sent", "Should be " +
                "successful if email does not exist");
        Logger.info("Posting transaction with the same email");
        softAssert.assertEquals(ApiHelpers.sendPost(), "Email already exists", "Should not post " +
                "anything as email exists");
        Logger.info("*** Test Two completed *** \n");
        softAssert.assertAll();
    }

    /**
     * Make the GET request, asserting that there are not duplicate email accounts.
     *
     * @param url From API
     */
    @Test(testName = "Test Tree", priority = 3)

    public void validateDuplicatedEmails() {
        Logger.info("Validating duplicated emails");
        softAssert.assertEquals(ApiHelpers.validateEmailDuplication(), ApiHelpers.getListSize(), "Unique " +
                "emails should match with Number of transactions");
        Logger.info("*** Test Tree completed *** \n");
        softAssert.assertAll();
    }

    /**
     * Add a test to update an existing AccountNumber.
     * @param url Fro, API.
     */
    @Test(testName = "Test Four", priority = 4)

    public void UpdateAccount() {
        Logger.info("Updating account");
        softAssert.assertEquals(ApiHelpers.updateAccountNumber(), 200, "Code 200 should be received");
        Logger.info("*** Test Four completed *** \n");
        softAssert.assertAll();
    }

}
