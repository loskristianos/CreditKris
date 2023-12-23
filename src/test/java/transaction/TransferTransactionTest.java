package transaction;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class TransferTransactionTest {

    HashMap<String, String> nullDataHM = new HashMap<>() {{
        put("transactionID",null); put("accountNumber",null); put("transactionAmount", null); put("transactionType",null); put("previousBalance",null); put("newBalance",null); put("transactionTime",null); put("authorised",null); put("additionalInfo",null);
    }};
    HashMap<String, String> testDataHM = new HashMap<>(Map.of("transactionID","123",  "accountNumber","12345678", "transactionAmount","34.65", "transactionType","Transfer", "previousBalance","538.75", "newBalance","504.10", "transactionTime","2023/12/19 14:30:00", "authorised","Y", "additionalInfo","98765432"));

    @Test
    void noArgsConstructor() {
        Transaction transaction = new TransferTransaction();
        HashMap<String, String> returnedDetails = transaction.getDetails();
        assertEquals(nullDataHM, returnedDetails);
    }

    @Test
    void noArgsConstructorSetData() {
        Transaction transaction = new TransferTransaction();
        transaction.setDetails(testDataHM);
        HashMap<String, String> returnedDetails = transaction.getDetails();
        assertEquals(testDataHM, returnedDetails);
    }

    @Test
    void withArgsConstructor() {
        Transaction transaction = new TransferTransaction(testDataHM);
        HashMap<String,String> returnedDetails = transaction.getDetails();
        assertEquals(testDataHM, returnedDetails);
    }

}