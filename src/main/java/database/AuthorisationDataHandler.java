package database;

import interfaces.DataObject;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AuthorisationDataHandler extends DataHandler {
    // needs to delete records (with customer_id and account_number as input somehow)

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

    public List getRecords(){
        return null;
    }

    public void update(){}

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
