package org.globantBank.tests;

import org.globantBank.utils.ApiHelpers;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import org.tinylog.Logger;

public class BankTransactionTest {

    SoftAssert softAssert = new SoftAssert();

    /**
     * Verify the Endpoint is empty(If it has any data use the DELETE request to clean and leave empty)
     * @param url From API
     */
    @Test(testName = "Test One", priority = 1)
    @Parameters({"url"})

    public void apiSizeTest(String url){
        Logger.info("Retrieving data from API...");
        ApiHelpers.ConvertToObject(url);
        Logger.info("Actual number of transactions = " + ApiHelpers.getListSize());
        Logger.info("Cleaning transactions if necessary");
        ApiHelpers.wipeTransactions(url);
        ApiHelpers.ConvertToObject(url);
        Logger.info("Number of transactions after cleaning = " + ApiHelpers.getListSize());
        softAssert.assertEquals(ApiHelpers.getListSize(),0, "The list should now be empty");
        softAssert.assertAll();
    }

    @Test(testName = "Test Two", priority = 2)
    @Parameters({"url"})

    public void dataInitializerTest(String url){
        Logger.info("Second");
    }

}
