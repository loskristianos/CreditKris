package database;

import account.*;
import customer.Customer;
import interfaces.*;
import login.LoginObject;
import transaction.*;

import java.io.IOException;
import java.sql.*;
import java.util.*;

public abstract class DataHandler implements DataHandling {
    Properties properties = new Properties();
    DataObject inputObject;
    String tableName;
    String readQuery;
    String outputType;
    String url;
    List<DataObject> resultList;

     public DataHandler()  {
       try {  properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties"));
         this.url = properties.getProperty("db.url");}
       catch (IOException e) {
           System.out.println(e.getMessage());
       }
     }

    public DataHandler(DataObject inputObject) {
        this.inputObject = inputObject;
        try {  properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties"));
            this.url = properties.getProperty("db.url");}
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void writeNewRecord() {
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

    @Override
    public List<DataObject> getRecords() {
        try (Connection dbConnection = DriverManager.getConnection(url);
            Statement statement = dbConnection.createStatement())
        {
            ResultSet resultSet = statement.executeQuery(readQuery);
            HashMap<String, String> outputMap = new HashMap<>();
            while (resultSet.next()) {
                for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                    String key = resultSet.getMetaData().getColumnName(i);
                    String mappedKey = MapFieldsToColumns.mappingsFromDB.get(key);
                    String value = resultSet.getString(i);
                    outputMap.put(mappedKey, value);
                }
                DataObject returnedObject = createReturnedObject(outputMap);
                resultList.add(returnedObject);
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return resultList;
    }

    public DataObject createReturnedObject(HashMap<String, String> inputMap) {
        return switch (outputType) {
            case "Account": switch (inputMap.get("accountType")) {
                case "Client":
                    yield new ClientAccount(inputMap);
                case "Community":
                    yield new CommunityAccount(inputMap);
                case "Business":
                    yield new SmallBusinessAccount(inputMap);
            }
            case "Customer": yield new Customer(inputMap);
            case "PendingAuthorisation": yield new PendingAuthorisation(inputMap);
            case "Signatory": yield new Signatory(inputMap);
            case "Login": yield new LoginObject(inputMap);
            case "Transaction": switch (inputMap.get("transactionType")) {
                case "Deposit":
                    yield new DepositTransaction(inputMap);
                case "Withdrawal":
                    yield new WithdrawalTransaction(inputMap);
                case "TransferIn":
                    yield new TransferTransaction(inputMap);
                case "TransferOut":
                    yield new TransferTransaction(inputMap);
            }
            default: yield null;
        };
    }

    public void update(){}
    public void delete(){}
    }
