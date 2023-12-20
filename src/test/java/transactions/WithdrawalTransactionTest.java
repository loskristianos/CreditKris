package transactions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WithdrawalTransactionTest {

    String[] nullData = {null, null, null, null, null, null, null, null, null};
    String[] testData = {"1087", "12345678", "34.65", "Withdrawal", "538.75", "504.10", "2023/12/19 14:30:00", "Y", ""};

    @Test
    void noArgsConstructor() {
        Transaction transaction = new WithdrawalTransaction();
        String[] returnedDetails = transaction.getTransactionDetails();
        assertArrayEquals(nullData, returnedDetails);
    }

    @Test
    void noArgsConstructorSetData() {
        Transaction transaction = new WithdrawalTransaction();
        transaction.setTransactionDetails(testData);
        String[] returnedDetails = transaction.getTransactionDetails();
        assertArrayEquals(testData, returnedDetails);
    }

    @Test
    void withArgsConstructor() {
        Transaction transaction = new WithdrawalTransaction(testData);
        String[] returnedDetails = transaction.getTransactionDetails();
        assertArrayEquals(testData, returnedDetails);
    }

}