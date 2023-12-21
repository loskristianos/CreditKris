package transactions;

public class TransferTransaction extends Transaction {

    public TransferTransaction(){
        super();
    }

    public TransferTransaction(String[] transactionDetails) {
        super(transactionDetails);
        setTransactionType("Transfer");
    }
}
