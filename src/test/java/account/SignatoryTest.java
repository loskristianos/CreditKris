package account;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class SignatoryTest {

    HashMap<String,String> testDetails = new HashMap<>(){{put("accountNumber","1234"); put("customerID","9876");}};
    HashMap<String,String> expectedDetails = new HashMap<>(){{put("accountNumber","1234"); put("customerID","9876");}};

    @Test
    void getDetails() {
        Signatory testSignatory = new Signatory(testDetails);
        assertEquals(expectedDetails, testSignatory.getDetails());
        }

    @Test
    void setDetails() {
        Signatory testSignatory = new Signatory(testDetails);
        HashMap<String,String> newDetails = new HashMap<>(){{put("accountNumber","5432"); put("customerID","5678");}};
        testSignatory.setDetails(newDetails);
        expectedDetails.put("accountNumber","5432");expectedDetails.put("customerID","5678");
        assertEquals(expectedDetails, testSignatory.getDetails());
    }
}