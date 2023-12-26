package database;

import account.Account;
import customer.Customer;
import interfaces.DataHandling;
import login.LoginObject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class CustomerDataHandler implements DataHandling {
    Connection dbConnection;
    Customer customer;
    String customerID;
    String accountID;

    public CustomerDataHandler() {
        // not used
    }

    public CustomerDataHandler(Customer customer){
        this.dbConnection = new DatabaseConnection().getDbConnection();
        this.customer = customer;
        this.customerID = customer.getCustomerID();
    }

    public CustomerDataHandler(LoginObject login) {
        this.dbConnection = new DatabaseConnection().getDbConnection();
        this.customerID = login.getCustomerID();
    }

    public CustomerDataHandler(Account account) {
        this.dbConnection = new DatabaseConnection().getDbConnection();
        this.accountID = account.getDetails().get("accountID");
    }

    public void writeNewRecord() {
        HashMap<String,String> customerDetails = customer.getDetails();
        StringJoiner columns = new StringJoiner("','","('","')");
        StringJoiner values = new StringJoiner("','","('","');");
        for (Map.Entry<String,String> entry : customerDetails.entrySet()) {
            String key = entry.getKey();
            String mappedKey = MapFieldsToColumns.mappingsToDB.get(key);
            String value = entry.getValue();
            columns.add(mappedKey);
            values.add(value);
        }

        try (Statement statement = dbConnection.createStatement())
        {
            statement.executeUpdate("INSERT INTO customers " + columns + " VALUES" + values);
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }
    public List getRecords() {
        List<Customer> resultList = new ArrayList<>();
        // check if we've been passed an Account to retrieve the associated customer records and get all linked
        if (accountID != null) {
            try (Statement statement = dbConnection.createStatement()) {
                ResultSet resultSet = statement.executeQuery("SELECT * FROM customers WHERE customer_id IN (SELECT customer_id FROM accounts WHERE account_number="+accountID+"UNION SELECT customer_id FROM signatories WHERE account_number="+accountID+")");
                HashMap<String, String> outputMap = new HashMap<>();
                while (resultSet.next()) {
                    for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                        String key = resultSet.getMetaData().getColumnName(i);
                        String mappedKey = MapFieldsToColumns.mappingsFromDB.get(key);
                        String value = resultSet.getString(i);
                        outputMap.put(mappedKey, value);
                    }
                    Customer x = new Customer(outputMap);
                    resultList.add(x);
                }
            }
            catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        }
        else {

            try (Statement statement = dbConnection.createStatement()) {
                ResultSet resultSet = statement.executeQuery("SELECT * FROM customers WHERE customer_id = " + customerID);
                HashMap<String, String> outputMap = new HashMap<>();
                while (resultSet.next()) {
                    for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                        String key = resultSet.getMetaData().getColumnName(i);
                        String mappedKey = MapFieldsToColumns.mappingsFromDB.get(key);
                        String value = resultSet.getString(i);
                        outputMap.put(mappedKey, value);
                    }
                    Customer x = new Customer(outputMap);
                    resultList.add(x);
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        }
        return resultList;

    }
    public void update(){} // not implemented to start - do we need to allow changing customer details?
    public void delete(){} // not implemented for customer accounts

}
