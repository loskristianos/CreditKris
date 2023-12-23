package transaction;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class WithdrawalTransactionTest {

    HashMap<String, String> nullDataHM = new HashMap<>() {{
        put("transactionID",null); put("accountNumber",null); put("transactionAmount", null); put("transactionType",null); put("previousBalance",null); put("newBalance",null); put("transactionTime",null); put("authorised",null); put("additionalInfo",null);
    }};
    HashMap<String, String> testDataHM = new HashMap<>(Map.of("transactionID","1087",  "accountNumber","12345678", "transactionAmount","34.65", "transactionType","Withdrawal", "previousBalance","538.75", "newBalance","504.10", "transactionTime","2023/12/19 14:30:00", "authorised","Y", "additionalInfo",""));

    @Test
    void noArgsConstructor() {
        Transaction transaction = new WithdrawalTransaction();
        HashMap<String, String> returnedDetails = transaction.getDetails();
        assertEquals(nullDataHM, returnedDetails);
    }

   @Test
    void noArgsConstructorSetData() {
        Transaction transaction = new WithdrawalTransaction();
        transaction.setDetails(testDataHM);
        HashMap<String, String> returnedDetails = transaction.getDetails();
        assertEquals(testDataHM, returnedDetails);
    }

    @Test
    void withArgsConstructor() {
        Transaction transaction = new WithdrawalTransaction(testDataHM);
        HashMap<String,String> returnedDetails = transaction.getDetails();
        assertEquals(testDataHM, returnedDetails);
    }
}