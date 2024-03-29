package transaction;

import account.Account;
import account.ClientAccount;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class DepositTransactionTest {
        HashMap<String, String> testData = new HashMap<>(){{put("transactionID","1087"); put("accountNumber","12345678"); put("customerID","321"); put("transactionAmount","34.65"); put("transactionType","Deposit"); put("previousBalance","538.75"); put("newBalance","573.40"); put("transactionTime","2023/12/19 14:30:00");}};
        HashMap<String, String> testAccountDetails = new HashMap<>(Map.of("customerID", "123", "accountNumber", "12345678", "currentBalance", "308.50", "signatories", "0"));


    @Test
    void constructorFromHashMap() {
        Transaction transaction = new DepositTransaction(testData);
        testData.put("targetAccountNumber",null);
        HashMap<String,String> returnedDetails = transaction.getDetails();
        assertEquals(testData, returnedDetails);
    }

    @Test
    void constructorFromAccount() {
        Account account = new ClientAccount(testAccountDetails);
        Transaction transaction = new DepositTransaction(account, "12.34");
        assertEquals("12345678",transaction.getAccountNumber());
        assertEquals("12.34",transaction.getTransactionAmount());
        assertEquals("Deposit",transaction.getTransactionType());
        assertEquals("308.50",transaction.getPreviousBalance());
        assertEquals("320.84",transaction.getNewBalance());
    }

    @Test
    void getAndSetMethods() {

        Transaction transaction = new DepositTransaction(testData);
        transaction.setTransactionAmount("900.76");
        assertEquals("900.76",transaction.getTransactionAmount());
        transaction.setNewBalance("3000.00");
        assertEquals("3000.00",transaction.getDetails().get("newBalance"));
        transaction.setTransactionType("testType");
        assertEquals("testType",transaction.getTransactionType());
        transaction.setPreviousBalance("11.23");
        assertEquals("11.23",transaction.getPreviousBalance());
        transaction.setTargetAccountNumber("98765");
        assertEquals("98765",transaction.getTargetAccountNumber());
        transaction.setAuthorised(1);
        assertEquals(1,transaction.getAuthorised());
    }

    @Test
    void calculateNewBalance() {
        Transaction transaction = new DepositTransaction(testData);
        assertEquals("573.40", transaction.calculateNewBalance());
    }

    @Test
    void overdraftCheck() {
        Account account = new ClientAccount(testAccountDetails);
        Transaction transaction = new DepositTransaction(account, "3000.00");
        assertEquals(0,transaction.overdraftCheck());
    }
}