/*  Will take an object (any of the data objects) and prepare the appropriate
*   SQL statement String to be passed to the read/write classes. I hope.
*/
package database;

import java.lang.reflect.Method;

public class DataFormatter {

    Object inputObject;
    String tableName;

    public DataFormatter(Object inputObject) {
        this.inputObject = inputObject;
        this.tableName = inputObject.getClass().getPackageName();
    }

    public String extractValuesToString() throws Exception {
        String[] inputArray;
        Method method = null;
        switch (tableName) {
            case "account":
                method = inputObject.getClass().getDeclaredMethod("getAccountDetails");
                break;
            case "transaction":
                method = inputObject.getClass().getDeclaredMethod("getTransactionDetails");
                break;
            case "login":
                method = inputObject.getClass().getDeclaredMethod("getLoginDetails");
                break;
            case "customer":
                method = inputObject.getClass().getDeclaredMethod("getCustomerDetails");
                break;
        }
        inputArray = (String[]) method.invoke(inputObject);
        StringBuilder x = new StringBuilder();
        for (int i = 0; i < inputArray.length; i++) {
            x.append("'");
            x.append(inputArray[i]);
            x.append("'");
            if (i != (inputArray.length - 1)) x.append(",");
        }
        return x.toString();
    }

    public String buildWriteQuery() throws Exception {
        String writeQuery = "insert into " + tableName + " values(" + extractValuesToString() + ")";
        return writeQuery;
    }
}