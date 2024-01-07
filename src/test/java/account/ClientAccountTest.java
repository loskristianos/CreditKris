package account;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ClientAccountTest {

    HashMap<String,String> testDataHM = new HashMap<>(Map.of("customerID","123", "accountNumber","12345678", "currentBalance","308.50", "overdraftLimit","500", "signatories","1"));
    HashMap<String,String> expectedDataHM = new HashMap<>(Map.of("customerID","123",  "accountNumber","12345678", "currentBalance","308.50", "overdraftLimit","1500", "signatories","1", "accountType","Client"));


        @Test
    void withArgsConstructorHM() {
        Account account = new ClientAccount(testDataHM);
        HashMap<String, String> returnedDetails = account.getDetails();
        assertEquals(expectedDataHM, returnedDetails);
    }

    @Test
    void withArgsConstructorSetBalanceHM() {
        Account account = new ClientAccount(testDataHM);
        account.setCurrentBalance("907.56");

        expectedDataHM.put("currentBalance","907.56");
        HashMap<String,String> returnedDetails = account.getDetails();
        assertEquals(expectedDataHM, returnedDetails);
    }
}