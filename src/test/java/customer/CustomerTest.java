package customer;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

        String[] nullData = {null, null, null, null, null, null, null, null};
        String[] testData = {"123", "Rodney", "Price", "12/09/1972", "1 The Street", "Amble", "MORPETH", "NE65 1BK"};
        String[] testData2 = {"456", "Mark", "Myrie", "17/07/1973", "10 The Close", "Southwark", "LONDON", "SE1 1BB"};

    @Test
    void noArgsConstructor() {
        var customer = new Customer();
        String[] returnedDetails = customer.getCustomerDetails();
        assertArrayEquals(nullData, returnedDetails);
    }

    @Test
    void noArgsConstructorSetData() {
        var customer = new Customer();
        customer.setCustomerDetails(testData);
        String[] returnedDetails = customer.getCustomerDetails();
        assertArrayEquals(testData, returnedDetails);
    }
    @Test
    void withArgsConstructor()  {
        var customer = new Customer(testData);
        String[] returnedDetails = customer.getCustomerDetails();
        assertArrayEquals(testData, returnedDetails);
    }

    @Test
    void withArgsConstructorSetData() {
        var customer = new Customer(testData);
        customer.setCustomerDetails(testData2);
        assertArrayEquals(testData2, customer.getCustomerDetails());
    }
}