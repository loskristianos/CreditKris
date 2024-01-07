package dao;

import database.MapFieldsToColumns;
import interfaces.DataObject;

import java.io.IOException;
import java.sql.*;
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

    List<HashMap<String,String>> databaseLookup(){
        try (Connection dbConnection = DriverManager.getConnection(url);
             Statement statement = dbConnection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(sqlStatement);
            List<HashMap<String,String>> resultList = new ArrayList<>();
            HashMap<String, String> outputMap = new HashMap<>();
            while (resultSet.next()) {
                for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                    String key = resultSet.getMetaData().getColumnName(i);
                    String mappedKey = MapFieldsToColumns.mappingsFromDB.get(key);
                    String value = resultSet.getString(i);
                    outputMap.put(mappedKey, value);
                }
                resultList.add(outputMap);
            } return resultList;
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
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

    public String prepareInsertStatement(){
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
