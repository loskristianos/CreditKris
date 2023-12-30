//package database;
//
//import java.io.IOException;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import java.util.Properties;
//
//public class DatabaseConnection {
//
//    Properties properties = new Properties();
//
//
//    private Connection dbConnection;
//
//    public DatabaseConnection() throws SQLException, IOException {
//        properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties"));
//        String url = properties.getProperty("db.url");
//        this.dbConnection = DriverManager.getConnection(url);
//    }
//
//    public Connection getDbConnection() {
//        return this.dbConnection;
//    }
//}
