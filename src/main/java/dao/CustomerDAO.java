package dao;

import customer.Customer;
import login.LoginObject;

import java.util.HashMap;
import java.util.List;


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
        List<HashMap<String,String>> resultList = super.databaseLookup();
        return new Customer(resultList.getFirst());
    }
}
