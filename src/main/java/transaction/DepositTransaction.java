package transaction;

public class DepositTransaction extends Transaction {

    public DepositTransaction(){
        super();
    }

    public DepositTransaction(String[] transactionDetails) {
        super(transactionDetails);
        setTransactionType("Deposit");
    }

}
