/*  Take a String of values and write to the appropriate table on
*   the database. The various Object types will be processed/prepared elsewhere
*   so that they can all be written by this class. Or that's the plan anyway...
*/
package database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseWrite {
    Connection dbConnection;
    String queryToWrite;

    public DatabaseWrite(String queryToWrite) {
        this.dbConnection = new DatabaseConnection().getDbConnection();
        this.queryToWrite = queryToWrite;
    }

    public void writeData() {
        try (Statement statement = dbConnection.createStatement())
        {
            statement.executeUpdate(queryToWrite);
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
