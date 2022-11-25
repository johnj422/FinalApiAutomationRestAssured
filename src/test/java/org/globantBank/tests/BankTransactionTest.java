package org.globantBank.tests;

import org.globantBank.utils.ApiHelpers;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import org.tinylog.Logger;

public class BankTransactionTest extends BaseTest {

    SoftAssert softAssert = new SoftAssert();



   /* @Test
    public void testInfo(String url){

       Response response =
               given().
                       contentType(ContentType.JSON).
               body("{\"email\":\"test@mail.com\"}").
               post(url);

               response.prettyPrint();

    }*/
   /* @Test(priority = 1)
    @Parameters({"url"})
    public void endPointValidation(String url){
        given().
                get(url).
        then().
                assertThat().
                statusCode(HttpStatus.SC_OK);
    }*/

    /**
     * Verify the Endpoint is empty(If it has any data use the DELETE request to clean and leave empty)
     * @param url
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
        softAssert.assertEquals(ApiHelpers.getListSize(),0);
        softAssert.assertAll();
    }

}
