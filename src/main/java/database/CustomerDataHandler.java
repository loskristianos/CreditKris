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

    public List<Customer> getRecords() {
        List<Customer> resultList = new ArrayList<>();
        // check if we've been passed an Account to retrieve the linked customer records
        if(inputObject.getClass().getSuperclass().getName().equals("Account")) {
            String accountNumber = inputObject.getDetails().get("accountNumber");
            this.readQuery = "SELECT * FROM customers WHERE customer_id IN (SELECT customer_id FROM accounts WHERE account_number="+accountNumber+"UNION SELECT customer_id FROM signatories WHERE account_number="+accountNumber+")";
        }
        else {
            String customerID = inputObject.getDetails().get("customerID");
            this.readQuery = "SELECT * FROM customers WHERE customer_id = " + customerID;
        }
        try (Statement statement = dbConnection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(readQuery);
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
        return resultList;
    }

    public void update(){} // not implemented to start - do we need to allow changing customer details?

}
