package account;

import dao.AccountDAO;
import dao.PendingTransactionDAO;
import dao.SignatoryDAO;
import dao.TransactionDAO;
import interfaces.DataObject;
import transaction.PendingTransaction;
import transaction.Transaction;

import java.util.HashMap;
import java.util.List;

public abstract class Account implements DataObject {
    private String customerID;
    private String accountNumber;
    private String accountType;
    private String currentBalance;
    private String overdraftLimit;
    private String signatories;

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
        currentBalance = details.get("currentBalance");
        signatories = details.get("signatories");
    }
    // set individual fields
    void setOverdraftLimit(String overdraftLimit) {
        this.overdraftLimit = overdraftLimit;
    }
    void setAccountType(String accountType) {
        this.accountType = accountType;
    }
    void setSignatories(String signatories){ this.signatories = signatories;}
    public void setCurrentBalance(String newBalance) {
        currentBalance = newBalance;
    }

    // get individual fields
    public String getAccountType(){ return accountType; }
    public String getCurrentBalance() { return this.currentBalance; }
    public String getAccountNumber(){ return accountNumber; }
    public String getOverdraftLimit(){ return overdraftLimit; }
    public String getSignatories() {return signatories;}
    public String getCustomerID() {return customerID;}

    public void writeData(){
        new AccountDAO(this).write();
    }
    public List<Transaction> getTransactions(){
        return new TransactionDAO(this).getTransactions();
    }

    public List<PendingTransaction> getPendingTransactions(){
        return new PendingTransactionDAO(this).getAccountPendingTransactions();
    }

    public List<Signatory> getSignatoryList(){
        return new SignatoryDAO(this).getSignatories();
    }
}
