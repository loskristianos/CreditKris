package transactions;

import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable (tableName = "withdrawal_transaction")
public class WithdrawalTransaction extends Transaction {

    public WithdrawalTransaction(){
        super();
    }

    public WithdrawalTransaction(String[] transactionDetails) {
        super(transactionDetails);
        setTransactionType("Withdrawal");
    }
}
