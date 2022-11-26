package org.globantBank.pojo;

public class BankTransactionPojo {

    private String name;
    private String lastName;
    private String accountNumber;
    private String amount;
    private String transactionType;
    private String email;
    private Boolean active;
    private String country;
    private String telephone;
    private String id;

    public BankTransactionPojo(String name, String lastName, String accountNumber, String amount, String transactionType,
                               String email, Boolean active, String country, String telephone, String id) {
        this.name = name;
        this.lastName = lastName;
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.transactionType = transactionType;
        this.email = email;
        this.active = active;
        this.country = country;
        this.telephone = telephone;
        this.id = id;
    }

    public BankTransactionPojo(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public String getId() {
        return id;
    }


    @Override
    public String toString() {
        return "" + '\n' +
                "{" + '\n' +
                "\"name\":\"" + name + "\""  + "," + '\n' +
                "\"lastName\":\"" + lastName + "\""  + "," + '\n' +
                "\"accountNumber\":\"" + accountNumber + "\""  + "," + '\n' +
                "\"amount\":\"" + amount + "\""  + "," + '\n' +
                "\"transactionType\":\"" + transactionType + "\""  + "," + '\n' +
                "\"email\":\"" + email + "\""  + "," + '\n' +
                "\"active\":\"" + active + "\""  + "," + '\n' +
                "\"country\":\"" + country + "\""  + "," + '\n' +
                "\"telephone\":\"" + telephone + "\""  + "," + '\n' +
                "\"id\":\"" + id + "\""  + "," + '\n' +
                '}';
    }
}
