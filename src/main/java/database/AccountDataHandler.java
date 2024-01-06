package database;


import interfaces.DataObject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class AccountDataHandler extends DataHandler {

    public AccountDataHandler(DataObject inputObject) {
        super(inputObject);
    }

    public void writeNewRecord() {
        this.tableName = "accounts";
        super.writeNewRecord();
    }

    public List<DataObject> getRecords() {
        HashMap<String,String> inputDetails = inputObject.getDetails();
        if (inputObject.getObjectType().equals("Customer")) {
            String customerID = inputDetails.get("customerID");
            this.readQuery = "SELECT * FROM accounts WHERE account_number IN (SELECT account_number FROM accounts WHERE customer_id = " + customerID + " UNION SELECT account_number FROM signatories WHERE customer_id = " + customerID + ")";
        }
        // get an account from a transaction
        else if (inputObject.getObjectType().equals("Transaction")) {
            String accountNumber = inputDetails.get("accountNumber");
            this.readQuery = "SELECT * FROM accounts WHERE account_number = " + accountNumber;
        }
        // get a newly created account (to get the account number)
        else if (inputObject.getObjectType().equals("Account") && inputDetails.get("AccountNumber") == null) {
            String customerID = inputDetails.get("customerID");
            String accountType = inputDetails.get("accountType");
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

            try (Connection dbConnection = DriverManager.getConnection(url);
                 Statement statement = dbConnection.createStatement())
        {
            statement.executeUpdate("UPDATE accounts SET current_balance = '" + newBalance + "' WHERE account_number = '" + accountNumber + "'");
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
