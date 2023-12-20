package transactions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TransferTransactionTest {
    String[] nullData = {null, null, null, null, null, null, null, null, null};
    String[] testData = {"1087", "12345678", "34.65", "Transfer", "538.75", "504.10", "2023/12/19 14:30:00", "Y", "98765432"};

    @Test
    void noArgsConstructor() {
        Transaction transaction = new TransferTransaction();
        String[] returnedDetails = transaction.getTransactionDetails();
        assertArrayEquals(nullData, returnedDetails);
    }

    @Test
    void noArgsConstructorSetData() {
        Transaction transaction = new TransferTransaction();
        transaction.setTransactionDetails(testData);
        String[] returnedDetails = transaction.getTransactionDetails();
        assertArrayEquals(testData, returnedDetails);
    }

    @Test
    void withArgsConstructor() {
        Transaction transaction = new TransferTransaction(testData);
        String[] returnedDetails = transaction.getTransactionDetails();
        assertArrayEquals(testData, returnedDetails);
    }

}