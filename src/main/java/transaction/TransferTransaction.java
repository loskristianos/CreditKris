package transaction;

import account.Account;

import java.math.BigDecimal;
import java.util.HashMap;

public class TransferTransaction extends Transaction {

    // constructor for new transactions from account screen
    public TransferTransaction(Account account, Account targetAccount, String transactionAmount){
        new TransferOut(account, transactionAmount);
        new TransferIn(targetAccount,transactionAmount);
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
}
