# Final Exercise API Automation

1. Create an account in Mockapi.io and set up an endpoint for **Bank Transactions** (All information should be embedded in the same API, i.e., only 1 endpoint required).
2. Structure the project making the requests reusable, avoid to repeat code or the endpoints, use TestNG and create a **Readme.md** with the specifications and steps to run the exercise and ass a gitIgnore.
3. For every request please make sure to include **at least** an assertion for the Status Code (Use POJOs to manage response data not just the body). Please make sure you use JavaDoc.
4. Create the following tests using the Bank transactions endpoint:

   * @Test 1 > Verify the Endpoint is empty(If it has any data use the DELETE request to clean and leave empty).
      - Gets the information registered in the API executing a GET request.
      - Transforms the JsonString into a List of JavaObjects type BankTransactionPojo.
      - Validates if there is any transaction in the list.
      - Wipes the existing transactions deleting one by one from API through DELETE method.
      - Retrieves the information from API and stores it again.
      - Validates if there is any transaction registered.
      - Transaction number should be 0.
      
   * @Test 2 > Initialize the POJO with 10 random data (Use the **minimal requirements**). Also make a code verification for avoiding duplicate email accounts. Then perform the POST request.
      - Initializes the data in the API with 10 random transactions.
      - Validates the number of transaction created by retrieving the information from API and storing it again.
      - Creates a Transaction instance with a test email. Validates if the email exists in the local list. If not, performs a POST with the new transaction.
      - Validates if the Status code is 200. If so, the POST was successful.
      - It tries to send the transaction with the same email. The POST cannot be sent.
     
   * @Test 3 > Make the GET request, asserting that there are not duplicate email accounts.
        - Gets the data from API and stores it locally.
        - Streams the emails in the list validating there is no duplicated email accounts.
        - Compares the number of unique emails with the number of transactions stored.
     
   * @Test 4 > Add a test to update an existing AccountNumber.
        - Sends a PUT request to transaction with ID 1 tu update account with number 12345678.
        - Validates the StatusCode to be 200.
     

