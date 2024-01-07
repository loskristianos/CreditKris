package dao;

import account.Account;
import customer.Customer;
import database.MapFieldsToColumns;
import transaction.PendingTransaction;

import javax.sql.rowset.CachedRowSet;
import java.sql.SQLException;
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
        try (CachedRowSet resultSet = super.databaseLookup()) {
            HashMap<String, String> outputMap = new HashMap<>();
            while (resultSet.next()) {
                for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                    String key = resultSet.getMetaData().getColumnName(i);
                    String mappedKey = MapFieldsToColumns.mappingsFromDB.get(key);
                    String value = resultSet.getString(i);
                    outputMap.put(mappedKey, value);
                }
                PendingTransaction resultTransaction = new PendingTransaction(outputMap);
                pendingTransactionList.add(resultTransaction);
            }
            return pendingTransactionList;
        } catch (SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<PendingTransaction> getAccountPendingTransactions(){
        String accountNumber = account.getAccountNumber();
        List<PendingTransaction> pendingTransactionList = new ArrayList<>();
        sqlStatement = "SELECT * FROM pending_transactions WHERE account_number = '" + accountNumber + "'";
        try (CachedRowSet resultSet = super.databaseLookup()) {
            HashMap<String, String> outputMap = new HashMap<>();
            while (resultSet.next()) {
                for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                    String key = resultSet.getMetaData().getColumnName(i);
                    String mappedKey = MapFieldsToColumns.mappingsFromDB.get(key);
                    String value = resultSet.getString(i);
                    outputMap.put(mappedKey, value);
                }
                PendingTransaction resultTransaction = new PendingTransaction(outputMap);
                pendingTransactionList.add(resultTransaction);
            }
            return pendingTransactionList;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Integer getRemainingTransactions(){
        String transactionID = transaction.getTransactionID();
        sqlStatement = "SELECT + FROM pending_transactions WHERE transaction_id = '" + transactionID + "'";
        try (CachedRowSet resultSet = super.databaseLookup()) {
            return resultSet.size();
        } catch (SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void deletePendingTransaction(){
        String transactionID = transaction.getTransactionID();
        String accountNumber = transaction.getAccountNumber();
        String signatoryID = transaction.getSignatoryID();
        sqlStatement = "DELETE FROM pending_transactions WHERE transaction_id = '" + transactionID + "' AND account_number = '" + accountNumber + "' AND signatory_id = '" + signatoryID + "'";
        super.write();
    }
}
