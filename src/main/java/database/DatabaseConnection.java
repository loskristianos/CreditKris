package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
   private Connection dbConnection;
    private final String dbUrl = "jdbc:sqlite:devdb";
    public DatabaseConnection() throws SQLException {
            this.dbConnection = DriverManager.getConnection(dbUrl);
    }

    public Connection getDbConnection() {
        return this.dbConnection;
    }
}
