package database;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StatementBuilderTest {

    String table = "test_table";
    String values = "'123','David','Brooks','30/11/1980'";

    @Test
    void testConstructor() {
        StatementBuilder x = new StatementBuilder();
        assertSame(StatementBuilder.class, x.getClass());
        assertEquals(null,x.finishedQuery);
        assertEquals(null, x.tableName);
    }

    @Test
    void writeNewRecord() {
        StatementBuilder x = new StatementBuilder();
        String result = x.writeNewRecord(values, table);
        assertEquals("INSERT INTO test_table VALUES('123','David','Brooks','30/11/1980')",result);
    }

    @Test
    void updateBalance() {
        StatementBuilder x = new StatementBuilder();
        String result = x.updateBalance("'204567'", "'265.66'");
        assertEquals("UPDATE account SET BALANCE = '265.66' WHERE ACCOUNT_NUMBER = '204567'", result);
    }

    @Test
    void getRecords() {
        StatementBuilder x = new StatementBuilder();
        String result = x.getRecords(table, "account_number", "'123456'");
        assertEquals("SELECT * FROM test_table WHERE account_number = '123456'", result);
    }
}