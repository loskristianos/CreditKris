package database;

import account.Account;
import account.ClientAccount;
import account.CommunityAccount;
import account.SmallBusinessAccount;
import customer.Customer;
import interfaces.DataHandling;
import transaction.Transaction;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class AccountDataHandler implements DataHandling {

    Connection dbConnection;
    Account inputAccount;
    Customer customer;
    Transaction transaction;

    public AccountDataHandler() {} // no-args not needed

    public AccountDataHandler(Account inputAccount) {
        this.dbConnection = new DatabaseConnection().getDbConnection();
        this.inputAccount = inputAccount;
    }

    public AccountDataHandler(Customer customer) {
        this.dbConnection = new DatabaseConnection().getDbConnection();
        this.customer = customer;
    }

    public AccountDataHandler(Transaction transaction) {
        this.dbConnection = new DatabaseConnection().getDbConnection();
        this.transaction = transaction;
    }

    public void writeNewRecord() {
        HashMap<String, String> accountDetails = inputAccount.getDetails();
        StringJoiner columns = new StringJoiner("','","('","')");
        StringJoiner values = new StringJoiner("','","('","');");
        for (Map.Entry<String,String> entry : accountDetails.entrySet()) {
            String key = entry.getKey();
            String mappedKey = MapFieldsToColumns.mappingsToDB.get(key);
            String value = entry.getValue();
            columns.add(mappedKey);
            values.add(value);
        }

        try (Statement statement = dbConnection.createStatement())
        {
            statement.executeUpdate("INSERT INTO accounts " + columns + " VALUES" + values);
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Account> getRecords() {
        String customerID = customer.getCustomerID();
        List<Account> resultList = new ArrayList<>();
        try (Statement statement = dbConnection.createStatement())
        {
           ResultSet resultSet = statement.executeQuery("SELECT * FROM accounts WHERE customer_id = '" + customerID + "'");
           HashMap<String,String> outputMap = new HashMap<>();
           while (resultSet.next()) {
               for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                   String key = resultSet.getMetaData().getColumnName(i);
                   String mappedKey = MapFieldsToColumns.mappingsFromDB.get(key);
                   String value = resultSet.getString(i);
                   outputMap.put(mappedKey, value);
               }
               Account x = null;
               switch (outputMap.get("accountType")){
                   case "client": x = new ClientAccount(outputMap);
                   break;
                   case "community": x = new CommunityAccount(outputMap);
                   break;
                   case "business": x = new SmallBusinessAccount(outputMap);
                   break;
               }
               resultList.add(x);
           }
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return resultList;
    }


    public void update() {
        HashMap<String,String> transactionDetails = transaction.getDetails();
        String accountNumber = transactionDetails.get("accountNumber");
        String newBalance = transactionDetails.get("newBalance");

        try (Statement statement = dbConnection.createStatement())
        {
            statement.executeUpdate("UPDATE accounts SET current_balance = " + newBalance + " WHERE account_number = " + accountNumber);
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public void delete() {} // no implementation for Account data
}
