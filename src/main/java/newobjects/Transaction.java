package newobjects;

import java.math.BigDecimal;

public class Transaction {
    public enum Type {DEPOSIT, WITHDRAWAL, TRANSFER_IN, TRANSFER_OUT}
    public enum Status {COMPLETE,FAILED_BALANCE,AUTHORISED, REQUIRES_AUTHORISATION}
    private String transactionID;
    private String accountNumber;
    private String customerID;          // customerID of the user requesting the transaction (only really applicable to accounts with more than one signatory)
    private BigDecimal transactionAmount;
    private Type transactionType;
    private BigDecimal previousBalance;
    private BigDecimal newBalance;
    private String transactionTime;
    private Status transactionStatus;
    private String transferAccountNumber; // only used for TransferTransaction

    public Transaction(){
        // set TransactionType here
    }

    public Transaction(Account account){
        account.setTransaction(this); // create new transaction with account as param, then pass the transaction into the account to use the methods in Account to set balances, validate, etc.
    }

    // set fields
    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    public void setTransactionAmount(BigDecimal transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public void setPreviousBalance(BigDecimal previousBalance) {
        this.previousBalance = previousBalance;
    }

    public void setNewBalance(BigDecimal newBalance) {
        this.newBalance = newBalance;
    }

    public void setTransferAccountNumber(String transferAccountNumber) {
        this.transferAccountNumber = transferAccountNumber;
    }

    private void setTransactionType(Type transactionType) {
        this.transactionType = transactionType;
    }

    public void setTransactionTime(String transactionTime) {
        this.transactionTime = transactionTime;
    }

    public void setTransactionStatus(Status transactionStatus) {
        this.transactionStatus = transactionStatus;
    }
    // get fields

    public String getTransactionID() {
        return transactionID;
    }

    public Type getTransactionType() {
        return transactionType;
    }

    public BigDecimal getTransactionAmount() {
        return transactionAmount;
    }

    public BigDecimal getNewBalance() {
        return newBalance;
    }

    public BigDecimal getPreviousBalance() {
        return previousBalance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getCustomerID() {
        return customerID;
    }

    public String getTransactionTime() {
        return transactionTime;
    }

    public String getTransferAccountNumber() {
        return transferAccountNumber;
    }

    public Status getTransactionStatus(){
        return transactionStatus;
    }
}
