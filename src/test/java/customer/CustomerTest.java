package customer;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

        HashMap<String,String> nullData = new HashMap<>() {{
            put("customerID",null); put("firstName",null); put("lastName",null); put("dob",null); put("address1",null); put("address2",null); put("addressTown",null); put("addressPostcode",null);
        }};
        HashMap<String,String> testData = new HashMap<>(Map.of("customerID","123", "firstName","Rodney", "lastName","Price", "dob","12/09/1972", "address1","1 The Street", "address2","Amble", "addressTown","MORPETH", "addressPostcode","NE65 1BK"));
        HashMap<String,String> testData2 = new HashMap<>(Map.of("customerID","456", "firstName","Mark", "lastName","Myrie", "dob","17/07/1973", "address1","10 The Close", "address2","Southwark", "addressTown","LONDON", "addressPostcode","SE1 1BB"));

    @Test
    void noArgsConstructor() {
        var customer = new Customer();
        HashMap<String, String> returnedDetails = customer.getDetails();
        assertEquals(nullData, returnedDetails);
    }

    @Test
    void noArgsConstructorSetData() {
        var customer = new Customer();
        customer.setDetails(testData);
        HashMap<String, String> returnedDetails = customer.getDetails();
        assertEquals(testData, returnedDetails);
    }
    @Test
    void withArgsConstructor()  {
        var customer = new Customer(testData);
        HashMap<String, String> returnedDetails = customer.getDetails();
        assertEquals(testData, returnedDetails);
    }

    @Test
    void withArgsConstructorSetData() {
        var customer = new Customer(testData);
        customer.setDetails(testData2);
        assertEquals(testData2, customer.getDetails());
    }

    @Test
    void getCustomerID() {
        Customer customer = new Customer(testData);
        assertEquals("123", customer.getCustomerID());
    }

    @Test
    void setCustomerID() {
        Customer customer = new Customer(testData);
        customer.setCustomerID("9999");
        assertEquals("9999",customer.getCustomerID());
    }

    @Test
    void getObjectType() {
        Customer customer = new Customer(testData);
        assertEquals("Customer",customer.getObjectType());
    }

    @Test
    void getFullAddress() {
        Customer customer = new Customer(testData);
        assertEquals("1 The Street\nAmble\nMORPETH\nNE65 1BK",customer.getFullAddress());
        testData.remove("address2");
        Customer customer2 = new Customer(testData);
        assertEquals("1 The Street\nMORPETH\nNE65 1BK",customer2.getFullAddress());
    }
}