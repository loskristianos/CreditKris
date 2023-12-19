package transactions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DepositTransactionTest {
    String[] nullData = {null, null, null, null, null, null, null, null};
    String[] testData = {"1087", "12345678", "34.65", "Deposit", "538.75", "573.40", "2023/12/19 14:30:00", "Y"};

    @Test
    void noArgsConstructor() {
        Transaction transaction = new DepositTransaction();
        String[] returnedDetails = transaction.getTransactionDetails();
        assertArrayEquals(nullData, returnedDetails);
    }

    @Test
    void noArgsConstructorSetData() {
        Transaction transaction = new DepositTransaction();
        transaction.setTransactionDetails(testData);
        String[] returnedDetails = transaction.getTransactionDetails();
        assertArrayEquals(testData, returnedDetails);
    }

    @Test
    void withArgsConstructor() {
        Transaction transaction = new DepositTransaction(testData);
        String[] returnedDetails = transaction.getTransactionDetails();
        assertArrayEquals(testData, returnedDetails);
    }
}