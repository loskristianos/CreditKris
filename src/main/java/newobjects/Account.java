/*
    new implementation of Account objects - instead of having a load of subclasses with only minor
    differences (type, overdraftLimit) just have the one and set them on creation.
 */
package newobjects;

import customer.Customer;

import java.math.BigDecimal;

public class Account {
    public enum Type {CLIENT, BUSINESS, COMMUNITY}
    private Customer customer;
    private Transaction transaction;
    private String accountNumber;
    private Type accountType;
    private BigDecimal currentBalance;
    private BigDecimal overdraftLimit;
    private Integer signatories;

    public Account(Type type){
        setAccountType(type);
        setOverdraftLimit(type);
    }

    public Account(Transaction transaction){
        this.transaction = transaction;
    }

    public Account(Customer customer){
        this.customer = customer;
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
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
    public void setTransaction(Transaction transaction){
        this.transaction = transaction;
    }

    // get fields
    public String getAccountNumber(){
        return accountNumber;
    }
    public Type getAccountType(){
        return accountType;
    }
    public BigDecimal getCurrentBalance() {
        return currentBalance;
    }
    public BigDecimal getOverdraftLimit() {
        return overdraftLimit;
    }
    public BigDecimal getAvailableBalance(){
        return currentBalance.add(overdraftLimit);
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
        transaction.setPreviousBalance(currentBalance);
        setCurrentBalance(currentBalance.add(transactionAmount));
        transaction.setNewBalance(currentBalance);
        transaction.setTransactionStatus(Transaction.Status.COMPLETE);
    }
    private void paymentOut(Transaction transaction){
        BigDecimal transactionAmount = transaction.getTransactionAmount();
        transaction.setPreviousBalance(currentBalance);
        setCurrentBalance(currentBalance.subtract(transactionAmount));
        transaction.setNewBalance(currentBalance);
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
        if (getSignatories()==0 || status.equals(Transaction.Status.AUTHORISED)) return 0;
        else {
            transaction.setTransactionStatus(Transaction.Status.REQUIRES_AUTHORISATION);
            return -1;
        }
    }
}
