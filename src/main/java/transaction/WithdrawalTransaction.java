package transaction;

import java.util.HashMap;

public class WithdrawalTransaction extends Transaction {

    public WithdrawalTransaction(){
        super();
    }

    public WithdrawalTransaction(HashMap<String,String> transactionDetails) {
        super(transactionDetails);
        setTransactionType("Withdrawal");
    }

    public String calculateNewBalance() {
        return null;
    }

}
