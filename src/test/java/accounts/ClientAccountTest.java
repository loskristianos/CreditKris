package accounts;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ClientAccountTest {

    String[] nullData = {null, null, null, null, null, null};
    String[] testData = {"123", "40-25-99", "12345678", "308.50", "500", "1"};
    String[] expectedData = testData.clone();

    @Test
    void noArgsConstructor() {
        Account account = new ClientAccount();
        String[] returnedDetails = account.getAccountDetails();
        assertArrayEquals(nullData, returnedDetails);
    }

    @Test
    void noArgsConstructorSetDetails() {
        Account account = new ClientAccount();
        account.setAccountDetails(testData);
        String[] returnedData = account.getAccountDetails();
        assertArrayEquals(testData, returnedData);
    }
    @Test
    void withArgsConstructor() {
        Account account = new ClientAccount(testData);
        expectedData[4] = "1500";
        String[] returnedDetails = account.getAccountDetails();
        assertArrayEquals(expectedData, returnedDetails);
    }

    @Test
    void withArgsConstructorSetBalance() {
        Account account = new ClientAccount(testData);
        account.setCurrentBalance("907.56");
        expectedData[4] = "1500";
        expectedData[3] = "907.56";
        String[] returnedDetails = account.getAccountDetails();
        assertArrayEquals(expectedData, returnedDetails);
    }
}