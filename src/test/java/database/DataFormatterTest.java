package database;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DataFormatterTest {

    String[] testData = {"18725", "walk", "kris", "hardy", "test"};
    @Test
    void testPassingObjectIn() {
        DataFormatter x = new DataFormatter(testData);
        String result = x.extractValuesToString();
        assertEquals("'18725','walk','kris','hardy','test'", result);
    }
}