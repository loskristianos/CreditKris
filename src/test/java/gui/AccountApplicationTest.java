package gui;

import account.Account;
import account.ClientAccount;
import customer.Customer;
import org.junit.jupiter.api.Test;
import transaction.PendingTransaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class AccountApplicationTest {

    HashMap<String, String> testAccountDetails = new HashMap<>(Map.of("customerID", "123", "accountNumber", "12345678", "currentBalance", "308.50", "signatories", "0"));
    HashMap<String, String> testCustomerDetails = new HashMap<>(Map.of("customerID", "432", "firstName", "Rodney", "lastName", "Price", "dob", "12/06/1972", "address1", "1 The Street", "address2", "The Village", "addressTown", "Townsville", "addressPostcode", "AB1 2CD"));



    @Test
    void pendingTransactionsForCustomerAndAccount() {
        Account account = new ClientAccount(testAccountDetails);
        Customer customer = new TestCustomer(testCustomerDetails);
        AccountApplication x = new AccountApplication(account,customer);
        List<PendingTransaction> returns = x.pendingTransactionsForCustomerAndAccount();
        assertEquals(1,returns.size());
    }
}

class TestCustomer extends Customer{

    TestCustomer(HashMap<String,String> inputmap){
        super(inputmap);
    }

    public List<PendingTransaction> getPendingTransactions(){
        HashMap<String,String> testData1 = new HashMap<>(){{
            put("transactionID","123456"); put("accountNumber","12345678"); put("signatoryID","123"); put("transactionAmount","34.56"); put("transactionType","Deposit"); put("customerID","811"); put("customerName","David Brooks");
        }};
        HashMap<String,String> testData2 = new HashMap<>(){{
            put("transactionID","123457"); put("accountNumber","875655"); put("signatoryID","123"); put("transactionAmount","34.56"); put("transactionType","Deposit"); put("customerID","445"); put("customerName","David Brooks");
        }};
        HashMap<String,String> testData3 = new HashMap<>(){{
            put("transactionID","123458"); put("accountNumber","987654"); put("signatoryID","123"); put("transactionAmount","34.56"); put("transactionType","Deposit"); put("customerID","707"); put("customerName","David Brooks");
        }};

        PendingTransaction pending1 = new PendingTransaction(testData1);
        PendingTransaction pending2 = new PendingTransaction(testData2);
        PendingTransaction pending3 = new PendingTransaction(testData3);

        List<PendingTransaction> pendingList = new ArrayList<>(){{add(pending1);add(pending2);add(pending3);}};
        return pendingList;
    }
}