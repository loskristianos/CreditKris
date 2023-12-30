package database;


import interfaces.DataHandling;
import interfaces.DataObject;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class AccountDataHandler extends DataHandler implements DataHandling {

    public AccountDataHandler(DataObject inputObject) {
        super(inputObject);
    }

    public void writeNewRecord() {
        this.tableName = "accounts";
        super.writeNewRecord();
    }

    public List<DataObject> getRecords() {
        // get all accounts for a customer
        if (inputObject.getClass().getSimpleName().equals("Customer")) {
            String customerID = inputObject.getDetails().get("customerID");
            this.readQuery = "SELECT * FROM accounts WHERE account_number IN (SELECT account_number FROM accounts WHERE customer_id = " + customerID + " UNION SELECT account_number FROM signatories WHERE customer_id = " + customerID + ")";
        }
        // get an account from a transaction
        else if (inputObject.getClass().getSuperclass().getSimpleName().equals("Transaction")) {
            String accountNumber = inputObject.getDetails().get("accountNumber");
            this.readQuery = "SELECT * FROM accounts WHERE account_number = " + accountNumber;
        }
        // get a newly created account (to get the account number)
        else if (inputObject.getClass().getSuperclass().getSimpleName().equals("Account") && inputObject.getDetails().get("AccountNumber") == null) {
            String customerID = inputObject.getDetails().get("customerID");
            String accountType = inputObject.getDetails().get("accountType");
            this.readQuery = "SELECT * FROM accounts WHERE customer_id = " + customerID + " AND account_type = '" + accountType+"'";
        }
        this.resultList = new ArrayList<>();
        this.outputType = "Account";
        return super.getRecords();
    }

    public void update() {
        // used only to update balance as a result of a transaction
        HashMap<String,String> transactionDetails = inputObject.getDetails();
        String accountNumber = transactionDetails.get("accountNumber");
        String newBalance = transactionDetails.get("newBalance");

        try (Statement statement = dbConnection.createStatement())
        {
            statement.executeUpdate("UPDATE accounts SET current_balance = '" + newBalance + "' WHERE account_number = '" + accountNumber + "'");
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
