/*  replaces DataFormatter (or at least the query building bits of it).
*   Might replace the string arrays in all the getDetails methods with hash maps
*   then I can probably get rid of that horrible extractValues mess as well.
*/
package database;

public class StatementBuilder {

    String finishedQuery;
    String tableName;
    String searchField;
    String searchValue;

    public StatementBuilder() {
    }

    public String writeNewRecord(String values, String table) {
        tableName = MapFieldsToColumns.mappings.get(table);
        finishedQuery = "INSERT INTO " + table + " VALUES(" + values + ")";
        return finishedQuery;
    }

    public String updateBalance(String accountNumber, String newBalance) {
        tableName = "accounts";
        finishedQuery = "UPDATE " + tableName + " SET CURRENT_BALANCE = " + newBalance +" WHERE ACCOUNT_NUMBER = " + accountNumber;
        return finishedQuery;
    }

    public String getRecords(String table, String searchField, String searchValue) {
        tableName = MapFieldsToColumns.mappings.get(table);
        this.searchField = MapFieldsToColumns.mappings.get(searchField);
        this.searchValue = MapFieldsToColumns.mappings.get(searchValue);
        finishedQuery = "SELECT * FROM " + tableName + " WHERE " + searchField + " = " + searchValue;
        return finishedQuery;
    }
}
