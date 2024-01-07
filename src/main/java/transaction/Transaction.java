package transaction;

import account.Account;
import dao.AccountDAO;
import dao.TransactionDAO;
import interfaces.DataObject;
import java.util.HashMap;

public abstract class Transaction implements DataObject {
    Account account;
    private String transactionID;
    private String accountNumber;
    private String transactionAmount;
    private String transactionType;
    private String previousBalance;
    private String newBalance;
    private String transactionTime;
    private String additionalInfo;

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
        details.put("additionalInfo",additionalInfo);
        return details;
    }

    public void setDetails(HashMap<String, String> details) {
        transactionID = details.get("transactionID");
        accountNumber = details.get("accountNumber");
        transactionAmount = details.get("transactionAmount");
        previousBalance = details.get("previousBalance");
        newBalance = details.get("newBalance");
        transactionTime = details.get("transactionTime");
        additionalInfo = details.get("additionalInfo");
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }
    public String getTransactionType() {
        return transactionType;
    }
    public String getAdditionalInfo() {
        return additionalInfo;
    }
    public String getAccountNumber() {
        return accountNumber;
    }
    public void setAdditionalInfo(String info) {
        this.additionalInfo = info;
    }
    public String getTransactionID(){ return transactionID;}
    public String getNewBalance(){return newBalance;}
    public String getTransactionTime(){return transactionTime;}
    public void setPreviousBalance(String currentBalance) {
        this.previousBalance = currentBalance;
    }
    public String getPreviousBalance(){ return this.previousBalance;}
    public void setTransactionAmount(String transactionAmount){this.transactionAmount = transactionAmount;}
    public String getTransactionAmount(){return this.transactionAmount;}
    public void setNewBalance(String newBalance) {
        this.newBalance = newBalance;
    }
    public abstract String calculateNewBalance();
    public void writeData(){
        new TransactionDAO(this).write();
        new AccountDAO(account).update();
    }
}
