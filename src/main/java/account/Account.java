package account;


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

    public String[] getAccountDetails() {
        return new String[] {customerID, sortCode, accountNumber, currentBalance, overdraftLimit, signatories};
    }

    public void setAccountDetails(String[] details) {
        customerID = details[0];
        sortCode = details[1];
        accountNumber = details[2];
        currentBalance = details[3];
        overdraftLimit = details[4];
        signatories = details[5];
    }

    void setOverdraftLimit(String overdraftLimit) {
        this.overdraftLimit = overdraftLimit;
    }

    public void setCurrentBalance(String newBalance) {
        currentBalance = newBalance;
    }
}
