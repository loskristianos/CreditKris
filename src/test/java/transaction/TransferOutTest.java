package transaction;

import account.Account;
import account.ClientAccount;
import account.SmallBusinessAccount;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class TransferOutTest {
    HashMap<String, String> testAccountDetails = new HashMap<>(Map.of("customerID", "123", "accountNumber", "12345678", "currentBalance", "308.50", "signatories", "0"));

    @Test
    void calculateNewBalance() {
        Account account = new SmallBusinessAccount(testAccountDetails);
        Transaction transaction = new TransferOut(account, "300.00");
        assertEquals("8.50",transaction.getNewBalance());
        assertEquals("8.50",account.getCurrentBalance());
        assertEquals("Transfer Out",transaction.getTransactionType());
    }

    @Test
    void overdraftCheck() {
        Account account = new ClientAccount(testAccountDetails);
        Transaction transaction = new TransferOut(account, "3000.00");
        assertEquals(-3,transaction.overdraftCheck());
    }
}