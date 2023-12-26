package account;

import interfaces.DataObject;

import java.util.HashMap;

public abstract class Account implements DataObject {
    private String customerID;
    private String accountNumber;
    private String accountType;
    private String currentBalance;
    private String overdraftLimit;
    private String signatories;

    public Account() {}

    public Account(HashMap<String,String> accountDetails) {
        setDetails(accountDetails);
    }

    public HashMap<String, String> getDetails() {
        HashMap<String, String> details = new HashMap<>();
        details.put("customerID",customerID);
        details.put("accountNumber", accountNumber);
        details.put("accountType", accountType);
        details.put("currentBalance", currentBalance);
        details.put("overdraftLimit", overdraftLimit);
        details.put("signatories", signatories);
        return details;
    }



    public void setDetails(HashMap<String, String> details) {
        customerID = details.get("customerID");
        accountNumber = details.get("accountNumber");
        accountType = details.get("accountType");
        currentBalance = details.get("currentBalance");
        overdraftLimit = details.get("overdraftLimit");
        signatories = details.get("signatories");
    }

    void setOverdraftLimit(String overdraftLimit) {
        this.overdraftLimit = overdraftLimit;
    }
    void setAccountType(String accountType) {
        this.accountType = accountType;
    }
    public void setCurrentBalance(String newBalance) {
        currentBalance = newBalance;
    }
}
