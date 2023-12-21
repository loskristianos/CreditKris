/*  Will take an object (any of the data objects) and prepare the appropriate
*   SQL statement String to be passed to the read/write classes.
*/
package database;

public class DataFormatter {
    String[] inputObject;

    public DataFormatter(String[] inputObject) {
        this.inputObject = inputObject;
    }

    /*  takes the array of strings from the object (getAccountDetails, getCustomerDetails, etc.)
        and convert them into a string of comma separated values to slot into the SQL query
     */
    public String extractValuesToString(){
        StringBuilder x = new StringBuilder();
        for (int i = 0; i < inputObject.length; i++) {
            x.append("'");
            x.append(inputObject[i]);
            x.append("'");
            if (i != (inputObject.length-1)) x.append(",");
        }
        return x.toString();
    }
}
