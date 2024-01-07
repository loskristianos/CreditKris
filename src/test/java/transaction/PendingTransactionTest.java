package transaction;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class PendingTransactionTest {
    HashMap<String,String> testData = new HashMap<>(){{
       put("transactionID","123456"); put("accountNumber","987654"); put("customerID","19821"); put("transactionAmount","34.56"); put("transactionType","Deposit");
    }};
    HashMap<String,String> amendedData = new HashMap<>(){{
        put("transactionID","987654"); put("accountNumber","111111"); put("customerID","666666"); put("transactionAmount","98.67"); put("transactionType","Withdrawal");
    }};

    @Test
    void getDetails() {
        PendingTransaction transaction = new PendingTransaction(testData);
        assertEquals(testData, transaction.getDetails());
    }

    @Test
    void setDetails() {
        PendingTransaction transaction = new PendingTransaction(testData);
        transaction.setDetails(amendedData);
        assertEquals(amendedData,transaction.getDetails());
    }
}