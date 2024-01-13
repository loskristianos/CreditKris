package transaction;

import account.Account;

import java.util.HashMap;

public class TransferTransaction extends Transaction {

   Account targetAccount;
   String transactionAmount;
   String thisTransactionID;


    // constructor for new transactions from UI account screen
    public TransferTransaction(Account account, Account targetAccount, String transactionAmount){
        super(account,transactionAmount);
        this.account = account;
        this.targetAccount = targetAccount;
        this.transactionAmount = transactionAmount;
        setTargetAccountNumber(targetAccount.getAccountNumber());
        setTransactionAmount(transactionAmount);
        setTransactionType("Transfer");
        }

    // constructor for creating objects from details returned from database lookup
    public TransferTransaction(HashMap<String, String> transactionDetails) {
        super(transactionDetails);
        if(getTransactionType()==null) {
            setTransactionType("Transfer");
        }
    }
    @Override
    public String calculateNewBalance() {
        return null;
    }

    public int writeData(){
        setTransactionID(thisTransactionID);
        thisTransactionID=getTransactionID();
        if (account.getSignatories().equals("0") || getAuthorised()==1) {
            TransferOut transferOut = new TransferOut(account, transactionAmount);
            transferOut.setTransactionID(thisTransactionID);
            transferOut.setTargetAccountNumber(targetAccount.getAccountNumber());
            transferOut.setCustomerID(getCustomerID());
            transferOut.setAuthorised(1);
            transferOut.writeData();
            TransferIn transferIn = new TransferIn(targetAccount, transactionAmount);
            transferIn.setTargetAccountNumber(account.getAccountNumber());
            transferIn.setCustomerID(getCustomerID());
            transferIn.writeData();
            return 0;
        }
        else {
            createPendingTransactions();
            return -2;
        }
    }
}
