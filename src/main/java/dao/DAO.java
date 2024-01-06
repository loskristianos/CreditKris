package dao;

import database.MapFieldsToColumns;
import interfaces.DataObject;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public abstract class DAO {
    Properties properties = new Properties();
    String url;
    DataObject inputObject;
    String tableName;
    String sqlStatement;
    public DAO(DataObject inputObject){
        this.inputObject = inputObject;
        try {  properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties"));
            this.url = properties.getProperty("db.url");}
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void write(){
        try (Connection dbConnection = DriverManager.getConnection(url);
             Statement statement = dbConnection.createStatement()) {
             statement.executeUpdate(sqlStatement);
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public DataObject read(){
        return null;
    }

    public void update(){
        try (Connection dbConnection = DriverManager.getConnection(url);
             Statement statement = dbConnection.createStatement()) {
            statement.executeUpdate(sqlStatement);
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public String prepareInputStatement(){
        HashMap<String, String> objectDetails = inputObject.getDetails();
        TreeMap<String,String> sortedDetails = new TreeMap<>(){{putAll(objectDetails);}};
        StringJoiner columns = new StringJoiner("','","('","')");
        StringJoiner values = new StringJoiner("','","('","')");
        for (Map.Entry<String,String> entry : sortedDetails.entrySet()) {
            String key = entry.getKey();
            String mappedKey = MapFieldsToColumns.mappingsToDB.get(key);
            String value = entry.getValue();
            if (value != null) {
                columns.add(mappedKey);
                values.add(value);
            }
        }
        return "INSERT INTO " + tableName + " " + columns + " VALUES " + values;
    }
}
