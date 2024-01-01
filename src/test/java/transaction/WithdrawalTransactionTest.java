package transaction;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class WithdrawalTransactionTest {

    HashMap<String, String> nullData = new HashMap<>() {{
        put("transactionID",null); put("accountNumber",null); put("transactionAmount", null); put("transactionType","Withdrawal"); put("previousBalance",null); put("newBalance",null); put("transactionTime",null); put("additionalInfo",null);
    }};
    HashMap<String, String> testData = new HashMap<>(){{ put("transactionID","1087"); put("accountNumber","12345678"); put("transactionAmount","34.65"); put("transactionType","Withdrawal"); put("previousBalance","538.75"); put("newBalance","504.10"); put("transactionTime","2023/12/19 14:30:00"); put("additionalInfo",null);}};

    @Test
    void noArgsConstructor() {
        Transaction transaction = new WithdrawalTransaction();
        HashMap<String, String> returnedDetails = transaction.getDetails();
        assertEquals(nullData, returnedDetails);
    }

   @Test
    void noArgsConstructorSetData() {
        Transaction transaction = new WithdrawalTransaction();
        transaction.setDetails(testData);
        HashMap<String, String> returnedDetails = transaction.getDetails();
        assertEquals(testData, returnedDetails);
    }

    @Test
    void withArgsConstructor() {
        Transaction transaction = new WithdrawalTransaction(testData);
        HashMap<String,String> returnedDetails = transaction.getDetails();
        assertEquals(testData, returnedDetails);
    }

    @Test
    void setAndGetMethods() {
        Transaction transaction = new WithdrawalTransaction(testData);
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
        assertEquals("Transaction",transaction.getObjectType());
    }

    @Test
    void calculateNewBalance() {
        Transaction transaction = new WithdrawalTransaction(testData);
        assertEquals("504.10",transaction.calculateNewBalance());
    }
}