package dao;

import account.Account;
import customer.Customer;
import transaction.PendingTransaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PendingTransactionDAO extends DAO{

    PendingTransaction transaction;
    Customer customer;
    Account account;

    public PendingTransactionDAO(PendingTransaction transaction) {
        super(transaction);
        this.transaction = transaction;
    }

    public PendingTransactionDAO(Customer customer){
        super(customer);
        this.customer = customer;
    }

    public PendingTransactionDAO(Account account) {
        super(account);
        this.account = account;
    }

    @Override
    public void write() {
        tableName = "pending_transactions";
        super.write();
    }

    public List<PendingTransaction> getCustomerPendingTransactions(){
        String signatoryID = customer.getCustomerID();
        List<PendingTransaction> pendingTransactionList = new ArrayList<>();
        sqlStatement = "SELECT * FROM pending_transactions WHERE signatory_id = '" + signatoryID + "'";
        List<HashMap<String,String>> resultList = super.databaseLookup();
        for (HashMap<String,String> map : resultList) {
            PendingTransaction resultTransaction = new PendingTransaction(map);
            pendingTransactionList.add(resultTransaction);
        }
        return pendingTransactionList;
    }

    public List<PendingTransaction> getAccountPendingTransactions(){
        String accountNumber = account.getAccountNumber();
        List<PendingTransaction> pendingTransactionList = new ArrayList<>();
        sqlStatement = "SELECT * FROM pending_transactions WHERE account_number = '" + accountNumber + "'";
        List<HashMap<String,String>> resultList = super.databaseLookup();
        for (HashMap<String,String> map : resultList) {
            PendingTransaction resultTransaction = new PendingTransaction(map);
            pendingTransactionList.add(resultTransaction);
        }
        return pendingTransactionList;
    }

    public Integer getRemainingTransactions(){
        String transactionID = transaction.getTransactionID();
        sqlStatement = "SELECT + FROM pending_transactions WHERE transaction_id = '" + transactionID + "'";
        List<HashMap<String,String>> resultList = super.databaseLookup();
        return resultList.size();
    }

    public void deletePendingTransaction(){
        String transactionID = transaction.getTransactionID();
        String accountNumber = transaction.getAccountNumber();
        String signatoryID = transaction.getSignatoryID();
        sqlStatement = "DELETE FROM pending_transactions WHERE transaction_id = '" + transactionID + "' AND account_number = '" + accountNumber + "' AND signatory_id = '" + signatoryID + "'";
        super.write();
    }
}
