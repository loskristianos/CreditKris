package transaction;

import account.Account;
import account.Signatory;
import dao.AccountDAO;
import dao.TransactionDAO;
import interfaces.DataObject;
import java.util.HashMap;
import java.util.List;

public abstract class Transaction implements DataObject {
    Account account;
    private String targetAccountNumber;
    private String transactionID;
    private String accountNumber;
    private String transactionAmount;
    private String transactionType;
    private String previousBalance;
    private String newBalance;
    private String transactionTime;

    public Transaction(){}

    public Transaction(Account account, String transactionAmount) {
        this.account = account;
        this.accountNumber = account.getAccountNumber();
        this.previousBalance = account.getCurrentBalance();
        this.transactionAmount = transactionAmount;
        this.newBalance = calculateNewBalance();
        this.account.setCurrentBalance(newBalance);
    }

    public Transaction(HashMap<String, String> transactionDetails) {
        setDetails(transactionDetails);
    }

    public HashMap<String, String> getDetails() {
        HashMap<String, String> details = new HashMap<>();
        details.put("transactionID",transactionID);
        details.put("accountNumber",accountNumber);
        details.put("transactionAmount", transactionAmount);
        details.put("transactionType",transactionType);
        details.put("previousBalance",previousBalance);
        details.put("newBalance",newBalance);
        details.put("transactionTime",transactionTime);
        return details;
    }

    public void setDetails(HashMap<String, String> details) {
        transactionID = details.get("transactionID");
        accountNumber = details.get("accountNumber");
        transactionAmount = details.get("transactionAmount");
        previousBalance = details.get("previousBalance");
        newBalance = details.get("newBalance");
        transactionTime = details.get("transactionTime");
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }
    public String getTransactionType() {
        return transactionType;
    }
    public String getAccountNumber() {
        return accountNumber;
    }
    public String getTransactionID(){ return transactionID;}
    public String getNewBalance(){return newBalance;}
    public String getTransactionTime(){return transactionTime;}
    public void setPreviousBalance(String currentBalance) {
        this.previousBalance = currentBalance;
    }
    public String getPreviousBalance(){ return this.previousBalance;}
    public void setTargetAccountNumber(String targetAccountNumber){
        this.targetAccountNumber = targetAccountNumber;
    }
    public void setTransactionAmount(String transactionAmount){this.transactionAmount = transactionAmount;}
    public String getTransactionAmount(){return this.transactionAmount;}
    public void setNewBalance(String newBalance) {
        this.newBalance = newBalance;
    }
    public abstract String calculateNewBalance();
    public void writeData(){
        if (account.getSignatories().equals("1")) {
            new TransactionDAO(this).write();
            new AccountDAO(account).update();
        } else {
            createPendingTransactions();
        }

    }

    public void createPendingTransactions(){
        List<Signatory> signatoryList = account.getSignatoryList();
        for (Signatory signatory : signatoryList){
            /*  Note: this creates a pending transaction for the person creating the account as well.
            *   Transactions needs a re-written constructor to take the customer creating the
            *   transaction as a parameter and a field to store their ID so that we can show who initiated
            *   the pending transaction (and the completed transaction in the accounts view) */
            PendingTransaction pendingTransaction = new PendingTransaction(account,this);
            pendingTransaction.setSignatoryID(signatory.getCustomerID());
            if (targetAccountNumber != null) pendingTransaction.setTargetAccountNumber(targetAccountNumber);
            pendingTransaction.writeData();
        }

    }
}
