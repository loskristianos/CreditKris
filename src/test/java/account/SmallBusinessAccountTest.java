package account;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class SmallBusinessAccountTest {

    Map<String,String> nullDataHM = new HashMap<>() {{
        put("customerID",null); put("sortCode", null); put("accountNumber",null); put("currentBalance",null); put("overdraftLimit",null); put("signatories",null);
    }};
    HashMap<String,String> testDataHM = new HashMap<>(Map.of("customerID","123", "sortCode", "40-25-99", "accountNumber","12345678", "currentBalance","308.50", "overdraftLimit","500", "signatories","1"));
    HashMap<String,String> expectedDataHM = new HashMap<>(Map.of("customerID","123", "sortCode", "40-25-99", "accountNumber","12345678", "currentBalance","308.50", "overdraftLimit","500", "signatories","1"));


    @Test
    void noArgsConstructorHM() {
        Account account = new SmallBusinessAccount();
        HashMap<String,String> returnedDetails = account.getDetails();
        assertEquals(nullDataHM, returnedDetails);
    }

    @Test
    void noArgsConstructorSetDetailsHM() {
        Account account = new SmallBusinessAccount();
        account.setDetails(testDataHM);
        HashMap<String, String> returnedData = account.getDetails();
        assertEquals(testDataHM, returnedData);
    }
    @Test
    void withArgsConstructorHM() {
        Account account = new SmallBusinessAccount(testDataHM);
        expectedDataHM.put("overdraftLimit","1000");
        HashMap<String, String> returnedDetails = account.getDetails();
        assertEquals(expectedDataHM, returnedDetails);
    }

    @Test
    void withArgsConstructorSetBalanceHM() {
        Account account = new SmallBusinessAccount(testDataHM);
        account.setCurrentBalance("907.56");
        expectedDataHM.put("overdraftLimit","1000");
        expectedDataHM.put("currentBalance","907.56");
        HashMap<String,String> returnedDetails = account.getDetails();
        assertEquals(expectedDataHM, returnedDetails);
    }

}