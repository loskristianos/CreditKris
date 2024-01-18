/*
    new implementation of Account objects - instead of having a load of subclasses with only minor
    differences (type, overdraftLimit) just have the one and set them on creation.
 */
package newobjects;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import customer.Customer;

import java.math.BigDecimal;

@DatabaseTable(tableName = "accounts")
public class Account {
    public enum Type {CLIENT, BUSINESS, COMMUNITY}
    private Customer customer;
    private Transaction transaction;
    @DatabaseField(id = true)
    private Integer accountNumber;
    @DatabaseField(dataType = DataType.ENUM_NAME)
    private Type accountType;
    @DatabaseField(dataType = DataType.BIG_DECIMAL)
    private BigDecimal currentBalance;
    @DatabaseField(dataType = DataType.BIG_DECIMAL)
    private BigDecimal overdraftLimit;
    @DatabaseField
    private Integer signatories;

    private Account(Type type){
        setAccountType(type);
        setOverdraftLimit(type);
    }

    // static factory methods for different account types
    public static Account createClientAccount(){
        return new Account(Type.CLIENT);
    }

    public static Account createBusinessAccount(){
        return new Account(Type.BUSINESS);
    }

    public static Account createCommunityAccount(){
        return new Account(Type.COMMUNITY);
    }

    // set fields
    public void setCurrentBalance(BigDecimal currentBalance){
        this.currentBalance = currentBalance;
    }
    private void setOverdraftLimit(Type type){
        switch (type){
            case CLIENT -> overdraftLimit = new BigDecimal("1500.00");
            case BUSINESS -> overdraftLimit = new BigDecimal("1000.00");
            case COMMUNITY -> overdraftLimit = new BigDecimal("2500.00");
        }
    }
    public void setSignatories(Integer signatories){
        this.signatories = signatories;
    }
    private void setAccountType(Type accountType){
        this.accountType = accountType;
    }
    public void setAccountNumber(Integer accountNumber) {
        this.accountNumber = accountNumber;
    }
    public void setTransaction(Transaction transaction){
        this.transaction = transaction;
    }

    // get fields
    public Integer getAccountNumber(){
        return accountNumber;
    }
    public Type getAccountType(){
        return accountType;
    }
    public BigDecimal getCurrentBalance() {
        if(currentBalance == null) return new BigDecimal("0.00");
        else return currentBalance;
    }
    public BigDecimal getOverdraftLimit() {
        return overdraftLimit;
    }
    public BigDecimal getAvailableBalance(){
        return getCurrentBalance().add(overdraftLimit);
    }
    public Integer getSignatories() {
        return signatories;
    }

    // Transaction methods
    public Transaction validateTransaction(Transaction transaction){
       if (signatoryCheck(transaction)==-1) return transaction;
       else if (balanceCheck(transaction)==-1) return transaction;
       else {
           applyTransaction(transaction);
           return transaction;
       }
    }

    private void applyTransaction(Transaction transaction){
        switch(transaction.getTransactionType()){
            case DEPOSIT, TRANSFER_IN -> paymentIn(transaction);
            case WITHDRAWAL, TRANSFER_OUT -> paymentOut(transaction);
        }
    }
    private void paymentIn(Transaction transaction){
        BigDecimal transactionAmount = transaction.getTransactionAmount();
        transaction.setPreviousBalance(getCurrentBalance());
        setCurrentBalance(getCurrentBalance().add(transactionAmount));
        transaction.setNewBalance(getCurrentBalance());
        transaction.setTransactionStatus(Transaction.Status.COMPLETE);
    }
    private void paymentOut(Transaction transaction){
        BigDecimal transactionAmount = transaction.getTransactionAmount();
        transaction.setPreviousBalance(getCurrentBalance());
        setCurrentBalance(getCurrentBalance().subtract(transactionAmount));
        transaction.setNewBalance(getCurrentBalance());
        transaction.setTransactionStatus(Transaction.Status.COMPLETE);
    }
    private int balanceCheck(Transaction transaction) {
        BigDecimal transactionAmount = transaction.getTransactionAmount();
        switch (transaction.getTransactionType()) {
            case WITHDRAWAL, TRANSFER_OUT -> {
                if (transactionAmount.compareTo(getAvailableBalance()) > 0) {
                    transaction.setTransactionStatus(Transaction.Status.FAILED_BALANCE);
                    return -1;
                } else return 0;
            }
            case DEPOSIT, TRANSFER_IN -> {return 0;}
            default -> {return 0;}
        }
    }
    private int signatoryCheck(Transaction transaction) {
        Transaction.Status status = transaction.getTransactionStatus();
        if (getSignatories()==1 || status.equals(Transaction.Status.AUTHORISED)) return 0;
        else {
            transaction.setTransactionStatus(Transaction.Status.REQUIRES_AUTHORISATION);
            return -1;
        }
    }
}
