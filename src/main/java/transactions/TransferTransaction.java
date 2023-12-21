package transactions;

import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable (tableName = "transfer_transaction")
public class TransferTransaction extends Transaction {

    public TransferTransaction(){
        super();
    }

    public TransferTransaction(String[] transactionDetails) {
        super(transactionDetails);
        setTransactionType("Transfer");
    }
}
