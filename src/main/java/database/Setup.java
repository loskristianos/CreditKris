/*  A couple of static methods to check that the database file exists and contains the correct tables
    for the application to function. Called first when the program is run: does nothing if everything exists,
    creates the database and tables if they're not already there. Doesn't check beyond that (i.e. if the database
    exists with the correct tables but changes have been made to columns/data types/constraints/whatever that would
    break functionality that won't be picked up (yet). This is mainly intended for first-run database-creation.
 */

package database;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

public class Setup {

    private static String url;

    public static void runChecks(){
        Properties properties = new Properties();
        try {  properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties"));
            url = properties.getProperty("db.url");
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
        switch (databaseCheck()){
            case 0: return;
            case -1: setupDatabase();break;
            case -2:
                System.out.println("SQL Error"); break;   // temp, will log Exception when logging is complete
        }
    }

    private static int databaseCheck(){
        try (Connection dbConnection = DriverManager.getConnection(url)) {
            DatabaseMetaData dbMetaData = dbConnection.getMetaData();
            ResultSet results = dbMetaData.getTables(null, null, null, new String[] {"TABLE"});
            ArrayList<String> requiredTables = new ArrayList<>() {{add("login");add("customers");add("accounts");add("transactions");add("signatories");add("pending_transactions");}};
            ArrayList<String> existingTables = new ArrayList<>();
            while (results.next()){
                String tableName = results.getString("TABLE_NAME");
                existingTables.add(tableName);
            }
            Collections.sort(existingTables); Collections.sort(requiredTables);
            if (existingTables.equals(requiredTables)) {
                return 0;
            }
            else {
                return -1;
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
            return -2;
        }
    }

    private static void setupDatabase(){
        try (Connection dbConnection = DriverManager.getConnection(url);
             Statement statement = dbConnection.createStatement()) {
            List<String> sqlCommands = Files.readAllLines(Paths.get("sql_table_setup.txt"));
            for(String command : sqlCommands){
                statement.executeUpdate(command);
            }
        }
        catch (SQLException | IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
