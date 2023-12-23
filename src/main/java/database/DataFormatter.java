
package database;

import interfaces.Banking;


public class DataFormatter {

    Object inputObject;
    String tableName;

    public DataFormatter(Object inputObject) {
        this.inputObject = inputObject;
        this.tableName = inputObject.getClass().getPackageName();
    }

    public DataFormatter(Banking object){
        object.getDetails();
    }

/*    public String extractValuesToString() throws Exception {
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
*/
    public String testMappings(String searchField) {
        String dbField = MapFieldsToColumns.mappings.get(searchField);
        return dbField;
    }

}