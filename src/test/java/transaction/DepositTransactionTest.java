package transaction;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class DepositTransactionTest {
        HashMap<String, String> testData = new HashMap<>(){{put("transactionID","1087"); put("accountNumber","12345678"); put("transactionAmount","34.65"); put("transactionType","Deposit"); put("previousBalance","538.75"); put("newBalance","573.40"); put("transactionTime","2023/12/19 14:30:00");}};



    @Test
    void withArgsConstructor() {
        Transaction transaction = new DepositTransaction(testData);
        HashMap<String,String> returnedDetails = transaction.getDetails();
        assertEquals(testData, returnedDetails);
    }

    @Test
    void getAndSetMethods() {

        Transaction transaction = new DepositTransaction(testData);
        transaction.setTransactionAmount("900.76");
        assertEquals("900.76",transaction.getTransactionAmount());
        transaction.setNewBalance("3000.00");
        assertEquals("3000.00",transaction.getDetails().get("newBalance"));
        transaction.setTransactionType("testType");
        assertEquals("testType",transaction.getTransactionType());
        transaction.setPreviousBalance("11.23");
        assertEquals("11.23",transaction.getPreviousBalance());
    }

    @Test
    void calculateNewBalance() {
        Transaction transaction = new DepositTransaction(testData);
        assertEquals("573.40", transaction.calculateNewBalance());
    }
}