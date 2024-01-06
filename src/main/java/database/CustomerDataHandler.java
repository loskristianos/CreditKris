package database;

import interfaces.*;

import login.LoginObject;

import java.util.*;

public class CustomerDataHandler extends DataHandler {

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

    public List<DataObject> getRecords() {
        this.resultList = new ArrayList<>();
        // check if we've been passed an Account to retrieve the linked customer records
        if (inputObject.getObjectType().equals("Account")) {
            String accountNumber = inputObject.getDetails().get("accountNumber");
            this.readQuery = "SELECT * FROM customers WHERE customer_id IN (SELECT customer_id FROM accounts WHERE account_number = " + accountNumber + " UNION SELECT customer_id FROM signatories WHERE account_number = " + accountNumber + ")";
        }
        else {
            String customerID = inputObject.getDetails().get("customerID");
            this.readQuery = "SELECT * FROM customers WHERE customer_id = " + customerID;
        }
        this.outputType = "Customer";
        return super.getRecords();
    }
}
