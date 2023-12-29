package transaction;

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
        return null;
    }

}
