package accounts;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommunityAccountTest {

    String[] nullData = {null, null, null, null, null, null};
    String[] testData = {"123", "40-25-99", "12345678", "308.50", "500", "1"};
    String[] expectedData = testData.clone();

    @Test
    void noArgsConstructor() {
        Account account = new CommunityAccount();
        String[] returnedDetails = account.getAccountDetails();
        assertArrayEquals(nullData, returnedDetails);
    }

    @Test
    void noArgsConstructorSetDetails() {
        Account account = new CommunityAccount();
        account.setAccountDetails(testData);
        String[] returnedData = account.getAccountDetails();
        assertArrayEquals(testData, returnedData);
    }
    @Test
    void withArgsConstructor() {
        Account account = new CommunityAccount(testData);
        expectedData[4] = "2500";
        String[] returnedDetails = account.getAccountDetails();
        assertArrayEquals(expectedData, returnedDetails);
    }

    @Test
    void withArgsConstructorSetBalance() {
        Account account = new CommunityAccount(testData);
        account.setCurrentBalance("907.56");
        expectedData[4] = "2500";
        expectedData[3] = "907.56";
        String[] returnedDetails = account.getAccountDetails();
        assertArrayEquals(expectedData, returnedDetails);
    }

}