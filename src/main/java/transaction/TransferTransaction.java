package transaction;

import account.Account;

import java.util.HashMap;

public class TransferTransaction extends Transaction {

   Account targetAccount;
   String transactionAmount;

    // constructor for new transactions from UI account screen
    public TransferTransaction(Account account, Account targetAccount, String transactionAmount){
        this.account = account;
        this.targetAccount = targetAccount;
        this.transactionAmount = transactionAmount;
        super.setTargetAccountNumber(targetAccount.getAccountNumber());
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
        if (account.getSignatories().equals("1")) {
            new TransferOut(account, transactionAmount).writeData();
            new TransferIn(targetAccount, transactionAmount).writeData();
            return 0;
        }
        else {
            createPendingTransactions();
            return -2;
        }
    }
}
