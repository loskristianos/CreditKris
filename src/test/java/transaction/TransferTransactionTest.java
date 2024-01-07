package transaction;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class TransferTransactionTest {


    HashMap<String, String> testDataNullType = new HashMap<>(){{put("transactionID","123"); put("accountNumber","12345678"); put("transactionAmount","34.65"); put("transactionType",null); put("previousBalance","538.75"); put("newBalance","504.10"); put("transactionTime","2023/12/19 14:30:00"); put("additionalInfo","98765432");}};
    HashMap<String, String> testDataWithType = new HashMap<>(){{put("transactionID","123"); put("accountNumber","12345678"); put("transactionAmount","34.65"); put("transactionType","Transfer"); put("previousBalance","538.75"); put("newBalance","504.10"); put("transactionTime","2023/12/19 14:30:00"); put("additionalInfo","98765432");}};



    @Test
    void withArgsConstructorNoType() {
        Transaction transaction = new TransferTransaction(testDataNullType);
        HashMap<String,String> returnedDetails = transaction.getDetails();
        testDataNullType.put("transactionType","Transfer");
        assertEquals(testDataNullType, returnedDetails);
    }

    @Test
    void withArgsConstructorWithType() {
        Transaction transaction = new TransferTransaction(testDataWithType);
        HashMap<String,String> returnedDetails = transaction.getDetails();
        assertEquals(testDataWithType, returnedDetails);
    }

    @Test
    void getAndSetMethods() {
        Transaction transaction = new TransferTransaction(testDataNullType);
        transaction.setTransactionAmount("900.76");
        assertEquals("900.76",transaction.getTransactionAmount());
        transaction.setNewBalance("3000.00");
        assertEquals("3000.00",transaction.getDetails().get("newBalance"));
        transaction.setAdditionalInfo("test info");
        assertEquals("test info",transaction.getAdditionalInfo());
        transaction.setTransactionType("testType");
        assertEquals("testType",transaction.getTransactionType());
        transaction.setPreviousBalance("11.23");
        assertEquals("11.23",transaction.getPreviousBalance());
    }

    @Test
    void calculateNewBalance() {
        Transaction transaction = new TransferTransaction(testDataNullType);
        transaction.setTransactionType("TransferOut");
        assertEquals("504.10", transaction.calculateNewBalance());
        transaction.setTransactionType("TransferIn");
        assertEquals("573.40", transaction.calculateNewBalance());
    }
}