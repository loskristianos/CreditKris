package controller;

import account.Account;
import account.ClientAccount;
import account.Signatory;
import customer.Customer;
import interfaces.DataHandlerCreator;
import interfaces.DataObject;
import interfaces.DataObjectCreator;
import login.LoginObject;
import org.junit.jupiter.api.Test;
import transaction.DepositTransaction;
import transaction.Transaction;
import transaction.TransferTransaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {
    DataObjectCreator doc = new DataObjectCreator();
    DataHandlerCreator han = new DataHandlerCreator();
    HashMap<String, String> testDataHM = new HashMap<>(Map.of("transactionID","31826",  "accountNumber","909912", "transactionAmount","34.65", "transactionType","Deposit", "previousBalance","538.75", "newBalance","573.40", "transactionTime","2023/12/19 14:30:00", "authorised","Y", "additionalInfo",""));


    @Test
    void newRecordTest() {
        Transaction tx = new DepositTransaction(testDataHM);
        Controller x = new Controller(doc, han);
        x.newTransaction(tx);
    }

    @Test
    void newPendingRecords() {
        Transaction tx = new DepositTransaction(testDataHM);
        Controller y = new Controller(new DataObjectCreator(), new DataHandlerCreator());
        y.newPendingTransaction(tx);
    }

    @Test
    void testTest() {
        HashMap<String, String> testData1 = new HashMap<>(Map.of( "accountNumber","909912", "transactionAmount","34.65", "transactionType","Deposit", "previousBalance","538.75", "newBalance","573.40", "transactionTime","2023/12/19 14:30:00", "authorised","Y", "additionalInfo","445654"));
        HashMap<String, String> testData2 = new HashMap<>(Map.of( "accountNumber","445654", "transactionAmount","34.65", "transactionType","Deposit", "previousBalance","538.75", "newBalance","573.40", "transactionTime","2023/12/19 14:30:00", "authorised","Y", "additionalInfo","909912"));
        Transaction tx = new TransferTransaction(testData1);
        Transaction ty = new TransferTransaction(testData2);
        Controller z = new Controller(doc, han);
        z.newTransaction(tx);
        z.newTransaction(ty);
    }

    @Test
    void newCustomer() {
        HashMap<String,String> customerData = new HashMap<>(Map.of( "firstName","Rodney", "lastName","Price", "dob","12/09/1972", "address1","1 The Street", "address2","Amble", "addressTown","MORPETH", "addressPostcode","NE65 1BK"));

        var a = new Customer(customerData);
        var b = new LoginObject("grungodzilla1","bounty92");
        Controller k = new Controller(doc, han);
        k.createNewCustomer(b, a);
    }

    @Test
    void newAccountWithSignatories() {
        HashMap<String, String> details = new HashMap<>() {{
                put("customerID", "626262");
                put("signatories", "9");
        }};

        HashMap<String,String> cust1 = new HashMap<>(Map.of( "customerID","101010","firstName","Rodney", "lastName","Price", "dob","12/09/1972", "address1","1 The Street", "address2","Amble", "addressTown","MORPETH", "addressPostcode","NE65 1BK"));
        HashMap<String,String> cust2 = new HashMap<>(Map.of( "customerID","202020","firstName","Albert", "lastName","Price", "dob","14/09/1972", "address1","1 The Street", "address2","Amble", "addressTown","MORPETH", "addressPostcode","NE65 1BK"));
        HashMap<String,String> cust3 = new HashMap<>(Map.of( "customerID","303030","firstName","Charlie", "lastName","Price", "dob","19/09/1972", "address1","1 The Street", "address2","Amble", "addressTown","MORPETH", "addressPostcode","NE65 1BK"));

        Account zbz = new ClientAccount(details);
        List<DataObject> sigList = new ArrayList<>();
        //Customer a = new Customer(cust1); Customer b = new Customer(cust2); Customer c = new Customer(cust3);
        Signatory a = new Signatory(cust1);Signatory b = new Signatory(cust2);Signatory c = new Signatory(cust3);
        sigList.add(a); sigList.add(b); sigList.add(c);
        Controller zz = new Controller(doc, han);
        zz.createAccountWithSignatories(zbz,sigList);
    }
}