package database;

import accounts.ClientAccount;
import org.junit.jupiter.api.Test;
import transactions.TransferTransaction;

import static org.junit.jupiter.api.Assertions.*;

class DataFormatterTest {

    String[] testAccountData = {"123", "40-25-99", "12345678", "308.50", "500", "1"};
    @Test
    void testPassingObjectToClass() {
        TransferTransaction a = new TransferTransaction();
        DataFormatter c = new DataFormatter(a);
        ClientAccount b = new ClientAccount();
        DataFormatter d = new DataFormatter(b);
        assertEquals("accounts", d.tableName);
        assertEquals("transactions", c.tableName);
    }

    @Test
    void testGettingDataOutOfObject() throws Exception {
        ClientAccount z = new ClientAccount(testAccountData);
        DataFormatter y = new DataFormatter(z);
        String query = "insert into " + y.tableName + " values(" + y.extractValuesToString() + ")";
        assertEquals("insert into accounts values('123','40-25-99','12345678','308.50','1500','1')", query);
    }

    @Test
    void buildWriteQueryFromObject() throws Exception {
        ClientAccount z = new ClientAccount(testAccountData);
        DataFormatter y = new DataFormatter(z);
        String sqlQuery = y.buildWriteQuery();
        assertEquals("insert into accounts values('123','40-25-99','12345678','308.50','1500','1')", sqlQuery);
    }
}