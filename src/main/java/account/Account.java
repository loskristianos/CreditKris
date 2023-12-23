package account;


import java.util.HashMap;

abstract class Account {
    private String customerID;
    private String sortCode;
    private String accountNumber;
    private String currentBalance;
    private String overdraftLimit;
    private String signatories;

    public Account() {}

    public Account(String[] accountDetails) {
        setAccountDetails(accountDetails);
    }

    public Account(HashMap<String,String> accountDetails) {
        setDetails(accountDetails);
    }

    public String[] getAccountDetails() {
        return new String[] {customerID, sortCode, accountNumber, currentBalance, overdraftLimit, signatories};
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

    public void setAccountDetails(String[] details) {
        customerID = details[0];
        sortCode = details[1];
        accountNumber = details[2];
        currentBalance = details[3];
        overdraftLimit = details[4];
        signatories = details[5];
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
