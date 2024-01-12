package dao;

import customer.Customer;
import login.LoginObject;
import transaction.Transaction;

import java.util.HashMap;
import java.util.List;


public class CustomerDAO extends DAO{

    String customerID;

    public CustomerDAO(Customer customer) {
        super(customer);
    }

    public CustomerDAO(Transaction transaction){
        super(transaction);
        this.customerID = transaction.getCustomerID();
    }

    public CustomerDAO(LoginObject login) {
        super(login);
        this.customerID = login.getCustomerID();
    }

    public void write(){
        this.tableName = "customers";
        sqlStatement = super.prepareInsertStatement();
        super.write();
    }

    public Customer getRecord(){
        sqlStatement = "SELECT * FROM customers WHERE customer_id = " + customerID;
        List<HashMap<String,String>> resultList = super.databaseLookup();
        return new Customer(resultList.getFirst());
    }
}
