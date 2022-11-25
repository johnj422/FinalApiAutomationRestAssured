# Final Exercise API Automation

1. Create an account in Mockapi.io and set up an endpoint for **Bank Transactions** (All information embedded in the same API, i.e., only 1 endpoint required)
2. Structure the project making the requests reusable, avoid to repeat code or the endpoints, use TestNG and create a **Readme.md** with the specifications and steps to run the exercise and ass a gitIgnore.
3. For every request please make sure to include **at least** an assertion for the Status Code (Use POJOs to manage response data not just the body). Please make sure you use JavaDoc.
4. Create the following tests using the Bank transactions endpoint:

    - @Test 1 > Verify the Endpoint is empty(If it has any data use the DELETE request to clean and leave empty)
    - @Test 2 > Initialize the POJO with 10 random data (Use the **minimal requirements**). Also make a code verification for avoiding duplicate email accounts. Then perform the POST request.
    - @Test 3 > Make the GET request, asserting that there are not duplicate email accounts.
    - @Test 4 > Add a test to update an existing AccountNumber.

