package newobjects;

import java.math.BigDecimal;

public class Transaction {
    public enum Type {DEPOSIT, WITHDRAWAL, TRANSFER_IN, TRANSFER_OUT}
    public enum Status {COMPLETE,FAILED_BALANCE,AUTHORISED, REQUIRES_AUTHORISATION, NOT_SET}
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

    private Transaction (Type type){
        setTransactionStatus(Status.NOT_SET);
        setTransactionType(type);
    }

    public static Transaction createDepositTransaction(BigDecimal transactionAmount){
        Transaction depositTransaction = new Transaction (Type.DEPOSIT);
        depositTransaction.setTransactionAmount(transactionAmount);
        return depositTransaction;
    }

    public static Transaction createWithdrawalTransaction(BigDecimal transactionAmount){
        Transaction withdrawalTransaction = new Transaction(Type.WITHDRAWAL);
        withdrawalTransaction.setTransactionAmount(transactionAmount);
        return withdrawalTransaction;
    }

    public static Transaction createTransferInTransaction(BigDecimal transactionAmount){
        Transaction transferIn = new Transaction(Type.TRANSFER_IN);
        transferIn.setTransactionAmount(transactionAmount);
        return transferIn;
    }

    public static Transaction createTransferOutTransaction(BigDecimal transactionAmount){
        Transaction transferOut = new Transaction(Type.TRANSFER_OUT);
        transferOut.setTransactionAmount(transactionAmount);
        return transferOut;
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
