package database;

import interfaces.DataHandling;
import interfaces.DataObject;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class TransactionDataHandler extends DataHandler implements DataHandling {

    String transactionID;

    public TransactionDataHandler(DataObject inputObject) {
        super(inputObject);
    }

    public void writeNewRecord() {
        this.tableName = "transactions";
        super.writeNewRecord();
    }

    public List<DataObject> getRecords(){
        // get all transactions for an account_number
        this.outputType = "Transaction";
        this.resultList = new ArrayList<>();
        String accountNumber = inputObject.getDetails().get("accountNumber");
        this.readQuery = "SELECT * FROM transactions WHERE account_number = " + accountNumber;
        return super.getRecords();

    }



    public void update(){
        this.transactionID = inputObject.getDetails().get("transactionID");
        try (Statement statement = dbConnection.createStatement())
        {
            statement.executeUpdate("UPDATE transactions SET authorised = 'Y' WHERE transaction_id = " + transactionID);
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void delete() {} // not implemented for transactions
}
