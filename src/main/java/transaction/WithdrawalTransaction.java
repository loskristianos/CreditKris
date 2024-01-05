package transaction;

import account.Account;

import java.math.BigDecimal;
import java.util.HashMap;

public class WithdrawalTransaction extends Transaction {

    public WithdrawalTransaction(){
        super();
        setTransactionType("Withdrawal");
    }

    public WithdrawalTransaction(Account account, String transactionType){
        super(account, transactionType);
        setTransactionType("Withdrawal");
    }

    public WithdrawalTransaction(HashMap<String,String> transactionDetails) {
        super(transactionDetails);
        setTransactionType("Withdrawal");
    }

    public String calculateNewBalance() {
        BigDecimal previousBalance = new BigDecimal(getPreviousBalance());
        BigDecimal transactionAmount = new BigDecimal(getTransactionAmount());
        BigDecimal newBalance = previousBalance.subtract(transactionAmount);
        return newBalance.toString();
    }

}
