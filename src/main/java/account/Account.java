package account;

import interfaces.Banking;

import java.util.HashMap;

abstract class Account implements Banking {
    private String customerID;
    private String sortCode;
    private String accountNumber;
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
        details.put("sortCode",sortCode);
        details.put("accountNumber", accountNumber);
        details.put("currentBalance", currentBalance);
        details.put("overdraftLimit", overdraftLimit);
        details.put("signatories", signatories);
        return details;
    }



    public void setDetails(HashMap<String, String> details) {
        customerID = details.get("customerID");
        sortCode = details.get("sortCode");
        accountNumber = details.get("accountNumber");
        currentBalance = details.get("currentBalance");
        overdraftLimit = details.get("overdraftLimit");
        signatories = details.get("signatories");
    }

    void setOverdraftLimit(String overdraftLimit) {
        this.overdraftLimit = overdraftLimit;
    }

    public void setCurrentBalance(String newBalance) {
        currentBalance = newBalance;
    }
}
