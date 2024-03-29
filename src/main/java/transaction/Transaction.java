package transaction;

import account.Account;
import account.Signatory;
import customer.Customer;
import dao.*;
import interfaces.DataObject;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

public abstract class Transaction implements DataObject {
    Account account;
    private String transactionID;
    private String accountNumber;
    private String customerID;          // customerID of the user requesting the transaction (only really applicable to accounts with more than one signatory)
    private String transactionAmount;
    private String transactionType;
    private String previousBalance;
    private String newBalance;
    private String transactionTime;     // generated by database when row is inserted
    private String targetAccountNumber; // only used for TransferTransaction
    private int authorised; // only used while completing pending transactions, not written to db or used outside the package

    public Transaction(){}

    public Transaction(Account account, String transactionAmount) {
        this.account = account;
        this.accountNumber = account.getAccountNumber();
        this.previousBalance = account.getCurrentBalance();
        this.transactionAmount = transactionAmount;
        if (calculateNewBalance() != null) {this.newBalance = calculateNewBalance();
        this.account.setCurrentBalance(newBalance);}
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
        details.put("customerID",customerID);
        details.put("targetAccountNumber",targetAccountNumber);
        return details;
    }

    public void setDetails(HashMap<String, String> details) {
        transactionID = details.get("transactionID");
        accountNumber = details.get("accountNumber");
        transactionAmount = details.get("transactionAmount");
        previousBalance = details.get("previousBalance");
        newBalance = details.get("newBalance");
        transactionTime = details.get("transactionTime");
        customerID = details.get("customerID");
        targetAccountNumber = details.get("targetAccountNumber");
        transactionType = details.get("transactionType");
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
    public void setTransactionID(String transactionID){
        if (transactionID == null) this.transactionID = accountNumber + System.currentTimeMillis();
        else this.transactionID = transactionID;
    }
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
    public void setCustomerID(String customerID){
        this.customerID = customerID;
    }
    public String getCustomerID(){
        return customerID;
    }
    public void setNewBalance(String newBalance) {
        this.newBalance = newBalance;
    }
    void setAuthorised(int x){
        authorised = x;
    }
    int getAuthorised(){
        return authorised;
    }
    public String getTargetAccountNumber(){
        return targetAccountNumber;
    }
    public abstract String calculateNewBalance();
    public int writeData(){
        if (overdraftCheck()<0) return -3;
        setTransactionID(transactionID);
        if (account.getSignatories().equals("0") || getAuthorised()==1 ||transactionType.equals("Transfer In") || transactionType.equals("Deposit")) {
            new TransactionDAO(this).write();
            new AccountDAO(account).update();
            return 0;
        } else {
            createPendingTransactions();
            return -2;
        }
    }

    public Transaction getThisTransaction(){
        return new TransactionDAO(this).getThisTransaction();
    }

    public Customer getInitiatingCustomer(){
       return new CustomerDAO(this).getRecord();
    }

    public void createPendingTransactions(){
        List<Signatory> signatoryList = account.getSignatoryList();
        String customerName = getInitiatingCustomer().getFullName();
        for (Signatory signatory : signatoryList){
            if (!customerID.equals(signatory.getCustomerID())) {    // prevents creation of pending transaction for customer initiating this transaction
                PendingTransaction pendingTransaction = new PendingTransaction(account, this);
                pendingTransaction.setSignatoryID(signatory.getCustomerID());
                pendingTransaction.setCustomerName(customerName);
                if (targetAccountNumber != null) {pendingTransaction.setTargetAccountNumber(targetAccountNumber);}
                pendingTransaction.writeData();
            }
        }
    }

    int overdraftCheck(){
        BigDecimal overdraft = new BigDecimal(account.getOverdraftLimit()).negate();
        BigDecimal newBalance = new BigDecimal(getNewBalance());
        if (newBalance.compareTo(overdraft) < 0 ) return -3;
        else return 0;
    }
}
