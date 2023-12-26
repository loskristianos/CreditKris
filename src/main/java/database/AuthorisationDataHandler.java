package database;

import interfaces.DataObject;

import java.util.List;

public class AuthorisationDataHandler extends DataHandler {
    // needs to write records to table (transaction object input, look up customers from account_number)

    // needs to delete records (with customer_id and account_number as input somehow)

    // needs to return all records for a transactionID so that main transaction table can be updated once they're done.
    public AuthorisationDataHandler(DataObject inputObject){
        super(inputObject);
    }

    public void writeNewRecord(){   // writes one record, calling method will have to loop the call however many times for number of customers
        // "INSERT INTO pending_authorisation (transaction_id, account_number, customer_id) VALUES (transactionID, accountNumber, customerID);
        this.tableName = "pending_authorisation";
        super.writeNewRecord();
    }

    public List getRecords(){
        return null;
    }

    public void update(){}

    public void delete() {

    }


}
