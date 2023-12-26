package database;

import account.Account;
import customer.Customer;
import interfaces.DataHandling;
import interfaces.DataObject;
import login.LoginObject;
import transaction.Transaction;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public abstract class DataHandler implements DataHandling {
     Connection dbConnection;
     DataObject inputObject;
     String tableName;

    public DataHandler(DataObject inputObject) {
        this.dbConnection = new DatabaseConnection().getDbConnection();
        this.inputObject = inputObject;
    }


    @Override
    public void writeNewRecord() {
        HashMap<String, String> inputObjectDetails = inputObject.getDetails();
        StringJoiner columns = new StringJoiner("','","('","')");
        StringJoiner values = new StringJoiner("','","('","');");
        for (Map.Entry<String,String> entry : inputObjectDetails.entrySet()) {
            String key = entry.getKey();
            String mappedKey = MapFieldsToColumns.mappingsToDB.get(key);
            String value = entry.getValue();
            columns.add(mappedKey);
            values.add(value);
        }
        try (Statement statement = dbConnection.createStatement())
        {
            statement.executeUpdate("INSERT INTO "+tableName+" "+columns+" VALUES "+values);
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void delete(){}
}
