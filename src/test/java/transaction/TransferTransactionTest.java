package transaction;

import account.Account;
import account.ClientAccount;
import account.CommunityAccount;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class TransferTransactionTest {


    HashMap<String, String> testDataNullType = new HashMap<>(){{put("transactionID","123"); put("accountNumber","12345678"); put("transactionAmount","34.65"); put("transactionType",null); put("previousBalance","538.75"); put("newBalance","504.10"); put("transactionTime","2023/12/19 14:30:00");put("customerID","123");put("targetAccountNumber","987654");}};
    HashMap<String, String> testDataWithType = new HashMap<>(){{put("transactionID","123"); put("accountNumber","12345678"); put("transactionAmount","34.65"); put("transactionType","Transfer Out"); put("previousBalance","538.75"); put("newBalance","504.10"); put("transactionTime","2023/12/19 14:30:00");put("customerID","123");put("targetAccountNumber","987654");}};
    HashMap<String, String> testAccountDetails = new HashMap<>(Map.of("customerID", "123", "accountNumber", "12345678", "currentBalance", "308.50", "signatories", "0"));
    HashMap<String, String> testAccountDetails1 = new HashMap<>(Map.of("customerID", "456", "accountNumber", "98765432", "currentBalance", "10.00", "signatories", "0"));


    @Test
    void constructorFromHashMapNoTransactionType() {
        Transaction transaction = new TransferTransaction(testDataNullType);
        HashMap<String,String> returnedDetails = transaction.getDetails();
        testDataNullType.put("transactionType","Transfer");
        assertEquals(testDataNullType, returnedDetails);
    }

    @Test
    void constructorFromHashMapWithTransactionType() {
        Transaction transaction = new TransferTransaction(testDataWithType);
        HashMap<String,String> returnedDetails = transaction.getDetails();
        assertEquals(testDataWithType, returnedDetails);
    }

    @Test
    void constructorFromAccounts() {
        Account account1 = new ClientAccount(testAccountDetails);
        Account account2 = new CommunityAccount(testAccountDetails1);
        Transaction transaction = new TransferTransaction(account1, account2, "300.00");
        assertEquals("12345678",transaction.getAccountNumber());
        assertEquals("308.50",transaction.getPreviousBalance());
        assertEquals("300.00",transaction.getTransactionAmount());
        assertEquals("98765432",transaction.getTargetAccountNumber());
        assertEquals("Transfer",transaction.getTransactionType());
    }

    @Test
    void getAndSetMethods() {
        Transaction transaction = new TransferTransaction(testDataNullType);
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

}