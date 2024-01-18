package newobjects;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.math.BigDecimal;

@DatabaseTable(tableName = "transactions")
public class Transaction {
    public enum Type {DEPOSIT, WITHDRAWAL, TRANSFER_IN, TRANSFER_OUT}
    public enum Status {COMPLETE,FAILED_BALANCE,AUTHORISED, REQUIRES_AUTHORISATION, NOT_SET}
    @DatabaseField(canBeNull = false)
    private String transactionID;
    @DatabaseField
    private Integer accountNumber;
    @DatabaseField
    private Integer customerID;          // customerID of the user requesting the transaction (only really applicable to accounts with more than one signatory)
    @DatabaseField(dataType = DataType.BIG_DECIMAL)
    private BigDecimal transactionAmount;
    @DatabaseField(dataType = DataType.ENUM_NAME)
    private Type transactionType;
    @DatabaseField(dataType = DataType.BIG_DECIMAL)
    private BigDecimal previousBalance;
    @DatabaseField(dataType = DataType.BIG_DECIMAL)
    private BigDecimal newBalance;
    @DatabaseField
    private String transactionTime;
    @DatabaseField(dataType = DataType.ENUM_NAME)
    private Status transactionStatus;
    @DatabaseField
    private String transferAccountNumber; // only used for TransferTransaction

    public Transaction(){
        setTransactionStatus(Status.NOT_SET);
        // set TransactionType here
    }
    public Transaction (Type type){
        setTransactionStatus(Status.NOT_SET);
        setTransactionType(type);
    }

    public Transaction(Account account){
        setTransactionStatus(Status.NOT_SET);
        account.setTransaction(this); // create new transaction with account as param, then pass the transaction into the account to use the methods in Account to set balances, validate, etc.
    }

    // set fields
    public void setCustomerID(Integer customerID) {
        this.customerID = customerID;
    }

    public void setAccountNumber(Integer accountNumber) {
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

    public Integer getAccountNumber() {
        return accountNumber;
    }

    public Integer getCustomerID() {
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
