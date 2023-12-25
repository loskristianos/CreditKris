package database;

import customer.Customer;
import interfaces.DataHandling;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class CustomerDataHandler implements DataHandling {
    Connection dbConnection;
    Customer customer;

    public CustomerDataHandler() {
        // not used
    }

    public CustomerDataHandler(Customer customer){
        this.dbConnection = new DatabaseConnection().getDbConnection();
        this.customer = customer;
    }

    public void writeNewRecord() {
        // INSERT INTO customer
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
    public List getRecords(){
        // return customer by customer ID
        // SELECT * FROM customers WHERE customer_id = <id>

        // return customers by account ID - need a join query
    return null;
    }
    public void update(){} // not implemented to start - do we need to allow changing customer details?
    public void delete(){} // not implemented for customer accounts

}
