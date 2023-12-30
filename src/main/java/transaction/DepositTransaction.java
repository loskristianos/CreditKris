package transaction;

import java.math.BigDecimal;
import java.util.HashMap;

public class DepositTransaction extends Transaction {

    public DepositTransaction(){
        super();
    }

    public DepositTransaction(HashMap<String, String> transactionDetails) {
        super(transactionDetails);
        setTransactionType("Deposit");
    }

    @Override
    public String calculateNewBalance() {
        // calculate new balance of account after this transaction
        BigDecimal previousBalance = new BigDecimal(getPreviousBalance());
        BigDecimal transactionAmount = new BigDecimal(getTransactionAmount());
        BigDecimal newBalance = previousBalance.add(transactionAmount);
        return newBalance.toString();
    }

}
