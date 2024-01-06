package dao;

import transaction.Transaction;

public class TransactionDAO extends DAO{
    public TransactionDAO(Transaction inputTransaction) {
        super(inputTransaction);
    }

    public void write(){
        this.tableName = "transactions";
        this.sqlStatement = super.prepareInputStatement();
        super.write();
    }
}
