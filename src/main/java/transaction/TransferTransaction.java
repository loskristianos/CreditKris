package transaction;

import account.Account;

import java.math.BigDecimal;
import java.util.HashMap;

public class TransferTransaction extends Transaction {

    public TransferTransaction(){
        super();
        setTransactionType("Transfer");
    }

    public TransferTransaction(Account account, String transactionAmount){
        super(account, transactionAmount);
        setTransactionType("Transfer");
    }

    public TransferTransaction(HashMap<String, String> transactionDetails) {
        super(transactionDetails);
        if(getTransactionType()==null) {
            setTransactionType("Transfer");
        }
    }

    @Override
    public String calculateNewBalance() {
        BigDecimal previousBalance = new BigDecimal(getPreviousBalance());
        BigDecimal transactionAmount = new BigDecimal(getTransactionAmount());
        return switch (getTransactionType()) {
            case "TransferIn": yield previousBalance.add(transactionAmount).toString();
            case "TransferOut": yield previousBalance.subtract(transactionAmount).toString();
            default: yield null;
        };

    }
}
