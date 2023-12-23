package customer;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

        HashMap<String,String> nullDataHM = new HashMap<>() {{
            put("customerID",null); put("firstName",null); put("lastName",null); put("dob",null); put("address1",null); put("address2",null); put("addressTown",null); put("addressPostcode",null);
        }};
        HashMap<String,String> testDataHM = new HashMap<>(Map.of("customerID","123", "firstName","Rodney", "lastName","Price", "dob","12/09/1972", "address1","1 The Street", "address2","Amble", "addressTown","MORPETH", "addressPostcode","NE65 1BK"));
        HashMap<String,String> testData2HM = new HashMap<>(Map.of("customerID","456", "firstName","Mark", "lastName","Myrie", "dob","17/07/1973", "address1","10 The Close", "address2","Southwark", "addressTown","LONDON", "addressPostcode","SE1 1BB"));

    @Test
    void noArgsConstructorHM() {
        var customer = new Customer();
        HashMap<String, String> returnedDetails = customer.getDetails();
        assertEquals(nullDataHM, returnedDetails);
    }

    @Test
    void noArgsConstructorSetDataHM() {
        var customer = new Customer();
        customer.setDetails(testDataHM);
        HashMap<String, String> returnedDetails = customer.getDetails();
        assertEquals(testDataHM, returnedDetails);
    }
    @Test
    void withArgsConstructorHM()  {
        var customer = new Customer(testDataHM);
        HashMap<String, String> returnedDetails = customer.getDetails();
        assertEquals(testDataHM, returnedDetails);
    }

    @Test
    void withArgsConstructorSetDataHM() {
        var customer = new Customer(testDataHM);
        customer.setDetails(testData2HM);
        assertEquals(testData2HM, customer.getDetails());
    }
}