package gui;

import customer.Customer;
import org.junit.jupiter.api.Test;
import transaction.PendingTransaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CustomerApplicationTest {
    HashMap<String, String> testCustomerDetails = new HashMap<>(Map.of("customerID", "432", "firstName", "Rodney", "lastName", "Price", "dob", "12/06/1972", "address1", "1 The Street", "address2", "The Village", "addressTown", "Townsville", "addressPostcode", "AB1 2CD"));

    @Test
    void checkPendingTransactionsTest() {
        Customer customer = new mockCustomer(testCustomerDetails);
        CustomerController controller = new mockCustomerController();
        CustomerApplication x = new CustomerApplication(customer);
        x.customerController = controller;
        x.checkPendingTransactions();
        mockCustomerController y = (mockCustomerController) x.customerController;
        assertEquals("\n12345678\n875655\n987654\n\n",y.result);
    }
}

class mockCustomer extends Customer{
    mockCustomer(HashMap<String,String> inputMap){
        super(inputMap);
    }
    @Override
    public List<PendingTransaction> getPendingTransactions(){
        HashMap<String,String> testData1 = new HashMap<>(){{
            put("transactionID","123456"); put("accountNumber","12345678"); put("signatoryID","432"); put("transactionAmount","34.56"); put("transactionType","Deposit"); put("customerID","811"); put("customerName","David Brooks");
        }};
        HashMap<String,String> testData2 = new HashMap<>(){{
            put("transactionID","123457"); put("accountNumber","875655"); put("signatoryID","432"); put("transactionAmount","34.56"); put("transactionType","Deposit"); put("customerID","445"); put("customerName","David Brooks");
        }};
        HashMap<String,String> testData3 = new HashMap<>(){{
            put("transactionID","123458"); put("accountNumber","987654"); put("signatoryID","432"); put("transactionAmount","34.56"); put("transactionType","Deposit"); put("customerID","707"); put("customerName","David Brooks");
        }};

        PendingTransaction pending1 = new PendingTransaction(testData1);
        PendingTransaction pending2 = new PendingTransaction(testData2);
        PendingTransaction pending3 = new PendingTransaction(testData3);

        return new ArrayList<>(){{add(pending1);add(pending2);add(pending3);}};
    }
}

class mockCustomerController extends CustomerController{

    public String result;
    @Override
    void pendingTransactionAlert(String string) {
        this.result = string;
    }
}