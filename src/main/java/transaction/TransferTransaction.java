package transaction;

import java.util.HashMap;

public class TransferTransaction extends Transaction {

    public TransferTransaction(){
        super();
    }

    public TransferTransaction(HashMap<String, String> transactionDetails) {
        super(transactionDetails);
        setTransactionType("Transfer");
    }

    @Override
    public String calculateNewBalance() {
        return null;
    }

}
