package transaction;

import account.Account;

import java.util.HashMap;

public class PendingAuthorisation extends Transaction {
    private String transactionID;
    private String accountNumber;
    private String customerID;
    private String transactionAmount;
    private String transactionType;

    public PendingAuthorisation(){}

    public PendingAuthorisation(Account account, Transaction transaction){
        this.transactionAmount = transaction.getTransactionAmount();
        this.transactionType = transaction.getTransactionType();
        this.customerID = account.getCustomerID();
        this.accountNumber = account.getAccountNumber();
    }

    public PendingAuthorisation(HashMap<String,String> details){
        setDetails(details);
    }

    @Override
    public HashMap<String, String> getDetails() {
        return new HashMap<>() {{
            put("transactionID",transactionID);
            put("accountNumber",accountNumber);
            put("customerID",customerID);
            put("transactionAmount", transactionAmount);
            put("transactionType", transactionType);
        }};
    }

    @Override
    public void setDetails(HashMap<String, String> details) {
        transactionID = details.get("transactionID");
        accountNumber = details.get("accountNumber");
        customerID = details.get("customerID");
        transactionAmount= details.get("transactionAmount");
        transactionType = details.get("transactionType");
    }

    @Override
    public String calculateNewBalance() { return null;} // not required for pending transactions

    @Override
    public void writeData() {
    /*
        New PendingDAO(this).write()
        or
        New TransactionDAO(this).writePending()

        also need a function for when this is the last pending transaction
    */
    }
}
