package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private Connection dbConnection = null;
    private final String dbUrl = "jdbc:sqlite:devdb";
    public DatabaseConnection()  {
        try {
            this.dbConnection = DriverManager.getConnection(dbUrl);
        }
        catch (SQLException e) {
           System.out.println(e.getMessage());
        }
    }

    public Connection getDbConnection() {
        return this.dbConnection;
    }

}
