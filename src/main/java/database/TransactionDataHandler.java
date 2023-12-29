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

    public List<DataObject> getRecords(){
        // get all transactions for an account_number, or a single transaction from a pending_authorisation
        this.outputType = "Transaction";
        this.resultList = new ArrayList<>();
        String column = null; String value = null;
        if (inputObject.getClass().getSuperclass().getSimpleName().equals("Account")) {
            value = inputObject.getDetails().get("accountNumber");
            column = "account_number";
        }
        else if (inputObject.getClass().getSuperclass().getSimpleName().equals("Transaction")) {
            value = inputObject.getDetails().get("transactionID");
            column = "transaction_id";
        }
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
