package customer;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    HashMap<String, String> testCustomerDetails = new HashMap<>(Map.of("customerID", "432", "firstName", "Rodney", "lastName", "Price", "dob", "12/06/1972", "address1", "1 The Street", "address2", "The Village", "addressTown", "Townsville", "addressPostcode", "AB1 2CD"));
    HashMap<String,String> testCustomerDetails2 = new HashMap<>(Map.of("customerID","456", "firstName","Mark", "lastName","Myrie", "dob","17/07/1973", "address1","10 The Close", "address2","Southwark", "addressTown","LONDON", "addressPostcode","SE1 1BB"));

    @Test
    void constructorfromHashMap()  {
        Customer customer = new Customer(testCustomerDetails);
        HashMap<String, String> returnedDetails = customer.getDetails();
        assertEquals(testCustomerDetails, returnedDetails);
    }

    @Test
    void setDetails() {
        Customer customer = new Customer(testCustomerDetails);
        customer.setDetails(testCustomerDetails2);
        assertEquals(testCustomerDetails2, customer.getDetails());
    }

    @Test
    void getCustomerID() {
        Customer customer = new Customer(testCustomerDetails);
        assertEquals("432", customer.getCustomerID());
    }

    @Test
    void getDoB() {
        Customer customer = new Customer(testCustomerDetails);
        assertEquals("12/06/1972",customer.getDob());
    }

    @Test
    void setCustomerID() {
        Customer customer = new Customer(testCustomerDetails2);
        customer.setCustomerID("9999");
        assertEquals("9999",customer.getCustomerID());
    }

    @Test
    void getFullAddress() {
        Customer customer = new Customer(testCustomerDetails);
        assertEquals("1 The Street\nThe Village\nTownsville\nAB1 2CD",customer.getFullAddress());
        testCustomerDetails.remove("address2");
        Customer customer2 = new Customer(testCustomerDetails);
        assertEquals("1 The Street\nTownsville\nAB1 2CD",customer2.getFullAddress());
    }

    @Test
    void getFullName() {
        Customer customer = new Customer(testCustomerDetails);
        assertEquals("Rodney Price",customer.getFullName());
    }
}