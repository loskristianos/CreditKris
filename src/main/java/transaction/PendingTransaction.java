package transaction;

import account.Account;
import dao.PendingTransactionDAO;

import java.util.HashMap;

public class PendingTransaction extends Transaction {
    private String transactionID;
    private String accountNumber;
    private String transactionAmount;
    private String transactionType;
    private String signatoryID;

    public PendingTransaction() {
    }

    // constructor for new pending transaction
    public PendingTransaction(Account account, Transaction transaction) {
        // for each signatory a new pending transaction is created (by the class/method calling this constructor)
        transactionAmount = transaction.getTransactionAmount();
        transactionType = transaction.getTransactionType();
        accountNumber = account.getAccountNumber();
        transactionID = transaction.getTransactionID();
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
        }};
    }

    @Override
    public void setDetails(HashMap<String, String> details) {
        transactionID = details.get("transactionID");
        accountNumber = details.get("accountNumber");
        transactionAmount = details.get("transactionAmount");
        transactionType = details.get("transactionType");
        signatoryID = details.get("signatoryID");
    }

    public String getTransactionID(){
        return transactionID;
    }
    public String getSignatoryID(){
        return signatoryID;
    }

    public void setTransactionID(String transactionID){
        this.transactionID = transactionID;
    }
    public void setSignatoryID(String signatory){
        signatoryID = signatory;
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

    public void delete(){
        new PendingTransactionDAO(this).deletePendingTransaction();
    }
}
