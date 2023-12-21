package account;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SmallBusinessAccountTest {
    String[] nullData = {null, null, null, null, null, null};
    String[] testData = {"123", "40-25-99", "12345678", "308.50", "500", "1"};
    String[] expectedData = testData.clone();

    @Test
    void noArgsConstructor() {
        Account account = new SmallBusinessAccount();
        String[] returnedDetails = account.getAccountDetails();
        assertArrayEquals(nullData, returnedDetails);
    }

    @Test
    void noArgsConstructorSetDetails() {
        Account account = new SmallBusinessAccount();
        account.setAccountDetails(testData);
        String[] returnedData = account.getAccountDetails();
        assertArrayEquals(testData, returnedData);
    }
    @Test
    void withArgsConstructor() {
        Account account = new SmallBusinessAccount(testData);
        expectedData[4] = "1000";
        String[] returnedDetails = account.getAccountDetails();
        assertArrayEquals(expectedData, returnedDetails);
    }

    @Test
    void withArgsConstructorSetBalance() {
        Account account = new SmallBusinessAccount(testData);
        account.setCurrentBalance("907.56");
        expectedData[4] = "1000";
        expectedData[3] = "907.56";
        String[] returnedDetails = account.getAccountDetails();
        assertArrayEquals(expectedData, returnedDetails);
    }

}