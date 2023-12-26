package database;

import account.Account;
import interfaces.DataHandling;
import transaction.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class TransactionDataHandler implements DataHandling {

    Connection dbConnection;
    Transaction transaction;
    Account account;
    String transactionID;

    public TransactionDataHandler(){}
    public TransactionDataHandler(Transaction transaction) {
        this.dbConnection = new DatabaseConnection().getDbConnection();
        this.transaction = transaction;
    }

    public TransactionDataHandler(Account account) {
        this.dbConnection = new DatabaseConnection().getDbConnection();
        this.account = account;
    }

    public TransactionDataHandler(String transactionID){
        this.dbConnection = new DatabaseConnection().getDbConnection();
        this.transactionID = transactionID;
    }

    public void writeNewRecord(){
        HashMap<String,String> transactionDetails = transaction.getDetails();
        StringJoiner columns = new StringJoiner("','","('","')");
        StringJoiner values = new StringJoiner("','","('","');");
        for (Map.Entry<String,String> entry : transactionDetails.entrySet()) {
            String key = entry.getKey();
            String mappedKey = MapFieldsToColumns.mappingsToDB.get(key);
            String value = entry.getValue();
            columns.add(mappedKey);
            values.add(value);
        }

        try (Statement statement = dbConnection.createStatement())
        {
            statement.executeUpdate("INSERT INTO transactions " + columns + " VALUES" + values);
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public List getRecords(){
        // get all transactions for an account_number
        List<Transaction> resultList = new ArrayList<>();
        String accountNumber = account.getDetails().get("accountNumber");
        try (Statement statement = dbConnection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM transactions WHERE account_number = " + accountNumber);
            HashMap<String, String> outputMap = new HashMap<>();
            while (resultSet.next()) {
                for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                    String key = resultSet.getMetaData().getColumnName(i);
                    String mappedKey = MapFieldsToColumns.mappingsFromDB.get(key);
                    String value = resultSet.getString(i);
                    outputMap.put(mappedKey, value);
                }
                Transaction x = null;
                switch (outputMap.get("transactionType")) {
                    case "deposit": x = new DepositTransaction(outputMap);
                    break;
                    case "withdrawal": x = new WithdrawalTransaction(outputMap);
                    break;
                    case "transfer": x = new TransferTransaction(outputMap);
                    break;
                }
                resultList.add(x);
            }}
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return resultList;
    }

    public void update(){
        try (Statement statement = dbConnection.createStatement())
        {
            statement.executeUpdate("UPDATE transactions SET authorised = 'Y' WHERE transaction_id = " + transactionID);
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void delete() {} // not implemented for transactions
}
