package database;

import interfaces.DataHandling;
import interfaces.DataObject;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class TransactionDataHandler extends DataHandler implements DataHandling {

 //   String transactionID;

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



    public void update(){
        // maybe not used at all
//        this.transactionID = inputObject.getDetails().get("transactionID");
//        try (Statement statement = dbConnection.createStatement())
//        {
//            statement.executeUpdate("UPDATE transactions SET authorised = 'Y' WHERE transaction_id = " + transactionID);
//        }
//        catch (SQLException e){
//            System.out.println(e.getMessage());
//        }
    }

    public void delete() {} // not implemented for transactions
}
