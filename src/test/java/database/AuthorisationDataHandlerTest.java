package database;

import interfaces.DataObject;
import org.junit.jupiter.api.Test;
import transaction.PendingAuthorisation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AuthorisationDataHandlerTest {

    HashMap<String,String> testData = new HashMap<>() {{
       put("accountNumber","123445");put("customerID","3");put("transactionID","465");
    }};
    HashMap<String,String> testData1 = new HashMap<>() {{
        put("accountNumber","123445");put("customerID","7");put("transactionID","465");
    }};
    HashMap<String,String> testData2 = new HashMap<>() {{
        put("accountNumber","123445");put("customerID","99");put("transactionID","465");
    }};
    HashMap<String,String> testData3 = new HashMap<>() {{
        put("accountNumber","123445");put("customerID","1234");put("transactionID","465");
    }};
    @Test
    void writeOneRow() {
        var z = new PendingAuthorisation(testData);
        DataHandler x = new AuthorisationDataHandler(z);
        x.writeNewRecord();
    }

    @Test
    void writeMultipleRows() {
        ArrayList<DataObject> testList = new ArrayList<>() {{
            add(new PendingAuthorisation(testData));
            add(new PendingAuthorisation(testData1));
            add(new PendingAuthorisation(testData2));
            add(new PendingAuthorisation(testData3));
        }};
        AuthorisationDataHandler x = new AuthorisationDataHandler(testList);
        x.writeAllRecords();

    }
}