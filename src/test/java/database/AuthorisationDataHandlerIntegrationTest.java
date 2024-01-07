package database;

import account.Account;
import account.ClientAccount;
import customer.Customer;
import interfaces.DataObject;
import org.junit.jupiter.api.Test;
import transaction.DepositTransaction;
import transaction.PendingTransaction;
import transaction.Transaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class AuthorisationDataHandlerIntegrationTest {

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
        var z = new PendingTransaction(testData);
        DataHandler x = new AuthorisationDataHandler(z);
        x.writeNewRecord();
    }

    @Test
    void writeMultipleRows() {
        ArrayList<DataObject> testList = new ArrayList<>() {{
            add(new PendingTransaction(testData));
            add(new PendingTransaction(testData1));
            add(new PendingTransaction(testData2));
            add(new PendingTransaction(testData3));
        }};
        AuthorisationDataHandler x = new AuthorisationDataHandler(testList);
        x.writeAllRecords();
    }

    @Test
    void getRecords() {
        HashMap<String,String> accData = new HashMap<>(Map.of("customerID","123",  "accountNumber","123445", "currentBalance","308.50", "overdraftLimit","500", "signatories","1"));
        Account x = new ClientAccount(); x.setDetails(accData);
        HashMap<String,String> custData = new HashMap<>(Map.of("customerID","1234", "firstName","Rodney", "lastName","Price", "dob","12/09/1972", "address1","1 The Street", "address2","Amble", "addressTown","MORPETH", "addressPostcode","NE65 1BK"));
        Customer y = new Customer(custData);
        Transaction z = new DepositTransaction(testData);
        DataHandler a = new AuthorisationDataHandler(x);
        List<DataObject> ax = a.getRecords();
        assertEquals(4, ax.size());

        DataHandler b = new AuthorisationDataHandler(y);
        List<DataObject> by = b.getRecords();
        assertEquals(1, by.size());

        DataHandler c = new AuthorisationDataHandler(z);
        List<DataObject> cz = c.getRecords();
        assertEquals(4, cz.size());
    }
}