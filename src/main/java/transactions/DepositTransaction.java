package transactions;

import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable (tableName = "deposit_transaction")
public class DepositTransaction extends Transaction {

    public DepositTransaction(){
        super();
    }

    public DepositTransaction(String[] transactionDetails) {
        super(transactionDetails);
        setTransactionType("Deposit");
    }

}
