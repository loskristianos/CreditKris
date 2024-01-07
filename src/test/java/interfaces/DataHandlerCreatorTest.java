package interfaces;

import account.ClientAccount;
import account.Signatory;
import customer.Customer;
import database.*;
import login.LoginObject;
import org.junit.jupiter.api.Test;
import transaction.DepositTransaction;
import transaction.PendingTransaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DataHandlerCreatorTest {

    @Test
    void createLoginDataHandler() {
        DataHandlerCreator dataHandlerCreator = new DataHandlerCreator();
        LoginObject login = new LoginObject();
        DataHandler returnedObject = dataHandlerCreator.createLoginDataHandler(login);
        LoginDataHandler expectedObject = new LoginDataHandler(login);
        assertEquals(expectedObject.getClass(),returnedObject.getClass());
    }

    @Test
    void createCustomerDataHandler() {
        DataHandlerCreator dataHandlerCreator = new DataHandlerCreator();
        Customer customer = new Customer();
        DataHandler returnedObject = dataHandlerCreator.createCustomerDataHandler(customer);
        CustomerDataHandler expectedObject = new CustomerDataHandler(customer);
        assertEquals(expectedObject.getClass(),returnedObject.getClass());
    }

    @Test
    void createAccountDataHandler() {
        DataHandlerCreator dataHandlerCreator = new DataHandlerCreator();
        ClientAccount account = new ClientAccount();
        DataHandler returnedObject = dataHandlerCreator.createAccountDataHandler(account);
        AccountDataHandler expectedObject = new AccountDataHandler(account);
        assertEquals(expectedObject.getClass(),returnedObject.getClass());
    }

    @Test
    void createTransactionDataHandler() {
        DataHandlerCreator dataHandlerCreator = new DataHandlerCreator();
        DepositTransaction transaction = new DepositTransaction();
        DataHandler returnedObject = dataHandlerCreator.createTransactionDataHandler(transaction);
        TransactionDataHandler expectedObject = new TransactionDataHandler(transaction);
        assertEquals(expectedObject.getClass(),returnedObject.getClass());
    }

    @Test
    void createAuthorisationDataHandler() {
        DataHandlerCreator dataHandlerCreator = new DataHandlerCreator();
        PendingTransaction transaction = new PendingTransaction();
        DataHandler returnedObject = dataHandlerCreator.createAuthorisationDataHandler(transaction);
        AuthorisationDataHandler expectedObject = new AuthorisationDataHandler(transaction);
        assertEquals(expectedObject.getClass(),returnedObject.getClass());
    }

    @Test
    void createAuthorisationDataHandlerFromList() {
        DataHandlerCreator dataHandlerCreator = new DataHandlerCreator();
        PendingTransaction transaction1 = new PendingTransaction();
        PendingTransaction transaction2 = new PendingTransaction();
        PendingTransaction transaction3 = new PendingTransaction();
        List<DataObject> transactionList = new ArrayList<>(){{
            add(transaction1); add(transaction2);add(transaction3);
        }};
        DataHandler returnedObject = dataHandlerCreator.createAuthorisationDataHandler(transactionList);
        AuthorisationDataHandler expectedObject = new AuthorisationDataHandler(transactionList);
        assertEquals(expectedObject.getClass(),returnedObject.getClass());    }

    @Test
    void createSignatoryDataHandler() {
        DataHandlerCreator dataHandlerCreator = new DataHandlerCreator();
        HashMap<String,String> signatoryDetails = new HashMap<>(){{
            put("accountNumber","12345");put("customerID","9911");
        }};
        Signatory signatory = new Signatory(signatoryDetails);
        DataHandler returnedObject = dataHandlerCreator.createSignatoryDataHandler(signatory);
        SignatoryDataHandler expectedObject = new SignatoryDataHandler(signatory);
        assertEquals(expectedObject.getClass(),returnedObject.getClass());
    }

    @Test
    void createSignatoryDataHandlerFromList() {
        DataHandlerCreator dataHandlerCreator = new DataHandlerCreator();
        HashMap<String,String> signatoryDetails = new HashMap<>(){{
            put("accountNumber","12345");put("customerID","9911");
        }};
        HashMap<String,String> signatoryDetails1 = new HashMap<>(){{
            put("accountNumber","123451");put("customerID","9019");
        }};
        Signatory signatory = new Signatory(signatoryDetails);
        Signatory signatory1 = new Signatory(signatoryDetails1);
        List<DataObject> signatoryList = new ArrayList<>(){{
            add(signatory);add(signatory1);
        }};
        DataHandler returnedObject = dataHandlerCreator.createSignatoryDataHandler(signatoryList);
        SignatoryDataHandler expectedObject = new SignatoryDataHandler(signatoryList);
        assertEquals(expectedObject.getClass(),returnedObject.getClass());
    }
}