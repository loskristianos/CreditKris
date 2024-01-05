package dao;

import transaction.Transaction;

public class TransactionDAO extends DAO{

    //fields

    //constructors
    public TransactionDAO(Transaction transaction){
        super(transaction);
    }
    //methods

    @Override
    public void write() {
        this.tableName = "transactions";
        super.write();
    }
}
