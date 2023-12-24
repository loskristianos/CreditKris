package database;

import account.Account;
import account.ClientAccount;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class AccountDataHandlerTest {

    HashMap<String,String> testDataHM = new HashMap<>(Map.of("customerID","123", "accountNumber","12345678", "currentBalance","308.50", "overdraftLimit","500", "signatories","1"));


    @Test
    void writeAccountData() {
       Account x = new ClientAccount(testDataHM);
       new AccountDataHandler(x).writeNewRecord();
    }
}