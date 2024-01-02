package account;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CommunityAccountTest {

    Map<String,String> nullDataHM = new HashMap<>() {{
        put("customerID",null); put("accountType", "Community"); put("accountNumber",null); put("currentBalance",null); put("overdraftLimit","2500"); put("signatories",null);
    }};
    HashMap<String,String> testDataHM = new HashMap<>(Map.of("customerID","123",  "accountNumber","12345678", "currentBalance","308.50", "overdraftLimit","500", "signatories","1"));
    HashMap<String,String> expectedDataHM = new HashMap<>(Map.of("customerID","123", "accountType", "Community", "accountNumber","12345678", "currentBalance","308.50", "overdraftLimit","2500", "signatories","1"));

    @Test
    void noArgsConstructorHM() {
        Account account = new CommunityAccount();
        HashMap<String,String> returnedDetails = account.getDetails();
        assertEquals(nullDataHM, returnedDetails);
    }

    @Test
    void noArgsConstructorSetDetailsHM() {
        Account account = new CommunityAccount();
        account.setDetails(testDataHM);
        HashMap<String, String> returnedData = account.getDetails();
        assertEquals(expectedDataHM, returnedData);
    }

    @Test
    void withArgsConstructorHM() {
        Account account = new CommunityAccount(testDataHM);
    //    expectedDataHM.put("overdraftLimit","2500");
        HashMap<String, String> returnedDetails = account.getDetails();
        assertEquals(expectedDataHM, returnedDetails);
    }

    @Test
    void withArgsConstructorSetBalanceHM() {
        Account account = new CommunityAccount(testDataHM);
        account.setCurrentBalance("907.56");
    //    expectedDataHM.put("overdraftLimit","2500");
        expectedDataHM.put("currentBalance","907.56");
        HashMap<String,String> returnedDetails = account.getDetails();
        assertEquals(expectedDataHM, returnedDetails);
    }

}