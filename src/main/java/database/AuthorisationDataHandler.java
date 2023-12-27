package database;

import interfaces.DataObject;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AuthorisationDataHandler extends DataHandler {
        // needs to return all records for a transactionID so that main transaction table can be updated once they're done.
    ArrayList<DataObject> inputList;

    public AuthorisationDataHandler(DataObject inputObject){
        super(inputObject);
    }

    public AuthorisationDataHandler(ArrayList<DataObject> inputObjects) {
        super();
        this.inputList = inputObjects;
    }

    public void writeNewRecord(){   // writes one record, calling method will have to loop the call however many times for number of customers
        this.tableName = "pending_authorisation";
        super.writeNewRecord();
    }

    public void writeAllRecords(){
        for (DataObject o : inputList) {
            new AuthorisationDataHandler(o).writeNewRecord();
        }
    }

    public List<DataObject> getRecords(){
        /*  Needs two queries depending on object type passed in:
            - Return all records for a given transaction; and
            - Return all records for a given customer
            - (and possibly also return all records for a given account)
         */
        this.resultList = new ArrayList<>();
        String column = null; String value = null;
        switch (inputObject.getClass().getPackageName()) {
            case "account": column = "account_number"; value = inputObject.getDetails().get("accountNumber");break;
            case "customer": column = "customer_id"; value = inputObject.getDetails().get("customerID");break;
            case "transaction": column = "transaction_id"; value = inputObject.getDetails().get("transactionID");break;
        }
        this.readQuery = "SELECT * FROM pending_authorisation WHERE "+column+" = "+value;
        this.outputType = "PendingAuthorisation";
        return super.getRecords();
    }

    public void update(){} // not required (unless we move delete functionality here and get rid of that method, since this is the only implementation)

    public void delete() {
        HashMap<String,String> rowDetails = inputObject.getDetails();
        String accountNumber = rowDetails.get("accountNumber");
        String customerID = rowDetails.get("customerID");
        String transactionID = rowDetails.get("transactionID");
        try (Statement statement = dbConnection.createStatement())
        {
            statement.executeUpdate("DELETE FROM pending_authorisation WHERE transaction_id = "+transactionID+" AND account_number = "+accountNumber+" AND customer_id = "+customerID );
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
