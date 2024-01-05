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

    public DAO() {
        try {  properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties"));
            this.url = properties.getProperty("db.url");}
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public DAO(DataObject inputObject) {
        this.inputObject = inputObject;
        try {  properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties"));
            this.url = properties.getProperty("db.url");}
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void write(){
        HashMap<String, String> inputObjectDetails = inputObject.getDetails();
        StringJoiner columns = new StringJoiner("','","('","')");
        StringJoiner values = new StringJoiner("','","('","')");
        /*  convert the HashMap to an ordered TreeMap - not functionally necessary for the
         *   constructing the query, but always having the output columns in the same order
         *   makes automated unit testing a lot more pleasant */
        TreeMap<String,String> sortedInputObjectDetails = new TreeMap<>(){{putAll(inputObjectDetails);}};
        for (Map.Entry<String,String> entry : sortedInputObjectDetails.entrySet()) {
            String key = entry.getKey();
            String mappedKey = MapFieldsToColumns.mappingsToDB.get(key);
            String value = entry.getValue();
            if (value != null) {
                columns.add(mappedKey);
                values.add(value);
            }
        }
        try (Connection dbConnection = DriverManager.getConnection(url);
             Statement statement = dbConnection.createStatement())
        {
            statement.executeUpdate("INSERT INTO "+tableName+" "+columns+" VALUES "+values);
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}

