package transaction;

import account.Account;
import account.ClientAccount;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class TransferInTest {
    HashMap<String, String> testAccountDetails = new HashMap<>(Map.of("customerID", "123", "accountNumber", "12345678", "currentBalance", "308.50", "signatories", "0"));

    @Test
    void calculateNewBalance() {
        Account account = new ClientAccount(testAccountDetails);
        Transaction transaction = new TransferIn(account, "300.00");
        assertEquals("608.50",transaction.getNewBalance());
        assertEquals("608.50",account.getCurrentBalance());
        assertEquals("Transfer In",transaction.getTransactionType());
    }
}