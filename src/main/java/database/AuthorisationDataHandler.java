package database;

import interfaces.DataObject;

import java.util.ArrayList;
import java.util.List;

public class AuthorisationDataHandler extends DataHandler {
    // needs to write records to table (transaction object input, look up customers from account_number)

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

    }


}
