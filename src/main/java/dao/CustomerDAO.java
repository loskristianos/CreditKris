package dao;

import customer.Customer;
import database.MapFieldsToColumns;
import login.LoginObject;

import javax.sql.rowset.CachedRowSet;
import java.sql.SQLException;
import java.util.HashMap;


public class CustomerDAO extends DAO{
    LoginObject login;

    public CustomerDAO(Customer customer) {
        super(customer);
    }

    public CustomerDAO(LoginObject login) {
        super(login);
        this.login = login;
    }

    public void write(){
        this.tableName = "customers";
        super.prepareInsertStatement();
        super.write();
    }

    public Customer getRecord(){
        String customerID = login.getCustomerID();
        sqlStatement = "SELECT * FROM customers WHERE customer_id = " + customerID;
        try (CachedRowSet resultSet = super.databaseLookup()) {
            HashMap<String, String> outputMap = new HashMap<>();
            for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                String key = resultSet.getMetaData().getColumnName(i);
                String mappedKey = MapFieldsToColumns.mappingsFromDB.get(key);
                String value = resultSet.getString(i);
                outputMap.put(mappedKey, value);
            }
            return new Customer(outputMap);
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
