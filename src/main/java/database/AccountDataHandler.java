package database;

import account.Account;
import interfaces.DataHandling;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class AccountDataHandler implements DataHandling {

    Connection dbConnection;
    Account inputAccount;
    String tableName = "accounts";

    public AccountDataHandler() {}

    public AccountDataHandler(Account inputAccount) {
        this.dbConnection = new DatabaseConnection().getDbConnection();
        this.inputAccount = inputAccount;
    }

    public void writeNewRecord() {
        HashMap<String, String> accountDetails = inputAccount.getDetails();
        StringJoiner columns = new StringJoiner("','","('","')");
        StringJoiner values = new StringJoiner("','","('","');");
        for (Map.Entry<String,String> entry : accountDetails.entrySet()) {
            String key = entry.getKey();
            String mappedKey = MapFieldsToColumns.mappings.get(key);
            String value = entry.getValue();
            columns.add(mappedKey);
            values.add(value);
        }

        try (Statement statement = dbConnection.createStatement())
        {
            statement.executeUpdate("INSERT INTO " + tableName + " " + columns + " VALUES" + values);
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public void read() {}
    public void update() {}
    public void delete() {}
}
