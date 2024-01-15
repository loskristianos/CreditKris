package transaction;

import account.Account;
import account.ClientAccount;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PendingTransactionTest {
    HashMap<String,String> testData = new HashMap<>(){{
       put("transactionID","123456"); put("accountNumber","987654"); put("signatoryID","19821"); put("transactionAmount","34.56"); put("transactionType","Deposit"); put("customerID","123"); put("customerName","David Brooks");
    }};
    HashMap<String,String> amendedData = new HashMap<>(){{
        put("transactionID","987654"); put("accountNumber","111111"); put("signatoryID","666666"); put("transactionAmount","98.67"); put("transactionType","Withdrawal"); put("customerID","456"); put("customerName","Moses Davis");
    }};
    HashMap<String, String> testTransactionData = new HashMap<>(){{put("transactionID","1087"); put("accountNumber","12345678"); put("customerID","321"); put("transactionAmount","34.65"); put("transactionType","Deposit"); put("previousBalance","538.75"); put("newBalance","573.40"); put("transactionTime","2023/12/19 14:30:00");}};
    HashMap<String, String> testAccountDetails = new HashMap<>(Map.of("customerID", "123", "accountNumber", "12345678", "currentBalance", "308.50", "signatories", "0"));


    @Test
    void getDetails() {
        PendingTransaction transaction = new PendingTransaction(testData);
        testData.put("targetAccountNumber",null);
        assertEquals(testData, transaction.getDetails());
    }

    @Test
    void setDetails() {
        PendingTransaction transaction = new PendingTransaction(testData);
        transaction.setDetails(amendedData);
        amendedData.put("targetAccountNumber",null);
        assertEquals(amendedData,transaction.getDetails());
    }

    @Test
    void setAndGetMethods() {
        Transaction transaction = new DepositTransaction(testTransactionData);
        Account account = new ClientAccount(testAccountDetails);
        PendingTransaction pendingTransaction = new PendingTransaction(account, transaction);
        pendingTransaction.setCustomerName("Mark Myrie");
        pendingTransaction.setCustomerID("909");
        pendingTransaction.setSignatoryID("443");

        assertNull(pendingTransaction.getTargetAccountNumber());
        assertEquals("Mark Myrie",pendingTransaction.getCustomerName());
        assertEquals("909",pendingTransaction.getCustomerID());
       assertEquals ("34.65",pendingTransaction.getTransactionAmount());
        assertEquals("443",pendingTransaction.getSignatoryID());
        assertEquals("12345678",transaction.getAccountNumber());
    }
}