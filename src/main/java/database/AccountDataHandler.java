package database;

import account.*;
import interfaces.DataHandling;
import interfaces.DataObject;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class AccountDataHandler extends DataHandler implements DataHandling {

    public AccountDataHandler(DataObject inputObject) {
        super(inputObject);
    }

    public void writeNewRecord() {
        this.tableName = "accounts";
        super.writeNewRecord();
    }

    public List<Account> getRecords() {
        String customerID = inputObject.getDetails().get("customerID");
        this.readQuery = "SELECT * FROM accounts WHERE account_number IN (SELECT account_number FROM accounts WHERE customer_id = " + customerID + " UNION SELECT account_number FROM signatories WHERE customer_id = " +customerID+")";
        List<Account> resultList = new ArrayList<>();
        try (Statement statement = dbConnection.createStatement())
        {
           ResultSet resultSet = statement.executeQuery(readQuery);
           HashMap<String,String> outputMap = new HashMap<>();
           while (resultSet.next()) {
               for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                   String key = resultSet.getMetaData().getColumnName(i);
                   String mappedKey = MapFieldsToColumns.mappingsFromDB.get(key);
                   String value = resultSet.getString(i);
                   outputMap.put(mappedKey, value);
               }
               Account x = null;
               switch (outputMap.get("accountType")){
                   case "client": x = new ClientAccount(outputMap);
                   break;
                   case "community": x = new CommunityAccount(outputMap);
                   break;
                   case "business": x = new SmallBusinessAccount(outputMap);
                   break;
               }
               resultList.add(x);
           }
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return resultList;
    }

    public void update() {
        HashMap<String,String> transactionDetails = inputObject.getDetails();
        String accountNumber = transactionDetails.get("accountNumber");
        String newBalance = transactionDetails.get("newBalance");

        try (Statement statement = dbConnection.createStatement())
        {
            statement.executeUpdate("UPDATE accounts SET current_balance = " + newBalance + " WHERE account_number = " + accountNumber);
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
