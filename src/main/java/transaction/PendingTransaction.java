package transaction;

import account.Account;
import dao.AccountDAO;
import dao.PendingTransactionDAO;

import java.util.HashMap;

public class PendingTransaction extends Transaction {
    private String transactionID;
    private String customerID;
    private String accountNumber;
    private String transactionAmount;
    private String transactionType;
    private String signatoryID;
    private String targetAccountNumber;

    // constructor for new pending transaction
    public PendingTransaction(Account account, Transaction transaction) {
        // for each signatory a new pending transaction is created (by the class/method calling this constructor)
        transactionAmount = transaction.getTransactionAmount();
        transactionType = transaction.getTransactionType();
        accountNumber = account.getAccountNumber();
        transactionID = transaction.getTransactionID();
        customerID = transaction.getCustomerID();
    }

    //  constructor for PendingTransaction objects created from database returns (existing transactions)
    //  (also used to construct objects from test data in unit tests)
    public PendingTransaction(HashMap<String, String> details) {
        setDetails(details);
    }

    @Override
    public HashMap<String, String> getDetails() {
        return new HashMap<>() {{
            put("transactionID", transactionID);
            put("accountNumber", accountNumber);
            put("transactionAmount", transactionAmount);
            put("transactionType", transactionType);
            put("signatoryID",signatoryID);
            put("targetAccountNumber",targetAccountNumber);
            put("customerID",customerID);
        }};
    }

    @Override
    public void setDetails(HashMap<String, String> details) {
        transactionID = details.get("transactionID");
        accountNumber = details.get("accountNumber");
        transactionAmount = details.get("transactionAmount");
        transactionType = details.get("transactionType");
        signatoryID = details.get("signatoryID");
        targetAccountNumber = details.get("targetAccountNumber");
        customerID = details.get("customerID");
    }

    public String getTransactionID(){
        return transactionID;
    }
    public String getSignatoryID(){
        return signatoryID;
    }
    public String getTargetAccountNumber(){
        return targetAccountNumber;
    }
    public void setTargetAccountNumber(String targetAccount){
        targetAccountNumber = targetAccount;
    }

    public void setSignatoryID(String signatory){
        signatoryID = signatory;
    }
    public void setCustomerID(String customerID){
        this.customerID = customerID;
    }

    public String getCustomerId(){
        return customerID;
    }

    @Override
    public String calculateNewBalance() {
        return null;
    } // not required for pending transactions

    @Override
    public void writeData() {
          new PendingTransactionDAO(this).write();
    }

    public Integer getRemainingTransactions(){
        // check number of pending transactions remaining for this transactionID
        return new PendingTransactionDAO(this).getRemainingTransactions();
    }

    public void completeTransaction(){
        Account account = new AccountDAO(this).getAccountByAccountNumber(accountNumber);
        switch (transactionType) {
            case "Deposit": new DepositTransaction(account, transactionAmount);
            case "Withdrawal": new WithdrawalTransaction(account, transactionAmount);
            case "Transfer": {
                Account targetAccount = new AccountDAO(this).getAccountByAccountNumber(targetAccountNumber);
                new TransferTransaction(account, targetAccount, transactionAmount);
            }
        }
    }

    public void authorise(){
        delete();
        if (getRemainingTransactions()==0) completeTransaction();

    }
    public void delete(){
        new PendingTransactionDAO(this).deletePendingTransaction();
    }
}
