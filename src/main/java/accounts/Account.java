package accounts;

import com.j256.ormlite.field.DatabaseField;

abstract class Account {
    @DatabaseField (columnName = "customer_id", foreign = true)
    private String customerID;
    @DatabaseField (columnName = "sort_code")
    private String sortCode;
    @DatabaseField (columnName = "account_number", id = true)
    private String accountNumber;
    @DatabaseField (columnName = "current_balance")
    private String currentBalance;
    @DatabaseField (columnName = "overdraft_limit")
    private String overdraftLimit;
    @DatabaseField (columnName = "signatories")
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
