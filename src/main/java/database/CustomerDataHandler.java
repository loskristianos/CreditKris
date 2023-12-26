package database;

import customer.Customer;
import interfaces.DataHandling;
import interfaces.DataObject;
import login.LoginObject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class CustomerDataHandler extends DataHandler implements DataHandling {


    public CustomerDataHandler(DataObject inputObject){
        super(inputObject);
    }


    public CustomerDataHandler(LoginObject login) {
        super(login);
    }


    public void writeNewRecord() {
        this.tableName = "customers";
        super.writeNewRecord();
    }

    public List getRecords() {
        List<Customer> resultList = new ArrayList<>();
        // check if we've been passed an Account to retrieve the associated customer records and get all linked
        if (inputObject.getDetails().get("accountID") != null) {
            String accountID = inputObject.getDetails().get("accountID");
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
            String customerID = inputObject.getDetails().get("customerID");
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

}
