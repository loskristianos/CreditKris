package database;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapFieldsToColumnsTest {

    @Test
    void mappingsObjectToDB() {
        String objectField = "customerID";
        String databaseField = MapFieldsToColumns.mappingsToDB.get(objectField);
        assertEquals("customer_id", databaseField);
    }

    @Test
    void mappingsDBtoObject() {
        String returnedDatabaseColumn = "current_balance";
        String objectField = MapFieldsToColumns.mappingsFromDB.get(returnedDatabaseColumn);
        assertEquals("currentBalance", objectField);
    }
}