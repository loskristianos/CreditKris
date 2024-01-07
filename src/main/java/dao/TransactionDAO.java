package dao;

import account.Account;
import database.MapFieldsToColumns;
import transaction.*;

import javax.sql.rowset.CachedRowSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TransactionDAO extends DAO{
    Account account;
    public TransactionDAO(Transaction inputTransaction) {
        super(inputTransaction);
    }

    public TransactionDAO(Account account) {
        super(account);
        this.account = account;
    }

    public void write(){
        tableName = "transactions";
        sqlStatement = super.prepareInsertStatement();
        super.write();
    }

    public List<Transaction> getTransactions() {
        List<Transaction> transactionList = new ArrayList<>();
        String accountNumber = account.getAccountNumber();
        sqlStatement = "SELECT * FROM transactions WHERE account_number =  " + accountNumber;
        try (CachedRowSet resultSet = super.databaseLookup()) {
            HashMap<String, String> outputMap = new HashMap<>();
            while (resultSet.next()) {
                for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                    String key = resultSet.getMetaData().getColumnName(i);
                    String mappedKey = MapFieldsToColumns.mappingsFromDB.get(key);
                    String value = resultSet.getString(i);
                    outputMap.put(mappedKey, value);
                }
                Transaction resultTransaction = switch (outputMap.get("transactionType")) {
                    case "Deposit":
                        yield new DepositTransaction(outputMap);
                    case "Withdrawal":
                        yield new WithdrawalTransaction(outputMap);
                    case "Transfer In", "Transfer Out":
                        yield new TransferTransaction(outputMap);
                    default:
                        yield null;
                };
                transactionList.add(resultTransaction);
            }
            return transactionList;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
