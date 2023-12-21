package database;

import org.junit.jupiter.api.Test;

class DatabaseWriteTest {

    String testData = "insert into login values(null, 'johannes', 'wooden56')";
    @Test
    void testConstructor() {
        DatabaseWrite x = new DatabaseWrite(testData);
        x.writeData();
    }
}