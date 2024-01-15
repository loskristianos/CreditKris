package database;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static database.MapFieldsToColumns.mappingsFromDB;
import static database.MapFieldsToColumns.mappingsToDB;
import static org.junit.jupiter.api.Assertions.*;

class MapFieldsToColumnsTest {

    @Test
    void mappingsObjectToDB() {
        String objectField = "customerID";
        String databaseField = mappingsToDB.get(objectField);
        assertEquals("customer_id", databaseField);
    }

    @Test
    void mappingsDBtoObject() {
        String returnedDatabaseColumn = "current_balance";
        String objectField = mappingsFromDB.get(returnedDatabaseColumn);
        assertEquals("currentBalance", objectField);
    }

    @Test
    void mapsAreTheSame() {
        // compare the list of keys from one map to the list of values from the other, and vice versa
        List<String> mapToDBKeys = new ArrayList<>(mappingsToDB.keySet().stream().toList()); Collections.sort(mapToDBKeys);
        List<String> mapFromDBValues = new ArrayList<>(mappingsFromDB.values().stream().toList()); Collections.sort(mapFromDBValues);
        assertEquals(mapToDBKeys, mapFromDBValues);
        List<String> mapFromDBKeys = new ArrayList<>(mappingsFromDB.keySet().stream().toList()); Collections.sort(mapFromDBKeys);
        List<String> mapToDBValues = new ArrayList<>(mappingsToDB.values().stream().toList()); Collections.sort(mapToDBValues);
        assertEquals(mapFromDBKeys,mapToDBValues);
    }


}