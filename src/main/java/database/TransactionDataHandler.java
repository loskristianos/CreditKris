package database;

import interfaces.DataHandling;
import interfaces.DataObject;
import transaction.*;

import java.sql.ResultSet;
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

    public List getRecords(){
        // get all transactions for an account_number
        List<Transaction> resultList = new ArrayList<>();
        String accountNumber = inputObject.getDetails().get("accountNumber");
        this.readQuery = "SELECT * FROM transactions WHERE account_number = " + accountNumber;
        try (Statement statement = dbConnection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(readQuery);
            HashMap<String, String> outputMap = new HashMap<>();
            while (resultSet.next()) {
                for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                    String key = resultSet.getMetaData().getColumnName(i);
                    String mappedKey = MapFieldsToColumns.mappingsFromDB.get(key);
                    String value = resultSet.getString(i);
                    outputMap.put(mappedKey, value);
                }
                Transaction x = null;
                switch (outputMap.get("transactionType")) {
                    case "deposit": x = new DepositTransaction(outputMap);
                    break;
                    case "withdrawal": x = new WithdrawalTransaction(outputMap);
                    break;
                    case "transfer": x = new TransferTransaction(outputMap);
                    break;
                }
                resultList.add(x);
            }}
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return resultList;
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
