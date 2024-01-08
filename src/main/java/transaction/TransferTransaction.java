package transaction;

import account.Account;

import java.math.BigDecimal;
import java.util.HashMap;

public class TransferTransaction extends Transaction {

   Account targetAccount;
   String transactionAmount;

    // constructor for new transactions from account screen
    public TransferTransaction(Account account, Account targetAccount, String transactionAmount){
        this.account = account;
        this.targetAccount = targetAccount;
        this.transactionAmount = transactionAmount;
        setTransactionType("Transfer");
        }

    /*  constructor for creating objects from details returned from database queries
    *   (might not need this depending on the DAO re-writes
    */

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

    public void writeData(){
        if (account.getSignatories().equals("1")) {
            new TransferOut(account, transactionAmount).writeData();
            new TransferIn(targetAccount, transactionAmount).writeData();
        }
        else {
            createPendingTransactions();
        }
    }
}
