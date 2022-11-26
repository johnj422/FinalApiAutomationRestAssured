package org.globantBank.tests;

import org.globantBank.utils.Actions;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import org.tinylog.Logger;

public class BankTransactionTest {

    SoftAssert softAssert = new SoftAssert();

    /**
     * Verifies the Endpoint is empty(If it has any data it uses the DELETE request to clean and leaves the endpoint
     * empty)
     */
    @Test(testName = "Test One", priority = 1)

    public void apiSizeTest() {
        Logger.info("Retrieving data from API...");
        Actions.convertToObject();
        Logger.info("Transactions registered at the endpoint = " + Actions.getListSize());
        Logger.info("Deleting transactions if necessary");
        softAssert.assertEquals(Actions.wipeTransactions(), 0, "Total transactions should be 0");
        Logger.info("Transactions after cleaning = " + Actions.getListSize());
        Logger.info("*** Test one completed *** \n");
        softAssert.assertAll();
    }

    /**
     * Initializes the BankTransactionPojo with 10 random data. Also performs a code verification
     * avoiding duplicate email accounts. Then performs the POST request.
     */
    @Test(testName = "Test Two", priority = 2)

    public void dataInitializerTest() {
        Logger.info("Creating transactions");
        softAssert.assertEquals(Actions.initializeData(), 10, "Should create 10 transactions");
        Logger.info("Transactions created = " + Actions.getListSize());
        Logger.info("Posting transaction");
        softAssert.assertEquals(Actions.sendPost(), "Post successfully sent", "Should be " +
                "successful if email does not exist");
        Logger.info("Posting transaction with the same email");
        softAssert.assertEquals(Actions.sendPost(), "Email already exists", "Should not post " +
                "anything as email exists");
        Logger.info("*** Test Two completed *** \n");
        softAssert.assertAll();
    }

    /**
     * Triggers the GET request, asserting that there are not duplicate email accounts.
     */
    @Test(testName = "Test Tree", priority = 3)

    public void validateDuplicatedEmails() {
        Logger.info("Validating duplicated emails");
        softAssert.assertEquals(Actions.validateEmailDuplication(), Actions.getListSize(), "Unique " +
                "emails should match with Number of transactions");
        Logger.info("*** Test Tree completed *** \n");
        softAssert.assertAll();
    }

    /**
     * Updates an existing AccountNumber.
     */
    @Test(testName = "Test Four", priority = 4)

    public void UpdateAccount() {
        Logger.info("Updating account");
        softAssert.assertEquals(Actions.updateAccountNumber(), 200, "Code 200 should be received");
        Logger.info("*** Test Four completed *** \n");
        softAssert.assertAll();
    }

}
