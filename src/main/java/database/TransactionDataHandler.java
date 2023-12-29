package database;

import interfaces.DataHandling;
import interfaces.DataObject;

import java.util.*;

public class TransactionDataHandler extends DataHandler implements DataHandling {

    public TransactionDataHandler(DataObject inputObject) {
        super(inputObject);
    }

    public void writeNewRecord() {
        this.tableName = "transactions";
        super.writeNewRecord();
    }

    public List<DataObject> getRecords(){   // get all transactions for an account number
        this.outputType = "Transaction";
        this.resultList = new ArrayList<>();
        String value = inputObject.getDetails().get("accountNumber");
        String column = "account_number";
        this.readQuery = "SELECT * FROM transactions WHERE " + column + " =  " + value;
        return super.getRecords();
    }



    public void update(){} // not implemented for transactions

    public void delete() {} // not implemented for transactions
}
