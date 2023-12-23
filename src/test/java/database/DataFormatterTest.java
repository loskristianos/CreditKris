package database;

import account.ClientAccount;
import org.junit.jupiter.api.Test;
import transaction.TransferTransaction;

import static org.junit.jupiter.api.Assertions.*;

class DataFormatterTest {

    String[] testAccountData = {"123", "40-25-99", "12345678", "308.50", "500", "1"};
    @Test
    void testPassingObjectToClass() {
        TransferTransaction a = new TransferTransaction();
        DataFormatter c = new DataFormatter(a);
        ClientAccount b = new ClientAccount();
        DataFormatter d = new DataFormatter(b);
        assertEquals("account", d.tableName);
        assertEquals("transaction", c.tableName);
    }

    @Test
    void testGettingDataOutOfObject() throws Exception {
        ClientAccount z = new ClientAccount(testAccountData);
        DataFormatter y = new DataFormatter(z);
        String query = "insert into " + y.tableName + " values(" + y.extractValuesToString() + ")";
        assertEquals("insert into accounts values('123','40-25-99','12345678','308.50','1500','1')", query);
    }
}