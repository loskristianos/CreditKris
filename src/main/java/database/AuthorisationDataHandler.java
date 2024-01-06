package database;

import interfaces.DataObject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AuthorisationDataHandler extends DataHandler {
    List<DataObject> inputList;

    public AuthorisationDataHandler(DataObject inputObject){
        super(inputObject);
    }

    public AuthorisationDataHandler(List<DataObject> inputObjects) {
        super();
        this.inputList = inputObjects;
    }

    public void writeNewRecord() {
        this.tableName = "pending_authorisation";
        super.writeNewRecord();
    }

    public void writeAllRecords(){
        this.tableName = "pending_authorisation";
        for (DataObject dataObject : inputList) {
            this.inputObject = dataObject;
            super.writeNewRecord();
        }
    }

    public List<DataObject> getRecords(){
        this.resultList = new ArrayList<>();
        String column = null; String value = null;
        switch (inputObject.getObjectType()) {
            case "Account": column = "account_number"; value = inputObject.getDetails().get("accountNumber");break;
            case "Customer": column = "customer_id"; value = inputObject.getDetails().get("customerID");break;
            case "Transaction": column = "transaction_id"; value = inputObject.getDetails().get("transactionID");break;
        }
        this.readQuery = "SELECT * FROM pending_authorisation WHERE "+column+" = "+value;
        this.outputType = "PendingAuthorisation";
        return super.getRecords();
    }

     public void delete() {
        HashMap<String,String> rowDetails = inputObject.getDetails();
        String accountNumber = rowDetails.get("accountNumber");
        String customerID = rowDetails.get("customerID");
        String transactionID = rowDetails.get("transactionID");
        try (Connection dbConnection = DriverManager.getConnection(url);
             Statement statement = dbConnection.createStatement())
        {
            statement.executeUpdate("DELETE FROM pending_authorisation WHERE transaction_id = "+transactionID+" AND account_number = "+accountNumber+" AND customer_id = "+customerID );
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
