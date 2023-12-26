package database;


import account.*;
import customer.Customer;
import interfaces.*;
import transaction.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public abstract class DataHandler implements DataHandling {
     Connection dbConnection;
     DataObject inputObject;
     String tableName;
     String readQuery;
     String outputType;
     List<DataObject> resultList;

    public DataHandler(DataObject inputObject) {
        this.dbConnection = new DatabaseConnection().getDbConnection();
        this.inputObject = inputObject;
    }


    @Override
    public void writeNewRecord() {
        HashMap<String, String> inputObjectDetails = inputObject.getDetails();
        StringJoiner columns = new StringJoiner("','","('","')");
        StringJoiner values = new StringJoiner("','","('","');");
        for (Map.Entry<String,String> entry : inputObjectDetails.entrySet()) {
            String key = entry.getKey();
            String mappedKey = MapFieldsToColumns.mappingsToDB.get(key);
            String value = entry.getValue();
            columns.add(mappedKey);
            values.add(value);
        }
        try (Statement statement = dbConnection.createStatement())
        {
            statement.executeUpdate("INSERT INTO "+tableName+" "+columns+" VALUES "+values);
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<DataObject> getRecords() {
        try (Statement statement = dbConnection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(readQuery);
            HashMap<String, String> outputMap = new HashMap<>();
            while (resultSet.next()) {
                for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                    String key = resultSet.getMetaData().getColumnName(i);
                    String mappedKey = MapFieldsToColumns.mappingsFromDB.get(key);
                    String value = resultSet.getString(i);
                    outputMap.put(mappedKey, value);
                }
                DataObject x = createReturnedObject(outputMap);
                resultList.add(x);
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
                case "client":
                    yield new ClientAccount(inputMap);
                case "community":
                    yield new CommunityAccount(inputMap);
                case "business":
                    yield new SmallBusinessAccount(inputMap);
            }
            case "Customer": yield new Customer(inputMap);
            case "Transaction": switch (inputMap.get("transactionType")) {
                case "deposit":
                    yield new DepositTransaction(inputMap);
                case "withdrawal":
                    yield new WithdrawalTransaction(inputMap);
                case "transfer":
                    yield new TransferTransaction(inputMap);
            }

            default: yield null;
        };
    }

    public void delete(){}

    }
