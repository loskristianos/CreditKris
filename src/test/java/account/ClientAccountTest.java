package account;

import customer.Customer;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


class ClientAccountTest {

    HashMap<String, String> testAccountDetails = new HashMap<>(Map.of("customerID", "123", "accountNumber", "12345678", "currentBalance", "308.50", "signatories", "0"));
    HashMap<String, String> expectedAccountDetails = new HashMap<>(Map.of("customerID", "123", "accountNumber", "12345678", "currentBalance", "308.50", "overdraftLimit", "1500.00", "signatories", "0", "accountType", "Client"));
    HashMap<String, String> testCustomerDetails = new HashMap<>(Map.of("customerID", "432", "firstName", "Rodney", "lastName", "Price", "dob", "12/06/1972", "address1", "1 The Street", "address2", "The Village", "addressTown", "Townsville", "addressPostcode", "AB1 2CD"));

    @Test
    void constructorFromHashMap() {
        Account account = new ClientAccount(testAccountDetails);
        HashMap<String, String> returnedDetails = account.getDetails();
        assertEquals(expectedAccountDetails, returnedDetails);
    }

    @Test
    void constructorFromCustomer() {
        Customer customer = new Customer(testCustomerDetails);
        Account account = new ClientAccount(customer);
        expectedAccountDetails.put("customerID", "432");
        expectedAccountDetails.put("accountNumber", null);
        expectedAccountDetails.put("currentBalance", null);
        expectedAccountDetails.put("signatories", null);
        assertEquals(expectedAccountDetails, account.getDetails());
    }

    @Test
    void getAndSetMethods() {
        Account account = new ClientAccount(testAccountDetails);
        account.setSignatories("3");
        account.setCurrentBalance("214.77");
        assertEquals("3", account.getSignatories());
        assertEquals("214.77", account.getCurrentBalance());
        assertEquals("Client", account.getAccountType());
        assertEquals("12345678", account.getAccountNumber());
        assertEquals("1500.00", account.getOverdraftLimit());
        assertEquals("123", account.getCustomerID());
    }

}